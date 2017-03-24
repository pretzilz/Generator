package PolygonGenerator;
import java.awt.*;
import javax.swing.*;
import javax.swing.Box;
public class Generator {

    public static void main (String args[]) {
        JFrame frame = new JFrame("Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        generate(frame);
        frame.pack();
        frame.setVisible(true);
        
    }

    public static void generate(JFrame frame) {
        int verticesToGenerate = 10;

        Polygon poly = new Polygon(verticesToGenerate);
        poly.printToFile();
        PolygonPanel p = new PolygonPanel(poly);
        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(Box.createVerticalGlue());
        box.add(p);
        box.add(Box.createVerticalGlue());
        frame.add(box);
        frame.setSize(new Dimension(poly.getHeight() + 50, poly.getWidth() + 50));
    }
}