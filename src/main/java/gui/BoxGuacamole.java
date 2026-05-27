package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoxGuacamole {
    private static JFrame frameGuacamole;
    private JPanel boxGuacamole;
    private JLabel ristGuacamole;
    private JButton acquistaGuacamole;

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
        ristGuacamole.setCursor (new Cursor(Cursor.HAND_CURSOR)) ;

        ristGuacamole.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                frameRistorante.setVisible (true) ;
                frameGuacamole.setVisible(false);

            }
        });

    }
}
