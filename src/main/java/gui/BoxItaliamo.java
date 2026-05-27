package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoxItaliamo {
    private static JFrame frameItaliamo;
    private JPanel boxItaliamo;
    private JLabel ristItaliamo;
    private JButton acquistaItaliamo;

//costruttore
    public BoxItaliamo(JFrame loginFrame, Controller controller) {

        frameItaliamo = new JFrame("Italiamo");
        frameItaliamo.setContentPane(boxItaliamo);
        frameItaliamo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameItaliamo.pack();
        frameItaliamo.setVisible(true);

        //per la grandezza della finestra
        frameItaliamo.setResizable(false);
        frameItaliamo.setSize(450, 450);
        frameItaliamo.setLocationRelativeTo(null);
        frameItaliamo.setVisible(true);


    }
    }

