package exceptions;

/**
 * Eccezione personalizzata unchecked lanciata durante la fase di registrazione
 * qualora la password inserita dall'utente non rispetti i criteri di validazione (es. campo vuoto o formato non valido).
 */
public class ExceptionPassword extends RuntimeException {

    /**
     * Costruttore con messaggio personalizzato.
     * Inoltra il dettaglio dell'errore alla superclasse RuntimeException tramite super().
     *
     * @param message la descrizione del motivo per cui la password non è valida
     */
//costruttore che passa il messaggio di errore alla superclasse
    public ExceptionPassword(String message) {
        super(message);
    }
}