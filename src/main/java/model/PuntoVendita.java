package model;

/**
 * Superclasse che modella l'entità PuntoVendita all'interno del dominio applicativo.
 * Raggruppa i dati anagrafici e logistici comuni (identificativo, denominazione, indirizzo e orario di ritiro)
 * ereditati poi dalle sottoclassi Ristorante e Supermercato.
 */
public class PuntoVendita {

    //attributi del punto vendita
    private int idPuntoVendita;
    private String nome;
    private String indirizzo;
    private String orarioRitiro;

    /**
     * Costruttore parametrico per l'inizializzazione del punto vendita.
     * Assegna l'identificativo univoco, il nome, l'indirizzo della sede e la fascia oraria di ritiro.
     *
     * @param idPuntoVendita l'identificativo univoco del punto vendita
     * @param nome           la denominazione dell'attività commerciale
     * @param indirizzo      l'indirizzo della sede fisica
     * @param orarioRitiro   la fascia oraria consentita per il ritiro delle box
     */
//costruttore per creare il punto vendita
    public PuntoVendita(int idPuntoVendita, String nome, String indirizzo, String orarioRitiro) {
        this.idPuntoVendita = idPuntoVendita;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.orarioRitiro = orarioRitiro;
    }

    /**
     * Restituisce l'indirizzo della sede fisica del punto vendita, in conformità con il Class Diagram.
     *
     * @return la stringa contenente l'indirizzo
     */
//metodo del diagramma per fornire i dati dell indirizzo
    public String fornireIndirizzo() {
        return this.indirizzo;
    }

    /**
     * Metodi getter e setter per l'accesso e la modifica degli attributi privati
     * della classe PuntoVendita (idPuntoVendita, nome, indirizzo, orarioRitiro),
     * nel rispetto del principio di incapsulamento.
     */
//metodi get e set per id
    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }


//metodi get e set per nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


//metodi get e set per indirizzo
    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }


//metodi get e set per orario ritiro
    public String getOrarioRitiro() {
        return orarioRitiro;
    }

    public void setOrarioRitiro(String orarioRitiro) {
        this.orarioRitiro = orarioRitiro;
    }
}