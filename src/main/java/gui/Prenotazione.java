package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Prenotazione {
    private static JFrame framePrenotazione;
    private JPanel prenotazionePanel;
    private JLabel tornaHomeP;
    private JLabel introduzione;
    private JLabel elencoR;
    private JLabel elencoS;
    private JLabel italiamoNPrenotazioni;
    private JLabel guacamoleNPrenotazioni;
    private JLabel tokyoNPrenotazioni;
    private JLabel soleNPrenotazioni;
    private JLabel desparNPrenotazioni;
    private JLabel conadNPrenotazioni;
    private JButton annullaTokyo;
    private JButton annullaItaliamo;
    private JButton annullaGuacamole;
    private JButton annullaSole;
    private JButton annullaConad;
    private JButton annullaDespar;

    //costruttore
    public Prenotazione(JFrame frameHome, Controller controller) {
        framePrenotazione = new JFrame("Prenotazioni");
        framePrenotazione.setContentPane(prenotazionePanel);
        framePrenotazione.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePrenotazione.pack();
        framePrenotazione.setVisible(true);

        framePrenotazione.setResizable(false); //non cambia dimensione
        framePrenotazione.setSize(450, 450); //grandezza della finestra
        framePrenotazione.setLocationRelativeTo(null); //finestra si apre al centro
        framePrenotazione.setVisible(true);

        //Jlabel cliccabile, per tornare dalla pagina delle prenotazioni alla home
        tornaHomeP.setCursor(new Cursor(Cursor.HAND_CURSOR)); //cambia il cursore

        tornaHomeP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frameHome.setVisible(true);
                framePrenotazione.setVisible(false);
            }
        });
    }

    //metodo getter per far recuperare alla Home lo stesso frame delle prenotazioni
    public static JFrame getFramePrenotazione() {
        return framePrenotazione;
    }

    //metodo per aggiornare i contatori delle box prenotate
    //l'operatore == confronta i riferimenti in memoria (le istanze), mentre .equals() verifica il reale contenuto testuale delle stringhe
    public void aggiornaPrenotazione(String negozio, int quantitaAcquistata) {

        if (negozio.equals("Italiano")) {
            annullaItaliamo.setText("Italiano: " + quantitaAcquistata);
        }
        else if (negozio.equals("Guacamole")) {
            annullaGuacamole.setText("Guacamole: " + quantitaAcquistata);
        }
        else if (negozio.equals("Tokyo")) {
            annullaTokyo.setText("Tokyo: " + quantitaAcquistata);
        }
        else if (negozio.equals("Sole365")) {
            annullaSole.setText("Sole365: " + quantitaAcquistata);
        }
        else if (negozio.equals("Despar")) {
            annullaDespar.setText("Despar: " + quantitaAcquistata);
        }
        else if (negozio.equals("Conad")) {
            annullaConad.setText("Conad: " + quantitaAcquistata);
        }
    }
}
