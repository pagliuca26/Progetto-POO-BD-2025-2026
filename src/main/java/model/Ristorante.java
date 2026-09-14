package model;

/**
 * Rappresenta un punto vendita di tipo ristorativo all'interno del dominio applicativo.
 * Estende la superclasse PuntoVendita specializzandola con l'attributo relativo al tipo di cucina offerta.
 */
public class Ristorante extends PuntoVendita {

    //attributo specifico del ristorante
    private String tipoCucina;

    /**
     * Costruttore parametrico per la classe Ristorante.
     * Inizializza i campi comuni delegando alla superclasse PuntoVendita tramite super()
     * e assegna il tipo di cucina specifico dell'attività.
     *
     * @param idPuntoVendita l'identificativo univoco del punto vendita
     * @param nome           la denominazione del ristorante
     * @param indirizzo      l'indirizzo della sede fisica
     * @param orarioRitiro   la fascia oraria consentita per il ritiro
     * @param tipoCucina     la tipologia di cucina proposta (es. Giapponese, Tradizionale)
     */
//costruttore che richiama i campi della superclasse puntovendita
    public Ristorante(int idPuntoVendita, String nome, String indirizzo, String orarioRitiro, String tipoCucina) {
        super(idPuntoVendita, nome, indirizzo, orarioRitiro);
        this.tipoCucina = tipoCucina;
    }

    /**
     * Metodi getter e setter per l'accesso e la modifica controllata
     * dell'attributo privato tipoCucina, nel rispetto del principio di incapsulamento.
     */
//metodi get e set per tipo cucina
    public String getTipoCucina() {
        return tipoCucina;
    }

    public void setTipoCucina(String tipoCucina) {
        this.tipoCucina = tipoCucina;
    }
}