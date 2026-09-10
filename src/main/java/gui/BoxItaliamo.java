package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoxItaliamo {
    private static JFrame frameItaliamo;
    private JPanel boxItaliamo;
    private JLabel ristItaliamo;
    private JButton acquistaItaliamo;
    private JLabel qntDispItaliamo;
    private JLabel spaghettiItaliamo;
    private static int quantitàDisponibileItaliamo = 10;

    //costruttore
    public BoxItaliamo(JFrame frameRistorante, Controller controller) {

        frameItaliamo = new JFrame("Italiamo");
        frameItaliamo.setContentPane(boxItaliamo);
        frameItaliamo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameItaliamo.pack();
        frameItaliamo.setVisible(true);

        frameItaliamo.setResizable(false); //non cambia dimensione
        frameItaliamo.setSize(450, 450); //grandezza della finestra
        frameItaliamo.setLocationRelativeTo(null); //finestra si apre al centro
        frameItaliamo.setVisible(true);

        //JLable cliccabile, per tornare alla scelta dei ristoranti
        ristItaliamo.setCursor (new Cursor(Cursor.HAND_CURSOR)) ;

        ristItaliamo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                frameRistorante.setVisible (true) ;
                frameItaliamo.setVisible(false);
            }
        });

        //quantità che diminuisce col bottone acquista
        qntDispItaliamo.setText("Quantità disponibile: " + quantitàDisponibileItaliamo);

        acquistaItaliamo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantitàDisponibileItaliamo > 0) {
                    quantitàDisponibileItaliamo--;

                    qntDispItaliamo.setText("Quantità disponibile: " + quantitàDisponibileItaliamo);

                    int quantitaPresa = 10 - quantitàDisponibileItaliamo;

                    //salva l'acquisto nel db e recupera il codice univoco generato
                    String codiceRitiro = controller.acquistaBoxDB(3);

                    //aggiorna la pagina Prenotazioni passando anche il codice univoco
                    Home.getPaginaPrenotazione().aggiornaPrenotazione("Italiamo", quantitaPresa, codiceRitiro);

                    //mostra il popup di conferma con il codice univoco
                    JOptionPane.showMessageDialog(
                            frameItaliamo,
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
    public static JFrame getFrameItaliano() {
        return frameItaliamo;
    }

    public static void aumentaDisponibile() {
        quantitàDisponibileItaliamo++;
    }

    public static int getDisponibile() {
        return quantitàDisponibileItaliamo;
    }

    public void aggiornaLabelDisponibile() {
        qntDispItaliamo.setText("Quantità disponibile: " + quantitàDisponibileItaliamo);
    }
}