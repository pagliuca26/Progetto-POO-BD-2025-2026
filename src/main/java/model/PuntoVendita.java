package model;

import java.time.LocalTime;

public class PuntoVendita {

    //attributi
    private int idPuntoVendita;
    private String nome, indirizzo, orarioRitiro;

    public PuntoVendita(int idPuntoVendita, String nome, String indirizzo, String orarioRitiro) {
        this.idPuntoVendita = idPuntoVendita;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.orarioRitiro = orarioRitiro;
    }
    //get and set
    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getOrarioRitiro() {
        return orarioRitiro;
    }

    public void setOrarioRitiro(String orarioRitiro) {
        this.orarioRitiro = orarioRitiro;
    }

    //metodi dal class diagram
    public String fornireIndirizzo() {
        return " Nome: " + this.nome + ", Indirizzo: " + this.indirizzo + "Orario di ritiro" +this.orarioRitiro; }





}
