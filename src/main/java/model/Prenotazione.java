package model;

public class Prenotazione {

    //attributi
    private String codiceRitiro;
    private String stato;

    //costruttore
    public Prenotazione(String codiceRitiro) {
        this.codiceRitiro = codiceRitiro;
    }

    //overloading costruttore
    public Prenotazione(String codiceRitiro, String stato) {
        this.codiceRitiro = codiceRitiro;
        this.stato = stato;
    }

    //get e set
    public String getCodiceRitiro() {
        return codiceRitiro;
    }

    public void setCodiceRitiro(String codiceRitiro) {
        this.codiceRitiro = codiceRitiro;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
}