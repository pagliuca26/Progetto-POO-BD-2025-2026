package dao;

import java.sql.SQLException;
import model.Utente;

public interface UtenteDAO {

    //registra un nuovo utente nel database
    boolean registraUtente(Utente utente) throws SQLException;

    //effettua il login verificando le credenziali
    Utente login(String email, String password) throws SQLException;

    //aggiorna i dati del profilo utente
    boolean modificaUtente(Utente utente, String vecchiaEmail) throws SQLException;

    //elimina l account dell utente
    boolean eliminaUtente(String email) throws SQLException;
}