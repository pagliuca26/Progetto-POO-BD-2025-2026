package gui;

import controller.Controller;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * Schermata di dettaglio e acquisto per le box del supermercato Sole365.
 * Mostra le box rimaste a disposizione e gestisce la prenotazione,
 * aggiornando la disponibilità e salvando l'ordine tramite il Controller.
 */
public class BoxSole365 {

    //componenti grafici della finestra box sole365
    private static JFrame frameSole365;
    private JPanel boxSole365;
    private JButton acquistaSole365;
    private JLabel supSole365;
    private JLabel qntDispSole;
    private JLabel logoSole;
    private static int quantitaDisponibileSole = 5;

    /**
     * Costruttore della schermata BoxSole365.
     * Inizializza l'interfaccia grafica, visualizza la disponibilità residua di box
     * e gestisce l'acquisto memorizzando la prenotazione sul database e fornendo
     * il codice di ritiro all'utente.
     *
     * @param frameSupermercato il frame del menu supermercati per tornare indietro
     * @param controller        l'istanza del Controller per gestire l'acquisto sul database
     */
//costruttore della schermata box sole365
    public BoxSole365(JFrame frameSupermercato, Controller controller) {
        frameSole365 = new JFrame("Sole365");
        frameSole365.setContentPane(boxSole365);
        frameSole365.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSole365.pack();

        //impostazioni della finestra
        frameSole365.setResizable(false);
        frameSole365.setSize(450, 450);
        frameSole365.setLocationRelativeTo(null);
        frameSole365.setVisible(true);

        //ritorno alla schermata dei supermercati
        supSole365.setCursor(new Cursor(Cursor.HAND_CURSOR));
        supSole365.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frameSupermercato.setVisible(true);
                frameSole365.setVisible(false);
            }
        });

        //inizializzazione del testo di disponibilita
        qntDispSole.setText("Quantità disponibile: " + quantitaDisponibileSole);

        //gestione click del pulsante acquista
        acquistaSole365.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantitaDisponibileSole > 0) {
                    quantitaDisponibileSole--;
                    qntDispSole.setText("Quantità disponibile: " + quantitaDisponibileSole);

                    int quantitaPresa = 5 - quantitaDisponibileSole;

                    //salva lacquisto nel db con id 3 e ottiene il codice generato
                    String codiceRitiro = controller.acquistaBoxDB(3);

                    //aggiorna la schermata di riepilogo prenotazioni
                    Home.getPaginaPrenotazione().aggiornaPrenotazione("Sole365", quantitaPresa, codiceRitiro);

                    //notifica di conferma con codice di ritiro
                    JOptionPane.showMessageDialog(
                            frameSole365,
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
     * Restituisce la finestra principale della schermata Box Sole365.
     * Permette alla schermata dei supermercati di renderla visibile senza ricrearla.
     *
     * @return il frame grafico della pagina Sole365
     */
//restituisce il frame della finestra sole365
    public static JFrame getFrameSole365() {
        return frameSole365;
    }

    /**
     * Incrementa il contatore delle box disponibili per Sole365.
     * Viene richiamato quando l'utente annulla una prenotazione esistente.
     */
//incrementa il numero di box disponibili a seguito di annullamento
    public static void aumentaDisponibile() {
        quantitaDisponibileSole++;
    }

    /**
     * Restituisce il numero di box Sole365 attualmente disponibili per l'acquisto.
     *
     * @return la quantità residua di box
     */
//restituisce la quantita attualmente disponibile
    public static int getDisponibile() {
        return quantitaDisponibileSole;
    }

    /**
     * Ricarica il testo dell'etichetta grafica mostrando la quantità aggiornata di box disponibili per Sole365.
     */
//aggiorna il testo dell etichetta della disponibilita
    public void aggiornaLabelDisponibile() {
        qntDispSole.setText("Quantità disponibile: " + quantitaDisponibileSole);
    }
}