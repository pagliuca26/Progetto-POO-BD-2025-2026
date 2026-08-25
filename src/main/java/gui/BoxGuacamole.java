package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BoxGuacamole {
    private static JFrame frameGuacamole;
    private JPanel boxGuacamole;
    private JLabel ristGuacamole;
    private JButton acquistaGuacamole;
    private JLabel qntDispGuacamole;
    private static int quantitàDisponibileGuacamole = 6;

    //costruttore
    public BoxGuacamole(JFrame frameRistorante, Controller controller) {

        frameGuacamole = new JFrame("Guacamole");
        frameGuacamole.setContentPane(boxGuacamole);
        frameGuacamole.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameGuacamole.pack();
        frameGuacamole.setVisible(true);

        frameGuacamole.setResizable(false); //non cambia dimensione
        frameGuacamole.setSize(450, 450);//grandezza della finestra
        frameGuacamole.setLocationRelativeTo(null);//finestra si apre al centro
        frameGuacamole.setVisible(true);

        //JLable cliccabile, per tortare alla scelta dei ristoranti
        ristGuacamole.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ristGuacamole.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frameRistorante.setVisible(true);
                frameGuacamole.setVisible(false);
            }
        });

        //Quantità che diminuisce col bottone acquista
        qntDispGuacamole.setText("Quantità disponibile: " + quantitàDisponibileGuacamole);

        acquistaGuacamole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantitàDisponibileGuacamole > 0) { //controllo condizionale: verifica se ci sono ancora box disponibili (maggiore di zero)
                    quantitàDisponibileGuacamole--;    //decrementa di 1 il valore della variabile intera che tiene il conto delle box

                    qntDispGuacamole.setText("Quantità disponibile: " + quantitàDisponibileGuacamole); // Aggiorna la scritta DIRETTAMENTE sulla pagina

                    int quantitaPresa = 6 - quantitàDisponibileGuacamole;
                    Home.getPaginaPrenotazione().aggiornaPrenotazione("Guacamole", quantitaPresa);


                    //POP-UP 1: Finestra di successo dell'acquisto
                    JOptionPane.showMessageDialog(null, "Acquisto effettuato con successo!");
                } else {     //se la condizione dell if è falsa (ovvero la quantità è uguale a zero)
                    //POP-UP 2: Finestra di errore: la quantità è 0
                    JOptionPane.showMessageDialog(null, "Errore: Le Box per questo punto vendita sono terminate!",  "Box Terminate",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    //metodo getter per accedere al frame privato ed evitare il reset dei dati
    public static JFrame getFrameGuacamole () { return frameGuacamole; }

    public static void aumentaDisponibile() { quantitàDisponibileGuacamole++; }

    public static int getDisponibile() {
        return quantitàDisponibileGuacamole;
    }

    public void aggiornaLabelDisponibile() {
        qntDispGuacamole.setText("Quantità disponibile: " + quantitàDisponibileGuacamole);
    }

}

