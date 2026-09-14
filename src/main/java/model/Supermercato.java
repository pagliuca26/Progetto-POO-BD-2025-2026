package model;

/**
 * Rappresenta un punto vendita di tipo supermercato all'interno del dominio applicativo.
 * Estende la superclasse PuntoVendita specializzandola con l'attributo relativo al banco di ritiro dedicato.
 */
public class Supermercato extends PuntoVendita {

    //attributo specifico del supermercato
    private String bancoRitiro;

    /**
     * Costruttore parametrico per la classe Supermercato.
     * Inizializza i campi comuni delegando alla superclasse PuntoVendita tramite super()
     * e assegna il banco di ritiro specifico del supermercato.
     *
     * @param idPuntoVendita l'identificativo univoco del punto vendita
     * @param nome           la denominazione del supermercato
     * @param indirizzo      l'indirizzo della sede fisica
     * @param orarioRitiro   la fascia oraria consentita per il ritiro
     * @param bancoRitiro    il banco o reparto designato al ritiro delle box
     */
//costruttore che richiama la superclasse puntovendita
    public Supermercato(int idPuntoVendita, String nome, String indirizzo, String orarioRitiro, String bancoRitiro) {
        super(idPuntoVendita, nome, indirizzo, orarioRitiro);
        this.bancoRitiro = bancoRitiro;
    }

    /**
     * Metodi getter e setter per l'accesso e la modifica controllata
     * dell'attributo privato bancoRitiro, nel rispetto del principio di incapsulamento.
     */
//metodi get e set per banco ritiro
    public String getBancoRitiro() {
        return bancoRitiro;
    }

    public void setBancoRitiro(String bancoRitiro) {
        this.bancoRitiro = bancoRitiro;
    }
}