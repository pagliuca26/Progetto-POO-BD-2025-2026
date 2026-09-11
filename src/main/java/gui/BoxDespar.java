package gui;

import controller.Controller;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class BoxDespar {

    //componenti grafici della finestra box despar
    private static JFrame frameDespar;
    private JPanel boxDespar;
    private JButton acquistaDespar;
    private JLabel supDespar;
    private JLabel qntDispDespar;
    private JLabel logoDespar;
    private static int quantitaDisponibileDespar = 9;

    //costruttore della schermata box despar
    public BoxDespar(JFrame frameSupermercato, Controller controller) {
        frameDespar = new JFrame("Despar");
        frameDespar.setContentPane(boxDespar);
        frameDespar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameDespar.pack();

        //impostazioni della finestra
        frameDespar.setResizable(false);
        frameDespar.setSize(450, 450);
        frameDespar.setLocationRelativeTo(null);
        frameDespar.setVisible(true);

        //ritorno alla schermata dei supermercati
        supDespar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        supDespar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frameSupermercato.setVisible(true);
                frameDespar.setVisible(false);
            }
        });

        //inizializzazione del testo di disponibilita
        qntDispDespar.setText("Quantità disponibile: " + quantitaDisponibileDespar);

        //gestione click del pulsante acquista
        acquistaDespar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantitaDisponibileDespar > 0) {
                    quantitaDisponibileDespar--;
                    qntDispDespar.setText("Quantità disponibile: " + quantitaDisponibileDespar);

                    int quantitaPresa = 9 - quantitaDisponibileDespar;

                    //salva lacquisto nel db e ottiene il codice generato
                    String codiceRitiro = controller.acquistaBoxDB(1);

                    //aggiorna la schermata di riepilogo prenotazioni
                    Home.getPaginaPrenotazione().aggiornaPrenotazione("Despar", quantitaPresa, codiceRitiro);

                    //notifica di conferma con codice di ritiro
                    JOptionPane.showMessageDialog(
                            frameDespar,
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

    //restituisce il frame della finestra despar
    public static JFrame getFrameDespar() {
        return frameDespar;
    }

    //incrementa il numero di box disponibili a seguito di annullamento
    public static void aumentaDisponibile() {
        quantitaDisponibileDespar++;
    }

    //restituisce la quantita attualmente disponibile
    public static int getDisponibile() {
        return quantitaDisponibileDespar;
    }

    //aggiorna il testo dell etichetta della disponibilita
    public void aggiornaLabelDisponibile() {
        qntDispDespar.setText("Quantità disponibile: " + quantitaDisponibileDespar);
    }
}