package gui;

import javax.swing.*;

public class BoxSole365 {
    private static JFrame frameSole365;
    private JPanel boxSole365;
    private JButton acquistaSole365;
    private JLabel supSole365;

    //costruttore
    public BoxSole365(JPanel boxSole365, JButton acquistaSole365, JLabel supSole365) {

        frameSole365= new JFrame("Sole365");
        frameSole365.setContentPane(boxSole365);
        frameSole365.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSole365.pack();
        frameSole365.setVisible(true);

        //per la grandezza della finestra
        frameSole365.setResizable(false);
        frameSole365.setSize(450, 450);
        frameSole365.setLocationRelativeTo(null);
        frameSole365.setVisible(true);
    }
}
