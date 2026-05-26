package model;

import java.time.LocalTime;

public class Ristorante  extends PuntoVendita{

    private String tipoCucina;

    public Ristorante(int idPuntoVendita, String nome, String indirizzo, String orarioRitiro, String tipoCucina) {
        super(idPuntoVendita, nome, indirizzo, orarioRitiro);
        this.tipoCucina = tipoCucina;
    }

    public String getTipoCucina() {
        return tipoCucina;
    }

    public void setTipoCucina(String tipoCucina) {
        this.tipoCucina = tipoCucina;
    }

    //creazione ristorante 1
    Ristorante Italiamo = new Ristorante(101, "Italiamo", "Via Giacomo Leopardi  52 (NA)", "dalle 19:00 alle 22:30","Ristorante italiano");

    //creazione ristorante 2
    Ristorante Guacamole = new Ristorante(102, "Guacamole", "Via Roma 26 (NA)", "dalle 16:00 alle 23:00", "Ristorante messicano");

    //creazione ristante 3
    Ristorante Tokyo = new Ristorante(103, "Tokyo", "Via Torquato Tasso 16 (NA)", "dalle 21:00 alle 23:30", "Ristorante giapponese");
}
