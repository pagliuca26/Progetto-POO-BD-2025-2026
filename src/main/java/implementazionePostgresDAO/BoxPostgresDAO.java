package implementazionePostgresDAO;

import dao.BoxDAO;
import database.ConnessioneDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Box;

public class BoxPostgresDAO implements BoxDAO {

    //connessione al database
    private Connection connection;

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
    public ArrayList<Box> getBoxDisponibili() throws SQLException {
        ArrayList<Box> lista = new ArrayList<>();
        String sql = "SELECT prezzo_originale, prezzo_scontato, quantita_disponibile, grandezza_box FROM box WHERE quantita_disponibile > 0";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                double prezzoOrig = rs.getDouble("prezzo_originale");
                double prezzoScont = rs.getDouble("prezzo_scontato");
                int quantita = rs.getInt("quantita_disponibile");
                String grandezza = rs.getString("grandezza_box");

                lista.add(new Box(prezzoOrig, prezzoScont, quantita, grandezza));
            }
        }
        return lista;
    }

    //aggiorna la disponibilita di una specifica box
    @Override
    public boolean aggiornaDisponibilita(int idBox, int nuovaQuantita) throws SQLException {
        String sql = "UPDATE box SET quantita_disponibile = ? WHERE id_box = ?";
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