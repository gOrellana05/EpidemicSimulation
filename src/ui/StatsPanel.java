package ui;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {

    private JLabel sLabel = new JLabel();
    private JLabel iLabel = new JLabel();
    private JLabel rLabel = new JLabel();

    public StatsPanel() {

        setLayout(new FlowLayout());
        setBackground(Color.DARK_GRAY);

        sLabel.setForeground(Color.GREEN);
        iLabel.setForeground(Color.RED);
        rLabel.setForeground(Color.BLUE);

        add(sLabel);
        add(iLabel);
        add(rLabel);
    }

    public void update(int s, int i, int r) {
        sLabel.setText("S: " + s);
        iLabel.setText(" I: " + i);
        rLabel.setText(" R: " + r);
    }
}