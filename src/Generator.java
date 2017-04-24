package PolygonGenerator;
import java.awt.*;
import javax.swing.*;
import javax.swing.Box;
import java.awt.event.*;

public class Generator {

    public static int verticesToGenerate = 10;

    public static String currentPolygonId;
    public static void main (String args[]) {
        JFrame frame = new JFrame("Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUpFrame(frame);
        frame.pack();
        frame.setVisible(true);
        
    }

    public static void setUpFrame(JFrame frame) {
        Box box = new Box(BoxLayout.Y_AXIS);
        PolygonPanel polygonPanel = new PolygonPanel();
        polygonPanel.setMinimumSize(new Dimension(2000, 2000));
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if ("generateNew".equals(event.getActionCommand())) {
                    polygonPanel.generate(verticesToGenerate);
                }
                if ("setVertices".equals(event.getActionCommand())) {
                    String vertices = (String)JOptionPane.showInputDialog(frame, "Vertices:", "Vertices", JOptionPane.PLAIN_MESSAGE, null, null, verticesToGenerate);
                    verticesToGenerate = Integer.parseInt(vertices);
                }
                if ("endRun".equals(event.getActionCommand())) {
                    polygonPanel.stopRun();
                }
            }
        };
        polygonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        box.add(polygonPanel);
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JButton generateNew = new JButton("Run Generator");
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
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));

        JButton endRun = new JButton("End Run");
        endRun.setVerticalTextPosition(AbstractButton.CENTER);
        endRun.setHorizontalTextPosition(AbstractButton.LEADING);
        endRun.setActionCommand("endRun");
        endRun.addActionListener(listener);
        buttonPane.add(endRun);

        box.add(buttonPane);
        frame.add(box);
        frame.setMinimumSize(new Dimension(500, 400));
    }

}