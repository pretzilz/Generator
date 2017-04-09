package PolygonGenerator;
import java.awt.*;
import javax.swing.*;
import javax.swing.Box;
import java.awt.event.*;
import java.util.UUID;
public class Generator {
    //TODO add ability to save image of polygon

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
        polygonPanel.setMinimumSize(new Dimension(500, 250));
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if ("generateNew".equals(event.getActionCommand())) {
                    generateNewPolygon(polygonPanel);
                }
                if ("setVertices".equals(event.getActionCommand())) {
                    String vertices = (String)JOptionPane.showInputDialog(frame, "Vertices:", "Vertices", JOptionPane.PLAIN_MESSAGE, null, null, verticesToGenerate);
                    verticesToGenerate = Integer.parseInt(vertices);
                }
                if ("savePolygon".equals(event.getActionCommand())) {
                    if (polygonPanel.polygonHasBeenGenerated()) {
                        polygonPanel.savePolygon(currentPolygonId);
                    }
                }
            }
        };
        polygonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        box.add(polygonPanel);
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

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
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));

        JButton savePolygon = new JButton("Save polygon");
        savePolygon.setVerticalTextPosition(AbstractButton.CENTER);
        savePolygon.setHorizontalTextPosition(AbstractButton.LEADING);
        savePolygon.setActionCommand("savePolygon");
        savePolygon.addActionListener(listener);
        buttonPane.add(savePolygon);

        box.add(buttonPane);
        frame.add(box);
        frame.setMinimumSize(new Dimension(500, 400));
    }


    public static void generateNewPolygon(PolygonPanel polygonPanel) {
        currentPolygonId = UUID.randomUUID().toString();
        polygonPanel.generate(verticesToGenerate, currentPolygonId);
    }


}