package controller;

import model.Utente;

import java.util.ArrayList;

public class Controller {
    private ArrayList<Utente> listaUtenti;

    public Controller() {
        listaUtenti = new ArrayList<>();
    }

    public void creaAccount(String nome, String cognome, String email, String password) {
        for(Utente u : listaUtenti) {
            if(u.getEmail().equals(email)) {
                //inserire eccezione email già esistente
            }
        }

        Utente utente = new Utente(nome,cognome,email,password);
        listaUtenti.add(utente);
    }




}
