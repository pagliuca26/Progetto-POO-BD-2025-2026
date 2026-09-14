package gui;

import controller.Controller;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * Schermata di dettaglio e acquisto per le box del ristorante giapponese Tokyo.
 * Mostra le box rimaste a disposizione e gestisce la prenotazione,
 * aggiornando la disponibilità e salvando l'ordine tramite il Controller.
 */
public class BoxTokyo {

    //componenti grafici della finestra box tokyo
    private static JFrame frameTokyo;
    private JPanel boxTokyo;
    private JButton acquistaTokyo;
    private JLabel ristTokyo;
    private JLabel qntDispTokyo;
    private JLabel sushiTokyo;
    private static int quantitaDisponibileTokyo = 7;

    /**
     * Costruttore della schermata BoxTokyo.
     * Inizializza l'interfaccia grafica, visualizza la disponibilità residua di box
     * e gestisce l'acquisto memorizzando la prenotazione sul database e fornendo
     * il codice di ritiro all'utente.
     *
     * @param frameRistorante il frame del menu ristoranti per consentire il ritorno indietro
     * @param controller      l'istanza del Controller per effettuare l'acquisto sul database
     */
//costruttore della schermata box tokyo
    public BoxTokyo(JFrame frameRistorante, Controller controller) {
        frameTokyo = new JFrame("Tokyo");
        frameTokyo.setContentPane(boxTokyo);
        frameTokyo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTokyo.pack();

        //impostazioni della finestra
        frameTokyo.setResizable(false);
        frameTokyo.setSize(450, 450);
        frameTokyo.setLocationRelativeTo(null);
        frameTokyo.setVisible(true);

        //ritorno alla schermata dei ristoranti
        ristTokyo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ristTokyo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frameRistorante.setVisible(true);
                frameTokyo.setVisible(false);
            }
        });

        //inizializzazione del testo di disponibilita
        qntDispTokyo.setText("Quantità disponibile: " + quantitaDisponibileTokyo);

        //gestione click del pulsante acquista
        acquistaTokyo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantitaDisponibileTokyo > 0) {
                    quantitaDisponibileTokyo--;
                    qntDispTokyo.setText("Quantità disponibile: " + quantitaDisponibileTokyo);

                    int quantitaPresa = 7 - quantitaDisponibileTokyo;

                    //salva lacquisto nel db con id 6 e ottiene il codice generato
                    String codiceRitiro = controller.acquistaBoxDB(6);

                    //aggiorna la schermata di riepilogo prenotazioni
                    Home.getPaginaPrenotazione().aggiornaPrenotazione("Tokyo", quantitaPresa, codiceRitiro);

                    //notifica di conferma con codice di ritiro
                    JOptionPane.showMessageDialog(
                            frameTokyo,
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
     * Restituisce la finestra principale della schermata Box Tokyo.
     * Permette alla schermata dei ristoranti di renderla visibile senza doverla ricreare.
     *
     * @return il frame grafico della pagina Tokyo
     */
//restituisce il frame della finestra tokyo
    public static JFrame getFrameTokyo() {
        return frameTokyo;
    }

    /**
     * Incrementa il contatore delle box disponibili per Tokyo.
     * Viene richiamato quando l'utente annulla una prenotazione esistente.
     */
//incrementa il numero di box disponibili a seguito di annullamento
    public static void aumentaDisponibile() {
        quantitaDisponibileTokyo++;
    }

    /**
     * Restituisce il numero di box Tokyo attualmente disponibili per l'acquisto.
     *
     * @return la quantità residua di box
     */
//restituisce la quantita attualmente disponibile
    public static int getDisponibile() {
        return quantitaDisponibileTokyo;
    }

    /**
     * Ricarica il testo dell'etichetta grafica mostrando la quantità aggiornata di box disponibili per Tokyo.
     */
//aggiorna il testo dell etichetta della disponibilita
    public void aggiornaLabelDisponibile() {
        qntDispTokyo.setText("Quantità disponibile: " + quantitaDisponibileTokyo);
    }
}