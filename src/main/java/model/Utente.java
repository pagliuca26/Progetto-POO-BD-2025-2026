package model;

/**
 * Rappresenta l'entità Utente all'interno del dominio applicativo.
 * Mantiene le informazioni anagrafiche, le credenziali di accesso e lo stato della sessione.
 */
public class Utente {

    //attributi dell utente
    private int idUtente;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private boolean accessoEffettuato = false;

    /**
     * Costruttore per la creazione di un utente senza ID.
     * Utilizzato durante la fase di registrazione quando l'ID non è ancora stato generato dal database.
     *
     * @param nome     il nome dell'utente
     * @param cognome  il cognome dell'utente
     * @param email    l'indirizzo email dell'utente
     * @param password la password dell'utente
     */
//costruttore senza id utile per la registrazione
    public Utente(String nome, String cognome, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }

    /**
     * Costruttore completo per la creazione di un oggetto Utente con ID.
     * Utilizzato principalmente quando si recuperano record già esistenti dal database.
     *
     * @param idUtente l'identificativo univoco dell'utente
     * @param nome     il nome dell'utente
     * @param cognome  il cognome dell'utente
     * @param email    l'indirizzo email dell'utente
     * @param password la password dell'utente
     */
//costruttore completo utile quando leggiamo i dati dal database
    public Utente(int idUtente, String nome, String cognome, String email, String password) {
        this.idUtente = idUtente;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }

    /**
     * Metodi getter e setter per l'accesso e la modifica controllata degli attributi privati
     * della classe Utente (id, nome, cognome, email, password, accessoEffettuato),
     * nel rispetto del principio di incapsulamento.
     */
//metodi get e set per id utente
    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }


//metodi get e set per nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


//metodi get e set per cognome
    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }


//metodi get e set per email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


//metodi get e set per password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


//metodi get e set per verificare lo stato del login
    public boolean isAccessoEffettuato() {
        return accessoEffettuato;
    }

    /**
     * Sets accesso effettuato.
     *
     * @param accessoEffettuato the accesso effettuato
     */
    public void setAccessoEffettuato(boolean accessoEffettuato) {
        this.accessoEffettuato = accessoEffettuato;
    }
}