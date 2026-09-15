package exceptions;

/**
 * Eccezione personalizzata unchecked lanciata durante la fase di registrazione
 * qualora l'indirizzo email inserito risulti già presente nel database.
 */
public class ExceptionEmailUguale extends RuntimeException {

    /**
     * Costruttore con messaggio personalizzato.
     * Inoltra il dettaglio dell'errore alla superclasse RuntimeException tramite super().
     *
     * @param message la descrizione dell'errore relativo all'email già registrata
     */
//costruttore che passa il messaggio di errore alla superclasse
    public ExceptionEmailUguale(String message) {
        super(message);
    }
}