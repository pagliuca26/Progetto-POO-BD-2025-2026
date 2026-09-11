package gui;

public class ExceptionPassword extends RuntimeException {

    //costruttore che passa il messaggio di errore alla superclasse
    public ExceptionPassword(String message) {
        super(message);
    }
}