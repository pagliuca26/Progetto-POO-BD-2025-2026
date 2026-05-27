package controller;

import gui.*;
import model.Utente;

import java.util.ArrayList;

public class Controller {
    private ArrayList<Utente> listaUtenti;
    //per eccezione
    private Utente utenteAttuale = null;

    public Controller() {

        listaUtenti = new ArrayList<>();
    }

    public void aggiungiUtente(Utente utente) {listaUtenti.add(utente);}

    public boolean checkUtente(String campoEmail, String campoPassword){
        if(campoEmail.isBlank()) throw new ExceptionEmail("Il campo email è vuoto.");
        if (campoPassword.isBlank()) throw new ExceptionEmail("Il campo password è vuoto.");

        for (Utente u : listaUtenti ){
            if (u.getEmail().equals(campoEmail) && u.getPassword().equals(campoPassword)){
    u.setAccessoEffettuato(true);
    utenteAttuale = u;
    return true;

            }
        }
        throw new ExceptionEmail("Utente non trovato.");
    }


      //Eccezioni per crea account
    public void creaUtente (String email, String password) throws RuntimeException {
           if (email.isBlank()) throw new ExceptionEmail("Il campo email è vuoto.");
           if (password.isBlank()) throw new ExceptionPassword("Il campo password è vuoto.");

           for (Utente u: listaUtenti){
               if (u.getEmail().equals(email)){
                   throw new ExceptionEmailUguale("Email già esistente.");

               }
        }

           Utente utente = new Utente(email,password);
           listaUtenti.add(utente);
    }
 public Utente getUtenteAttuale() {
        return utenteAttuale;
 }

    public void esciUtente() {
        this.utenteAttuale.setAccessoEffettuato(false);
        this.utenteAttuale = null;
    }

    public ArrayList<Utente> getListaUtenti(){
        return listaUtenti;
    }

}
