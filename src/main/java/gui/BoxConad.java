package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoxConad {
    private static JFrame frameConad;
    private JPanel boxConad;
    private JPanel boxConadd;
    private JButton acquistaConad;
    private JLabel supConad;

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

    }
}
