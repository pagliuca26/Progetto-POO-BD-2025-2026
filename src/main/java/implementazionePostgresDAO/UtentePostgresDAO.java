package implementazionePostgresDAO;

import dao.UtenteDAO;
import database.ConnessioneDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Utente;

public class UtentePostgresDAO implements UtenteDAO {

    //connessione al database
    private Connection connection;

    //costruttore che recupera l istanza della connessione
    public UtentePostgresDAO() {
        try {
            this.connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("errore connessione utentedao: " + e.getMessage());
        }
    }

    //registra un nuovo utente nel database
    @Override
    public boolean registraUtente(Utente utente) throws SQLException {
        String sql = "INSERT INTO utente (email, password, nome, cognome) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, utente.getEmail());
            statement.setString(2, utente.getPassword());
            statement.setString(3, utente.getNome());
            statement.setString(4, utente.getCognome());
            return statement.executeUpdate() > 0;
        }
    }

    //effettua il login verificando email e password
    @Override
    public Utente login(String email, String password) throws SQLException {
        String sql = "SELECT email, password, nome, cognome FROM utente WHERE email = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String mail = rs.getString("email");
                    String pass = rs.getString("password");
                    String nome = rs.getString("nome");
                    String cognome = rs.getString("cognome");
                    return new Utente(nome, cognome, mail, pass);
                }
            }
        }
        return null;
    }

    //modifica i dati dell utente nel database
    @Override
    public boolean modificaUtente(Utente utente, String vecchiaEmail) throws SQLException {
        String sql = "UPDATE utente SET email = ?, password = ?, nome = ?, cognome = ? WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, utente.getEmail());
            statement.setString(2, utente.getPassword());
            statement.setString(3, utente.getNome());
            statement.setString(4, utente.getCognome());
            statement.setString(5, vecchiaEmail);
            return statement.executeUpdate() > 0;
        }
    }

    //elimina l utente dal database
    @Override
    public boolean eliminaUtente(String email) throws SQLException {
        String sql = "DELETE FROM utente WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            return statement.executeUpdate() > 0;
        }
    }
}