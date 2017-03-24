package PolygonGenerator;

import java.awt.*;
import javax.swing.*;
import java.util.*;
class PolygonPanel extends JPanel {

    Polygon polygonToDraw;

    public PolygonPanel(Polygon poly) {
        this.polygonToDraw = poly;
        //TODO make this scalable
        this.setPreferredSize(new Dimension(poly.getHeight() + 50, poly.getWidth() + 50));
        this.setMaximumSize(new Dimension(poly.getHeight() + 50, poly.getWidth() + 50));
        this.setMinimumSize(new Dimension(poly.getHeight() + 50, poly.getWidth() + 50));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x1, y1, x2, y2;
        int vertexRadius = 5;   //pixels, that is
        for (int edgeIndex = 0; edgeIndex < polygonToDraw.Edges.size(); edgeIndex++) {
            x1 = polygonToDraw.Edges.get(edgeIndex).getPoint1().getX();
            y1 = this.getSize().height - polygonToDraw.Edges.get(edgeIndex).getPoint1().getY();
            x2 = polygonToDraw.Edges.get(edgeIndex).getPoint2().getX();
            y2 = this.getSize().height - polygonToDraw.Edges.get(edgeIndex).getPoint2().getY();
            if (!polygonToDraw.Edges.get(edgeIndex).onTop()) {
                g.setColor(Color.RED);
            }
            else {
                g.setColor(Color.BLUE);
            }
            g.drawLine(x1, y1, x2, y2);
            if (edgeIndex == 0) {
                g.fillOval(polygonToDraw.Edges.get(edgeIndex).getPoint1().getX() - (vertexRadius/2), this.getSize().height - polygonToDraw.Edges.get(edgeIndex).getPoint1().getY() - (vertexRadius/2), vertexRadius, vertexRadius);
            }
            g.fillOval(polygonToDraw.Edges.get(edgeIndex).getPoint2().getX() - (vertexRadius/2), this.getSize().height - polygonToDraw.Edges.get(edgeIndex).getPoint2().getY() - (vertexRadius/2), vertexRadius, vertexRadius);
        }
    }
}