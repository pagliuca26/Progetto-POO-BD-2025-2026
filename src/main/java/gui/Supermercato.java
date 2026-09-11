package gui;

import controller.Controller;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Supermercato {

    //componenti grafici della schermata supermercati
    private static JFrame frameSupermercato;
    private JPanel supermercatoPanel;
    private JButton conadButton;
    private JButton sole365Button;
    private JButton desparButton;
    private JLabel tornaHomeS;
    private JLabel iconaSupermercato;

    //istanze delle schermate box per preservare lo stato delle disponibilita
    private BoxConad conad = null;
    private BoxSole365 sole365 = null;
    private BoxDespar despar = null;

    //costruttore della schermata selezione supermercati
    public Supermercato(JFrame homeFrame, Controller controller) {
        frameSupermercato = new JFrame("Supermercati");
        frameSupermercato.setContentPane(supermercatoPanel);
        frameSupermercato.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSupermercato.pack();

        //impostazioni della finestra
        frameSupermercato.setResizable(false);
        frameSupermercato.setSize(450, 450);
        frameSupermercato.setLocationRelativeTo(null);
        frameSupermercato.setVisible(true);

        //navigazione verso box conad
        conadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (conad == null) {
                    conad = new BoxConad(frameSupermercato, controller);
                } else {
                    BoxConad.getFrameConad().setVisible(true);
                }
                conad.aggiornaLabelDisponibile();
                frameSupermercato.setVisible(false);
            }
        });

        //navigazione verso box sole365
        sole365Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sole365 == null) {
                    sole365 = new BoxSole365(frameSupermercato, controller);
                } else {
                    BoxSole365.getFrameSole365().setVisible(true);
                }
                sole365.aggiornaLabelDisponibile();
                frameSupermercato.setVisible(false);
            }
        });

        //navigazione verso box despar
        desparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (despar == null) {
                    despar = new BoxDespar(frameSupermercato, controller);
                } else {
                    BoxDespar.getFrameDespar().setVisible(true);
                }
                despar.aggiornaLabelDisponibile();
                frameSupermercato.setVisible(false);
            }
        });

        //ritorno alla schermata home
        tornaHomeS.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tornaHomeS.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                homeFrame.setVisible(true);
                frameSupermercato.setVisible(false);
            }
        });
    }

    //restituisce il frame della schermata supermercati
    public static JFrame getFrameSupermercato() {
        return frameSupermercato;
    }
}