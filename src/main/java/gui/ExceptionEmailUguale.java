package gui;

public class ExceptionEmailUguale extends RuntimeException {

    //costruttore che passa il messaggio di errore alla superclasse
    public ExceptionEmailUguale(String message) {
        super(message);
    }
}