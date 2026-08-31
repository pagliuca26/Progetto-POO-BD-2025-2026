package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Home {
    private JPanel homePanel;
    private JButton ristoranteButton;
    private JButton supermercatoButton;
    private JLabel returnLogin;
    private JButton prenotazioneButton;
    private JButton impHome;
    private JFrame frameHome;
    private Controller controller;

    //variabili per salvare le pagine principali così non si resettano quando torni alla home
    private Supermercato paginaSupermercato = null;
    private RistoranteForm paginaRistorante = null;
    private static Prenotazione paginaPrenotazione = null;
private static Impostazioni paginaImpostazioni = null;

    //costruttore
    public Home(JFrame loginFrame, Controller controller) {
        frameHome = new JFrame("Home");
        frameHome.setContentPane(homePanel);
        frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameHome.pack();
        frameHome.setVisible(true);

        frameHome.setResizable(false); //non cambia dimensione
        frameHome.setSize(450, 450); //grandezza della finestra
        frameHome.setLocationRelativeTo(null); //finestra si apre al centro
        frameHome.setVisible(true);paginaPrenotazione = new Prenotazione(frameHome, controller);

 //paginaImpostazioni = new Impostazioni (frameHome, controller);

        //JLable cliccabile, per passare dalla pagina home a quella di login
        returnLogin.setCursor (new Cursor(Cursor.HAND_CURSOR)) ;

        returnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                loginFrame.setVisible (true) ;
                frameHome.setVisible (false);

            }
        });

        //bottone dalla home al ristorante
        ristoranteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (paginaRistorante == null) {
                    // Prima volta
                    paginaRistorante = new RistoranteForm(frameHome, controller);
                } else {
                    // Volte successive
                    RistoranteForm.getFrameRistorante().setVisible(true);
                }
                frameHome.setVisible(false);
            }
        });

        //bottone dalla home al supermercato
        supermercatoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (paginaSupermercato == null) {
                    // Prima volta: la creiamo da zero
                    paginaSupermercato = new Supermercato(frameHome, controller);
                } else {
                    // Volte successive: riapriamo quella esistente con tutti i dati salvati
                    Supermercato.getFrameSupermercato().setVisible(true);
                }
                frameHome.setVisible(false);
            }
        });

        //bottone dalla home alle prenotazioni
        prenotazioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (paginaPrenotazione == null) {
                    //prima volta
                    paginaPrenotazione = new Prenotazione(frameHome, controller);
                } else {
                    //volte successive
                    Prenotazione.getFramePrenotazione().setVisible(true);
                }
                frameHome.setVisible(false);
            }
        });


    }

    public static Prenotazione getPaginaPrenotazione() {
        return paginaPrenotazione;
    }
}
/*
//bottone da home a impostazioni
impHome.addActionListener(new ActionListener () {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (paginaImpostazioni == null) {
                paginaImpostazioni = new Impostazioni(frameHome, controller);
            } else {
                Impostazioni.getFrameImpostazioni().setVisible(true);
            }
            frameHome.setVisible(false);
        }
    });










        //metodo getter per far accedere le altre classi alla schermata prenotazioni
        public static Prenotazione getPaginaPrenotazione() {
            return paginaPrenotazione;
        }
}


//metodo getter di impostazioni
public static Impostazioni getFrameImpostazioni() {
return paginaImpostazioni;
}



*/
