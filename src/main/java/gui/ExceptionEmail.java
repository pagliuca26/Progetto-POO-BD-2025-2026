package gui;

public class ExceptionEmail extends RuntimeException {

    //costruttore che passa il messaggio di errore alla superclasse
    public ExceptionEmail(String message) {
        super(message);
    }
}