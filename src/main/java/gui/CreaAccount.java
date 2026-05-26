package gui;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CreaAccount {

    private JTextField creaNome;
    private JTextField creaEmail;
    private JPanel creaAccountPanel;
    private JTextField creaCognome;

    private JButton accountCreato;
    private JPasswordField creaPassword;
    private JLabel tornaLogin;
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


        // rendere il Jlabel cliccabile
        tornaLogin.setCursor (new Cursor(Cursor.HAND_CURSOR)); //cambia il cursore

        tornaLogin.addMouseListener(new MouseAdapter() {
        @Override
            public void mouseClicked (MouseEvent e) {
            loginFrame.setVisible(true);
            frame.setVisible(false);
        }
        });



    }
}

