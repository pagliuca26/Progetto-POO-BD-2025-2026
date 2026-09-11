package model;

public class Prenotazione {

    //attributi della prenotazione
    private int idBox;
    private String codiceRitiro;
    private String stato;

    //costruttore base con solo codice ritiro
    public Prenotazione(String codiceRitiro) {
        this.codiceRitiro = codiceRitiro;
    }

    //costruttore con codice e stato
    public Prenotazione(String codiceRitiro, String stato) {
        this.codiceRitiro = codiceRitiro;
        this.stato = stato;
    }

    //costruttore completo con anche id del box per il db
    public Prenotazione(int idBox, String codiceRitiro, String stato) {
        this.idBox = idBox;
        this.codiceRitiro = codiceRitiro;
        this.stato = stato;
    }

    //metodo comodo per annullare la prenotazione cambiando lo stato
    public void annulla() {
        this.stato = "ANNULLATA";
    }

    //metodi get e set ordinati per idbox
    public int getIdBox() {
        return idBox;
    }

    public void setIdBox(int idBox) {
        this.idBox = idBox;
    }

    //metodi get e set ordinati per codice ritiro
    public String getCodiceRitiro() {
        return codiceRitiro;
    }

    public void setCodiceRitiro(String codiceRitiro) {
        this.codiceRitiro = codiceRitiro;
    }

    //metodi get e set ordinati per stato
    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
}