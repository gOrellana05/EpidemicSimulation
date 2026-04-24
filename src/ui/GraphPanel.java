package ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraphPanel extends JPanel {

    private List<Integer> s;
    private List<Integer> i;
    private List<Integer> r;
    int padding = 20;

    public GraphPanel(List<Integer> s, List<Integer> i, List<Integer> r) {
        this.s = s;
        this.i = i;
        this.r = r;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (s.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        int max = Math.max(
                Math.max(s.get(s.size()-1), i.get(i.size()-1)),
                r.get(r.size()-1)
        );

        draw(g2, s, w, h, max, Color.GREEN, 3);
        draw(g2, i, w, h, max, Color.RED, 3);
        draw(g2, r, w, h, max, Color.BLUE, 3);
    }

    private void draw(Graphics2D g2, List<Integer> data, int w, int h, int max, Color c, int thickness) {

        g2.setColor(c);
        g2.setStroke(new BasicStroke(thickness));

        for (int i = 1; i < data.size(); i++) {

            int x1 = (i - 1) * w / data.size();
            int x2 = i * w / data.size();

            int y1 = h - (data.get(i - 1) * h / max);
            int y2 = h - (data.get(i) * h / max);

            g2.drawLine(x1, y1, x2, y2);
        }
    }
}