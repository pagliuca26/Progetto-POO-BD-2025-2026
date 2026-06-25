package gui;

import javax.swing.*;

public class Prenotazione {
    private static JFrame framePrenotazione;
    private JPanel prenotazionePanel;

    //costruttore
    public Prenotazione(JPanel prenotazionePanel) {
        framePrenotazione = new JFrame("Prenotazioni");
        framePrenotazione.setContentPane(prenotazionePanel);
        framePrenotazione.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePrenotazione.pack();
        framePrenotazione.setVisible(true);

        framePrenotazione.setResizable(false); //non cambia dimensione
        framePrenotazione.setSize(450, 450); //grandezza della finestra
        framePrenotazione.setLocationRelativeTo(null); //finestra si apre al centro
        framePrenotazione.setVisible(true);
    }
}
