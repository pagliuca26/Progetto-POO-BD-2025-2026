package model;

public class Supermercato extends PuntoVendita {

    //attributi
    private String bancoRitiro;

    //costruttore
    public Supermercato(int idPuntoVendita, String nome, String indirizzo, String orarioRitiro, String bancoRitiro) {
        super(idPuntoVendita, nome, indirizzo, orarioRitiro);
        this.bancoRitiro = bancoRitiro;
    }

    //get e set
    public String getBancoRitiro() {
        return bancoRitiro;
    }

    public void setBancoRitiro(String bancoRitiro) {
        this.bancoRitiro = bancoRitiro;
    }
}