package View;

import Model.NoteGeneration;

import javax.swing.*;
import java.awt.*;

public class CustomizeSound extends JFrame {

    private NoteGeneration melody = new NoteGeneration();

    private JButton startSonorization;
    private JComboBox<String> durationChoice;
    private JComboBox<String> rhytmChoice;
    private JComboBox<String> dynamicChoice;
    private JComboBox<String> panChoice;

    public CustomizeSound(){

        super("Settings");
        pack();
        this.setSize(900,130);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        startSonorization = new JButton("Start");
        durationChoice = new JComboBox<String>(new String[]{"things"});
        rhytmChoice = new JComboBox<String>(new String[]{"things"});
        dynamicChoice = new JComboBox<String>(new String[]{"things"});
        panChoice = new JComboBox<String>(new String[]{"things"});

        setLayout(new FlowLayout());

        JPanel dynamicSelection = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(dynamicSelection);
        dynamicSelection.add(new JLabel("Volume: "));
        dynamicSelection.add(dynamicChoice);

        JPanel durationSelection = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(durationSelection);
        durationSelection.add(new JLabel("Durata: "));
        durationSelection.add(durationChoice);

        JPanel rhytmSelection = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(rhytmSelection);
        rhytmSelection.add(new JLabel("Ritmo: "));
        rhytmSelection.add(rhytmChoice);

        JPanel panSelection = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(panSelection);
        panSelection.add(new JLabel("Pan: "));
        panSelection.add(panChoice);

        JPanel startPanel = new JPanel(new BorderLayout());
        add(startPanel);
        startPanel.add(startSonorization, BorderLayout.PAGE_END);
        startSonorization.addActionListener(actionEvent -> {
            setVisible(false);
            new NoteGeneration();
            melody.generateSound(rhytmChoice.getSelectedIndex(), dynamicChoice.getSelectedIndex(), durationChoice.getSelectedIndex(), panChoice.getSelectedIndex());
        });


        this.setVisible(true);

    }


}
