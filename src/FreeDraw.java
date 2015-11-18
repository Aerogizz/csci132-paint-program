import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class FreeDraw extends JComponent implements MouseMotionListener, MouseListener{
    Point point1;
    Point point2;
    Line2D line2d;
    
    public FreeDraw(){
    	super();
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    @Override
    public void paintComponent(Graphics g){
    	//super.paintComponent(g);
    	Graphics2D g2d = (Graphics2D) g;
    	if(point1!=null && point2!=null){
    		g2d.setPaint(Color.BLACK);
            g2d.setStroke(new BasicStroke(1.5f));
            g2d.draw(line2d);
    	}
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
    	point2 = e.getPoint();
        line2d = new Line2D.Double(point1, point2); 
        point1 = point2;
        repaint();
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {

    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    	
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    	point1 = e.getPoint();
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    	
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    
    @Override
    public void mouseExited(MouseEvent e) {
 	   
    }
}