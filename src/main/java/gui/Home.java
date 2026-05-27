package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Home {
    private JPanel homePanel;
    private JButton ristoranteButton;
    private JButton supermercatoButton;
    private JLabel returnLogin;
    private JButton preferitiHome;
    private JButton impHome;
    private JFrame frameHome;
    private Controller controller;

    //costruttore
    public Home(JFrame loginFrame, Controller controller) {
        frameHome = new JFrame("Home");
        frameHome.setContentPane(homePanel);
        frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameHome.pack();
        frameHome.setVisible(true);

        frameHome.setResizable(false); //non cambia dimensione
        frameHome.setSize(450, 450); //grandezza della finestra
        frameHome.setLocationRelativeTo(null); //finestra si apre al centro
        frameHome.setVisible(true);


        //JLable cliccabile, per passare dalla pagina home a quella di login
        returnLogin.setCursor (new Cursor(Cursor.HAND_CURSOR)) ;

        returnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                loginFrame.setVisible (true) ;
                frameHome.setVisible (false);

            }
        });

        //bottone dalla home al ristorante
        ristoranteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RistoranteForm ristorante = new RistoranteForm(frameHome, controller);
                frameHome.setVisible(false);
            }
        });

        //bottone dalla home al supermercato
        supermercatoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Supermercato supermercato = new Supermercato (frameHome, controller);
                frameHome.setVisible(false);
            }
        });

    }
}









