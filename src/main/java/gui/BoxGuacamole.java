package gui;

import controller.Controller;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class BoxGuacamole {

    //componenti grafici della finestra box guacamole
    private static JFrame frameGuacamole;
    private JPanel boxGuacamole;
    private JLabel ristGuacamole;
    private JButton acquistaGuacamole;
    private JLabel qntDispGuacamole;
    private JLabel nachosGuacamole;
    private static int quantitaDisponibileGuacamole = 6;

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

    //restituisce il frame della finestra guacamole
    public static JFrame getFrameGuacamole() {
        return frameGuacamole;
    }

    //incrementa il numero di box disponibili a seguito di annullamento
    public static void aumentaDisponibile() {
        quantitaDisponibileGuacamole++;
    }

    //restituisce la quantita attualmente disponibile
    public static int getDisponibile() {
        return quantitaDisponibileGuacamole;
    }

    //aggiorna il testo dell etichetta della disponibilita
    public void aggiornaLabelDisponibile() {
        qntDispGuacamole.setText("Quantità disponibile: " + quantitaDisponibileGuacamole);
    }
}