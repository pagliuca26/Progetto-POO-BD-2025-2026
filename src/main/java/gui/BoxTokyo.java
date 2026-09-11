package gui;

import controller.Controller;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class BoxTokyo {

    //componenti grafici della finestra box tokyo
    private static JFrame frameTokyo;
    private JPanel boxTokyo;
    private JButton acquistaTokyo;
    private JLabel ristTokyo;
    private JLabel qntDispTokyo;
    private JLabel sushiTokyo;
    private static int quantitaDisponibileTokyo = 7;

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

    //restituisce il frame della finestra tokyo
    public static JFrame getFrameTokyo() {
        return frameTokyo;
    }

    //incrementa il numero di box disponibili a seguito di annullamento
    public static void aumentaDisponibile() {
        quantitaDisponibileTokyo++;
    }

    //restituisce la quantita attualmente disponibile
    public static int getDisponibile() {
        return quantitaDisponibileTokyo;
    }

    //aggiorna il testo dell etichetta della disponibilita
    public void aggiornaLabelDisponibile() {
        qntDispTokyo.setText("Quantità disponibile: " + quantitaDisponibileTokyo);
    }
}