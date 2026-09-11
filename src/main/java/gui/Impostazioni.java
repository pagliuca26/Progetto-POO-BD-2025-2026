package gui;

import controller.Controller;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Impostazioni {

    //componenti grafici della finestra impostazioni
    private JFrame frameImpostazioni;
    private JLabel tornaHomeImpostazioni;
    private JLabel impostazioniName;
    private JTextField modificaNomeTextField;
    private JLabel modificaNomeLabel;
    private JTextField modificaCognomeTextField;
    private JLabel modificaCognomeLabel;
    private JTextField modificaEmailTextField;
    private JLabel modificaEmailLabel;
    private JTextField modificaPasswordTextField;
    private JLabel modificaPasswordLabel;
    private JButton salvaModificheButton;
    private JButton eliminaAccountButton;
    private JRadioButton avatarMaschileRadioButton;
    private JRadioButton avatarFemminileRadioButton;
    private JLabel scegliAvatarLabel;
    private JPanel impostazioniPanel;
    private JLabel rotellaImpostazioni;

    //costruttore della schermata impostazioni
    public Impostazioni(JFrame frameHome, Controller controller) {
        frameImpostazioni = new JFrame("Impostazioni");
        frameImpostazioni.setContentPane(impostazioniPanel);
        frameImpostazioni.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameImpostazioni.pack();
        frameImpostazioni.setResizable(false);
        frameImpostazioni.setSize(450, 450);
        frameImpostazioni.setLocationRelativeTo(null);
        frameImpostazioni.setVisible(true);

        //precarica i dati dell utente attualmente loggato
        if (controller.getUtenteAttuale() != null) {
            modificaNomeTextField.setText(controller.getUtenteAttuale().getNome());
            modificaCognomeTextField.setText(controller.getUtenteAttuale().getCognome());
            modificaEmailTextField.setText(controller.getUtenteAttuale().getEmail());
        }

        //gestione pulsante per tornare alla home
        tornaHomeImpostazioni.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tornaHomeImpostazioni.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frameHome.setVisible(true);
                frameImpostazioni.setVisible(false);
            }
        });

        //raggruppamento dei radio button per l avatar
        ButtonGroup gruppoAvatar = new ButtonGroup();
        gruppoAvatar.add(avatarMaschileRadioButton);
        gruppoAvatar.add(avatarFemminileRadioButton);

        //gestione pulsante salva modifiche
        salvaModificheButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //aggiornamento dell avatar
                if (avatarMaschileRadioButton.isSelected()) {
                    controller.setAvatarSelezionato("img/man-avatar.png");
                } else if (avatarFemminileRadioButton.isSelected()) {
                    controller.setAvatarSelezionato("img/woman-avatar.png");
                }

                //recupero dei campi di testo
                String nuovoNome = modificaNomeTextField.getText().trim();
                String nuovoCognome = modificaCognomeTextField.getText().trim();
                String nuovaEmail = modificaEmailTextField.getText().trim();
                String nuovaPassword = modificaPasswordTextField.getText().trim();

                //salvataggio sul database
                boolean salvato = controller.aggiornaDatiUtente(nuovoNome, nuovoCognome, nuovaEmail, nuovaPassword);

                if (salvato) {
                    JOptionPane.showMessageDialog(frameImpostazioni, "Modifiche salvate con successo!");
                    modificaPasswordTextField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frameImpostazioni, "Impostazioni aggiornate!");
                }
            }
        });

        //gestione pulsante elimina account
        eliminaAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int conferma = JOptionPane.showConfirmDialog(
                        frameImpostazioni,
                        "Sei sicuro di voler eliminare definitivamente il tuo account?",
                        "Conferma eliminazione",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (conferma == JOptionPane.YES_OPTION) {
                    boolean rimosso = controller.eliminaAccount();
                    if (rimosso) {
                        JOptionPane.showMessageDialog(frameImpostazioni, "Account eliminato con successo.");
                        frameImpostazioni.dispose();
                        frameHome.dispose();
                        Login finestraLogin = new Login(controller);
                        finestraLogin.mostraFinestra();
                    } else {
                        JOptionPane.showMessageDialog(frameImpostazioni, "Errore durante l'eliminazione dell'account.", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    //restituisce il frame delle impostazioni
    public JFrame getFrameImpostazioni() {
        return frameImpostazioni;
    }
}