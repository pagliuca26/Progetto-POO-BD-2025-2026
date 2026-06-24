package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoxConad {
    private static JFrame frameConad;
    private JPanel boxConad;
    private JPanel boxConadd;
    private JButton acquistaConad;
    private JLabel supConad;
    private JLabel qntDispConad;
    private int quantitàDisponibileConad = 7;

    //costruttore
    public BoxConad(JFrame frameSupermercato, Controller controller) {

        frameConad = new JFrame("Conad");
        frameConad.setContentPane(boxConad);
        frameConad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameConad.pack();
        frameConad.setVisible(true);

        frameConad.setResizable(false); //non cambia dimensione
        frameConad.setSize(450, 450);//grandezza della finestra
        frameConad.setLocationRelativeTo(null);//finestra si apre al centro
        frameConad.setVisible(true);

        //JLable cliccabile, per tortare alla scelta dei supemercati
        supConad.setCursor (new Cursor(Cursor.HAND_CURSOR)) ;

        supConad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                frameSupermercato.setVisible (true) ;
                frameConad.setVisible(false);
            }
        });

        //quantità che diminuisce col bottone acquista
        qntDispConad.setText("Quantità disponibile: " + quantitàDisponibileConad);

        acquistaConad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantitàDisponibileConad > 0) { //controllo condizionale: verifica se ci sono ancora box disponibili (maggiore di zero)
                    quantitàDisponibileConad--;    //decrementa di 1 il valore della variabile intera che tiene il conto delle box

                    qntDispConad.setText("Quantità disponibile: " + quantitàDisponibileConad); // Aggiorna la scritta DIRETTAMENTE sulla pagina

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


