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
    private int quantitàDisponibileItaliamo = 10;

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

        //JLable cliccabile, per tortare alla scelta dei ristoranti
        ristItaliamo.setCursor (new Cursor(Cursor.HAND_CURSOR)) ;

        ristItaliamo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                frameRistorante.setVisible (true) ;
                frameItaliamo.setVisible(false);
            }
        });

        //Quantità che diminuisce col bottone acquista
        qntDispItaliamo.setText("Quantità disponibile: " + quantitàDisponibileItaliamo);

        acquistaItaliamo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantitàDisponibileItaliamo > 0) { //controllo condizionale: verifica se ci sono ancora box disponibili (maggiore di zero)
                    quantitàDisponibileItaliamo--;    //decrementa di 1 il valore della variabile intera che tiene il conto delle box

                    qntDispItaliamo.setText("Quantità disponibile: " + quantitàDisponibileItaliamo); // Aggiorna la scritta DIRETTAMENTE sulla pagina

                    // POP-UP 1: Finestra di successo dell'acquisto
                    JOptionPane.showMessageDialog(null, "Acquisto effettuato con successo!");
                } else {     //se la condizione dell if è falsa (ovvero la quantità è uguale a zero)
                    // POP-UP 2: Finestra di errore: la quantità è 0
                    JOptionPane.showMessageDialog(null, "Errore: Le Box per questo punto vendita sono terminate!",  "Box Terminate",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });



    }
}

