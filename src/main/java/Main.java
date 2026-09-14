import controller.Controller;
import gui.Login;
import javax.swing.SwingUtilities;

/**
 * Classe di avvio dell'applicazione.
 * Si occupa di inizializzare il Controller e lanciare l'interfaccia grafica
 * sul thread dedicato ai componenti Swing (Event Dispatch Thread).
 */
public class Main {

    /**
     * Punto di ingresso principale (main) dell'applicazione.
     * Inizializza il Controller e apre la finestra di Login all'interno dell'Event Dispatch Thread (EDT)
     * tramite SwingUtilities.invokeLater per garantire la thread-safety dell'interfaccia grafica.
     *
     * @param args eventuali argomenti passati da riga di comando
     */
//punto di ingresso del programma
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Controller controller = new Controller();
                Login login = new Login(controller);
                login.mostraFinestra();
            }
        });
    }
}