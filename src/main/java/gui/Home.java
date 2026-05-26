package gui;

import controller.Controller;
import model.Ristorante;

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
    private static JFrame frameHome;
    private Controller controller;

    public Home(JFrame loginFrame, Controller controller) {
        frameHome = new JFrame("Home");
        frameHome.setContentPane(homePanel);
        frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameHome.pack();
        frameHome.setVisible(true);

        frameHome.setResizable(false);
        frameHome.setSize(400, 325);
        frameHome.setLocationRelativeTo(null);
        frameHome.setVisible(true);


         // JLable cliccabile
        returnLogin.setCursor (new Cursor(Cursor.HAND_CURSOR)) ;

        returnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                loginFrame.setVisible (true) ;
                frameHome.setVisible (false);

            }
        }) ;




               //da rivedere
                //ristoranteButton.addActionListener(new ActionListener() {

                        //    @Override
                           // public void actionPerformed(ActionEvent e) {
                              //  Ristorante ristoranteForm  = new Ristorante(frameHome,controller);
                              //  frameHome.setVisible(false);



                            }
                      //  });
    }

//}









