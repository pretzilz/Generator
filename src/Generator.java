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
        //getTNandBN(n);
        
    }

    /*
        Page 6, Snoeyink/Zhu
        This function will get the TN and BN(?) based on the number of vertices designated.
    */
    public int getTNandBN (int n) {
        return 0;
    }

    public static void generate(JFrame frame) {
        Polygon poly = new Polygon(50);
        PolygonPanel p = new PolygonPanel(poly);
        frame.add(p, BorderLayout.CENTER);
    }
}