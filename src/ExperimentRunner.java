package PolygonGenerator;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.util.UUID;

public class ExperimentRunner implements Runnable {
    
    public PolygonPanel panel;
    public int numVertices;

    public ExperimentRunner(PolygonPanel panel, int numVertices) {
        this.panel = panel;
        this.numVertices = numVertices;
    }
    public void run() {
        long polygonIndex = 0;
        //while(true) {
            try {
                String polygonId = UUID.randomUUID().toString();
                Polygon poly = new Polygon(numVertices, polygonId);
                panel.polygonToDraw = poly;
                // ActionListener savePolygonDrawing = new ActionListener() {
                //     public void actionPerformed(ActionEvent evt) {
                //         panel.savePolygon(polygonId);
                //     }
                // };
                if (poly.hasDesiredSolution) {
                    panel.repaint();
                    //Timer timer = new Timer(1000, savePolygonDrawing).start();
                    panel.savePolygon(polygonId);
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
        //}
    }
}