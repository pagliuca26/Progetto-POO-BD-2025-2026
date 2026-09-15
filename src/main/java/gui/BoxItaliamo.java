package gui;

import controller.Controller;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.*;

/**
 * Schermata di dettaglio e acquisto per le box del ristorante Italiamo.
 * Mostra le box rimaste a disposizione e gestisce la prenotazione,
 * aggiornando la disponibilità e salvando l'ordine tramite il Controller.
 */
public class BoxItaliamo {

    //componenti grafici della finestra box italiamo
    private static JFrame frameItaliamo;
    private JPanel boxItaliamo;
    private JLabel ristItaliamo;
    private JButton acquistaItaliamo;
    private JLabel qntDispItaliamo;
    private JLabel spaghettiItaliamo;
    private static int quantitaDisponibileItaliamo;

    /**
     * Costruttore della schermata BoxItaliamo.
     * Inizializza l'interfaccia grafica, visualizza la disponibilità residua di box
     * e gestisce l'acquisto memorizzando la prenotazione sul database e fornendo
     * il codice di ritiro all'utente.
     *
     * @param frameRistorante il frame del menu ristoranti per consentire il ritorno indietro
     * @param controller      l'istanza del Controller per effettuare l'acquisto sul database
     */
//costruttore della schermata box italiamo
    public BoxItaliamo(JFrame frameRistorante, Controller controller) {
        frameItaliamo = new JFrame("Italiamo");
        frameItaliamo.setContentPane(boxItaliamo);
        frameItaliamo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameItaliamo.pack();

        //impostazioni della finestra
        frameItaliamo.setResizable(false);
        frameItaliamo.setSize(450, 450);
        frameItaliamo.setLocationRelativeTo(null);
        frameItaliamo.setVisible(true);

        //ritorno alla schermata dei ristoranti
        ristItaliamo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ristItaliamo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frameRistorante.setVisible(true);
                frameItaliamo.setVisible(false);
            }
        });

        //recupera quantita delle box disponibili
        try{
            quantitaDisponibileItaliamo=controller.getQtaBoxDisponibili(4);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //inizializzazione del testo di disponibilita
        qntDispItaliamo.setText("Quantità disponibile: " + quantitaDisponibileItaliamo);

        //gestione click del pulsante acquista
        acquistaItaliamo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantitaDisponibileItaliamo > 0) {
                    quantitaDisponibileItaliamo--;
                    qntDispItaliamo.setText("Quantità disponibile: " + quantitaDisponibileItaliamo);

                    int quantitaPresa = 10 - quantitaDisponibileItaliamo;

                    //salva lacquisto nel db con id 4 e ottiene il codice generato
                    String codiceRitiro = controller.acquistaBoxDB(4, quantitaDisponibileItaliamo);

                    //aggiorna la schermata di riepilogo prenotazioni
                    Home.getPaginaPrenotazione().aggiornaPrenotazione("Italiamo", quantitaPresa, codiceRitiro);

                    //notifica di conferma con codice di ritiro
                    JOptionPane.showMessageDialog(
                            frameItaliamo,
                            "Acquisto effettuato con successo!\nCodice di ritiro: " + codiceRitiro,
                            "Prenotazione Confermata",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Errore: Le Box per questo punto vendita sono terminate!",
                            "Box Terminate",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    /**
     * Restituisce la finestra principale della schermata Box Italiamo.
     * Permette al menu dei ristoranti di renderla visibile senza ricrearla.
     *
     * @return il frame grafico della pagina Italiamo
     */
//restituisce il frame della finestra italiamo
    public static JFrame getFrameItaliano() {
        return frameItaliamo;
    }

    /**
     * Incrementa il contatore delle box disponibili per Italiamo.
     * Viene richiamato quando l'utente annulla una prenotazione esistente.
     */
//incrementa il numero di box disponibili a seguito di annullamento
    public static void aumentaDisponibile() {
        quantitaDisponibileItaliamo++;
    }

    /**
     * Restituisce il numero di box Italiamo attualmente disponibili per l'acquisto.
     *
     * @return la quantità residua di box
     */
//restituisce la quantita attualmente disponibile
    public static int getDisponibile() {
        return quantitaDisponibileItaliamo;
    }

    /**
     * Ricarica il testo dell'etichetta grafica mostrando la quantità aggiornata di box disponibili per Italiamo.
     */
//aggiorna il testo dell etichetta della disponibilita
    public void aggiornaLabelDisponibile() {
        qntDispItaliamo.setText("Quantità disponibile: " + quantitaDisponibileItaliamo);
    }
}