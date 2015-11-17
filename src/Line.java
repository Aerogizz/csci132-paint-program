import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Line extends JPanel {

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    float[] dash1 = { 2f, 0f, 2f };

    g2d.drawLine(20, 40, 250, 40);



    }
}