package controller;

import dao.BoxDAO;
import dao.PrenotazioneDAO;
import dao.UtenteDAO;
import gui.ExceptionCognome;
import gui.ExceptionEmail;
import gui.ExceptionEmailUguale;
import gui.ExceptionNome;
import gui.ExceptionPassword;
import implementazionePostgresDAO.BoxPostgresDAO;
import implementazionePostgresDAO.PrenotazionePostgresDAO;
import implementazionePostgresDAO.UtentePostgresDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Prenotazione;
import model.Utente;

public class Controller {

    //attributi per la gestione della sessione e dei dati in memoria
    private ArrayList<Utente> listaUtenti;
    private Utente utenteAttuale = null;
    private String avatarSelezionato = "iconaP-removebg";

    //riferimenti alle interfacce dao
    private UtenteDAO utenteDAO;
    private PrenotazioneDAO prenotazioneDAO;
    private BoxDAO boxDAO;

    //costruttore del controller che inizializza le strutture dati e i dao
    public Controller() {
        this.listaUtenti = new ArrayList<>();
        this.utenteDAO = new UtentePostgresDAO();
        this.prenotazioneDAO = new PrenotazionePostgresDAO();
        this.boxDAO = new BoxPostgresDAO();
    }

    //aggiunge un utente alla lista in memoria
    public void aggiungiUtente(Utente utente) {
        listaUtenti.add(utente);
    }

    //verifica le credenziali dell utente ed effettua il login
    public boolean checkUtente(String campoEmail, String campoPassword) {
        if (campoEmail.isBlank()) {
            throw new ExceptionEmail("Il campo email è vuoto.");
        }
        if (campoPassword.isBlank()) {
            throw new ExceptionEmail("Il campo password è vuoto.");
        }

        try {
            Utente utenteTrovato = utenteDAO.login(campoEmail, campoPassword);
            if (utenteTrovato != null) {
                utenteTrovato.setAccessoEffettuato(true);
                this.utenteAttuale = utenteTrovato;
                return true;
            }
        } catch (SQLException e) {
            System.out.println("errore verifica utente: " + e.getMessage());
        }

        throw new ExceptionEmail("Utente non trovato.");
    }

    //registra un nuovo utente verificando la compilazione dei campi
    public void creaUtente(String email, String password, String nome, String cognome) throws RuntimeException {
        if (email.isBlank()) {
            throw new ExceptionEmail("Il campo email è vuoto.");
        }
        if (password.isBlank()) {
            throw new ExceptionPassword("Il campo password è vuoto.");
        }
        if (nome.isBlank()) {
            throw new ExceptionNome("Il campo nome è vuoto.");
        }
        if (cognome.isBlank()) {
            throw new ExceptionCognome("Il campo cognome è vuoto.");
        }

        Utente nuovo = new Utente(nome, cognome, email, password);
        try {
            boolean inserito = utenteDAO.registraUtente(nuovo);
            if (inserito) {
                listaUtenti.add(nuovo);
            }
        } catch (SQLException e) {
            throw new ExceptionEmailUguale("Email già esistente o errore nel database.");
        }
    }

    //restituisce l utente attualmente autenticato
    public Utente getUtenteAttuale() {
        return utenteAttuale;
    }

    //effettua il logout dell utente corrente
    public void esciUtente() {
        if (this.utenteAttuale != null) {
            this.utenteAttuale.setAccessoEffettuato(false);
            this.utenteAttuale = null;
        }
    }

    //restituisce la lista degli utenti
    public ArrayList<Utente> getListaUtenti() {
        return listaUtenti;
    }

    //imposta l avatar scelto dall utente
    public void setAvatarSelezionato(String avatar) {
        this.avatarSelezionato = avatar;
    }

    //restituisce il percorso dell avatar scelto
    public String getAvatarSelezionato() {
        return this.avatarSelezionato;
    }

    //restituisce la stringa di benvenuto in base all avatar selezionato
    public String getSaluto() {
        if (avatarSelezionato != null && avatarSelezionato.equals("img/woman-avatar.png")) {
            return "Benvenuta ";
        }
        return "Benvenuto ";
    }

    //aggiorna le credenziali dell utente sia in memoria che su postgresql
    public boolean aggiornaDatiUtente(String nuovoNome, String nuovoCognome, String nuovaEmail, String nuovaPassword) {
        if (utenteAttuale != null) {
            String vecchiaEmail = utenteAttuale.getEmail();

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

            try {
                return utenteDAO.modificaUtente(utenteAttuale, vecchiaEmail);
            } catch (SQLException e) {
                System.out.println("errore aggiornamento dati utente: " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    //annulla la prenotazione sul database
    public boolean annullaPrenotazioneDB(int idBox) {
        if (utenteAttuale == null) {
            return false;
        }
        try {
            return prenotazioneDAO.annullaPrenotazione(idBox, utenteAttuale.getEmail());
        } catch (SQLException e) {
            System.out.println("errore annullamento prenotazione: " + e.getMessage());
            return false;
        }
    }

    //procede all acquisto della box creando la relativa prenotazione
    public String acquistaBoxDB(int idBox) {
        if (utenteAttuale == null) {
            return null;
        }

        try {
            String codiceUnivoco = "BOX-" + System.currentTimeMillis();
            Prenotazione nuovaPrenotazione = new Prenotazione(codiceUnivoco);
            nuovaPrenotazione.setStato("ATTIVA");

            boolean inserito = prenotazioneDAO.inserisciPrenotazione(nuovaPrenotazione, utenteAttuale.getEmail(), idBox);
            if (inserito) {
                return codiceUnivoco;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("errore acquisto box: " + e.getMessage());
            return null;
        }
    }

    //cancella l account dell utente autenticato
    public boolean eliminaAccount() {
        if (utenteAttuale == null) {
            return false;
        }
        try {
            boolean eliminato = utenteDAO.eliminaUtente(utenteAttuale.getEmail());
            if (eliminato) {
                this.utenteAttuale = null;
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("errore eliminazione account: " + e.getMessage());
            return false;
        }
    }

    //restituisce le prenotazioni attive dell utente autenticato
    public ArrayList<Prenotazione> getPrenotazioniAttiveUtente() {
        if (utenteAttuale == null) {
            return new ArrayList<>();
        }
        try {
            return prenotazioneDAO.getPrenotazioniPerUtente(utenteAttuale.getEmail());
        } catch (SQLException e) {
            System.out.println("errore recupero prenotazioni: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    //restituisce il numero di box disponibili dal database
    public int getDisponibilitaBoxDB(int idBox) {
        try {
            return boxDAO.getDisponibilitaBox(idBox);
        } catch (SQLException e) {
            System.out.println("errore disponibilita box: " + e.getMessage());
            return 0;
        }
    }
}