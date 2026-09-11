package gui;

import controller.Controller;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;

public class Prenotazione {

    //componenti grafici della finestra prenotazioni
    private static JFrame framePrenotazione;
    private JPanel prenotazionePanel;
    private JLabel tornaHomePrenotazione;
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
    private JLabel iconaP;
    private JLabel benvenutoP;
    private Controller controller;

    //costruttore della schermata prenotazioni
    public Prenotazione(JFrame frameHome, Controller controller) {
        this.controller = controller;
        framePrenotazione = new JFrame("Prenotazioni");
        framePrenotazione.setContentPane(prenotazionePanel);
        framePrenotazione.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePrenotazione.pack();

        //impostazioni della finestra
        framePrenotazione.setResizable(false);
        framePrenotazione.setSize(450, 450);
        framePrenotazione.setLocationRelativeTo(null);
        framePrenotazione.setVisible(false);

        //scritta di benvenuto dinamica
        if (controller.getUtenteAttuale() != null) {
            benvenutoP.setText(controller.getSaluto() + controller.getUtenteAttuale().getNome());
        }

        //impostazione dell avatar selezionato
        cambiaAvatar(controller.getAvatarSelezionato());

        //ritorno alla schermata home
        tornaHomePrenotazione.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tornaHomePrenotazione.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frameHome.setVisible(true);
                framePrenotazione.setVisible(false);
            }
        });

        //inizializzazione visibilita componenti prenotazione
        italiamoNPrenotazioni.setVisible(false);
        guacamoleNPrenotazioni.setVisible(false);
        tokyoNPrenotazioni.setVisible(false);
        soleNPrenotazioni.setVisible(false);
        desparNPrenotazioni.setVisible(false);
        conadNPrenotazioni.setVisible(false);

        annullaItaliamo.setVisible(false);
        annullaGuacamole.setVisible(false);
        annullaTokyo.setVisible(false);
        annullaSole.setVisible(false);
        annullaDespar.setVisible(false);
        annullaConad.setVisible(false);

        elencoR.setVisible(false);
        elencoS.setVisible(false);

        //gestione annullamento prenotazione despar
        annullaDespar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (confermaAnnullamento()) {
                    BoxDespar.aumentaDisponibile();
                    controller.annullaPrenotazioneDB(1);

                    int quantitaRimasta = 9 - BoxDespar.getDisponibile();

                    if (quantitaRimasta > 0) {
                        String testoAttuale = desparNPrenotazioni.getText();
                        String pezzoCodice = testoAttuale.substring(testoAttuale.indexOf("<br>"));
                        desparNPrenotazioni.setText("<html>Despar: " + quantitaRimasta + " box" + pezzoCodice);
                    } else {
                        desparNPrenotazioni.setVisible(false);
                        annullaDespar.setVisible(false);
                    }

                    if (!conadNPrenotazioni.isVisible() && !desparNPrenotazioni.isVisible() && !soleNPrenotazioni.isVisible()) {
                        elencoS.setVisible(false);
                    }
                }
            }
        });

        //gestione annullamento prenotazione conad
        annullaConad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (confermaAnnullamento()) {
                    BoxConad.aumentaDisponibile();
                    controller.annullaPrenotazioneDB(2);

                    int quantitaRimasta = 7 - BoxConad.getDisponibile();

                    if (quantitaRimasta > 0) {
                        String testoAttuale = conadNPrenotazioni.getText();
                        String pezzoCodice = testoAttuale.substring(testoAttuale.indexOf("<br>"));
                        conadNPrenotazioni.setText("<html>Conad: " + quantitaRimasta + " box" + pezzoCodice);
                    } else {
                        conadNPrenotazioni.setVisible(false);
                        annullaConad.setVisible(false);
                    }

                    if (!conadNPrenotazioni.isVisible() && !desparNPrenotazioni.isVisible() && !soleNPrenotazioni.isVisible()) {
                        elencoS.setVisible(false);
                    }
                }
            }
        });

        //gestione annullamento prenotazione sole365
        annullaSole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (confermaAnnullamento()) {
                    BoxSole365.aumentaDisponibile();
                    controller.annullaPrenotazioneDB(3);

                    int quantitaRimasta = 5 - BoxSole365.getDisponibile();

                    if (quantitaRimasta > 0) {
                        String testoAttuale = soleNPrenotazioni.getText();
                        String pezzoCodice = testoAttuale.substring(testoAttuale.indexOf("<br>"));
                        soleNPrenotazioni.setText("<html>Sole365: " + quantitaRimasta + " box" + pezzoCodice);
                    } else {
                        soleNPrenotazioni.setVisible(false);
                        annullaSole.setVisible(false);
                    }

                    if (!conadNPrenotazioni.isVisible() && !desparNPrenotazioni.isVisible() && !soleNPrenotazioni.isVisible()) {
                        elencoS.setVisible(false);
                    }
                }
            }
        });

        //gestione annullamento prenotazione italiamo
        annullaItaliamo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (confermaAnnullamento()) {
                    BoxItaliamo.aumentaDisponibile();
                    controller.annullaPrenotazioneDB(4);

                    int quantitaRimasta = 10 - BoxItaliamo.getDisponibile();

                    if (quantitaRimasta > 0) {
                        String testoAttuale = italiamoNPrenotazioni.getText();
                        String pezzoCodice = testoAttuale.substring(testoAttuale.indexOf("<br>"));
                        italiamoNPrenotazioni.setText("<html>Italiamo: " + quantitaRimasta + " box" + pezzoCodice);
                    } else {
                        italiamoNPrenotazioni.setVisible(false);
                        annullaItaliamo.setVisible(false);
                    }

                    if (!guacamoleNPrenotazioni.isVisible() && !italiamoNPrenotazioni.isVisible() && !tokyoNPrenotazioni.isVisible()) {
                        elencoR.setVisible(false);
                    }
                }
            }
        });

        //gestione annullamento prenotazione guacamole
        annullaGuacamole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (confermaAnnullamento()) {
                    BoxGuacamole.aumentaDisponibile();
                    controller.annullaPrenotazioneDB(5);

                    int quantitaRimasta = 6 - BoxGuacamole.getDisponibile();

                    if (quantitaRimasta > 0) {
                        String testoAttuale = guacamoleNPrenotazioni.getText();
                        String pezzoCodice = testoAttuale.substring(testoAttuale.indexOf("<br>"));
                        guacamoleNPrenotazioni.setText("<html>Guacamole: " + quantitaRimasta + " box" + pezzoCodice);
                    } else {
                        guacamoleNPrenotazioni.setVisible(false);
                        annullaGuacamole.setVisible(false);
                    }

                    if (!guacamoleNPrenotazioni.isVisible() && !italiamoNPrenotazioni.isVisible() && !tokyoNPrenotazioni.isVisible()) {
                        elencoR.setVisible(false);
                    }
                }
            }
        });

        //gestione annullamento prenotazione tokyo
        annullaTokyo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (confermaAnnullamento()) {
                    BoxTokyo.aumentaDisponibile();
                    controller.annullaPrenotazioneDB(6);

                    int quantitaRimasta = 7 - BoxTokyo.getDisponibile();

                    if (quantitaRimasta > 0) {
                        String testoAttuale = tokyoNPrenotazioni.getText();
                        String pezzoCodice = testoAttuale.substring(testoAttuale.indexOf("<br>"));
                        tokyoNPrenotazioni.setText("<html>Tokyo: " + quantitaRimasta + " box" + pezzoCodice);
                    } else {
                        tokyoNPrenotazioni.setVisible(false);
                        annullaTokyo.setVisible(false);
                    }

                    if (!guacamoleNPrenotazioni.isVisible() && !italiamoNPrenotazioni.isVisible() && !tokyoNPrenotazioni.isVisible()) {
                        elencoR.setVisible(false);
                    }
                }
            }
        });

        //carica le prenotazioni memorizzate nel database
        caricaPrenotazioniSalvate();
    }

    //restituisce il frame della pagina prenotazioni
    public static JFrame getFramePrenotazione() {
        return framePrenotazione;
    }

    //aggiorna la visualizzazione dei contatori e del codice di ritiro
    public void aggiornaPrenotazione(String negozio, int quantitaAcquistata, String codiceUnivoco) {
        if (negozio.equals("Guacamole") || negozio.equals("Italiamo") || negozio.equals("Tokyo")) {
            elencoR.setVisible(true);
        } else {
            elencoS.setVisible(true);
        }

        if (negozio.equals("Guacamole")) {
            guacamoleNPrenotazioni.setText("<html>Guacamole: " + quantitaAcquistata + " box<br>Cod: " + codiceUnivoco + "</html>");
            guacamoleNPrenotazioni.setVisible(true);
            annullaGuacamole.setVisible(true);
        }

        if (negozio.equals("Tokyo")) {
            tokyoNPrenotazioni.setText("<html>Tokyo: " + quantitaAcquistata + " box<br>Cod: " + codiceUnivoco + "</html>");
            tokyoNPrenotazioni.setVisible(true);
            annullaTokyo.setVisible(true);
        }

        if (negozio.equals("Italiamo")) {
            italiamoNPrenotazioni.setText("<html>Italiamo: " + quantitaAcquistata + " box<br>Cod: " + codiceUnivoco + "</html>");
            italiamoNPrenotazioni.setVisible(true);
            annullaItaliamo.setVisible(true);
        }

        if (negozio.equals("Despar")) {
            desparNPrenotazioni.setText("<html>Despar: " + quantitaAcquistata + " box<br>Cod: " + codiceUnivoco + "</html>");
            desparNPrenotazioni.setVisible(true);
            annullaDespar.setVisible(true);
        }

        if (negozio.equals("Conad")) {
            conadNPrenotazioni.setText("<html>Conad: " + quantitaAcquistata + " box<br>Cod: " + codiceUnivoco + "</html>");
            conadNPrenotazioni.setVisible(true);
            annullaConad.setVisible(true);
        }

        if (negozio.equals("Sole365")) {
            soleNPrenotazioni.setText("<html>Sole365: " + quantitaAcquistata + " box<br>Cod: " + codiceUnivoco + "</html>");
            soleNPrenotazioni.setVisible(true);
            annullaSole.setVisible(true);
        }
    }

    //aggiorna l icona dell avatar dell utente
    public void cambiaAvatar(String nomeFile) {
        if (nomeFile != null) {
            String path = nomeFile.startsWith("/") ? nomeFile : "/" + nomeFile;
            URL url = getClass().getResource(path);
            if (url == null && !path.startsWith("/img/")) {
                url = getClass().getResource("/img" + path);
            }
            if (url != null) {
                iconaP.setIcon(new ImageIcon(url));
            }
        }
    }

    //aggiorna il messaggio di benvenuto con i dati della sessione
    public void aggiornaSaluto(Controller controller) {
        if (controller.getUtenteAttuale() != null) {
            benvenutoP.setText(controller.getSaluto() + controller.getUtenteAttuale().getNome());
        }
    }

    //mostra il dialogo di conferma annullamento
    private boolean confermaAnnullamento() {
        int risposta = JOptionPane.showConfirmDialog(
                null,
                "Vuoi annullare la prenotazione?",
                "Conferma annullamento",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        return risposta == JOptionPane.YES_OPTION;
    }

    //carica le prenotazioni registrate sul database all avvio
    public void caricaPrenotazioniSalvate() {
        ArrayList<model.Prenotazione> lista = controller.getPrenotazioniAttiveUtente();

        for (model.Prenotazione p : lista) {
            if (p.getIdBox() == 1) {
                aggiornaPrenotazione("Despar", 1, p.getCodiceRitiro());
            } else if (p.getIdBox() == 2) {
                aggiornaPrenotazione("Conad", 1, p.getCodiceRitiro());
            } else if (p.getIdBox() == 3) {
                aggiornaPrenotazione("Sole365", 1, p.getCodiceRitiro());
            } else if (p.getIdBox() == 4) {
                aggiornaPrenotazione("Italiamo", 1, p.getCodiceRitiro());
            } else if (p.getIdBox() == 5) {
                aggiornaPrenotazione("Guacamole", 1, p.getCodiceRitiro());
            } else if (p.getIdBox() == 6) {
                aggiornaPrenotazione("Tokyo", 1, p.getCodiceRitiro());
            }
        }
    }
}