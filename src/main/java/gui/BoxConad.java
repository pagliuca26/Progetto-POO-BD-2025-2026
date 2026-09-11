package gui;

import controller.Controller;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class BoxConad {

    //componenti grafici della finestra box conad
    private static JFrame frameConad;
    private JPanel boxConad;
    private JPanel boxConadd;
    private JButton acquistaConad;
    private JLabel supConad;
    private JLabel qntDispConad;
    private static int quantitaDisponibileConad = 7;

    //costruttore della schermata box conad
    public BoxConad(JFrame frameSupermercato, Controller controller) {
        frameConad = new JFrame("Conad");
        frameConad.setContentPane(boxConad);
        frameConad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameConad.pack();

        //impostazioni della finestra
        frameConad.setResizable(false);
        frameConad.setSize(450, 450);
        frameConad.setLocationRelativeTo(null);
        frameConad.setVisible(true);

        //ritorno alla schermata dei supermercati
        supConad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        supConad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frameSupermercato.setVisible(true);
                frameConad.setVisible(false);
            }
        });

        //inizializzazione del testo di disponibilita
        qntDispConad.setText("Quantità disponibile: " + quantitaDisponibileConad);

        //gestione click del pulsante acquista
        acquistaConad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantitaDisponibileConad > 0) {
                    quantitaDisponibileConad--;
                    qntDispConad.setText("Quantità disponibile: " + quantitaDisponibileConad);

                    int quantitaPresa = 7 - quantitaDisponibileConad;

                    //salva lacquisto nel db e ottiene il codice generato
                    String codiceRitiro = controller.acquistaBoxDB(2);

                    //aggiorna la schermata di riepilogo prenotazioni
                    Home.getPaginaPrenotazione().aggiornaPrenotazione("Conad", quantitaPresa, codiceRitiro);

                    //notifica di conferma con codice di ritiro
                    JOptionPane.showMessageDialog(
                            frameConad,
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

    //restituisce il frame della finestra conad
    public static JFrame getFrameConad() {
        return frameConad;
    }

    //incrementa il numero di box disponibili a seguito di annullamento
    public static void aumentaDisponibile() {
        quantitaDisponibileConad++;
    }

    //restituisce la quantita attualmente disponibile
    public static int getDisponibile() {
        return quantitaDisponibileConad;
    }

    //aggiorna il testo dell etichetta della disponibilita
    public void aggiornaLabelDisponibile() {
        qntDispConad.setText("Quantità disponibile: " + quantitaDisponibileConad);
    }
}