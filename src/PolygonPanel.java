package PolygonGenerator;

import java.awt.*;
import javax.swing.*;
class PolygonPanel extends JPanel {

    public PolygonPanel() {
        this.setPreferredSize(new Dimension(500, 500));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawLine(10, 10, 20, 20);
    }
}