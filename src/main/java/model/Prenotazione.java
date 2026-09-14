package model;

/**
 * Rappresenta l'entità Prenotazione nel modello di dominio.
 * Associa una box prenotata a un codice di ritiro alfanumerico e al relativo stato (es. ATTIVA, ANNULLATA).
 */
public class Prenotazione {

    //attributi della prenotazione
    private int idBox;
    private String codiceRitiro;
    private String stato;

    /**
     * Costruttore minimale con il solo codice di ritiro.
     * Utilizzato quando è necessario fare riferimento rapidamente a una prenotazione tramite il suo identificativo testuale.
     *
     * @param codiceRitiro il codice alfanumerico associato al ritiro della box
     */
//costruttore base con solo codice ritiro
    public Prenotazione(String codiceRitiro) {
        this.codiceRitiro = codiceRitiro;
    }

    /**
     * Costruttore con codice di ritiro e stato.
     * Utilizzato per rappresentare una prenotazione quando non occorre specificare l'ID della box.
     *
     * @param codiceRitiro il codice alfanumerico associato al ritiro
     * @param stato        lo stato attuale della prenotazione (es. ATTIVA, ANNULLATA)
     */
//costruttore con codice e stato
    public Prenotazione(String codiceRitiro, String stato) {
        this.codiceRitiro = codiceRitiro;
        this.stato = stato;
    }

    /**
     * Costruttore completo della prenotazione.
     * Utilizzato principalmente dal livello DAO per mappare tutti i dati estratti dal database.
     *
     * @param idBox        l'identificativo della box prenotata
     * @param codiceRitiro il codice univoco di ritiro
     * @param stato        lo stato attuale dell'ordine (es. ATTIVA, ANNULLATA)
     */
//costruttore completo con anche id del box per il db
    public Prenotazione(int idBox, String codiceRitiro, String stato) {
        this.idBox = idBox;
        this.codiceRitiro = codiceRitiro;
        this.stato = stato;
    }

    /**
     * Aggiorna lo stato della prenotazione in memoria impostandolo su "ANNULLATA".
     */
//metodo comodo per annullare la prenotazione cambiando lo stato
    public void annulla() {
        this.stato = "ANNULLATA";
    }

    /**
     * Metodi getter e setter per l'accesso e la modifica degli attributi privati
     * della classe Prenotazione (idBox, codiceRitiro, stato), garantendo l'incapsulamento dei dati.
     */
//metodi get e set per idbox
    public int getIdBox() {
        return idBox;
    }

    public void setIdBox(int idBox) {
        this.idBox = idBox;
    }


//metodi get e set per codice ritiro
    public String getCodiceRitiro() {
        return codiceRitiro;
    }

    public void setCodiceRitiro(String codiceRitiro) {
        this.codiceRitiro = codiceRitiro;
    }


//metodi get e set per stato
    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
}