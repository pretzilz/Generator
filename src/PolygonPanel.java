package PolygonGenerator;

import java.awt.*;
import javax.swing.*;
import java.util.*;
class PolygonPanel extends JPanel {

    Polygon polygonToDraw;
    
    public PolygonPanel(Polygon poly) {
        this.polygonToDraw = poly;
        this.setPreferredSize(new Dimension(500, 500));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x1, y1, x2, y2;
        int vertexRadius = 5;   //pixels, that is
        for (int vertexIndex = 1; vertexIndex < polygonToDraw.TopChain.size(); vertexIndex++) {
            x1 = polygonToDraw.TopChain.get(vertexIndex - 1).getX();
            y1 = polygonToDraw.TopChain.get(vertexIndex - 1).getY();
            x2 = polygonToDraw.TopChain.get(vertexIndex).getX();
            y2 = polygonToDraw.TopChain.get(vertexIndex).getY();
            g.drawLine(x1, y1, x2, y2);
            g.fillOval(polygonToDraw.getVertex(vertexIndex).getX() - (vertexRadius/2), polygonToDraw.getVertex(vertexIndex).getY() - (vertexRadius/2), vertexRadius, vertexRadius);
        }
    }
}