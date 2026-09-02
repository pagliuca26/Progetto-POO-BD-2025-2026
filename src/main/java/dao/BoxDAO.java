package dao;

import model.Box;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BoxDAO {
    ArrayList<Box> getBoxDisponibili() throws SQLException;
    boolean aggiornaDisponibilita(int idBox, int nuovaQuantita) throws SQLException;
}
