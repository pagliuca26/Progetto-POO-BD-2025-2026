package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoxItaliamo {
    private static JFrame frameItaliamo;
    private JPanel boxItaliamo;
    private JLabel ristItaliamo;
    private JButton acquistaItaliamo;
    private JLabel qntDispItaliamo;
    private static int quantitàDisponibileItaliamo = 10;

    //costruttore
    public BoxItaliamo(JFrame frameRistorante, Controller controller) {

        frameItaliamo = new JFrame("Italiamo");
        frameItaliamo.setContentPane(boxItaliamo);
        frameItaliamo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameItaliamo.pack();
        frameItaliamo.setVisible(true);

        frameItaliamo.setResizable(false); //non cambia dimensione
        frameItaliamo.setSize(450, 450); //grandezza della finestra
        frameItaliamo.setLocationRelativeTo(null); //finestra si apre al centro
        frameItaliamo.setVisible(true);

        /*
        // Aggiungo l'immagine di sfondo al form
        try {
            java.awt.Image img = javax.imageio.ImageIO.read(new java.io.File("src/sfondo_per_italiamo.png"));
            ImageIcon iconaSfondo = new ImageIcon(img);
            JLabel labelSfondo = new JLabel(iconaSfondo);
            labelSfondo.setBounds(0, 0, 450, 450);
            boxItaliamo.add(labelSfondo, Integer.valueOf(0));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Impossibile caricare l'immagine di sfondo.");
        }
*/
        //JLable cliccabile, per tortare alla scelta dei ristoranti
        ristItaliamo.setCursor (new Cursor(Cursor.HAND_CURSOR)) ;

        ristItaliamo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                frameRistorante.setVisible (true) ;
                frameItaliamo.setVisible(false);
            }
        });

        //Quantità che diminuisce col bottone acquista
        qntDispItaliamo.setText("Quantità disponibile: " + quantitàDisponibileItaliamo);

        acquistaItaliamo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantitàDisponibileItaliamo > 0) { //controllo condizionale: verifica se ci sono ancora box disponibili (maggiore di zero)
                    quantitàDisponibileItaliamo--;    //decrementa di 1 il valore della variabile intera che tiene il conto delle box

                    qntDispItaliamo.setText("Quantità disponibile: " + quantitàDisponibileItaliamo); //aggiorna la scritta sulla pagina

                    int quantitaPresa = 10 - quantitàDisponibileItaliamo;
                    Home.getPaginaPrenotazione().aggiornaPrenotazione("Italiamo", quantitaPresa);

                    // POP-UP 1: Finestra di successo dell'acquisto
                    JOptionPane.showMessageDialog(null, "Acquisto effettuato con successo!");
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
    public static JFrame getFrameItaliano() {
        return frameItaliamo;
    }

    public static void aumentaDisponibile() {
        quantitàDisponibileItaliamo++;
    }

    public static int getDisponibile() {
        return quantitàDisponibileItaliamo;
    }

    public void aggiornaLabelDisponibile() {
        qntDispItaliamo.setText("Quantità disponibile: " + quantitàDisponibileItaliamo);
    }

}

