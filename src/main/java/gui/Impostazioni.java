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
    private JTextField moficaNome;
    private JLabel modificaNomeLabel;
    private JTextField modificaCognome;
    private JLabel modoficaCognomeLabel;
    private JTextField laTuaEmail;
    private JLabel emailModificaLabel;
    private JTextField modificaEmail;
    private JButton salvaModificheButton;
    private JButton eliminaAccountButton;
    private JRadioButton avatarMaschileRadioButton;
    private JRadioButton avatarFemminileRadioButton;
    private JLabel scegliAvatarLabel;
    private JPanel impostazioniPanel;
    private JLabel modificaPasswordLabel;

    private static Impostazioni paginaImpostazioni = null;


    //costruttore
    public Impostazioni(JFrame frameHome, Controller controller) {
        frameImpostazioni = new JFrame("Impostazioni");
        frameImpostazioni.setContentPane(impostazioniPanel);
        frameImpostazioni.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameImpostazioni.pack();
        frameImpostazioni.setVisible(true);

        frameImpostazioni.setResizable(false); //non cambia dimensione
        frameImpostazioni.setSize(450, 450); //grandezza della finestra
        frameImpostazioni.setLocationRelativeTo(null);




        // Aggiunge l'immagine di sfondo
        // Aggiunge l'immagine di sfondo
        try {
            java.awt.Image img = javax.imageio.ImageIO.read(new java.io.File("src/sfondo_per_impostazioni.png"));
            ImageIcon iconaSfondo = new ImageIcon(img);
            JLabel labelSfondo = new JLabel(iconaSfondo);
            labelSfondo.setBounds(0, 0, 450, 450);
            impostazioniPanel.add(labelSfondo, Integer.valueOf(0));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Impossibile caricare l'immagine di sfondo.");
        }

// Rendo trasparenti le etichette e i campi in modo che si veda lo sfondo dietro
            scegliAvatarLabel.setOpaque(false);
            modificaNomeLabel.setOpaque(false);
            modificaCognome.setOpaque(false);
            emailModificaLabel.setOpaque(false);
            modificaPasswordLabel.setOpaque(false);
            emailModificaLabel.setOpaque(false);
            impostazioniPanel.setOpaque(false);
            avatarFemminileRadioButton.setOpaque(false);
            avatarMaschileRadioButton.setOpaque(false);
            eliminaAccountButton.setOpaque(false);
            tornaHomeImpostazioni.setOpaque(false);
            salvaModificheButton.setOpaque(false);





        //JLable cliccabile, per passare dalla pagina impostazioni a quella di home
        tornaHomeImpostazioni.setCursor (new Cursor(Cursor.HAND_CURSOR)) ;

        tornaHomeImpostazioni.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                frameHome.setVisible (true) ;
                frameImpostazioni.setVisible (false);
            }


        });

            // Creo il gruppo per permettere di selezionare uno o l'altro
            ButtonGroup gruppoAvatar = new ButtonGroup();
            gruppoAvatar.add(avatarMaschileRadioButton);
            gruppoAvatar.add(avatarFemminileRadioButton);

            // Gestione del pulsante Salva Modifiche
            salvaModificheButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (avatarMaschileRadioButton.isSelected()) {
                        controller.setAvatarSelezionato("man-avatar.png");
                    } else if (avatarFemminileRadioButton.isSelected()) {
                        controller.setAvatarSelezionato("woman-avatar.png");
                    }

                    JOptionPane.showMessageDialog(null, "Avatar aggiornato correttamente!");
                }
            });
    }

    public JFrame getFrameImpostazioni() { return frameImpostazioni; }

}

