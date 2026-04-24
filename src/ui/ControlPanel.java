package ui;

import simulation.SimulationEngine;

import javax.swing.*;
import java.awt.*;
import java.util.function.IntConsumer;

public class ControlPanel extends JPanel {

    public ControlPanel(Runnable start, Runnable pause,Runnable step, Runnable reset, IntConsumer setSpeed) {

        setLayout(new FlowLayout());

        JButton startBtn = new JButton("START");
        JButton pauseBtn = new JButton("PAUSE");
        JButton stepBtn = new JButton("STEP");
        JButton resetBtn = new JButton("RESET");

        JSlider speed = new JSlider(10, 500, 100);

        startBtn.addActionListener(e -> start.run());
        pauseBtn.addActionListener(e -> pause.run());
        stepBtn.addActionListener(e -> step.run());
        resetBtn.addActionListener(e -> reset.run());

        speed.addChangeListener(e -> setSpeed.accept(speed.getValue()));


        add(startBtn);
        add(pauseBtn);
        add(stepBtn);
        add(resetBtn);

    }
}
