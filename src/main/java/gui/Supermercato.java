package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        frameSupermercato.setResizable(false); //non cambia dimensione
        frameSupermercato.setSize(450, 450); //grandezza della finestra
        frameSupermercato.setLocationRelativeTo(null); //finestra si apre al centro
        frameSupermercato.setVisible(true);

        //bottone da supermercato a conad
        conadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoxConad conad = new BoxConad(frameSupermercato, controller);
                frameSupermercato.setVisible(false);
            }
        });

        //bottone da ristorante a sole365
        sole365Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoxSole365 sole365 = new BoxSole365(frameSupermercato, controller);
                frameSupermercato.setVisible(false);
            }
        });

        //bottone da ristorante a despar
        desparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoxDespar despar = new BoxDespar(frameSupermercato, controller);
                frameSupermercato.setVisible(false);
            }
        });

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
