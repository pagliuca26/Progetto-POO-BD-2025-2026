package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Supermercato {
    private static JFrame frameSupermercato;
    private JPanel supermercatoPanel;
    private JButton conadButton;
    private JButton sole365Button;
    private JButton desparButton;
    private JLabel tornaHome;


    //costruttore
    public Supermercato(JPanel supermercatoPanel, JButton conadButton, JButton sole365Button, JButton desparButton) {
        frameSupermercato = new JFrame("Home");
        frameSupermercato.setContentPane(supermercatoPanel);
        frameSupermercato.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSupermercato.pack();
        frameSupermercato.setVisible(true);

        //per la grandezza della finestra
        frameSupermercato.setResizable(false);
        frameSupermercato.setSize(400, 325);
        frameSupermercato.setLocationRelativeTo(null);
        frameSupermercato.setVisible(true);

        tornaHome.setCursor (new Cursor(Cursor.HAND_CURSOR)); //cambia il cursore


        //DA RIVEDERE PER IL PULSANTE JLABEL DA SUPERMERCATO A HOME
        //tornaHome.addMouseListener(new MouseAdapter() {
         //   @Override
           // public void mouseClicked (MouseEvent e) {
                //frameHome.setVisible(true);
              //  frameSupermercato.setVisible(false);
        //    }
       // }); modifica 



    }


}
