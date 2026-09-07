package model;

public class Ristorante extends PuntoVendita {

    //attributi
    private String tipoCucina;

    //costruttore
    public Ristorante(int idPuntoVendita, String nome, String indirizzo, String orarioRitiro, String tipoCucina) {
        super(idPuntoVendita, nome, indirizzo, orarioRitiro);
        this.tipoCucina = tipoCucina;
    }

    //get e set
    public String getTipoCucina() {
        return tipoCucina;
    }

    public void setTipoCucina(String tipoCucina) {
        this.tipoCucina = tipoCucina;
    }
}