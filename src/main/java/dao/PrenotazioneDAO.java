package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Prenotazione;

public interface PrenotazioneDAO {

    //inserisce una nuova prenotazione per un utente e un box specifico
    boolean inserisciPrenotazione(Prenotazione prenotazione, String emailUtente, int idBox) throws SQLException;

    //recupera tutte le prenotazioni collegate all email dell utente
    ArrayList<Prenotazione> getPrenotazioniPerUtente(String emailUtente) throws SQLException;

    //annulla la prenotazione tramite id del box e email utente
    boolean annullaPrenotazione(int idBox, String emailUtente) throws SQLException;
}