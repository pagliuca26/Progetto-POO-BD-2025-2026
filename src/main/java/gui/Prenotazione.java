package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Prenotazione {
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
        framePrenotazione.setVisible(false);


        /*
        // Aggiungo l'immagine di sfondo al form
        try {
            java.awt.Image img = javax.imageio.ImageIO.read(new java.io.File("src/sfondo_prenotazione_java.png"));
            ImageIcon iconaSfondo = new ImageIcon(img);
            JLabel labelSfondo = new JLabel(iconaSfondo);
            labelSfondo.setBounds(0, 0, 450, 450);
            prenotazionePanel.add(labelSfondo, Integer.valueOf(0));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Impossibile caricare l'immagine di sfondo.");
        }
*/
        //imposto la scritta di benvenuto dinamica con il nome dell'utente loggato
        benvenutoP.setText(controller.getSaluto() + controller.getUtenteAttuale().getNome());

        // imposto l'avatar scelto salvato nel controller
        cambiaAvatar(controller.getAvatarSelezionato());

        //Jlabel cliccabile, per tornare dalla pagina delle prenotazioni alla home
        tornaHomePrenotazione.setCursor(new Cursor(Cursor.HAND_CURSOR)); //cambia il cursore

        tornaHomePrenotazione.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frameHome.setVisible(true);
                framePrenotazione.setVisible(false);

            }
        });

        //all'inizio nascondiamo tutto per avere la schermata pulita: compariranno solo i luoghi delle box acquistate
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

        //evento Annulla Despar
        annullaDespar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoxDespar.aumentaDisponibile();

                int quantitaRimasta = 9 - BoxDespar.getDisponibile();

                if (quantitaRimasta > 0) {
                    desparNPrenotazioni.setText("Despar: " + quantitaRimasta + " box");
                } else {
                    desparNPrenotazioni.setVisible(false);
                    annullaDespar.setVisible(false);
                }

                if (!conadNPrenotazioni.isVisible() && !desparNPrenotazioni.isVisible() && !soleNPrenotazioni.isVisible()) {
                    elencoS.setVisible(false);
                }
            }
        });

        //evento Annulla Conad
        annullaConad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoxConad.aumentaDisponibile();

                int quantitaRimasta = 7 - BoxConad.getDisponibile();

                if (quantitaRimasta > 0) {
                    conadNPrenotazioni.setText("Conad: " + quantitaRimasta + " box");
                } else {
                    conadNPrenotazioni.setVisible(false);
                    annullaConad.setVisible(false);
                }

                if (!conadNPrenotazioni.isVisible() && !desparNPrenotazioni.isVisible() && !soleNPrenotazioni.isVisible()) {
                    elencoS.setVisible(false);
                }
            }
        });

        //evento Annulla Sole365
        annullaSole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoxSole365.aumentaDisponibile();

                int quantitaRimasta = 5 - BoxSole365.getDisponibile();

                if (quantitaRimasta > 0) {
                    soleNPrenotazioni.setText("Sole365: " + quantitaRimasta + " box");
                } else {
                    soleNPrenotazioni.setVisible(false);
                    annullaSole.setVisible(false);
                }

                if (!conadNPrenotazioni.isVisible() && !desparNPrenotazioni.isVisible() && !soleNPrenotazioni.isVisible()) {
                    elencoS.setVisible(false);
                }
            }
        });

        //evento Annulla Italiamo
        annullaItaliamo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoxItaliamo.aumentaDisponibile();

                int quantitaRimasta = 10 - BoxItaliamo.getDisponibile();

                if (quantitaRimasta > 0) {
                    italiamoNPrenotazioni.setText("Italiamo: " + quantitaRimasta + " box");
                } else {
                    italiamoNPrenotazioni.setVisible(false);
                    annullaItaliamo.setVisible(false);
                }

                if (!guacamoleNPrenotazioni.isVisible() && !italiamoNPrenotazioni.isVisible() && !tokyoNPrenotazioni.isVisible()) {
                    elencoR.setVisible(false);
                }
            }
        });

        //evento Annulla Guacamole
        annullaGuacamole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoxGuacamole.aumentaDisponibile();

                int quantitaRimasta = 6 - BoxGuacamole.getDisponibile();

                if (quantitaRimasta > 0) {
                    guacamoleNPrenotazioni.setText("Guacamole: " + quantitaRimasta + " box");
                } else {
                    guacamoleNPrenotazioni.setVisible(false);
                    annullaGuacamole.setVisible(false);
                }

                if (!guacamoleNPrenotazioni.isVisible() && !italiamoNPrenotazioni.isVisible() && !tokyoNPrenotazioni.isVisible()) {
                    elencoR.setVisible(false);
                }
            }
        });

        //evento Annulla Tokyo
        annullaTokyo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoxTokyo.aumentaDisponibile();

                int quantitaRimasta = 7 - BoxTokyo.getDisponibile();

                if (quantitaRimasta > 0) {
                    tokyoNPrenotazioni.setText("Tokyo: " + quantitaRimasta + " box");
                } else {
                    tokyoNPrenotazioni.setVisible(false);
                    annullaTokyo.setVisible(false);
                }

                if (!guacamoleNPrenotazioni.isVisible() && !italiamoNPrenotazioni.isVisible() && !tokyoNPrenotazioni.isVisible()) {
                    elencoR.setVisible(false);
                }
            }
        });
    }

    //metodo getter per far recuperare alla Home lo stesso frame delle prenotazioni
    public static JFrame getFramePrenotazione() {
        return framePrenotazione;
    }

    //metodo per aggiornare i contatori delle box prenotate
    //l'operatore == confronta i riferimenti in memoria (le istanze), mentre .equals() verifica il reale contenuto testuale delle stringhe
    public void aggiornaPrenotazione (String negozio, int quantitaAcquistata) {

        //facciamo un controllo all'inizio: se è un ristorante mostra elencoR, altrimenti elencoS
        if (negozio.equals("Guacamole") || negozio.equals("Italiamo") || negozio.equals("Tokyo")) {
            elencoR.setVisible(true);
        } else {
            elencoS.setVisible(true);
        }

        //poi controlliamo quali e quante box sono state acquistate per farle uscire all'interno della pagina
        if (negozio.equals("Guacamole")) {
            guacamoleNPrenotazioni.setText("Guacamole: " + quantitaAcquistata + " box");
            guacamoleNPrenotazioni.setVisible(true);
            annullaGuacamole.setVisible(true);
        }

        if (negozio.equals("Tokyo")) {
            tokyoNPrenotazioni.setText("Tokyo: " + quantitaAcquistata + " box");
            tokyoNPrenotazioni.setVisible(true);
            annullaTokyo.setVisible(true);
        }

        if (negozio.equals("Italiamo")) {
            italiamoNPrenotazioni.setText("Italiamo: " + quantitaAcquistata + " box");
            italiamoNPrenotazioni.setVisible(true);
            annullaItaliamo.setVisible(true);
        }

        if (negozio.equals("Despar")) {
            desparNPrenotazioni.setText("Despar: " + quantitaAcquistata + " box");
            desparNPrenotazioni.setVisible(true);
            annullaDespar.setVisible(true);
        }

        if (negozio.equals("Conad")) {
            conadNPrenotazioni.setText("Conad: " + quantitaAcquistata + " box");
            conadNPrenotazioni.setVisible(true);
            annullaConad.setVisible(true);
        }

        if (negozio.equals("Sole365")){
            soleNPrenotazioni.setText("Sole365: " + quantitaAcquistata + " box");
            soleNPrenotazioni.setVisible(true);
            annullaSole.setVisible(true);
        }
    }

    // metodo per cambiare l'immagine dell'avatar
    public void cambiaAvatar(String nomeFile) {
        if (nomeFile != null && getClass().getResource("/" + nomeFile) != null) {
            ImageIcon icona = new ImageIcon(getClass().getResource("/" + nomeFile));
            iconaP.setIcon(icona);
        }
    }

    //metodo per aggiornare la scritta del saluto
    public void aggiornaSaluto(Controller controller) {
        benvenutoP.setText(controller.getSaluto() + controller.getUtenteAttuale().getNome());
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}