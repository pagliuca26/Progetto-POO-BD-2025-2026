package gui;

import javax.swing.*;



public class RistoranteForm {
    private static JFrame frameRistorante;
    private JPanel ristorantePanel;
    private JButton italianoButton;
    private JButton messicanoButton;
    private JButton giapponeseButton;


    //costruttore
    public RistoranteForm(JPanel ristorantePanel, JButton italianoButton, JButton messicanoButton, JButton giapponeseButton) {
        //posso ricopiare per le prossime
        frameRistorante = new JFrame("Home");
        frameRistorante.setContentPane(ristorantePanel);
        frameRistorante.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameRistorante.pack();
        frameRistorante.setVisible(true);

       //per la grandezza della finestra
        frameRistorante.setResizable(false);
        frameRistorante.setSize(400, 325);
        frameRistorante.setLocationRelativeTo(null);
        frameRistorante.setVisible(true);
    }
}
