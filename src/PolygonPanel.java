package PolygonGenerator;

import java.awt.*;
import javax.swing.*;
import java.util.*;
class PolygonPanel extends JPanel {

    
    public PolygonPanel() {
        this.setPreferredSize(new Dimension(500, 500));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Polygon polygon = new Polygon(50);    //will generate the polygon with n vertices, set to 50 for testing
        //g.drawLine(10, 10, 10, 30);
    }
}