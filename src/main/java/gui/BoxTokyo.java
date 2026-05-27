package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoxTokyo {
    private static JFrame frameTokyo;
    private JPanel boxTokyo;
    private JButton acquistaTokyo;
    private JLabel ristTokyo;

    //costruttore
    public BoxTokyo(JFrame frameRistorante, Controller controller) {

        frameTokyo = new JFrame("Tokyo");
        frameTokyo.setContentPane(boxTokyo);
        frameTokyo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTokyo.pack();
        frameTokyo.setVisible(true);

        frameTokyo.setResizable(false); //non cambia dimensione
        frameTokyo.setSize(450, 450); //grandezza della finestra
        frameTokyo.setLocationRelativeTo(null); //finestra si apre al centro
        frameTokyo.setVisible(true);

        //JLable cliccabile, per tornare alla scelta dei ristoranti
        ristTokyo.setCursor (new Cursor(Cursor.HAND_CURSOR)) ;

        ristTokyo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                frameRistorante.setVisible (true) ;
                frameTokyo.setVisible(false);
            }
        });
    }
}
