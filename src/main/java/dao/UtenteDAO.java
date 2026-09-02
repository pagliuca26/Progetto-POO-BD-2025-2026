package dao;

import model.Utente;
import java.sql.SQLException;

public interface UtenteDAO {
    boolean registraUtente(Utente utente) throws SQLException;
    Utente login(String email, String password) throws SQLException;
}
