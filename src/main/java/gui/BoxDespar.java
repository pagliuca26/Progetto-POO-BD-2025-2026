package gui;

import javax.swing.*;

public class BoxDespar {
    private static JFrame frameDespar;
    private JPanel boxDespar;
    private JButton acquistaDespar;
    private JLabel supDespar;

    //costruttore
    public BoxDespar(JPanel boxDespar, JButton acquistaDespar, JLabel supDespar) {

        frameDespar = new JFrame("Despar");
        frameDespar.setContentPane(boxDespar);
        frameDespar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameDespar.pack();
        frameDespar.setVisible(true);

//per la grandezza della finestra
        frameDespar.setResizable(false);
        frameDespar.setSize(450, 450);
        frameDespar.setLocationRelativeTo(null);
        frameDespar.setVisible(true);
    }
}
