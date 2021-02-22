package View;

import jm.music.data.Score;
import jm.util.Play;

import javax.swing.*;
import java.awt.*;

public class PlayWindow extends JFrame{

    /**
     * This class contains a button to reproduce the score and, at the end of it, it will open the CustomizeSound window again letting the user the possibility to generate
     * another score with different parameters.
     */

    private JButton playButton;

    public PlayWindow(Score score) {

        super("Reproducing music....");
        pack();
        this.setSize(400, 130);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        playButton = new JButton("Play");

        setLayout(new FlowLayout());

        JPanel playPanel = new JPanel(new BorderLayout());
        add(playPanel);
        playPanel.add(playButton, BorderLayout.PAGE_END);
        playButton.addActionListener(actionEvent -> {

            Play.midi(score);
            this.setVisible(false);
            new CustomizeSound();

        });

        this.setVisible(true);
    }


}
