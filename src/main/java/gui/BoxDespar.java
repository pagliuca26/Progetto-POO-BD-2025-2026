package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BoxDespar {
    private static JFrame frameDespar;
    private JPanel boxDespar;
    private JButton acquistaDespar;
    private JLabel supDespar;
    private JLabel qntDispDespar;
    private int quantitàDisponibileDespar = 9;

    //costruttore
    public BoxDespar(JFrame frameSupermercato, Controller controller) {

        frameDespar = new JFrame("Despar");
        frameDespar.setContentPane(boxDespar);
        frameDespar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameDespar.pack();
        frameDespar.setVisible(true);

        frameDespar.setResizable(false); //non cambia dimensione
        frameDespar.setSize(450, 450); //grandezza della finestra
        frameDespar.setLocationRelativeTo(null); //finestra si apre al centro
        frameDespar.setVisible(true);

        //JLable cliccabile, per tortare alla scelta dei supemercati
        supDespar.setCursor (new Cursor(Cursor.HAND_CURSOR)) ;

        supDespar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                frameSupermercato.setVisible (true) ;
                frameDespar.setVisible(false);
            }
        });

        //Quantità che diminuisce col bottone acquista
        qntDispDespar.setText("Quantità disponibile: " + quantitàDisponibileDespar);

        acquistaDespar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantitàDisponibileDespar > 0) { //controllo condizionale: verifica se ci sono ancora box disponibili (maggiore di zero)
                    quantitàDisponibileDespar--;    //decrementa di 1 il valore della variabile intera che tiene il conto delle box

                    qntDispDespar.setText("Quantità disponibile: " + quantitàDisponibileDespar); // Aggiorna la scritta DIRETTAMENTE sulla pagina

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

    //metodo getter per accedere al frame privato ed evitare il reset dei dati
    public static JFrame getFrameDespar() {
        return frameDespar;
    }
}
