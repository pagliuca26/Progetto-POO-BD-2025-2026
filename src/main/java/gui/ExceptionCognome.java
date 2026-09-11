package gui;

public class ExceptionCognome extends RuntimeException {

    //costruttore che passa il messaggio di errore alla superclasse
    public ExceptionCognome(String message) {
        super(message);
    }
}