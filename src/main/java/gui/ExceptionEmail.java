package gui;

/**
 * Eccezione personalizzata unchecked lanciata durante la fase di registrazione
 * qualora l'e-mail inserito dall'utente non rispetti i criteri di validazione (es. campo vuoto o formato non valido).
 */
public class ExceptionEmail extends RuntimeException {

    /**
     * Costruttore con messaggio personalizzato.
     * Inoltra il dettaglio dell'errore alla superclasse RuntimeException tramite super().
     *
     * @param message la descrizione del motivo per cui l'e-mail non è valida
     */
//costruttore che passa il messaggio di errore alla superclasse
    public ExceptionEmail(String message) {
        super(message);
    }
}