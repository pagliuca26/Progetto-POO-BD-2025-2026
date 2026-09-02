package implementazionePostgresDAO;

import dao.BoxDAO;
import database.ConnessioneDatabase;
import model.Box;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoxPostgresDAO implements BoxDAO {

    private Connection connection;

    public BoxPostgresDAO() {
        try {
            this.connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    @Override
    public boolean aggiornaDisponibilita(int idBox, int nuovaQuantita) throws SQLException {
        String sql = "UPDATE box SET quantita_disponibile = ? WHERE id_box = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, nuovaQuantita);
            statement.setInt(2, idBox);
            return statement.executeUpdate() > 0;
        }
    }
}
