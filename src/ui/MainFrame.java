package ui;

import simulation.*;
import model.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private SimulationEngine engine;
    private Grid grid;

    private SimulatorPanel simulatorPanel;
    private ControlPanel controlPanel;
    private StatsPanel statsPanel;
    private GraphPanel graphPanel;

    private Timer timer;

    public MainFrame(SimulationEngine engine, Grid grid) {

        this.engine = engine;
        this.grid = grid;
        setTitle("Epidemic Simulator");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        simulatorPanel = new SimulatorPanel(engine, grid);
        statsPanel = new StatsPanel();

        graphPanel = new GraphPanel(
                engine.getSusceptibleHistory(),
                engine.getInfectedHistory(),
                engine.getRecoveredHistory()
        );

        timer = new Timer(200, e -> {

            engine.step();

            simulatorPanel.repaint();
            graphPanel.repaint();

            statsPanel.update(
                    engine.getSusceptibleHistory().getLast(),
                    engine.getInfectedHistory().getLast(),
                    engine.getRecoveredHistory().getLast()
            );
        });

        controlPanel = new ControlPanel(

                () -> timer.start(),

                () -> timer.stop(),

                () -> {
                    engine.step();
                    simulatorPanel.repaint();
                    graphPanel.repaint();
                },

                () -> {
                    timer.stop();
                    engine.reset();
                    simulatorPanel.repaint();
                    graphPanel.repaint();
                },

                (delay) -> timer.setDelay(delay)
        );

        add(controlPanel, BorderLayout.NORTH);
        add(simulatorPanel, BorderLayout.CENTER);
        JSplitPane bottomSplit = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                statsPanel,
                graphPanel
        );

        bottomSplit.setResizeWeight(0.1);
        bottomSplit.setDividerSize(5);
        bottomSplit.setPreferredSize(new Dimension(1000, 200));

        add(bottomSplit, BorderLayout.SOUTH);

        setVisible(true);
    }
    public void startSimulation() {
        timer.start();
    }

    public void pauseSimulation() {
        timer.stop();
    }

    public void setSpeed(int delay) {
        timer.setDelay(delay);
    }

    public void reset() {
        timer.stop();
        engine.reset();
        simulatorPanel.repaint();
    }
    public static void main(String[] args) {

        Grid grid = new Grid(50, 50);
        grid.getPerson(40, 40).state = Person.State.INFECTED;
        grid.getPerson(10,10).state=Person.State.INFECTED;
        SimulationEngine engine = new SimulationEngine(grid);

        new MainFrame(engine, grid);
    }
}
