package controller;

import gui.*;
import model.Utente;

import java.util.ArrayList;

public class Controller {
    private ArrayList<Utente> listaUtenti;
    //variabile per memorizzare l'utente loggato
    private Utente utenteAttuale = null;
    //variabile per memorizzare l'avatar
    private String avatarSelezionato = "iconaP-removebg";
    private dao.UtenteDAO utenteDAO = new implementazionePostgresDAO.UtentePostgresDAO();
    private dao.PrenotazioneDAO prenotazioneDAO = new implementazionePostgresDAO.PrenotazionePostgresDAO();

    public Controller() {
        listaUtenti = new ArrayList<>();
    }

    public void aggiungiUtente(Utente utente) {
        listaUtenti.add(utente);
    }

    public boolean checkUtente(String campoEmail, String campoPassword) {
        if (campoEmail.isBlank()) throw new ExceptionEmail("Il campo email è vuoto.");
        if (campoPassword.isBlank()) throw new ExceptionEmail("Il campo password è vuoto.");

        try {
            Utente utenteTrovato = utenteDAO.login(campoEmail, campoPassword);
            if (utenteTrovato != null) {
                utenteTrovato.setAccessoEffettuato(true);
                this.utenteAttuale = utenteTrovato;
                return true;
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        throw new ExceptionEmail("Utente non trovato.");
    }

    //eccezioni per crea account
    public void creaUtente(String email, String password, String nome, String cognome) throws RuntimeException {
        if (email.isBlank()) throw new ExceptionEmail("Il campo email è vuoto.");
        if (password.isBlank()) throw new ExceptionPassword("Il campo password è vuoto.");
        if (nome.isBlank()) throw new ExceptionNome("Il campo nome è vuoto.");
        if (cognome.isBlank()) throw new ExceptionCognome("Il campo cognome è vuoto.");

        Utente nuovo = new Utente(email, password, nome, cognome);
        try {
            boolean inserito = utenteDAO.registraUtente(nuovo);
            if (inserito) {
                listaUtenti.add(nuovo);
            }
        } catch (java.sql.SQLException e) {
            throw new ExceptionEmailUguale("Email già esistente o errore nel database.");
        }
    }

    public Utente getUtenteAttuale() {
        return utenteAttuale;
    }

    public void esciUtente() {
        this.utenteAttuale.setAccessoEffettuato(false);
        this.utenteAttuale = null;
    }

    public ArrayList<Utente> getListaUtenti() {
        return listaUtenti;
    }

    //metodo per impostare l'avatar scelto
    public void setAvatarSelezionato(String avatar) {
        this.avatarSelezionato = avatar;
    }

    //metodo per recuperare l'avatar scelto
    public String getAvatarSelezionato() {
        return this.avatarSelezionato;
    }

    //metodo per restituire il saluto in base all'avatar
    public String getSaluto() {
        if (avatarSelezionato != null && avatarSelezionato.equals("woman-avatar.png")) {
            return "Benvenuta ";
        }
        return "Benvenuto ";
    }

    //metodo per aggiornare i dati dell'utente attuale
    public void aggiornaDatiUtente(String nuovoNome, String nuovoCognome, String nuovaEmail, String nuovaPassword) {
        if (utenteAttuale != null) {
            if (!nuovoNome.isBlank()) {
                utenteAttuale.setNome(nuovoNome);
            }
            if (!nuovoCognome.isBlank()) {
                utenteAttuale.setCognome(nuovoCognome);
            }
            if (!nuovaEmail.isBlank()) {
                utenteAttuale.setEmail(nuovaEmail);
            }
            if (!nuovaPassword.isBlank()) {
                utenteAttuale.setPassword(nuovaPassword);
            }
        }
    }

    //metodo chiamato dalla GUI per annullare una prenotazione memorizzata nel database
    public boolean annullaPrenotazioneDB(int idBox) {
        if (utenteAttuale == null) return false;
        try {
            return prenotazioneDAO.annullaPrenotazione(idBox, utenteAttuale.getEmail());
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //metodo chiamato quando l'utente acquista una box da una schermata del punto vendita
    public String acquistaBoxDB(int idBox) {
        //se non c'è nessun utente loggato, l'acquisto non può procedere
        if (utenteAttuale == null) {
            return null;
        }

        try {
            //genera un codice casuale univoco per il ritiro della box
            String codiceUnivoco = "BOX-" + System.currentTimeMillis();

            //crea l'oggetto Prenotazione impostando il codice appena generato
            model.Prenotazione nuovaPrenotazione = new model.Prenotazione(codiceUnivoco);
            nuovaPrenotazione.setStato("ATTIVA");

            //inserisce la prenotazione nel DB Postgres (attiverà il trigger SQL per scalare la quantità)
            boolean inserito = prenotazioneDAO.inserisciPrenotazione(nuovaPrenotazione, utenteAttuale.getEmail(), idBox);
            if (inserito) {
                return codiceUnivoco;
            }
            return null;
        } catch (java.sql.SQLException e) {
            //stampa l'errore SQL in console nel caso di problemi con il DB
            e.printStackTrace();
            return null;
        }
    }

}