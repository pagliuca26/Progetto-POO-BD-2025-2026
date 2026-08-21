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

    //variabili per salvare le pagine dei ristoranti. All'inizio sono null (vuote) così al primo click creiamo la finestra,
    //mentre i click successivi riaprono quella vecchia senza resettare le box.
    private BoxTokyo tokyo = null;
    private BoxGuacamole guacamole = null;
    private BoxItaliamo italiamo = null;

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
                if (italiamo == null) {
                    italiamo = new BoxItaliamo(frameRistorante, controller);
                } else {
                    BoxItaliamo.getFrameItaliano().setVisible(true);
                }
                italiamo.aggiornaLabelDisponibile();
                frameRistorante.setVisible(false);
            }
        });

        //bottone da ristorante a guacamole
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

        //bottone da ristorante a tokyo
        giapponeseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tokyo == null) {
                    // Se è null, la creiamo per la prima volta
                    tokyo = new BoxTokyo(frameRistorante, controller);
                } else {
                    // Se esiste già, riprendiamo quella vecchia senza resettare le box
                    BoxTokyo.getFrameTokyo().setVisible(true);
                }
                tokyo.aggiornaLabelDisponibile();
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

    //metodo getter per far recuperare alla Home lo stesso frame dei ristoranti
    public static JFrame getFrameRistorante() {
        return frameRistorante;
    }
}