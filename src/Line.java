import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class Line extends JComponent implements MouseMotionListener, MouseListener{
	ArrayList<Shape> shapes = new ArrayList<Shape>();
    Point startDrag;
    Point endDrag;
    Line2D line2d;
    
    public Line(){
    	super();
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    @Override
    public void paint(Graphics g){
    	//super.paintComponent(g);
    	Graphics2D g2d = (Graphics2D) g;
    	if(startDrag!=null && endDrag!=null){
    		g2d.setPaint(Color.BLACK);
            g2d.setStroke(new BasicStroke(5.0f));
            g2d.setPaint(Color.LIGHT_GRAY);
            g2d.draw(line2d);
            
           
    	}
        
         for (Shape s : shapes) {
                    g2d.setPaint(Color.BLACK);
                    g2d.draw(s);
                    //g2d.setPaint(colors[(colorIndex++) % 1]);
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        endDrag = e.getPoint();
        line2d = new Line2D.Double(startDrag, endDrag); 
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
    	startDrag = e.getPoint();
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    	endDrag = e.getPoint();
        line2d = new Line2D.Double(startDrag, endDrag); 
        shapes.add(line2d);
        repaint();
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    
    @Override
    public void mouseExited(MouseEvent e) {
 	   
    }
}