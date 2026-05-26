package gui;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CreaAccount {

    private JTextField inserisciNome;
    private JTextField inserisciEmail;
    private JPanel creaAccountPanel;
    private JTextField campoCognome;
    private JPasswordField campoPassword;

    private JButton accountCreato;
    private JLabel tornaAccedi;

    public CreaAccount(JFrame loginFrame, Controller controller) {


        JFrame frame = new JFrame("Crea Account");
        frame.setContentPane(creaAccountPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); //size fissa
        frame.setSize(400,325);
        frame.setVisible(true);

        //bottone crea account per tornare al login,nel costruttore
        accountCreato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                loginFrame.setVisible(true);
            }
        });




    }
}

