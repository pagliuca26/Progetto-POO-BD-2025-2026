package implementazionePostgresDAO;

import dao.PrenotazioneDAO;
import database.ConnessioneDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Prenotazione;

public class PrenotazionePostgresDAO implements PrenotazioneDAO {

    //connessione al database
    private Connection connection;

    //costruttore che recupera l istanza attiva del database
    public PrenotazionePostgresDAO() {
        try {
            this.connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("errore connessione prenotazionedao: " + e.getMessage());
        }
    }

    //inserisce una nuova prenotazione associata a utente e box
    @Override
    public boolean inserisciPrenotazione(Prenotazione prenotazione, String emailUtente, int idBox) throws SQLException {
        String sql = "INSERT INTO prenotazione (codice_ritiro, id_utente, id_box, stato) " +
                "VALUES (?, (SELECT id_utente FROM utente WHERE email = ?), ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, prenotazione.getCodiceRitiro());
            statement.setString(2, emailUtente);
            statement.setInt(3, idBox);
            statement.setString(4, prenotazione.getStato() != null ? prenotazione.getStato() : "ATTIVA");
            return statement.executeUpdate() > 0;
        }
    }

    //recupera tutte le prenotazioni attive collegate a un utente
    @Override
    public ArrayList<Prenotazione> getPrenotazioniPerUtente(String emailUtente) throws SQLException {
        ArrayList<Prenotazione> lista = new ArrayList<>();
        String sql = "SELECT p.id_box, p.codice_ritiro, p.stato FROM prenotazione p " +
                "JOIN utente u ON p.id_utente = u.id_utente " +
                "WHERE u.email = ? AND p.stato = 'ATTIVA'";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, emailUtente);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int idBox = rs.getInt("id_box");
                    String codice = rs.getString("codice_ritiro");
                    String stato = rs.getString("stato");

                    Prenotazione p = new Prenotazione(idBox, codice, stato);
                    lista.add(p);
                }
            }
        }
        return lista;
    }

    //annulla la prenotazione attiva aggiornando il suo stato
    @Override
    public boolean annullaPrenotazione(int idBox, String emailUtente) throws SQLException {
        String sql = "UPDATE prenotazione SET stato = 'ANNULLATA' WHERE id_prenotazione = (" +
                "SELECT id_prenotazione FROM prenotazione p " +
                "JOIN utente u ON p.id_utente = u.id_utente " +
                "WHERE p.id_box = ? AND u.email = ? AND p.stato = 'ATTIVA' " +
                "LIMIT 1)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idBox);
            statement.setString(2, emailUtente);
            return statement.executeUpdate() > 0;
        }
    }
}