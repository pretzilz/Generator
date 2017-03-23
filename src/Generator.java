package PolygonGenerator;
import java.awt.*;
import javax.swing.*;
public class Generator {
    public static void main (String args[]) {
        JFrame frame = new JFrame("Generator");
        frame.setLocation(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        generate(frame);
        frame.pack();
        frame.setVisible(true);
        
    }

    public static void generate(JFrame frame) {
        Polygon poly = new Polygon(10);
        poly.printToFile();
        PolygonPanel p = new PolygonPanel(poly);
        frame.add(p, BorderLayout.CENTER);
    }
}