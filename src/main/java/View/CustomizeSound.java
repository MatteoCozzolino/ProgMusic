package View;

import Controller.NoteGeneration;
import jm.music.data.Score;

import javax.swing.*;
import java.awt.*;

public class CustomizeSound extends JFrame {

    /**
     * This class is the main graphical interface of the program, it contains the fields to customize the melody and through the activation of the startSonorozation button
     * calls the melodyGeneration(...) method which returns the score to be played.
     */

    private final NoteGeneration melodyGeneration = new NoteGeneration();
    public Score score;

    private JButton startSonorization;
    private JComboBox<String> rhythmChoice;
    private JComboBox<String> dynamicChoice;
    private JComboBox<String> panChoice;

    public CustomizeSound(){

        super("Settings");
        pack();
        this.setSize(900,130);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        startSonorization = new JButton("Start");
        rhythmChoice = new JComboBox<String>(new String[]{"Random", "un quarto",  "un ottavo", "un sedicesimo", "un trentaduesimo" });
        dynamicChoice = new JComboBox<String>(new String[]{"Pianissimo", "Mezzo Piano", "Mezzo Forte", "Forte", "Fortissimo"});
        panChoice = new JComboBox<String>(new String[]{"Sinistra", "Centrale", "Destra", "Alternato"});

        setLayout(new FlowLayout());

        JPanel dynamicSelection = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(dynamicSelection);
        dynamicSelection.add(new JLabel("Volume: "));
        dynamicSelection.add(dynamicChoice);

        JPanel rhythmSelection = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(rhythmSelection);
        rhythmSelection.add(new JLabel("Ritmo, note da: "));
        rhythmSelection.add(rhythmChoice);

        JPanel panSelection = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(panSelection);
        panSelection.add(new JLabel("Pan: "));
        panSelection.add(panChoice);

        JPanel startPanel = new JPanel(new BorderLayout());
        add(startPanel);
        startPanel.add(startSonorization, BorderLayout.PAGE_END);
        startSonorization.addActionListener(actionEvent -> {

            setVisible(false);
            score = melodyGeneration.generateScore(rhythmChoice.getSelectedIndex(), dynamicChoice.getSelectedIndex(), panChoice.getSelectedIndex());
            launchPlayWindow(score);

        });

        this.setVisible(true);

    }

    /**
     * The launchPlayWindow(score) method opens a new window where the score will be reproduced.
     *
     */

    public void launchPlayWindow(Score score){

        new PlayWindow(score);

    }


}
