package PolygonGenerator;

import java.awt.*;
import javax.swing.*;
import java.util.*;
class PolygonPanel extends JPanel {

    Polygon polygonToDraw;

    public PolygonPanel() {
        this.polygonToDraw = null;
        //this.setMinimumSize(new Dimension(500, 500));
        //this.setSize(new Dimension(500, 500));
    }

    public void generate(int numVertices) {
        Polygon poly = new Polygon(numVertices);
        polygonToDraw = poly;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.polygonToDraw != null) {
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

    public boolean polygonHasBeenGenerated() {
        return this.polygonToDraw != null;
    }

    public void savePolygon() {
        this.polygonToDraw.savePolygon();
    }
}