package gui;

public class ExceptionNome extends RuntimeException {

    //costruttore che passa il messaggio di errore alla superclasse
    public ExceptionNome(String message) {
        super(message);
    }
}