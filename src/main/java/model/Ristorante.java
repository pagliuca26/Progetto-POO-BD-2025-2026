package model;

public class Ristorante extends PuntoVendita {

    //attributo specifico del ristorante
    private String tipoCucina;

    //costruttore che richiama i campi della superclasse puntovendita
    public Ristorante(int idPuntoVendita, String nome, String indirizzo, String orarioRitiro, String tipoCucina) {
        super(idPuntoVendita, nome, indirizzo, orarioRitiro);
        this.tipoCucina = tipoCucina;
    }

    //metodi get e set per tipo cucina
    public String getTipoCucina() {
        return tipoCucina;
    }

    public void setTipoCucina(String tipoCucina) {
        this.tipoCucina = tipoCucina;
    }
}