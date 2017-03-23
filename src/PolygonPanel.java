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
            g.fillOval(polygonToDraw.TopChain.get(vertexIndex).getX() - (vertexRadius/2), polygonToDraw.TopChain.get(vertexIndex).getY() - (vertexRadius/2), vertexRadius, vertexRadius);
        }

        g.setColor(Color.RED);
        for (int vertexIndex = 0; vertexIndex < polygonToDraw.BottomChain.size(); vertexIndex++) {
            if (vertexIndex == 0) { //connect the first point in the two chains
                x1 = polygonToDraw.TopChain.get(0).getX();
                y1 = polygonToDraw.TopChain.get(0).getY();
                x2 = polygonToDraw.BottomChain.get(0).getX();
                y2 = polygonToDraw.BottomChain.get(0).getY();
            }
            else {
                x1 = polygonToDraw.BottomChain.get(vertexIndex - 1).getX();
                y1 = polygonToDraw.BottomChain.get(vertexIndex - 1).getY();
                x2 = polygonToDraw.BottomChain.get(vertexIndex).getX();
                y2 = polygonToDraw.BottomChain.get(vertexIndex).getY();
                
            }
            
            g.drawLine(x1, y1, x2, y2);

            if (vertexIndex == polygonToDraw.BottomChain.size() - 1) { //connect the last point in the two chains
                x1 = polygonToDraw.BottomChain.get(vertexIndex).getX();
                y1 = polygonToDraw.BottomChain.get(vertexIndex).getY();
                x2 = polygonToDraw.TopChain.get(polygonToDraw.TopChain.size() - 1).getX();
                y2 = polygonToDraw.TopChain.get(polygonToDraw.TopChain.size() - 1).getY();
                g.drawLine(x1, y1, x2, y2);
            }
            g.fillOval(polygonToDraw.BottomChain.get(vertexIndex).getX() - (vertexRadius/2), polygonToDraw.BottomChain.get(vertexIndex).getY() - (vertexRadius/2), vertexRadius, vertexRadius);
            

        }
    }
}