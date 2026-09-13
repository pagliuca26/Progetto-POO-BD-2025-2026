package dao;

import java.sql.SQLException;
import model.Utente;

/**
 * Interfaccia DAO (Data Access Object) per la gestione degli utenti.
 * Definisce il contratto dei metodi per l'accesso e la manipolazione
 * dei dati relativi all'entità Utente, rendendo l'applicazione indipendente
 * dallo specifico database utilizzato.
 */
public interface UtenteDAO {

    /**
     * Dichiara l'operazione di registrazione di un nuovo utente nel database.
     *
     * @param utente l'oggetto Utente contenente i dati anagrafici e le credenziali da salvare
     * @return true se l'inserimento nel database va a buon fine, false altrimenti
     * @throws SQLException se si verifica un errore durante l'esecuzione della query SQL o di connessione
     */
//registra un nuovo utente nel database
    boolean registraUtente(Utente utente) throws SQLException;

    /**
     * Dichiara l'operazione di autenticazione dell'utente tramite email e password.
     *
     * @param email l'indirizzo email inserito per l'accesso
     * @param password la password associata all'account
     * @return l'oggetto Utente recuperato dal database se le credenziali sono corrette, altrimenti null
     * @throws SQLException se si verifica un errore durante l'interrogazione SQL o di connessione
     */
//effettua il login verificando le credenziali
    Utente login(String email, String password) throws SQLException;

    /**
     * Dichiara l'operazione di aggiornamento dei dati anagrafici o delle credenziali di un utente nel database.
     *
     * @param utente l'oggetto Utente contenente i nuovi dati da salvare
     * @param vecchiaEmail l'indirizzo email originario dell'utente, utilizzato come chiave per individuare il record da modificare
     * @return true se l'aggiornamento sul database è riuscito con successo, false altrimenti
     * @throws SQLException se si verifica un errore durante l'esecuzione dell'istruzione SQL di UPDATE o di connessione
     */
//aggiorna i dati del profilo utente
    boolean modificaUtente(Utente utente, String vecchiaEmail) throws SQLException;

    /**
     * Dichiara l'operazione di cancellazione definitiva di un utente dal database.
     *
     * @param email l'indirizzo email dell'account da rimuovere, utilizzato come identificativo univoco
     * @return true se l'eliminazione del record è andata a buon fine, false altrimenti
     * @throws SQLException se si verifica un errore durante l'esecuzione dell'istruzione SQL di DELETE o di connessione
     */
//elimina l account dell utente
    boolean eliminaUtente(String email) throws SQLException;
}