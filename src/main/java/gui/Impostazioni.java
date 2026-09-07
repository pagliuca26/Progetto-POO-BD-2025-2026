package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Impostazioni {
    private static JFrame frameImpostazioni;
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

    private static Impostazioni paginaImpostazioni = null;

    //costruttore
    public Impostazioni(JFrame frameHome, Controller controller) {
        frameImpostazioni = new JFrame("Impostazioni");
        frameImpostazioni.setContentPane(impostazioniPanel);
        frameImpostazioni.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameImpostazioni.pack();
        frameImpostazioni.setVisible(true);

        frameImpostazioni.setResizable(false);
        frameImpostazioni.setSize(450, 450);
        frameImpostazioni.setLocationRelativeTo(null);

        //carica i dati attuali nelle caselle di testo
        if (controller.getUtenteAttuale() != null) {
            modificaNomeTextField.setText(controller.getUtenteAttuale().getNome());
            modificaCognomeTextField.setText(controller.getUtenteAttuale().getCognome());
            modificaEmailTextField.setText(controller.getUtenteAttuale().getEmail());
        }

        //label cliccabile, per passare dalla pagina impostazioni a quella di home
        tornaHomeImpostazioni.setCursor(new Cursor(Cursor.HAND_CURSOR));

        tornaHomeImpostazioni.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frameHome.setVisible(true);
                frameImpostazioni.setVisible(false);
            }
        });

        //creo il gruppo per permettere di selezionare uno o l'altro
        ButtonGroup gruppoAvatar = new ButtonGroup();
        gruppoAvatar.add(avatarMaschileRadioButton);
        gruppoAvatar.add(avatarFemminileRadioButton);

        //gestione del pulsante salva modifiche
        salvaModificheButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //gestione avatar
                if (avatarMaschileRadioButton.isSelected()) {
                    controller.setAvatarSelezionato("img/man-avatar.png");
                } else if (avatarFemminileRadioButton.isSelected()) {
                    controller.setAvatarSelezionato("img/woman-avatar.png");
                }

                //recupero i valori inseriti nei campi di testo
                String nuovoNome = modificaNomeTextField.getText().trim();
                String nuovoCognome = modificaCognomeTextField.getText().trim();
                String nuovaEmail = modificaEmailTextField.getText().trim();
                String nuovaPassword = modificaPasswordTextField.getText().trim();

                //salvataggio nel database e aggiornamento dell'utente
                boolean salvato = controller.aggiornaDatiUtente(nuovoNome, nuovoCognome, nuovaEmail, nuovaPassword);

                if (salvato) {
                    JOptionPane.showMessageDialog(frameImpostazioni, "Modifiche salvate con successo!");
                    //svuota solo la password dopo il salvataggio
                    modificaPasswordTextField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frameImpostazioni, "Impostazioni aggiornate!");
                }
            }
        });

        //gestione del pulsante elimina account
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
                        new Login();
                    } else {
                        JOptionPane.showMessageDialog(frameImpostazioni, "Errore durante l'eliminazione dell'account.", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    public JFrame getFrameImpostazioni() {
        return frameImpostazioni;
    }

    // Creazione del pannello personalizzato con sfondo disegnato
    private void createUIComponents() {
        impostazioniPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                java.net.URL url = getClass().getResource("/img/sfondo_per_impostazioni.png");
                if (url == null) {
                    url = getClass().getResource("/sfondo_per_impostazioni.png");
                }
                if (url != null) {
                    Image bg = new ImageIcon(url).getImage();
                    g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
    }
}