package gui;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreaAccount {
    private JTextField creaEmail;
    private JPanel creaAccountPanel;
    private JButton accountCreato;
    private JPasswordField creaPassword;
    private JLabel tornaLogin;
    private JLabel tornaAccedi;

    //costruttore
    public CreaAccount(JFrame loginFrame, Controller controller) {

        JFrame frame = new JFrame("Crea un nuovo account ;)");
        frame.setContentPane(creaAccountPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setResizable(false); //non cambia dimensione
        frame.setLocationRelativeTo(null); //finestra si apre al centro
        frame.setSize(450,450); //grandezza della finestra
        frame.setVisible(true);

        //gestone pulsante torna alla pagina di login
        tornaLogin.setCursor(new Cursor(Cursor.HAND_CURSOR)); //cambia il cursore quando ci passa sopra

        tornaLogin.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                loginFrame.setVisible(true);
                frame.setVisible(false);
            }
        });

        //gestione pulsante crea account
        accountCreato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String email = creaEmail.getText();
                    String password = creaPassword.getText();
                    controller.creaUtente(email, password);
                    JOptionPane.showMessageDialog(null, "Account creato con successo!");

                    //torna alla pagina di login
                    loginFrame.setVisible(true);
                    frame.setVisible(false);
                }
                catch(RuntimeException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


    }
}

