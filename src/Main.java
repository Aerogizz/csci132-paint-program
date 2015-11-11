import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JApplet {
	
	public static void main(String args[]){
		JFrame window = new JFrame("Simple Paint");
	    paintPanel content = new paintPanel();
	    //menu bar stuff
	    JMenuBar b = new JMenuBar();
	    JMenu file = new JMenu("File");
	    JMenu edit = new JMenu("Edit");
	    JMenu help = new JMenu("Help");
	    b.add(file);
	    b.add(edit);
	    b.add(help);
	    //file items
	    JMenuItem save = new JMenuItem("Save");
	    JMenuItem load = new JMenuItem("Load");
	    file.add(save);
	    file.add(load);
	    
	    //edit items
	    JMenuItem cut = new JMenuItem("Cut");
	    JMenuItem copy = new JMenuItem("Copy");
	    JMenuItem paste = new JMenuItem("Paste");
	    edit.add(cut);
	    edit.add(copy);
	    edit.add(paste);
	    
	    //help items
	    JMenuItem about = new JMenuItem("About");
	    help.add(about);
	    
	    //help window
	    JFrame helpWindow = new JFrame("About");
	    JPanel helpPanel = new JPanel();
	    JTextArea helpText = new JTextArea("This paint program was designed by Mario and Luigi.", 12, 25);

	    helpText.setLineWrap(true);
	    helpText.setWrapStyleWord(true);
	    helpWindow.add(helpPanel);
	    helpPanel.add(helpText);
	    
	    about.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		helpWindow.setSize(300,235);
	    		helpWindow.setLocation(200,200);
	    		helpWindow.setResizable(false);
	    		helpWindow.setVisible(true);
	    	}
	    });
	    
	    //end menu bar
	    
	    //left side buttons/toolbar
	    JToolBar leftToolBar = new JToolBar(null, JToolBar.VERTICAL);
	    JButton filledRectangle = new JButton(new ImageIcon("filledBox.jpg"));
	    JButton emptyRectangle = new JButton(new ImageIcon("emptyBox.jpg"));
	    JButton filledOval = new JButton(new ImageIcon("filledOval.jpg"));
	    JButton emptyOval = new JButton(new ImageIcon("emptyOval.jpg"));
	    JButton line = new JButton(new ImageIcon("line.jpg"));
	    JButton freeDraw = new JButton(new ImageIcon("freeDraw.png"));
	    
	    leftToolBar.add(filledRectangle);
	    leftToolBar.add(emptyRectangle);
	    leftToolBar.add(filledOval);
	    leftToolBar.add(emptyOval);
	    leftToolBar.add(line);
	    leftToolBar.add(freeDraw);
	    
	    //end toolbar
	    
	    window.setLayout(new BorderLayout());
	    window.add(b, BorderLayout.NORTH);
	    window.add(leftToolBar, BorderLayout.WEST);
	    /*window.setContentPane(content);*/
	    window.add(content, BorderLayout.CENTER);
	    window.setSize(600,480);
	    window.setLocation(200,200);
	    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    window.setVisible(true);
	}
	
	public void init() {
		setContentPane( new paintPanel() );
	}
	
	
	public static class paintPanel extends JPanel implements MouseListener, MouseMotionListener {
		private final static int BLACK = 0, RED = 1, GREEN = 2, BLUE = 3, CYAN = 4, MAGENTA = 5, YELLOW = 6;
		private int currentColor = BLACK;
		private int prevX, prevY;
		private boolean dragging;
		private Graphics graphicsForDrawing;
		
		paintPanel() {
			setBackground(Color.WHITE);
			addMouseListener(this);
			addMouseMotionListener(this);
		}
		@Override
		public void paintComponent(Graphics g2) {
			super.paintComponent(g2);
			int width = getWidth();
			int height = getHeight();
			int colorSpacing = (height - 56) / 7;
			
			//gray borders
			g2.setColor(Color.GRAY);
			g2.drawRect(0, 0, width-1, height-1);
			g2.drawRect(1, 1, width-3, height-3);
			g2.drawRect(2, 2, width-5, height-5);
			
			g2.fillRect(width - 56, 0, 56, height);
			
			//clear box
			g2.setColor(Color.WHITE);
	        g2.fillRect(width-53,  height-53, 50, 50);
	        g2.setColor(Color.BLACK);
	        g2.drawRect(width-53, height-53, 49, 49);
	        g2.drawString("CLEAR", width-48, height-23);
	        
	        //color boxes
	        g2.setColor(Color.BLACK);
	        g2.fillRect(width-53, 3 + 0*colorSpacing, 50, colorSpacing-3);
	        g2.setColor(Color.RED);
	        g2.fillRect(width-53, 3 + 1*colorSpacing, 50, colorSpacing-3);
	        g2.setColor(Color.GREEN);
	        g2.fillRect(width-53, 3 + 2*colorSpacing, 50, colorSpacing-3);
	        g2.setColor(Color.BLUE);
	        g2.fillRect(width-53, 3 + 3*colorSpacing, 50, colorSpacing-3);
	        g2.setColor(Color.CYAN);
	        g2.fillRect(width-53, 3 + 4*colorSpacing, 50, colorSpacing-3);
	        g2.setColor(Color.MAGENTA);
	        g2.fillRect(width-53, 3 + 5*colorSpacing, 50, colorSpacing-3);
	        g2.setColor(Color.YELLOW);
	        g2.fillRect(width-53, 3 + 6*colorSpacing, 50, colorSpacing-3);
	        
	        //current color
	        g2.setColor(Color.WHITE);
	        g2.drawRect(width-55, 1 + currentColor*colorSpacing, 53, colorSpacing);
	        g2.drawRect(width-54, 2 + currentColor*colorSpacing, 51, colorSpacing-2);
	        
	        
		}
		
		private void changeColor(int y){
			int width = getWidth();
	        int height = getHeight();
	        int colorSpacing = (height - 56) / 7;
	        int newColor = y / colorSpacing;
	        
	        if (newColor < 0 || newColor > 6){
	        	return;
	        }
	        
	        Graphics2D g2 = (Graphics2D) getGraphics();
	        g2.setColor(Color.GRAY);
	        g2.drawRect(width-55, 1 + currentColor*colorSpacing, 53, colorSpacing);
	        g2.drawRect(width-54, 2 + currentColor*colorSpacing, 51, colorSpacing-2);
	        currentColor = newColor;
	        g2.setColor(Color.WHITE);
	        g2.drawRect(width-55, 1 + currentColor*colorSpacing, 53, colorSpacing);
	        g2.drawRect(width-54, 2 + currentColor*colorSpacing, 51, colorSpacing-2);
	        g2.dispose();
	        
	        
		}
		
		
		private void setUpDrawingGraphics() {
			graphicsForDrawing = getGraphics();
	        switch (currentColor) {
	        case BLACK:
	           graphicsForDrawing.setColor(Color.BLACK);
	           break;
	        case RED:
	           graphicsForDrawing.setColor(Color.RED);
	           break;
	        case GREEN:
	           graphicsForDrawing.setColor(Color.GREEN);
	           break;
	        case BLUE:
	           graphicsForDrawing.setColor(Color.BLUE);
	           break;
	        case CYAN:
	           graphicsForDrawing.setColor(Color.CYAN);
	           break;
	        case MAGENTA:
	           graphicsForDrawing.setColor(Color.MAGENTA);
	           break;
	        case YELLOW:
	           graphicsForDrawing.setColor(Color.YELLOW);
	           break;
	        }
		}
		
		
		public void mousePressed(MouseEvent evt) {
			int x = evt.getX();
			int y = evt.getY();
			
			int width = getWidth();
			int height = getHeight();
			
			if (dragging == true){
				return;
			}
			if (x > width - 53){
				if (y > height - 53){
					repaint();
				}else{
					changeColor(y);
				}
			}
			else if (x > 3 && x < width - 56 && y > 3 && y < height - 3) {
				prevX = x;
	            prevY = y;
	            dragging = true;
	            setUpDrawingGraphics();
			}
		}
		
		
		public void mouseReleased(MouseEvent evt) {
			if (dragging == false){
				return;
			}
			dragging = false;
	        graphicsForDrawing.dispose();
	        graphicsForDrawing = null;
		}
		
		
		public void mouseDragged(MouseEvent evt) {
			if (dragging == false){
				return;
			}
			int x = evt.getX();
			int y = evt.getY();
			
			if (x < 3){
				x = 3;
			}
			if (x > getWidth() - 57){
				x = getWidth() - 57;
			}
			if (y < 3){
				y = 3;
			}
			if (y > getHeight() - 4){
				y = getHeight() -4;
			}
			graphicsForDrawing.drawLine(prevX, prevY, x, y);
			prevX = x;
			prevY = y;
		}
		
		public void mouseEntered(MouseEvent evt) {
			
		}
		public void mouseExited(MouseEvent evt) {
			
		}
		public void mouseClicked(MouseEvent evt) {
			
		}
		public void mouseMoved(MouseEvent evt) {
			
		}
		
	}
}