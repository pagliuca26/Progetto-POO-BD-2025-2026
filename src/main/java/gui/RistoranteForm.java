package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;




public class RistoranteForm {
    private static JFrame frameRistorante;
    private JPanel ristorantePanel;
    private JButton italianoButton;
    private JButton messicanoButton;
    private JButton giapponeseButton;
    private JLabel tornaHomeR;

    //costruttore
    public RistoranteForm(JFrame frameHome, Controller controller) {

        frameRistorante = new JFrame("Ristoranti");
        frameRistorante.setContentPane(ristorantePanel);
        frameRistorante.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameRistorante.pack();
        frameRistorante.setVisible(true);

        frameRistorante.setResizable(false); //non cambia dimensione
        frameRistorante.setSize(450, 450); //grandezza della finestra
        frameRistorante.setLocationRelativeTo(null); //finestra si apre al centro
        frameRistorante.setVisible(true);

        //bottone da ristorante a italiamo
        italianoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoxItaliamo italiamo = new BoxItaliamo(frameRistorante, controller);
                frameRistorante.setVisible(false);
            }
        });

        //bottone da ristorante a guacamole
        messicanoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoxGuacamole italiamo = new BoxGuacamole(frameRistorante, controller);
                frameRistorante.setVisible(false);
            }
        });

        //bottone da ristorante a tokyo
        giapponeseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoxTokyo italiamo = new BoxTokyo(frameRistorante, controller);
                frameRistorante.setVisible(false);
            }
        });

        //Jlabel cliccabile, per tornare dalla pagina dei ristoranti alla home
        tornaHomeR.setCursor(new Cursor(Cursor.HAND_CURSOR)); //cambia il cursore

        tornaHomeR.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frameHome.setVisible(true);
                frameRistorante.setVisible(false);
            }
        });

    }
}