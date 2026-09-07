package dao;

import model.Utente;
import java.sql.SQLException;

public interface UtenteDAO {
    boolean registraUtente(Utente utente) throws SQLException;
    Utente login(String email, String password) throws SQLException;
    boolean modificaUtente(Utente utente, String vecchiaEmail) throws SQLException;
    boolean eliminaUtente(String email) throws SQLException;
}