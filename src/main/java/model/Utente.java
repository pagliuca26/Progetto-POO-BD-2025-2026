package model;

public class Utente {
    private String email;
    private String password;
    private String nome;
    private String cognome;

    //per eccezione
    private boolean accessoEffettuato = false;

    //costruttore
    public Utente(String email, String password, String nome, String cognome) {
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
    }

    //get e set
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    // get e set accesso effettuato, eccezione
    public boolean isAccessoEffettuato() { return accessoEffettuato; }
    public void setAccessoEffettuato(boolean accessoEffettuato) { this.accessoEffettuato = accessoEffettuato; }
}