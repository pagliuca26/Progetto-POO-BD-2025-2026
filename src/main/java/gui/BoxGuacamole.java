package gui;

import javax.swing.*;

public class BoxGuacamole {
    private static JFrame frameGuacamole;
    private JPanel boxGuacamole;
    private JLabel ristGuacamole;
    private JButton acquistaGuacamole;

   //costruttore
    public BoxGuacamole(JPanel boxGuacamole, JLabel ristGuacamole, JButton acquistaGuacamole) {

        frameGuacamole = new JFrame("Guacamole");
        frameGuacamole.setContentPane(boxGuacamole);
        frameGuacamole.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameGuacamole.pack();
        frameGuacamole.setVisible(true);

//per la grandezza della finestra
        frameGuacamole.setResizable(false);
        frameGuacamole.setSize(450, 450);
        frameGuacamole.setLocationRelativeTo(null);
        frameGuacamole.setVisible(true);
    }

}
