package gui;

import controller.Controller;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * Finestra dedicata alla registrazione di un nuovo utente nel sistema.
 * Gestisce l'interfaccia grafica per l'inserimento dei dati anagrafici e delle credenziali,
 * notificando eventuali errori di validazione tramite eccezioni dedicate e comunicando con il Controller.
 */
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

    /**
     * Costruttore della schermata di registrazione CreaAccount.
     * Configura il JFrame per la visualizzazione del form, gestisce l'evento di ritorno
     * alla schermata di login e associa al pulsante di conferma l'acquisizione dei dati,
     * delegando al Controller la creazione del nuovo utente con gestione delle eccezioni.
     *
     * @param loginFrame il riferimento alla finestra di Login precedente per consentire il ritorno
     * @param controller l'istanza del Controller dell'applicazione che coordina la logica di business
     */
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