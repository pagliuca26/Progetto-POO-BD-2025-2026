package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoxSole365 {
    private static JFrame frameSole365;
    private JPanel boxSole365;
    private JButton acquistaSole365;
    private JLabel supSole365;
    private JLabel qntDispSole;
    private JLabel logoSole;
    private static int quantitàDisponibileSole = 5;

    //costruttore
    public BoxSole365(JFrame frameSupermercato, Controller controller) {

        frameSole365 = new JFrame("Sole365");
        frameSole365.setContentPane(boxSole365);
        frameSole365.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSole365.pack();
        frameSole365.setVisible(true);

        frameSole365.setResizable(false); //non cambia dimensione
        frameSole365.setSize(450, 450); //grandezza della finestra
        frameSole365.setLocationRelativeTo(null); //finestra si apre al centro
        frameSole365.setVisible(true);

        //JLable cliccabile, per tornare alla scelta dei supermercati
        supSole365.setCursor(new Cursor(Cursor.HAND_CURSOR));

        supSole365.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frameSupermercato.setVisible(true);
                frameSole365.setVisible(false);
            }
        });

        //Quantità che diminuisce col bottone acquista
        qntDispSole.setText("Quantità disponibile: " + quantitàDisponibileSole);

        acquistaSole365.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantitàDisponibileSole > 0) {
                    quantitàDisponibileSole--;

                    qntDispSole.setText("Quantità disponibile: " + quantitàDisponibileSole);

                    int quantitaPresa = 5 - quantitàDisponibileSole;

                    //salva l'acquisto nel db e recupera il codice univoco generato
                    String codiceRitiro = controller.acquistaBoxDB(4);

                    //aggiorna la pagina Prenotazioni passando anche il codice univoco
                    Home.getPaginaPrenotazione().aggiornaPrenotazione("Sole365", quantitaPresa, codiceRitiro);

                    //mostra il popup di conferma con il codice univoco
                    JOptionPane.showMessageDialog(
                            frameSole365,
                            "Acquisto effettuato con successo!\nCodice di ritiro: " + codiceRitiro,
                            "Prenotazione Confermata",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(null, "Errore: Le Box per questo punto vendita sono terminate!",  "Box Terminate",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    //metodo getter per accedere al frame privato ed evitare il reset dei dati
    public static JFrame getFrameSole365() {
        return frameSole365;
    }

    public static void aumentaDisponibile() {
        quantitàDisponibileSole++;
    }

    public static int getDisponibile() {
        return quantitàDisponibileSole;
    }

    public void aggiornaLabelDisponibile() {
        qntDispSole.setText("Quantità disponibile: " + quantitàDisponibileSole);
    }


}