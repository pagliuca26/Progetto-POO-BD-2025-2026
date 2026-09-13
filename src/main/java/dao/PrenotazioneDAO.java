package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Prenotazione;

/**
 * Interfaccia DAO (Data Access Object) per la gestione delle prenotazioni.
 * Definisce il contratto delle operazioni per la creazione, l'annullamento
 * e la consultazione dello storico degli ordini effettuati dagli utenti sul database.
 */
public interface PrenotazioneDAO {

    /**
     * Dichiara l'operazione di salvataggio di una nuova prenotazione nel database relazionale.
     * Associa la prenotazione creata all'email dell'utente acquirente e all'identificativo della box selezionata.
     *
     * @param prenotazione l'oggetto Prenotazione contenente i dettagli dell'ordine (come il codice univoco e lo stato)
     * @param emailUtente l'indirizzo email dell'utente che effettua l'acquisto
     * @param idBox l'identificativo numerico della box prenotata
     * @return true se l'inserimento sul database ha avuto successo, false altrimenti
     * @throws SQLException se si verifica un errore durante l'esecuzione dell'INSERT SQL o di connessione
     */
//inserisce una nuova prenotazione per un utente e un box specifico
    boolean inserisciPrenotazione(Prenotazione prenotazione, String emailUtente, int idBox) throws SQLException;

    /**
     * Dichiara l'operazione di recupero dello storico di tutte le prenotazioni collegate a un utente.
     *
     * @param emailUtente l'indirizzo email dell'utente di cui estrarre gli ordini
     * @return un ArrayList contenente gli oggetti Prenotazione effettuati dall'utente specificato
     * @throws SQLException se si verifica un errore durante l'interrogazione SQL o di connessione al database
     */
//recupera tutte le prenotazioni collegate all email dell utente
    ArrayList<Prenotazione> getPrenotazioniPerUtente(String emailUtente) throws SQLException;

    /**
     * Dichiara l'operazione di annullamento di una prenotazione registrata sul database.
     * Rimuove il vincolo o aggiorna lo stato dell'ordine incrociando l'identificativo della box e l'email dell'utente.
     *
     * @param idBox il codice identificativo della box associata alla prenotazione da annullare
     * @param emailUtente l'indirizzo email del cliente proprietario della prenotazione
     * @return true se l'annullamento è avvenuto con successo, false altrimenti
     * @throws SQLException se si verifica un errore durante l'esecuzione dell'istruzione SQL o di connessione
     */
//annulla la prenotazione tramite id del box e email utente
    boolean annullaPrenotazione(int idBox, String emailUtente) throws SQLException;
}