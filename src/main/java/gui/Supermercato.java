package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Supermercato {
    private static JFrame frameSupermercato;
    private JPanel supermercatoPanel;
    private JButton conadButton;
    private JButton sole365Button;
    private JButton desparButton;
    private JLabel tornaHomeS;


    //variabili per salvare le pagine dei supermercati. All'inizio sono null (vuote) così al primo click creiamo la finestra,
    //mentre i click successivi riaprono quella vecchia senza resettare le boxriapriamo lo stesso senza azzerare i numeri delle box.
    private BoxConad conad = null;
    private BoxSole365 sole365 = null;
    private BoxDespar despar = null;

    //costruttore
    public Supermercato(JFrame homeFrame, Controller controller) {
        frameSupermercato = new JFrame("Supermercati");
        frameSupermercato.setContentPane(supermercatoPanel);
        frameSupermercato.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSupermercato.pack();
        frameSupermercato.setVisible(true);

        frameSupermercato.setResizable(false); //non cambia dimensione
        frameSupermercato.setSize(450, 450); //grandezza della finestra
        frameSupermercato.setLocationRelativeTo(null); //finestra si apre al centro
        frameSupermercato.setVisible(true);

        //bottone da supermercato a conad
        conadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (conad == null) {
                    conad = new BoxConad(frameSupermercato, controller);
                } else {
                    BoxConad.getFrameConad().setVisible(true);
                }
                frameSupermercato.setVisible(false);
            }
        });

        //bottone da supermercato a sole365
        sole365Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sole365 == null) {
                    sole365 = new BoxSole365(frameSupermercato, controller);
                } else {
                    BoxSole365.getFrameSole365().setVisible(true);
                }
                frameSupermercato.setVisible(false);
            }
        });

        //bottone da supermercato a despar
        desparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (despar == null) {
                    despar = new BoxDespar(frameSupermercato, controller);
                } else {
                    BoxDespar.getFrameDespar().setVisible(true);
                }
                frameSupermercato.setVisible(false);
            }
        });

        //Jlabel cliccabile, per tornare dalla pagina dei supermercati alla home
        tornaHomeS.setCursor (new Cursor(Cursor.HAND_CURSOR)); //cambia il cursore

        tornaHomeS.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                homeFrame.setVisible (true) ;
                frameSupermercato.setVisible (false);
            }
        });
    }

    // Metodo getter per far recuperare alla Home lo stesso frame dei supermercati
    public static JFrame getFrameSupermercato() {
        return frameSupermercato;
    }
}
