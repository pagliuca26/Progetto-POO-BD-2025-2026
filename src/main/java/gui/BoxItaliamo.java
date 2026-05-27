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


    }
}

