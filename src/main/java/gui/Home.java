package gui;

import controller.Controller;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * Schermata principale dell'applicazione per l'utente autenticato.
 * Fornisce il menu di navigazione verso le sezioni Ristoranti, Supermercati, Prenotazioni
 * e Impostazioni profilo, oltre a consentire il logout verso la schermata di Login.
 */
public class Home {

    //componenti grafici della finestra home
    private JPanel homePanel;
    private JButton ristoranteButton;
    private JButton supermercatoButton;
    private JLabel returnLogin;
    private JButton prenotazioneButton;
    private JButton impHome;
    private JLabel pizzaHome;
    private JLabel carrelloHome;
    private JFrame frameHome;
    private Controller controller;

    //riferimenti alle schermate secondarie per preservarne lo stato
    private Supermercato paginaSupermercato = null;
    private RistoranteForm paginaRistorante = null;
    private static Prenotazione paginaPrenotazione = null;
    private static Impostazioni paginaImpostazioni = null;

    /**
     * Costruttore della schermata principale Home.
     * Inizializza il frame grafico della dashboard, predispone l'istanza condivisa della schermata Prenotazione
     * e registra i listener per la navigazione verso Ristoranti, Supermercati, Prenotazioni, Impostazioni e Logout.
     *
     * @param loginFrame il riferimento al JFrame della schermata di Login per consentire il logout
     * @param controller l'istanza del Controller che gestisce la logica di business e i dati utente
     */
//costruttore della schermata home
    public Home(JFrame loginFrame, Controller controller) {
        this.controller = controller;

        frameHome = new JFrame("Home");
        frameHome.setContentPane(homePanel);
        frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameHome.pack();

        //impostazioni finestra
        frameHome.setResizable(false);
        frameHome.setSize(450, 450);
        frameHome.setLocationRelativeTo(null);
        frameHome.setVisible(true);

        //inizializzazione anticipata della pagina prenotazioni per evitare crash agli acquisti
        if (paginaPrenotazione == null) {
            paginaPrenotazione = new Prenotazione(frameHome, controller);
            Prenotazione.getFramePrenotazione().setVisible(false);
        }

        //ritorno alla pagina di login
        returnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        returnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginFrame.setVisible(true);
                frameHome.setVisible(false);
            }
        });

        //apertura schermata ristorante
        ristoranteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (paginaRistorante == null) {
                    paginaRistorante = new RistoranteForm(frameHome, controller);
                } else {
                    RistoranteForm.getFrameRistorante().setVisible(true);
                }
                frameHome.setVisible(false);
            }
        });

        //apertura schermata supermercato
        supermercatoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (paginaSupermercato == null) {
                    paginaSupermercato = new Supermercato(frameHome, controller);
                } else {
                    Supermercato.getFrameSupermercato().setVisible(true);
                }
                frameHome.setVisible(false);
            }
        });

        //apertura schermata prenotazioni
        prenotazioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paginaPrenotazione.cambiaAvatar(controller.getAvatarSelezionato());
                paginaPrenotazione.aggiornaSaluto(controller);
                Prenotazione.getFramePrenotazione().setVisible(true);
                frameHome.setVisible(false);
            }
        });

        //apertura schermata impostazioni
        impHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (paginaImpostazioni == null) {
                    paginaImpostazioni = new Impostazioni(frameHome, controller);
                } else {
                    paginaImpostazioni.getFrameImpostazioni().setVisible(true);
                }
                frameHome.setVisible(false);
            }
        });
    }

    /**
     * Restituisce la schermata delle prenotazioni già creata,
     * così le altre pagine possono usarla senza aprirne una nuova.
     *
     * @return la schermata Prenotazione condivisa
     */
//restituisce il riferimento alla pagina delle prenotazioni
    public static Prenotazione getPaginaPrenotazione() {
        return paginaPrenotazione;
    }

    /**
     * Inizializza il pannello principale disegnando l'immagine di sfondo.
     */
    //creazione personalizzata dei componenti grafici per lo sfondo
    private void createUIComponents() {
        final Image backgroundImage = new ImageIcon(getClass().getResource("/img/sfondoHome.png")).getImage();

        homePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
    }
}