package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BoxDespar {
    private static JFrame frameDespar;
    private JPanel boxDespar;
    private JButton acquistaDespar;
    private JLabel supDespar;
    private JLabel qntDispDespar;
    private static int quantitàDisponibileDespar = 9;

    //costruttore
    public BoxDespar(JFrame frameSupermercato, Controller controller) {

        frameDespar = new JFrame("Despar");
        frameDespar.setContentPane(boxDespar);
        frameDespar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameDespar.pack();
        frameDespar.setVisible(true);

        frameDespar.setResizable(false); //non cambia dimensione
        frameDespar.setSize(450, 450); //grandezza della finestra
        frameDespar.setLocationRelativeTo(null); //finestra si apre al centro
        frameDespar.setVisible(true);

        /*

        // Aggiungo l'immagine di sfondo al form
        try {
            java.awt.Image img = javax.imageio.ImageIO.read(new java.io.File("src/sfondo_per_despar.png"));
            ImageIcon iconaSfondo = new ImageIcon(img);
            JLabel labelSfondo = new JLabel(iconaSfondo);
            labelSfondo.setBounds(0, 0, 450, 450);
            boxDespar.add(labelSfondo, Integer.valueOf(0));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Impossibile caricare l'immagine di sfondo.");
        }
*/

        //JLable cliccabile, per tortare alla scelta dei supemercati
        supDespar.setCursor (new Cursor(Cursor.HAND_CURSOR)) ;

        supDespar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                frameSupermercato.setVisible (true) ;
                frameDespar.setVisible(false);
            }
        });

        //Quantità che diminuisce col bottone acquista
        qntDispDespar.setText("Quantità disponibile: " + quantitàDisponibileDespar);

        acquistaDespar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantitàDisponibileDespar > 0) { //controllo condizionale: verifica se ci sono ancora box disponibili (maggiore di zero)
                    quantitàDisponibileDespar--;    //decrementa di 1 il valore della variabile intera che tiene il conto delle box

                    qntDispDespar.setText("Quantità disponibile: " + quantitàDisponibileDespar); // Aggiorna la scritta DIRETTAMENTE sulla pagina

                    int quantitaPresa = 9 - quantitàDisponibileDespar;

                    //salva l'acquisto nel db e recupera il codice univoco generato
                    String codiceRitiro = controller.acquistaBoxDB(1);

                    //aggiorna la pagina Prenotazioni passando anche il codice univoco
                    Home.getPaginaPrenotazione().aggiornaPrenotazione("Despar", quantitaPresa, codiceRitiro);

                    //mostra il popup di conferma con il codice univoco
                    JOptionPane.showMessageDialog(
                            frameDespar,
                            "Acquisto effettuato con successo!\nCodice di ritiro: " + codiceRitiro,
                            "Prenotazione Confermata",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {     //se la condizione dell if è falsa (ovvero la quantità è uguale a zero)
                    //POP-UP 2: Finestra di errore: la quantità è 0
                    JOptionPane.showMessageDialog(null, "Errore: Le Box per questo punto vendita sono terminate!",  "Box Terminate",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    //metodo getter per accedere al frame privato ed evitare il reset dei dati
    public static JFrame getFrameDespar() {
        return frameDespar;
    }

    public static void aumentaDisponibile() {
        quantitàDisponibileDespar++;
    }

    public static int getDisponibile() {
        return quantitàDisponibileDespar;
    }

    public void aggiornaLabelDisponibile() {
        qntDispDespar.setText("Quantità disponibile: " + quantitàDisponibileDespar);
    }
}
