package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Box;

public interface BoxDAO {

    //recupera tutte le box con disponibilita nel database
    ArrayList<Box> getBoxDisponibili() throws SQLException;

    //aggiorna manualmente la quantita disponibile di una box
    boolean aggiornaDisponibilita(int idBox, int nuovaQuantita) throws SQLException;

    //restituisce la quantita residua di una determinata box
    int getDisponibilitaBox(int idBox) throws SQLException;
}