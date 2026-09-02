package implementazionePostgresDAO;

import dao.PrenotazioneDAO;
import database.ConnessioneDatabase;
import model.Prenotazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrenotazionePostgresDAO implements PrenotazioneDAO {

    private Connection connection;

    public PrenotazionePostgresDAO() {
        try {
            this.connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean inserisciPrenotazione(Prenotazione prenotazione, String emailUtente, int idBox) throws SQLException {
        // Query che recupera l'id_utente partendo dall'email e inserisce la prenotazione
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

    @Override
    public ArrayList<Prenotazione> getPrenotazioniPerUtente(String emailUtente) throws SQLException {
        ArrayList<Prenotazione> lista = new ArrayList<>();
        String sql = "SELECT p.codice_ritiro, p.stato FROM prenotazione p " +
                "JOIN utente u ON p.id_utente = u.id_utente WHERE u.email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, emailUtente);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String codice = rs.getString("codice_ritiro");
                    String stato = rs.getString("stato");
                    Prenotazione p = new Prenotazione(codice);
                    p.setStato(stato);
                    lista.add(p);
                }
            }
        }
        return lista;
    }

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
