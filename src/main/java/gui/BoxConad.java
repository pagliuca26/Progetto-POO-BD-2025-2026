package gui;

import javax.swing.*;

public class BoxConad {
    private static JFrame frameConad;
    private JPanel boxConad;
    private JPanel boxConadd;
    private JButton acquistaConad;
    private JLabel supConad;

    //costruttore
    public BoxConad(JPanel boxConad, JPanel boxConadd, JButton acquistaConad, JLabel supConad) {

        frameConad = new JFrame("Conad");
        frameConad.setContentPane(boxConad);
        frameConad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameConad.pack();
        frameConad.setVisible(true);

//per la grandezza della finestra
        frameConad.setResizable(false);
        frameConad.setSize(450, 450);
        frameConad.setLocationRelativeTo(null);
        frameConad.setVisible(true);
    }
}
