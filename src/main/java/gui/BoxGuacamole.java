package gui;

import controller.Controller;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * Schermata di dettaglio e acquisto per le box del ristorante messicano Guacamole.
 * Mostra le box rimaste a disposizione e gestisce la prenotazione,
 * aggiornando la disponibilità e salvando l'ordine tramite il Controller.
 */
public class BoxGuacamole {

    //componenti grafici della finestra box guacamole
    private static JFrame frameGuacamole;
    private JPanel boxGuacamole;
    private JLabel ristGuacamole;
    private JButton acquistaGuacamole;
    private JLabel qntDispGuacamole;
    private JLabel nachosGuacamole;
    private static int quantitaDisponibileGuacamole = 6;

    /**
     * Costruttore della schermata BoxGuacamole.
     * Inizializza la finestra grafica, mostra la disponibilità residua di box
     * e gestisce l'acquisto registrando la prenotazione sul database tramite il controller
     * e comunicando all'utente il codice di ritiro univoco.
     *
     * @param frameRistorante il riferimento alla schermata ristoranti per consentire il ritorno indietro
     * @param controller      l'istanza del Controller per effettuare l'acquisto sul database
     */
//costruttore della schermata box guacamole
    public BoxGuacamole(JFrame frameRistorante, Controller controller) {
        frameGuacamole = new JFrame("Guacamole");
        frameGuacamole.setContentPane(boxGuacamole);
        frameGuacamole.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameGuacamole.pack();

        //impostazioni della finestra
        frameGuacamole.setResizable(false);
        frameGuacamole.setSize(450, 450);
        frameGuacamole.setLocationRelativeTo(null);
        frameGuacamole.setVisible(true);

        //ritorno alla schermata dei ristoranti
        ristGuacamole.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ristGuacamole.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frameRistorante.setVisible(true);
                frameGuacamole.setVisible(false);
            }
        });

        //inizializzazione del testo di disponibilita
        qntDispGuacamole.setText("Quantità disponibile: " + quantitaDisponibileGuacamole);

        //gestione click del pulsante acquista
        acquistaGuacamole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantitaDisponibileGuacamole > 0) {
                    quantitaDisponibileGuacamole--;
                    qntDispGuacamole.setText("Quantità disponibile: " + quantitaDisponibileGuacamole);

                    int quantitaPresa = 6 - quantitaDisponibileGuacamole;

                    //salva lacquisto nel db e ottiene il codice generato
                    String codiceRitiro = controller.acquistaBoxDB(5);

                    //aggiorna la schermata di riepilogo prenotazioni
                    Home.getPaginaPrenotazione().aggiornaPrenotazione("Guacamole", quantitaPresa, codiceRitiro);

                    //notifica di conferma con codice di ritiro
                    JOptionPane.showMessageDialog(
                            frameGuacamole,
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
     * Restituisce la finestra principale della schermata Box Guacamole.
     * Permette al menu dei ristoranti di riaprirla senza doverla ricreare.
     *
     * @return il frame grafico della pagina Guacamole
     */
//restituisce il frame della finestra guacamole
    public static JFrame getFrameGuacamole() {
        return frameGuacamole;
    }

    /**
     * Incrementa il contatore delle box disponibili per Guacamole.
     * Viene richiamato quando l'utente annulla una prenotazione esistente.
     */
//incrementa il numero di box disponibili a seguito di annullamento
    public static void aumentaDisponibile() {
        quantitaDisponibileGuacamole++;
    }

    /**
     * Restituisce il numero di box Guacamole attualmente disponibili per l'acquisto.
     *
     * @return la quantità residua di box
     */
//restituisce la quantita attualmente disponibile
    public static int getDisponibile() {
        return quantitaDisponibileGuacamole;
    }

    /**
     * Ricarica il testo dell'etichetta grafica mostrando la quantità aggiornata di box disponibili per Guacamole.
     */
//aggiorna il testo dell etichetta della disponibilita
    public void aggiornaLabelDisponibile() {
        qntDispGuacamole.setText("Quantità disponibile: " + quantitaDisponibileGuacamole);
    }
}