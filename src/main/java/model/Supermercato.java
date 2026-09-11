package model;

public class Supermercato extends PuntoVendita {

    //attributo specifico del supermercato
    private String bancoRitiro;

    //costruttore che richiama la superclasse puntovendita
    public Supermercato(int idPuntoVendita, String nome, String indirizzo, String orarioRitiro, String bancoRitiro) {
        super(idPuntoVendita, nome, indirizzo, orarioRitiro);
        this.bancoRitiro = bancoRitiro;
    }

    //metodi get e set per banco ritiro
    public String getBancoRitiro() {
        return bancoRitiro;
    }

    public void setBancoRitiro(String bancoRitiro) {
        this.bancoRitiro = bancoRitiro;
    }
}