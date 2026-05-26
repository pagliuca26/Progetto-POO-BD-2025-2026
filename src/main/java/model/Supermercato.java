package model;

import java.time.LocalTime;

public class Supermercato extends PuntoVendita{

    private String BancoRitiro;

    public Supermercato(int idPuntoVendita, String nome, String indirizzo, String orarioRitiro, String bancoRitiro) {
        super(idPuntoVendita, nome, indirizzo, orarioRitiro);
        BancoRitiro = bancoRitiro;
    }

    public String getBancoRitiro() {
        return BancoRitiro;
    }

    public void setBancoRitiro(String bancoRitiro) {
        BancoRitiro = bancoRitiro;
    }

    //creazione supermercato 1
    Supermercato Conad = new Supermercato(201, "Conad", "Via Francesco Petrarca 86 (NA)", "dalle 17:30 alle 20:30","Macelleria");

    //creazione supermercato 2
    Supermercato Sole365 =new Supermercato(202, "Sole 365", "Via Giotto 125 (NA)", "dalle 18:00 alle 22:00", "Panetteria");

    //creazione supermercato 3
    Supermercato Despar = new Supermercato(203, "Despar", "Piazza Liberta 55 (NA)", "dalle 18:30 alle 21:00", "Cassa");

}
