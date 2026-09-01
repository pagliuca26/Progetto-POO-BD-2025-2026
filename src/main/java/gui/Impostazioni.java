package gui;

import controller.Controller;
import model.Utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class Impostazioni {
    private static JFrame frameImpostazioni;
    private JLabel tornaHomeImpostazioni;
    private JLabel impostazioniName;
    private JLabel nomeAttuale;
    private JTextField ilTuoNome;
    private JTextField moficaNome;
    private JLabel modificaNomeLabel;
    private JLabel cognomeAttuale;
    private JTextField ilTuoCognome;
    private JTextField modificaCognome;
    private JLabel modoficaCognomeLabel;
    private JTextField laTuaEmail;
    private JLabel emailAttualeLabel;
    private JTextField modificaEmail;
    private JLabel modificaEmailLabel;
    private JLabel passwordAttualeLabel;
    private JTextField laTuaPassword;
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

        frameImpostazioni.setResizable(false); //non cambia dimensione
        frameImpostazioni.setSize(450, 450); //grandezza della finestra
        frameImpostazioni.setLocationRelativeTo(null);

        //JLable cliccabile, per passare dalla pagina impostazioni a quella di home
        tornaHomeImpostazioni.setCursor (new Cursor(Cursor.HAND_CURSOR)) ;

        tornaHomeImpostazioni.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                frameHome.setVisible (true) ;
                frameImpostazioni.setVisible (false);

            }
        });
    }
}

     /*
     // Gestione del pulsante Salva Modifiche
     salvaModificheButton.addActionListener(e -> {
         try {
             // 1. Leggiamo i testi inseriti nei tuoi campi
             String nuovoNome = moficaNome.getText();
             String nuovoCognome = modificaCognome.getText();
             String nuovaEmail = modificaEmail.getText();
             String nuovaPassword = laTuaPassword.getText();

             // 2. Controllo dei RadioButton per l'avatar
             String avatarScelto = "";
             if (avatarMaschileRadioButton.isSelected()) {
                 avatarScelto = "maschio";
             } else if (avatarFemminileRadioButton.isSelected()) {
                 avatarScelto = "femmina";
             }

             // 3. Chiamata al controller per aggiornare il database
             // 3. Modifichiamo direttamente l'utente attualmente loggato
             Utente utenteCorrente = controller.getUtenteAttuale();
             if (utenteCorrente != null) {
                 if (!nuovoNome.isEmpty()) {
                     utenteCorrente.setNome(nuovoNome); // o il nome del metodo che usi nel modello Utente
                 }
                 if (!nuovoCognome.isEmpty()) {
                     utenteCorrente.setCognome(nuovoCognome);
                 }
                 // Se hai i metodi set per email, password e avatar, aggiornali qui
             }

             // 4. Aggiorniamo subito le etichette grafiche dei dati attuali
             if (!nuovoNome.isEmpty()) {
                 nomeAttuale.setText(nuovoNome);
             }
             if (!nuovoCognome.isEmpty()) {
                 cognomeAttuale.setText(nuovoCognome);
             }

             // 5. Messaggio di conferma pulito
             JOptionPane.showMessageDialog(null,
                     "Modifiche salvate con successo!",
                     "Successo",
                     JOptionPane.INFORMATION_MESSAGE);

             // Puliamo i campi di input
             moficaNome.setText("");
             modificaCognome.setText("");
             modificaEmail.setText("");
             laTuaPassword.setText("");

         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,
                     "Errore nel database: " + ex.getMessage(),
                     "Errore",
                     JOptionPane.ERROR_MESSAGE);
             ex.printStackTrace();
         } catch (Exception ex) {
             JOptionPane.showMessageDialog(null,
                     "Errore: " + ex.getMessage(),
                     "Errore",
                     JOptionPane.ERROR_MESSAGE);
         }
     });
 }


    //creo gruppo per legare i due radio button
 //   ButtonGroup gruppoAvatar = new ButtonGroup();
 //   gruppoAvatar.add(avatarMaschileRadioButton);
 /*   gruppoAvatar.add(avatarFemminileRadioButton);

    salvaModificheButton.addActionListener(new ActionListener()) {
        @Override
        public void actionPerformed (ActionEvent e){
        //verifichiamo l'avatar che è stato selezionato
        String avatarScelto = "";
        if (avatarMaschileRadioButton.isSelected()) {
                    avatarScelto = "maschio";
        } else if (avatarFemminileRadioButton.isSelected()) {
            avatarScelto = "femmina";
        }
        JOptionPane.showMessageDialog (null, "Avatar selezionato: " + avatarScelto);

    }
    };


}

// metodo getter
public static Impostazioni getFrameImpostazioni (){
return paginaImpostazioni;
}
}

*/
