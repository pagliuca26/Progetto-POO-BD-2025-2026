package gui;

import controller.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Login {

    //componenti grafici della finestra
    private JFrame loginFrame;
    private JPanel loginFinestra;
    private JTextField campoEmail;
    private JButton accediButton;
    private JButton creaAccountButton;
    private JLabel ominoLogin;
    private JLabel nomeApp;
    private JPasswordField campoPassword;

    //riferimento al controller per la logica di business
    private Controller controller;

    //costruttore della schermata di login
    public Login(Controller controller) {
        this.controller = controller;

        //configurazione della finestra principale
        loginFrame = new JFrame("Login");
        loginFrame.setContentPane(loginFinestra);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.pack();
        loginFrame.setResizable(false);
        loginFrame.setSize(450, 450);
        loginFrame.setLocationRelativeTo(null);

        //gestione click pulsante crea account
        creaAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoEmail.setText("");
                campoPassword.setText("");
                new CreaAccount(loginFrame, controller);
                loginFrame.setVisible(false);
            }
        });

        //gestione click pulsante accedi
        accediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (controller.checkUtente(campoEmail.getText(), new String(campoPassword.getPassword()))) {
                        JOptionPane.showMessageDialog(null, "Accesso effettuato correttamente.");
                        campoEmail.setText("");
                        campoPassword.setText("");
                        new Home(loginFrame, controller);
                        loginFrame.setVisible(false);
                    }
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    //rende visibile la finestra di login
    public void mostraFinestra() {
        loginFrame.setVisible(true);
    }

    //restituisce il frame principale del login
    public JFrame getLoginFrame() {
        return loginFrame;
    }
}