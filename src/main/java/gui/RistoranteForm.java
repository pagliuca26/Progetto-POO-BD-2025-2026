package gui;

import controller.Controller;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class RistoranteForm {

    //componenti grafici della schermata ristoranti
    private static JFrame frameRistorante;
    private JPanel ristorantePanel;
    private JButton italianoButton;
    private JButton messicanoButton;
    private JButton giapponeseButton;
    private JLabel tornaHomeR;
    private JLabel piattoRistoranti;

    //istanze delle schermate box per preservare lo stato delle disponibilita
    private BoxTokyo tokyo = null;
    private BoxGuacamole guacamole = null;
    private BoxItaliamo italiamo = null;

    //costruttore della schermata selezione ristoranti
    public RistoranteForm(JFrame frameHome, Controller controller) {
        frameRistorante = new JFrame("Ristoranti");
        frameRistorante.setContentPane(ristorantePanel);
        frameRistorante.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameRistorante.pack();

        //impostazioni della finestra
        frameRistorante.setResizable(false);
        frameRistorante.setSize(450, 450);
        frameRistorante.setLocationRelativeTo(null);
        frameRistorante.setVisible(true);

        //navigazione verso box ristorante italiano
        italianoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (italiamo == null) {
                    italiamo = new BoxItaliamo(frameRistorante, controller);
                } else {
                    BoxItaliamo.getFrameItaliano().setVisible(true);
                }
                italiamo.aggiornaLabelDisponibile();
                frameRistorante.setVisible(false);
            }
        });

        //navigazione verso box ristorante messicano
        messicanoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (guacamole == null) {
                    guacamole = new BoxGuacamole(frameRistorante, controller);
                } else {
                    BoxGuacamole.getFrameGuacamole().setVisible(true);
                }
                guacamole.aggiornaLabelDisponibile();
                frameRistorante.setVisible(false);
            }
        });

        //navigazione verso box ristorante giapponese
        giapponeseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tokyo == null) {
                    tokyo = new BoxTokyo(frameRistorante, controller);
                } else {
                    BoxTokyo.getFrameTokyo().setVisible(true);
                }
                tokyo.aggiornaLabelDisponibile();
                frameRistorante.setVisible(false);
            }
        });

        //ritorno alla schermata home
        tornaHomeR.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tornaHomeR.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frameHome.setVisible(true);
                frameRistorante.setVisible(false);
            }
        });
    }

    //restituisce il frame della schermata ristoranti
    public static JFrame getFrameRistorante() {
        return frameRistorante;
    }
}