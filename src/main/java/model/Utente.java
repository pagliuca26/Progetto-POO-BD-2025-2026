package model;

public class Utente {

    private String  email, password;
   // per eccezione
    private boolean accessoEffettuato = false ;

    public Utente( String email, String password) {

        this.email = email;
        this.password = password;
    }
//get e set

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // get e set accesso effettuato , eccezione
    public boolean isAccessoEffettuato() {
        return accessoEffettuato;
    }

    public void setAccessoEffettuato(boolean accessoEffettuato) {
        this.accessoEffettuato = accessoEffettuato;
    }
}
