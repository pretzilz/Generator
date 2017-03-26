package PolygonGenerator;
import java.awt.*;
import javax.swing.*;
import javax.swing.Box;
import java.awt.event.*;
public class Generator {
    //TODO add button to generate another polygon
    //TODO add ability to save image of polygon
    //TODO add ability to set vertices in gui

    public static int verticesToGenerate;
    public static void main (String args[]) {
        JFrame frame = new JFrame("Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUpFrame(frame);
        frame.pack();
        frame.setVisible(true);
        
    }

    public static void setUpFrame(JFrame frame) {
        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(Box.createVerticalGlue());
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if ("generateNew".equals(event.getActionCommand())) {
                    //generate(frame);
                }
                if ("setVertices".equals(event.getActionCommand())) {
                    System.out.println("Set vertices");
                }
            }
        };
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());

        JButton generateNew = new JButton("Generate new polygon");
        generateNew.setVerticalTextPosition(AbstractButton.CENTER);
        generateNew.setHorizontalTextPosition(AbstractButton.LEADING);
        generateNew.setActionCommand("generateNew");
        generateNew.addActionListener(listener);
        buttonPane.add(generateNew);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));

        JButton setVertices = new JButton("Set vertices");
        setVertices.setVerticalTextPosition(AbstractButton.CENTER);
        setVertices.setHorizontalTextPosition(AbstractButton.LEADING);
        setVertices.setActionCommand("setVertices");
        setVertices.addActionListener(listener);
        buttonPane.add(setVertices);
        box.add(buttonPane);
        box.add(Box.createVerticalGlue());
        frame.add(box);
        frame.setMinimumSize(new Dimension(100, 100));
        //frame.setMinimumSize(new Dimension(poly.getHeight() + 50, poly.getWidth() + 50));
    }


    public static void generate(JFrame frame) {
        int verticesToGenerate = 50;
        Polygon poly = new Polygon(verticesToGenerate);
        poly.printToFile();
        PolygonPanel p = new PolygonPanel(poly);
        
        //box.add(p);
    }


}