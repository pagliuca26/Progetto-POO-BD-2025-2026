package gui;

import controller.Controller;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class BoxSole365 {

    //componenti grafici della finestra box sole365
    private static JFrame frameSole365;
    private JPanel boxSole365;
    private JButton acquistaSole365;
    private JLabel supSole365;
    private JLabel qntDispSole;
    private JLabel logoSole;
    private static int quantitaDisponibileSole = 5;

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

    //restituisce il frame della finestra sole365
    public static JFrame getFrameSole365() {
        return frameSole365;
    }

    //incrementa il numero di box disponibili a seguito di annullamento
    public static void aumentaDisponibile() {
        quantitaDisponibileSole++;
    }

    //restituisce la quantita attualmente disponibile
    public static int getDisponibile() {
        return quantitaDisponibileSole;
    }

    //aggiorna il testo dell etichetta della disponibilita
    public void aggiornaLabelDisponibile() {
        qntDispSole.setText("Quantità disponibile: " + quantitaDisponibileSole);
    }
}