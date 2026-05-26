package model;

public class Box {

    private double prezzoOriginale, prezzoScontato;
    private int quantitaDisponibile;
    private String grandezzaBox;

    public Box(double prezzoOriginale, double prezzoScontato, int quantitaDisponibile, String grandezzaBox) {
        this.prezzoOriginale = prezzoOriginale;
        this.prezzoScontato = prezzoScontato;
        this.quantitaDisponibile = quantitaDisponibile;
        this.grandezzaBox = grandezzaBox;
    }

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

    public int getQuantitaDisponibile() {
        return quantitaDisponibile;
    }

    public void setQuantitaDisponibile(int quantitaDisponibile) {
        this.quantitaDisponibile = quantitaDisponibile;
    }

    public String getGrandezzaBox() {
        return grandezzaBox;
    }

    public void setGrandezzaBox(String grandezzaBox) {
        this.grandezzaBox = grandezzaBox;
    }
}
