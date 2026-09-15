package controller;

import dao.BoxDAO;
import dao.PrenotazioneDAO;
import dao.UtenteDAO;
import exceptions.ExceptionCognome;
import exceptions.ExceptionEmail;
import exceptions.ExceptionEmailUguale;
import exceptions.ExceptionNome;
import exceptions.ExceptionPassword;
import implementazionePostgresDAO.BoxPostgresDAO;
import implementazionePostgresDAO.PrenotazionePostgresDAO;
import implementazionePostgresDAO.UtentePostgresDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Prenotazione;
import model.Utente;

/**
 * Questa classe fa da ponte tra la grafica e il database.
 * Serve per gestire le azioni dell'utente quando usa l'applicazione,
 * come fare il login, registrarsi, comprare una box o annullare una prenotazione,
 * coordinando il passaggio dei dati senza far comunicare direttamente la grafica con Postgres.
 */
public class Controller {

    //attributi per la gestione della sessione e dei dati in memoria
    private ArrayList<Utente> listaUtenti;
    private Utente utenteAttuale = null;
    private String avatarSelezionato = "iconaP-removebg";

    //riferimenti alle interfacce dao
    private UtenteDAO utenteDAO;
    private PrenotazioneDAO prenotazioneDAO;
    private BoxDAO boxDAO;

    /**
     * Costruttore della classe Controller.
     * Crea una lista vuota in memoria per tenere traccia degli utenti
     * e collega l'applicazione alle classi DAO per dialogare con PostgreSQL.
     */
//costruttore del controller che inizializza le strutture dati e i dao
    public Controller() {
        this.listaUtenti = new ArrayList<>();
        this.utenteDAO = new UtentePostgresDAO();
        this.prenotazioneDAO = new PrenotazionePostgresDAO();
        this.boxDAO = new BoxPostgresDAO();
    }

    /**
     * Inserisce un nuovo oggetto utente all'interno della lista temporanea tenuta in memoria.
     *
     * @param utente l'utente da salvare nella lista locale dell'applicazione
     */
//aggiunge un utente alla lista in memoria
    public void aggiungiUtente(Utente utente) {
        listaUtenti.add(utente);
    }

    /**
     * Controlla se le credenziali inserite dall'utente sono corrette ed esegue il login.
     * Verifica che i campi non siano lasciati vuoti e chiede al database se esiste
     * una corrispondenza tra email e password. Se esiste, salva l'utente nella sessione.
     *
     * @param campoEmail l'email scritta dall'utente nella schermata di login
     * @param campoPassword la password scritta dall'utente nella schermata di login
     * @return true se l'accesso va a buon fine
     * @throws ExceptionEmail se uno dei due campi è vuoto oppure se non esiste nessun account con questi dati
     */
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

    /**
     * Registra un nuovo utente nel sistema dopo aver controllato che tutti i campi siano compilati.
     * Crea l'oggetto Utente, lo salva in modo permanente nel database PostgreSQL tramite il DAO
     * e lo aggiunge alla lista in memoria.
     *
     * @param email l'indirizzo email inserito nel form di registrazione
     * @param password la password scelta per l'account
     * @param nome il nome anagrafico dell'utente
     * @param cognome il cognome anagrafico dell'utente
     * @throws ExceptionEmail se il campo email è lasciato vuoto
     * @throws ExceptionPassword se il campo password è lasciato vuoto
     * @throws ExceptionNome se il campo nome è lasciato vuoto
     * @throws ExceptionCognome se il campo cognome è lasciato vuoto
     * @throws ExceptionEmailUguale se l'email è già usata da un altro utente sul database o se c'è un errore SQL
     */
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

    /**
     * Restituisce l'utente attualmente connesso all'applicazione.
     * Serve alle varie schermate della grafica per sapere chi sta usando il sistema.
     *
     * @return l'oggetto Utente loggato, oppure null se non è stato fatto l'accesso
     */
//restituisce l utente attualmente autenticato
    public Utente getUtenteAttuale() {
        return utenteAttuale;
    }

    /**
     * Effettua il logout dell'utente che sta usando l'applicazione.
     * Imposta lo stato di accesso su false e rimuove l'utente dalla sessione attiva.
     */
//effettua il logout dell utente corrente
    public void esciUtente() {
        if (this.utenteAttuale != null) {
            this.utenteAttuale.setAccessoEffettuato(false);
            this.utenteAttuale = null;
        }
    }

    /**
     * Restituisce l'elenco di tutti gli utenti salvati temporaneamente nella memoria dell'applicazione.
     *
     * @return la lista contenente gli oggetti Utente registrati durante la sessione
     */
//restituisce la lista degli utenti
    public ArrayList<Utente> getListaUtenti() {
        return listaUtenti;
    }

