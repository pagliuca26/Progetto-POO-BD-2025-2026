package gui;

import controller.Controller;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class BoxItaliamo {

    //componenti grafici della finestra box italiamo
    private static JFrame frameItaliamo;
    private JPanel boxItaliamo;
    private JLabel ristItaliamo;
    private JButton acquistaItaliamo;
    private JLabel qntDispItaliamo;
    private JLabel spaghettiItaliamo;
    private static int quantitaDisponibileItaliamo = 10;

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
                    String codiceRitiro = controller.acquistaBoxDB(4);

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

    //restituisce il frame della finestra italiamo
    public static JFrame getFrameItaliano() {
        return frameItaliamo;
    }

    //incrementa il numero di box disponibili a seguito di annullamento
    public static void aumentaDisponibile() {
        quantitaDisponibileItaliamo++;
    }

    //restituisce la quantita attualmente disponibile
    public static int getDisponibile() {
        return quantitaDisponibileItaliamo;
    }

    //aggiorna il testo dell etichetta della disponibilita
    public void aggiornaLabelDisponibile() {
        qntDispItaliamo.setText("Quantità disponibile: " + quantitaDisponibileItaliamo);
    }
}