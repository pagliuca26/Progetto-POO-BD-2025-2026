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

       //per la grandezza della finestra
        frameRistorante.setResizable(false);
        frameRistorante.setSize(450, 450);
        frameRistorante.setLocationRelativeTo(null);
        frameRistorante.setVisible(true);

        //Jlabel cliccabile, per tornare dalla pagina dei ristoranti alla home
        tornaHomeR.setCursor (new Cursor(Cursor.HAND_CURSOR)); //cambia il cursore

        tornaHomeR.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        frameHome.setVisible (true) ;
                     frameRistorante.setVisible (false);
                     }
                });
    }

                }