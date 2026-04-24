package ui;

import model.*;
import simulation.SimulationEngine;

import javax.swing.*;
import java.awt.*;

public class SimulatorPanel extends JPanel {

    private SimulationEngine engine;
    private Grid grid;

    public SimulatorPanel(SimulationEngine engine, Grid grid) {
        this.engine = engine;
        this.grid = grid;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Person[][] people = grid.getPeople();

        int cellSize = 10;

        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeigth(); y++) {

                Person p = people[x][y];

                switch (p.state) {
                    case SUSCEPTIBLE -> g.setColor(Color.GREEN);
                    case INFECTED -> g.setColor(Color.RED);
                    case RECOVERED -> g.setColor(Color.BLUE);
                }

                g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }
    }
}
