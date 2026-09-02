package dao;

import model.Prenotazione;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PrenotazioneDAO {
    boolean inserisciPrenotazione(Prenotazione prenotazione, String emailUtente, int idBox) throws SQLException;
    ArrayList<Prenotazione> getPrenotazioniPerUtente(String emailUtente) throws SQLException;
    boolean annullaPrenotazione(int idBox, String emailUtente) throws SQLException;
}