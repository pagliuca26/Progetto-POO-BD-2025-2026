package gui;

import javax.swing.*;

public class BoxTokyo {
    private static JFrame frameTokyo;
    private JPanel boxTokyo;
    private JButton acquistaTokyo;
    private JLabel ristTokyo;

    //costruttore
    public BoxTokyo(JPanel boxTokyo, JButton acquistaTokyo) {

        frameTokyo = new JFrame("Tokyo");
        frameTokyo.setContentPane(boxTokyo);
        frameTokyo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTokyo.pack();
        frameTokyo.setVisible(true);

//per la grandezza della finestra
        frameTokyo.setResizable(false);
        frameTokyo.setSize(450, 450);
        frameTokyo.setLocationRelativeTo(null);
        frameTokyo.setVisible(true);
    }
}
