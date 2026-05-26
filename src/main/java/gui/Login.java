package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private static JFrame loginFrame;
    private JPanel loginFinestra;
    private JTextField campoEmail;
    private JButton accediButton;
    private JButton creaAccountButton;
    private JLabel ominoLogin;
    private JLabel nomeApp;
    private JPasswordField campoPassword;


    public static void main(String[] args) {
        Controller controller= new Controller();
        Login login = new Login();
        loginFrame = new JFrame("Login");
        loginFrame.setContentPane(login.loginFinestra);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.pack();

        //per avviare bisogna andare su login quindi qui

        //size fissa, deve andare prima di setVisiblie
        loginFrame.setResizable(false);
        loginFrame.setSize(400,400);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

        //gestione pulsante account
        login.creaAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreaAccount creaAccount = new CreaAccount(loginFrame, controller);
                loginFrame.setVisible(false);
            }
        });

        //pulsante accedi
        login.accediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home Home = new Home(loginFrame, controller);
                loginFrame.setVisible(false);
            }
        });


    }
}

