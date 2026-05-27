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

        //size fissa, deve andare prima di setVisiblie
        loginFrame.setResizable(false);
        loginFrame.setSize(450,450);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

        //gestione pulsante account
        login.creaAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login.campoEmail.setText(""); //se l'utente ha scritto qualcosa in questi campi, vengono resettati
                login.campoPassword.setText("");
                CreaAccount creaAccount = new CreaAccount(loginFrame, controller);
                loginFrame.setVisible(false);
            }
        });

        //pulsante accedi
        login.accediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (controller.checkUtente(login.campoEmail.getText(), login.campoPassword.getText())) {
                        JOptionPane.showMessageDialog(null, "Accesso effettuato correttamente.");
                        login.campoEmail.setText("");
                        login.campoPassword.setText("");
                        Home Home = new Home(loginFrame, controller);
                        loginFrame.setVisible(false);
                    }
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }
}

