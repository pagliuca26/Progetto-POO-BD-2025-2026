package gui;

import controller.Controller;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class CreaAccount {

    //componenti grafici della finestra di registrazione
    private JTextField creaEmail;
    private JPanel creaAccountPanel;
    private JButton accountCreato;
    private JPasswordField creaPassword;
    private JLabel tornaLogin;
    private JLabel carrelloCreaAcc;
    private JTextField creaNome;
    private JTextField creaCognome;
    private JLabel nome;
    private JLabel cognome;
    private JLabel tornaAccedi;

    //costruttore della schermata di registrazione
    public CreaAccount(JFrame loginFrame, Controller controller) {

        JFrame frame = new JFrame("Crea un nuovo account ;)");
        frame.setContentPane(creaAccountPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        //impostazioni di dimensione e posizione della finestra
        frame.setResizable(false);
        frame.setSize(450, 450);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //gestione pulsante torna al login
        tornaLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tornaLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginFrame.setVisible(true);
                frame.setVisible(false);
            }
        });

        //gestione pulsante crea account con validazione del controller
        accountCreato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String email = creaEmail.getText();
                    String password = new String(creaPassword.getPassword());
                    String nome = creaNome.getText();
                    String cognome = creaCognome.getText();

                    controller.creaUtente(email, password, nome, cognome);
                    JOptionPane.showMessageDialog(null, "Account creato con successo!");

                    loginFrame.setVisible(true);
                    frame.setVisible(false);
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}