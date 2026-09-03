package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoxTokyo {
    private static JFrame frameTokyo;
    private JPanel boxTokyo;
    private JButton acquistaTokyo;
    private JLabel ristTokyo;
    private JLabel qntDispTokyo;
    private static int quantitàDisponibileTokyo = 7;

    //costruttore
    public BoxTokyo(JFrame frameRistorante, Controller controller) {

        frameTokyo = new JFrame("Tokyo");
        frameTokyo.setContentPane(boxTokyo);
        frameTokyo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTokyo.pack();
        frameTokyo.setVisible(true);

        frameTokyo.setResizable(false); //non cambia dimensione
        frameTokyo.setSize(450, 450); //grandezza della finestra
        frameTokyo.setLocationRelativeTo(null); //finestra si apre al centro
        frameTokyo.setVisible(true);

        /*
        // Aggiungo l'immagine di sfondo al form
        try {
            java.awt.Image img = javax.imageio.ImageIO.read(new java.io.File("src/sfondo_per_Tokyo.png"));
            ImageIcon iconaSfondo = new ImageIcon(img);
            JLabel labelSfondo = new JLabel(iconaSfondo);
            labelSfondo.setBounds(0, 0, 450, 450);
            boxTokyo.add(labelSfondo, Integer.valueOf(0));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Impossibile caricare l'immagine di sfondo.");
        }
*/

        //JLable cliccabile, per tornare alla scelta dei ristoranti
        ristTokyo.setCursor (new Cursor(Cursor.HAND_CURSOR)) ;

        ristTokyo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                frameRistorante.setVisible (true) ;
                frameTokyo.setVisible(false);
            }
        });

        //Quantità che diminuisce col bottone acquista
        qntDispTokyo.setText("Quantità disponibile: " + quantitàDisponibileTokyo);

        acquistaTokyo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantitàDisponibileTokyo > 0) { //controllo condizionale: verifica se ci sono ancora box disponibili (maggiore di zero)
                    quantitàDisponibileTokyo--;    //decrementa di 1 il valore della variabile intera che tiene il conto delle box

                    qntDispTokyo.setText("Quantità disponibile: " + quantitàDisponibileTokyo); // Aggiorna la scritta DIRETTAMENTE sulla pagina

                    int quantitaPresa = 7 - quantitàDisponibileTokyo;

                    //salva l'acquisto nel db e recupera il codice univoco generato
                    String codiceRitiro = controller.acquistaBoxDB(6);

                    //aggiorna la pagina Prenotazioni passando anche il codice univoco
                    Home.getPaginaPrenotazione().aggiornaPrenotazione("Tokyo", quantitaPresa, codiceRitiro);

                    //mostra il popup di conferma con il codice univoco
                    JOptionPane.showMessageDialog(
                            frameTokyo,
                            "Acquisto effettuato con successo!\nCodice di ritiro: " + codiceRitiro,
                            "Prenotazione Confermata",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {     //se la condizione dell if è falsa (ovvero la quantità è uguale a zero)
                    // POP-UP 2: Finestra di errore: la quantità è 0
                    JOptionPane.showMessageDialog(null, "Errore: Le Box per questo punto vendita sono terminate!",  "Box Terminate",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    //metodo getter per accedere al frame privato ed evitare il reset dei dati
    public static JFrame getFrameTokyo() {
        return frameTokyo;
    }

    public static void aumentaDisponibile() {
        quantitàDisponibileTokyo++;
    }

    public static int getDisponibile() {
        return quantitàDisponibileTokyo;
    }

    public void aggiornaLabelDisponibile() {
        qntDispTokyo.setText("Quantità disponibile: " + quantitàDisponibileTokyo);
    }
}
