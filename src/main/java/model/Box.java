package model;

/**
 * Rappresenta l'entità Box nel modello dei dati.
 * Contiene i prezzi (originale e scontato), la disponibilità residua e la dimensione.
 */
public class Box {

    //attributi
    private double prezzoOriginale;
    private double prezzoScontato;
    private int quantitaDisponibile;
    private String grandezzaBox;

    /**
     * Costruttore della classe Box.
     *
     * @param prezzoOriginale     il prezzo di listino iniziale della box
     * @param prezzoScontato      il prezzo ribassato per la vendita
     * @param quantitaDisponibile il numero di box attualmente disponibili
     * @param grandezzaBox        la taglia o dimensione della box (es. Piccola, Media, Grande)
     */
//costruttore
    public Box(double prezzoOriginale, double prezzoScontato, int quantitaDisponibile, String grandezzaBox) {
        this.prezzoOriginale = prezzoOriginale;
        this.prezzoScontato = prezzoScontato;
        this.quantitaDisponibile = quantitaDisponibile;
        this.grandezzaBox = grandezzaBox;
    }

    /**
     * Metodi getter e setter per l'accesso e la modifica controllata degli attributi privati
     * della classe Box (prezzoOriginale, prezzoScontato, quantitaDisponibile, grandezzaBox),
     * nel rispetto del principio di incapsulamento.
     */
//metodi get e set
    public double getPrezzoOriginale() {
        return prezzoOriginale;
    }

    public void setPrezzoOriginale(double prezzoOriginale) {
        this.prezzoOriginale = prezzoOriginale;
    }


    public double getPrezzoScontato() {
        return prezzoScontato;
    }

    public void setPrezzoScontato(double prezzoScontato) {
        this.prezzoScontato = prezzoScontato;
    }

    /**
     * Gets quantita disponibile.
     *
     * @return the quantita disponibile
     */
//restituisce la disponibilita residua della box
    public int getQuantitaDisponibile() {
        return quantitaDisponibile;
    }

    /**
     * Sets quantita disponibile.
     *
     * @param quantitaDisponibile the quantita disponibile
     */
    public void setQuantitaDisponibile(int quantitaDisponibile) {
        this.quantitaDisponibile = quantitaDisponibile;
    }

    /**
     * Gets grandezza box.
     *
     * @return the grandezza box
     */
    public String getGrandezzaBox() {
        return grandezzaBox;
    }

    /**
     * Sets grandezza box.
     *
     * @param grandezzaBox the grandezza box
     */
    public void setGrandezzaBox(String grandezzaBox) {
        this.grandezzaBox = grandezzaBox;
    }
}