package gui;

import controller.Controller;

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
    private JLabel tornaHomeS;


    //costruttore
    public Supermercato(JFrame homeFrame, Controller controller) {
        frameSupermercato = new JFrame("Supermercati");
        frameSupermercato.setContentPane(supermercatoPanel);
        frameSupermercato.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSupermercato.pack();
        frameSupermercato.setVisible(true);

        //per la grandezza della finestra
        frameSupermercato.setResizable(false);
        frameSupermercato.setSize(450, 450);
        frameSupermercato.setLocationRelativeTo(null);
        frameSupermercato.setVisible(true);

        //Jlabel cliccabile, per tornare dalla pagina dei supermercati alla home
        tornaHomeS.setCursor (new Cursor(Cursor.HAND_CURSOR)); //cambia il cursore

        tornaHomeS.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                homeFrame.setVisible (true) ;
                frameSupermercato.setVisible (false);

            }
        });

    }


}
