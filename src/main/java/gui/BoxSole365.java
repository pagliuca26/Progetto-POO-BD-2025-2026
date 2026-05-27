package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoxSole365 {
    private static JFrame frameSole365;
    private JPanel boxSole365;
    private JButton acquistaSole365;
    private JLabel supSole365;

    //costruttore
    public BoxSole365(JFrame frameSupermercato, Controller controller) {

        frameSole365= new JFrame("Sole365");
        frameSole365.setContentPane(boxSole365);
        frameSole365.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSole365.pack();
        frameSole365.setVisible(true);

        frameSole365.setResizable(false); //non cambia dimensione
        frameSole365.setSize(450, 450); //grandezza della finestra
        frameSole365.setLocationRelativeTo(null); //finestra si apre al centro
        frameSole365.setVisible(true);

        //JLable cliccabile, per tornare alla scelta dei supemercati
        supSole365.setCursor (new Cursor(Cursor.HAND_CURSOR)) ;

        supSole365.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                frameSupermercato.setVisible (true) ;
                frameSole365.setVisible(false);
            }
        });
    }
}
