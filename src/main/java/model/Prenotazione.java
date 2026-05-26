package model;

public class Prenotazione {

    private int idPrenotazione;
    private String codiceRitiro, stato;

    public Prenotazione(int idPrenotazione, String codiceRitiro, String stato) {
        this.idPrenotazione = idPrenotazione;
        this.codiceRitiro = codiceRitiro;
        this.stato = stato;
    }

    public int getIdPrenotazione() {
        return idPrenotazione;
    }

    public void setIdPrenotazione(int idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
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
