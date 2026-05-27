package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoxDespar {
    private static JFrame frameDespar;
    private JPanel boxDespar;
    private JButton acquistaDespar;
    private JLabel supDespar;

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
    }
}
