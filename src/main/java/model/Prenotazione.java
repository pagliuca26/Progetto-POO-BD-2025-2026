package model;

public class Prenotazione {

    private String codiceRitiro, stato;

    public Prenotazione( String codiceRitiro) {
        this.codiceRitiro = codiceRitiro;
    }

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
