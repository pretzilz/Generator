package PolygonGenerator;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
class PolygonPanel extends JPanel {

    Polygon polygonToDraw;
    private BufferedImage polygonImage;

    public PolygonPanel() {
        this.polygonToDraw = null;
        this.polygonImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
    }

    public void generate(int numVertices) {
        Polygon poly = new Polygon(numVertices);
        polygonToDraw = poly;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        polygonImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
        Graphics g = polygonImage.createGraphics();
        drawPolygon(g);
        graphics.drawImage(polygonImage, 0, 0, null);
        g.dispose();
        
    }

    public boolean polygonHasBeenGenerated() {
        return this.polygonToDraw != null;
    }

    public void savePolygon() {
        try {
            this.polygonToDraw.printToFile();
            ImageIO.write(polygonImage, "PNG", new File("polygon.png"));
        } catch (IOException ex) {
            System.out.println("¯\\_(ツ)_/¯ \n" + ex.getMessage());
        }
        

        
    }

    private void drawPolygon(Graphics g) {
        if (this.polygonToDraw != null) {
            int x1, y1, x2, y2;
            int vertexRadius = 5;   //pixels, that is
            for (int edgeIndex = 0; edgeIndex < polygonToDraw.Edges.size(); edgeIndex++) {
                x1 = polygonToDraw.Edges.get(edgeIndex).getPoint1().getX() + 10;    //so it's not right up against the edge of the window
                y1 = this.getSize().height - polygonToDraw.Edges.get(edgeIndex).getPoint1().getY();
                x2 = polygonToDraw.Edges.get(edgeIndex).getPoint2().getX() + 10;
                y2 = this.getSize().height - polygonToDraw.Edges.get(edgeIndex).getPoint2().getY();
                if (!polygonToDraw.Edges.get(edgeIndex).onTop()) {
                    g.setColor(Color.RED);
                }
                else {
                    g.setColor(Color.BLUE);
                }
                g.drawLine(x1, y1, x2, y2);
                if (edgeIndex == 0) {
                    g.fillOval(polygonToDraw.Edges.get(edgeIndex).getPoint1().getX() - (vertexRadius/2) + 10, this.getSize().height - polygonToDraw.Edges.get(edgeIndex).getPoint1().getY() - (vertexRadius/2), vertexRadius, vertexRadius);
                }
                g.fillOval(polygonToDraw.Edges.get(edgeIndex).getPoint2().getX() - (vertexRadius/2) + 10, this.getSize().height - polygonToDraw.Edges.get(edgeIndex).getPoint2().getY() - (vertexRadius/2), vertexRadius, vertexRadius);
            }
        }
    }
}
