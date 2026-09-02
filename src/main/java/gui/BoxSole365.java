package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoxSole365 {
    private static JFrame frameSole365;
    private JPanel boxSole365;
    private JButton acquistaSole365;
    private JLabel supSole365;
    private JLabel qntDispSole;
    private static int quantitàDisponibileSole = 5; //numero di partenza finto x il test

    //costruttore
    public BoxSole365(JFrame frameSupermercato, Controller controller) {

        frameSole365= new JFrame("Sole365");
        frameSole365.setContentPane(boxSole365);
        frameSole365.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSole365.pack();
        frameSole365.setVisible(true);

        frameSole365.setResizable(false); //non cambia dimensione
        frameSole365.setSize(450, 450); //grandezza della finestra
        frameSole365.setLocationRelativeTo(null); //finestra si apre al centro
        frameSole365.setVisible(true);


        /*
        // Aggiungo l'immagine di sfondo al form
        try {
            java.awt.Image img = javax.imageio.ImageIO.read(new java.io.File("src/sfondo_per_sole365.png"));
            ImageIcon iconaSfondo = new ImageIcon(img);
            JLabel labelSfondo = new JLabel(iconaSfondo);
            labelSfondo.setBounds(0, 0, 450, 450);
            boxSole365.add(labelSfondo, Integer.valueOf(0));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Impossibile caricare l'immagine di sfondo.");
        }
*/
        //JLable cliccabile, per tornare alla scelta dei supemercati
        supSole365.setCursor (new Cursor(Cursor.HAND_CURSOR)) ;

        supSole365.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                frameSupermercato.setVisible (true) ;
                frameSole365.setVisible(false);
            }
        });

        //quantità che diminuisce col bottone acquista
        qntDispSole.setText("Quantità disponibile: " + quantitàDisponibileSole);

        acquistaSole365.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantitàDisponibileSole > 0) { //controllo condizionale: verifica se ci sono ancora box disponibili (maggiore di zero)
                    quantitàDisponibileSole--;    //decrementa di 1 il valore della variabile intera che tiene il conto delle box

                    qntDispSole.setText("Quantità disponibile: " + quantitàDisponibileSole); // Aggiorna la scritta DIRETTAMENTE sulla pagina

                    int quantitaPresa = 5 - quantitàDisponibileSole;
                    Home.getPaginaPrenotazione().aggiornaPrenotazione("Sole365", quantitaPresa);

                    //salviamo l'acquisto nel db per la box di sole365
                    controller.acquistaBoxDB(3);

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
    public static JFrame getFrameSole365() {
        return frameSole365;
    }

    public static void aumentaDisponibile() {
        quantitàDisponibileSole++;
    }

    public static int getDisponibile() {
        return quantitàDisponibileSole;
    }

    public void aggiornaLabelDisponibile() {
        qntDispSole.setText("Quantità disponibile: " + quantitàDisponibileSole);
    }
}
