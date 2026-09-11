package model;

public class PuntoVendita {

    //attributi del punto vendita
    private int idPuntoVendita;
    private String nome;
    private String indirizzo;
    private String orarioRitiro;

    //costruttore per creare il punto vendita
    public PuntoVendita(int idPuntoVendita, String nome, String indirizzo, String orarioRitiro) {
        this.idPuntoVendita = idPuntoVendita;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.orarioRitiro = orarioRitiro;
    }

    //metodo del diagramma per fornire i dati dell indirizzo
    public String fornireIndirizzo() {
        return this.indirizzo;
    }

    //metodi get e set per id
    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }

    //metodi get e set per nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //metodi get e set per indirizzo
    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    //metodi get e set per orario ritiro
    public String getOrarioRitiro() {
        return orarioRitiro;
    }

    public void setOrarioRitiro(String orarioRitiro) {
        this.orarioRitiro = orarioRitiro;
    }
}