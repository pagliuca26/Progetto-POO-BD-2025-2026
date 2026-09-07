package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoxGuacamole {
    private static JFrame frameGuacamole;
    private JPanel boxGuacamole;
    private JLabel ristGuacamole;
    private JButton acquistaGuacamole;
    private JLabel qntDispGuacamole;
    private static int quantitàDisponibileGuacamole = 6;

    //costruttore
    public BoxGuacamole(JFrame frameRistorante, Controller controller) {

        frameGuacamole = new JFrame("Guacamole");
        frameGuacamole.setContentPane(boxGuacamole);
        frameGuacamole.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameGuacamole.pack();
        frameGuacamole.setVisible(true);

        frameGuacamole.setResizable(false); //non cambia dimensione
        frameGuacamole.setSize(450, 450);//grandezza della finestra
        frameGuacamole.setLocationRelativeTo(null);//finestra si apre al centro
        frameGuacamole.setVisible(true);

        //JLable cliccabile, per tornare alla scelta dei ristoranti
        ristGuacamole.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ristGuacamole.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frameRistorante.setVisible(true);
                frameGuacamole.setVisible(false);
            }
        });

        //quantità che diminuisce col bottone acquista
        qntDispGuacamole.setText("Quantità disponibile: " + quantitàDisponibileGuacamole);

        acquistaGuacamole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantitàDisponibileGuacamole > 0) {
                    quantitàDisponibileGuacamole--;

                    qntDispGuacamole.setText("Quantità disponibile: " + quantitàDisponibileGuacamole);

                    int quantitaPresa = 6 - quantitàDisponibileGuacamole;

                    //salva l'acquisto nel db e recupera il codice univoco generato
                    String codiceRitiro = controller.acquistaBoxDB(5);

                    //aggiorna la pagina Prenotazioni passando anche il codice univoco
                    Home.getPaginaPrenotazione().aggiornaPrenotazione("Guacamole", quantitaPresa, codiceRitiro);

                    //mostra il popup di conferma con il codice univoco
                    JOptionPane.showMessageDialog(
                            frameGuacamole,
                            "Acquisto effettuato con successo!\nCodice di ritiro: " + codiceRitiro,
                            "Prenotazione Confermata",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(null, "Errore: Le Box per questo punto vendita sono terminate!",  "Box Terminate",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    //metodo getter per accedere al frame privato ed evitare il reset dei dati
    public static JFrame getFrameGuacamole () { return frameGuacamole; }

    public static void aumentaDisponibile() { quantitàDisponibileGuacamole++; }

    public static int getDisponibile() {
        return quantitàDisponibileGuacamole;
    }

    public void aggiornaLabelDisponibile() {
        qntDispGuacamole.setText("Quantità disponibile: " + quantitàDisponibileGuacamole);
    }

    //creazione del pannello personalizzato con sfondo
    private void createUIComponents() {
        boxGuacamole = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                java.net.URL url = getClass().getResource("/img/sfondo_per_guacamole.png");
                if (url == null) {
                    url = getClass().getResource("/sfondo_per_guacamole.png");
                }
                if (url != null) {
                    Image bg = new ImageIcon(url).getImage();
                    g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
    }
}