package gui;

/**
 * Eccezione personalizzata unchecked lanciata durante la fase di registrazione
 * qualora il nome inserito dall'utente non rispetti i criteri di validazione (es. campo vuoto o formato non valido).
 */
public class ExceptionNome extends RuntimeException {

    /**
     * Costruttore con messaggio personalizzato.
     * Inoltra il dettaglio dell'errore alla superclasse RuntimeException tramite super().
     *
     * @param message la descrizione del motivo per cui il nome non è valido
     */
//costruttore che passa il messaggio di errore alla superclasse
    public ExceptionNome(String message) {
        super(message);
    }
}