    /**
     * Salva l'immagine di profilo o avatar scelta dall'utente.
     * Serve per personalizzare l'aspetto grafico dell'interfaccia durante la sessione.
     *
     * @param avatar il nome o percorso del file immagine selezionato
     */
//imposta l'avatar scelto dall utente
    public void setAvatarSelezionato(String avatar) {
        this.avatarSelezionato = avatar;
    }

    /**
     * Restituisce il percorso o il nome dell'avatar attualmente impostato per l'utente.
     * Viene richiamato dai form della grafica per caricare l'immagine corretta del profilo.
     *
     * @return la stringa che rappresenta l'avatar selezionato
     */
//restituisce il percorso dell avatar scelto
    public String getAvatarSelezionato() {
        return this.avatarSelezionato;
    }

    /**
     * Genera il messaggio di benvenuto adatto all'utente in base all'avatar scelto.
     * Se l'utente ha selezionato l'avatar femminile restituisce "Benvenuta ",
     * altrimenti restituisce la forma predefinita "Benvenuto ".
     *
     * @return la stringa di saluto personalizzata da mostrare a video
     */
//restituisce la stringa di benvenuto in base all avatar selezionato
    public String getSaluto() {
        if (avatarSelezionato != null && avatarSelezionato.equals("img/woman-avatar.png")) {
            return "Benvenuta ";
        }
        return "Benvenuto ";
    }

    /**
     * Modifica le informazioni dell'account dell'utente connesso sia in memoria che sul database PostgreSQL.
     * Aggiorna solo i campi che non sono stati lasciati vuoti e salva le modifiche tramite il DAO.
     *
     * @param nuovoNome il nuovo nome inserito, oppure vuoto se non deve essere modificato
     * @param nuovoCognome il nuovo cognome inserito, oppure vuoto se non deve essere modificato
     * @param nuovaEmail il nuovo indirizzo email inserito, oppure vuoto se non deve essere modificato
     * @param nuovaPassword la nuova password inserita, oppure vuota se non deve essere modificata
     * @return true se l'aggiornamento sul database è riuscito con successo, false altrimenti
     */
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

    /**
     * Annulla una prenotazione precedentemente effettuata dall'utente sul database.
     * Elimina il legame tra l'utente loggato e la box indicata richiamando il DAO.
     *
     * @param idBox il codice identificativo della box di cui annullare la prenotazione
     * @return true se l'annullamento è avvenuto con successo, false altrimenti
     */
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

    public int getQtaBoxDisponibili(int id) throws SQLException{
        return boxDAO.getQtaBoxDisponibili(id);
    }

    /**
     * Gestisce la procedura di acquisto di una box creando una nuova prenotazione.
     * Genera un codice identificativo univoco basato sul timestamp, crea l'oggetto
     * prenotazione impostandolo come "ATTIVA" e lo salva sul database tramite il DAO.
     *
     * @param idBox l'identificativo numerico della box che l'utente intende acquistare
     * @return il codice alfanumerico della prenotazione se l'acquisto riesce, altrimenti null
     */
//procede all acquisto della box creando la relativa prenotazione
    public String acquistaBoxDB(int idBox, int quantita) {
        if (utenteAttuale == null) {
            return null;
        }

        try {
            String codiceUnivoco = "BOX-" + System.currentTimeMillis();
            Prenotazione nuovaPrenotazione = new Prenotazione(codiceUnivoco);
            boxDAO.aggiornaDisponibilita(idBox, quantita);
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

    /**
     * Cancella definitivamente l'account dell'utente connesso dal database PostgreSQL.
     * Se la cancellazione va a buon fine, azzera la sessione attiva effettuando anche la disconnessione.
     *
     * @return true se l'account è stato eliminato con successo, false altrimenti
     */
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

    /**
     * Recupera dal database l'elenco di tutte le prenotazioni attive associate all'utente connesso.
     * Serve alla schermata delle prenotazioni o del profilo per visualizzare gli ordini in corso.
     *
     * @return una lista di oggetti Prenotazione appartenenti all'utente, oppure una lista vuota in caso di errore o se non ci sono ordini
     */
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

    /**
     * Interroga il database per sapere quante porzioni o pezzi sono ancora disponibili per una determinata box.
     * Serve all'interfaccia grafica per aggiornare il contatore a video e impedire l'acquisto se le scorte sono esaurite.
     *
     * @param idBox il codice identificativo della box da controllare
     * @return la quantità numerica rimasta a disposizione, oppure 0 in caso di errore o esaurimento scorte
     */
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