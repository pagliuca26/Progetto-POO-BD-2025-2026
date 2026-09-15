package implementazionePostgresDAO;

import dao.BoxDAO;
import database.ConnessioneDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementazione PostgreSQL per BoxDAO.
 * Gestisce l'estrazione e il controllo delle scorte delle box dal database.
 */
public class BoxPostgresDAO implements BoxDAO {

    //connessione al database
    private Connection connection;

    /**
     * Costruttore della classe.
     * Recupera la connessione attiva con il database PostgreSQL tramite la classe ConnessioneDatabase.
     */
//costruttore che recupera la connessione attiva
    public BoxPostgresDAO() {
        try {
            this.connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("errore connessione boxdao: " + e.getMessage());
        }
    }

    //recupera tutte le box con quantita maggiore di zero
    @Override
    public int getQtaBoxDisponibili(int id) throws SQLException {
        int quantita = 0;
        String sql = "SELECT quantita_disponibile FROM box WHERE id_box = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                quantita = rs.getInt("quantita_disponibile");
            }
        }
        return quantita;
    }

    //aggiorna la disponibilita di una specifica box
    @Override
    public boolean aggiornaDisponibilita(int idBox, int nuovaQuantita) throws SQLException {
        String sql = "UPDATE box SET quantita_disponibile = ? WHERE id_box = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, nuovaQuantita);
            statement.setInt(2, idBox);
            return statement.executeUpdate() > 0;
        }

    }

    //restituisce la quantita disponibile di una determinata box
    @Override
    public int getDisponibilitaBox(int idBox) throws SQLException {
        String sql = "SELECT quantita_disponibile FROM box WHERE id_box = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idBox);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("quantita_disponibile");
                }
            }
        }
        return 0;
    }
}