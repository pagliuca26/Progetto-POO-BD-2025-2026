package model;

public class Utente {

    //attributi dell utente
    private int idUtente;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private boolean accessoEffettuato = false;

    //costruttore senza id utile per la registrazione
    public Utente(String nome, String cognome, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }

    //costruttore completo utile quando leggiamo i dati dal database
    public Utente(int idUtente, String nome, String cognome, String email, String password) {
        this.idUtente = idUtente;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }

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

    public void setAccessoEffettuato(boolean accessoEffettuato) {
        this.accessoEffettuato = accessoEffettuato;
    }
}