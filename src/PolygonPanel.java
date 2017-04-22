package PolygonGenerator;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.util.UUID;
class PolygonPanel extends JPanel {

    Polygon polygonToDraw;
    private BufferedImage polygonImage;

    public boolean continueRunning = true;

    public PolygonPanel() {
        this.polygonToDraw = null;
        this.polygonImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
    }

    public void generate(int numVertices) {
        //EventQueue.invokeLater(new Runnable() {
            //public void run() {
                long polygonIndex = 0;
                while(continueRunning) {
                    try {
                        String polygonId = UUID.randomUUID().toString();
                        Polygon poly = new Polygon(numVertices, polygonId);
                        polygonToDraw = poly;

                        if (poly.hasDesiredSolution) {
                            repaint();
                            savePolygon(polygonId);
                        }
                        else {
                            ProcessBuilder pr = new ProcessBuilder("cmd", "/c del glpsol_out\\" + polygonId + ".txt");
                            Process p = pr.start();
                            p.waitFor();
                            pr = new ProcessBuilder("cmd", "/c del lp_constraints\\" + polygonId + ".txt");
                            p = pr.start();
                            p.waitFor();
                        }

                        polygonIndex++;
                    } catch(Exception e) {
                        System.out.println("¯\\_(ツ)_/¯ \n" + e.getMessage());
                    }
                }
            //}
        //});
        
    }

    public void stopRun() {
        continueRunning = false;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.polygonImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
        Graphics g = this.polygonImage.createGraphics();
        drawPolygon(g);
        graphics.drawImage(polygonImage, 0, 0, null);
        g.dispose();
        
    }

    public boolean polygonHasBeenGenerated() {
        return this.polygonToDraw != null;
    }

    public void savePolygon(String polygonId) {
        try {
            boolean directoryCreated = new File("saved_polygons/").mkdir();
            directoryCreated = new File("saved_polygons/images/").mkdir();
            directoryCreated = new File("saved_polygons/data/").mkdir();
            this.polygonToDraw.printToFile(polygonId);
            ImageIO.write(polygonImage, "PNG", new File("saved_polygons/images/" + polygonId + ".png"));
        } catch (IOException ex) {
            System.out.println("¯\\_(ツ)_/¯ \n" + ex.getMessage());
        }
        

        
    }

    private void drawPolygon(Graphics g) {
        if (this.polygonToDraw != null) {
            int x1, y1, x2, y2;
            int vertexRadius = 5;   //pixels, that is
            
            for (int edgeIndex = 0; edgeIndex < polygonToDraw.Edges.size(); edgeIndex++) {
                g.setColor(Color.MAGENTA);
                x1 = polygonToDraw.Edges.get(edgeIndex).getPoint1().getX() + 10;    //so it's not right up against the edge of the window
                y1 = this.getSize().height - polygonToDraw.Edges.get(edgeIndex).getPoint1().getY();
                x2 = polygonToDraw.Edges.get(edgeIndex).getPoint2().getX() + 10;
                y2 = this.getSize().height - polygonToDraw.Edges.get(edgeIndex).getPoint2().getY();

                g.drawLine(x1, y1, x2, y2);
                g.drawString(polygonToDraw.Edges.get(edgeIndex).getPoint1().index + "", x1, y1 + 20);
                if (edgeIndex == polygonToDraw.Edges.size() - 1) {
                    g.drawString(polygonToDraw.Edges.get(edgeIndex).getPoint2().index + "", x2, y2 + 20);
                }
                if (polygonToDraw.Edges.get(edgeIndex).getPoint2().onTop()) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.RED);
                }
                if (edgeIndex == 0) {
                    g.setColor(Color.BLACK);
                    g.fillOval(polygonToDraw.Edges.get(edgeIndex).getPoint1().getX() - (vertexRadius/2) + 10, this.getSize().height - polygonToDraw.Edges.get(edgeIndex).getPoint1().getY() - (vertexRadius/2), vertexRadius, vertexRadius);
                }
                g.fillOval(polygonToDraw.Edges.get(edgeIndex).getPoint2().getX() - (vertexRadius/2) + 10, this.getSize().height - polygonToDraw.Edges.get(edgeIndex).getPoint2().getY() - (vertexRadius/2), vertexRadius, vertexRadius);
            }
        }
    }
}
