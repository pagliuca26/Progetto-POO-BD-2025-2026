package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Box;

/**
 * Interfaccia DAO (Data Access Object) per la gestione delle box alimentari.
 * Definisce il contratto delle operazioni per il recupero, la consultazione del catalogo
 * e la verifica delle disponibilità delle box sul database, astraendo i dettagli di persistenza.
 */
public interface BoxDAO {

    /**
     * Dichiara l'operazione di recupero di tutte le box presenti nel database che hanno ancora disponibilità.
     *
     * @return un ArrayList contenente gli oggetti Box acquistabili dagli utenti
     * @throws SQLException se si verifica un errore durante l'interrogazione SQL o di connessione al database
     */
//recupera tutte le box con disponibilita nel database
    ArrayList<Box> getBoxDisponibili() throws SQLException;

    /**
     * Dichiara l'operazione di aggiornamento della quantità residua disponibile per una box.
     *
     * @param idBox il codice identificativo della box da aggiornare
     * @param nuovaQuantita il nuovo numero di porzioni disponibili da salvare sul database
     * @return true se l'aggiornamento ha avuto successo, false altrimenti
     * @throws SQLException se si verifica un errore durante l'esecuzione dell'UPDATE SQL o di connessione
     */
//aggiorna manualmente la quantita disponibile di una box
    boolean aggiornaDisponibilita(int idBox, int nuovaQuantita) throws SQLException;

    /**
     * Dichiara l'operazione di lettura della quantità residua disponibile per una specifica box.
     *
     * @param idBox il codice identificativo della box da verificare
     * @return il numero intero di porzioni o pezzi attualmente disponibili nel database
     * @throws SQLException se si verifica un errore durante l'interrogazione SQL o di connessione
     */
//restituisce la quantita residua di una determinata box
    int getDisponibilitaBox(int idBox) throws SQLException;
}