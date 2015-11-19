import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.*;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*

Programmed by Tyler Mattioli and Trevor Vannoy

*/

public class Main extends JApplet {
	
	public static void main(String args[]) throws IOException{
		JFrame window = new JFrame("Simple Paint");
		JPanel center = new JPanel();
		center.setBackground(Color.WHITE);
		
		BufferedImage myPicture = ImageIO.read(new File("images/test.jpg"));
		JLabel image = new JLabel(new ImageIcon(myPicture));
		
		/*ImageIcon image = new ImageIcon("images/test.jpg");
		JLabel imageFile = new JLabel();*/
		
		
		JPanel right = new JPanel();
		
	    PaintPanel content = new PaintPanel();
	    SolidRectangles boxSolid = new SolidRectangles();
	    EmptyRectangles boxEmpty = new EmptyRectangles();
	    SolidOval ovalSolid = new SolidOval();
	    EmptyOval ovalEmpty = new EmptyOval();
	    Line line = new Line();
	    
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
	    JButton filledRectangle = new JButton(new ImageIcon("images/buttons/filledBox.png"));
	    JButton emptyRectangle = new JButton(new ImageIcon("images/buttons/emptyBox.png"));
	    JButton filledOval = new JButton(new ImageIcon("images/buttons/filledOval.png"));
	    JButton emptyOval = new JButton(new ImageIcon("images/buttons/emptyOval.png"));
	    JButton drawLine = new JButton(new ImageIcon("images/buttons/line.png"));
	    JButton freeDraw = new JButton(new ImageIcon("images/buttons/freeDraw.png"));
	    JButton clear = new JButton(new ImageIcon("images/buttons/clear.png"));
	    
	    leftToolBar.add(filledRectangle);
	    leftToolBar.add(emptyRectangle);
	    leftToolBar.add(filledOval);
	    leftToolBar.add(emptyOval);
	    leftToolBar.add(drawLine);
	    leftToolBar.add(freeDraw);
	    leftToolBar.add(clear);
	    leftToolBar.setBackground(Color.LIGHT_GRAY);
	    leftToolBar.setOpaque(true);
	    
	    
	    
	    
	    //end toolbar
	    
	    
	    //right side toolbar/buttons
	    JToolBar rightToolBar = new JToolBar(null, JToolBar.VERTICAL);
	    JButton blue = new JButton(new ImageIcon("images/buttons/blue.jpg"));
	    blue.setBackground(Color.BLUE);
	    blue.setOpaque(true);
	    JButton green = new JButton(new ImageIcon("images/buttons/green.jpg"));
	    green.setBackground(Color.GREEN);
	    green.setOpaque(true);
	    JButton red = new JButton(new ImageIcon("images/buttons/red.jpg"));
	    red.setBackground(Color.RED);
	    red.setOpaque(true);
	    JButton yellow = new JButton(new ImageIcon("images/buttons/yellow.jpg"));
	    yellow.setBackground(Color.YELLOW);
	    yellow.setOpaque(true);
	    JButton magenta = new JButton(new ImageIcon("images/buttons/magenta.jpg"));
	    magenta.setBackground(Color.MAGENTA);
	    magenta.setOpaque(true);
	    JButton black = new JButton(new ImageIcon("images/buttons/black.jpg"));
	    black.setBackground(Color.BLACK);
	    black.setOpaque(true);
	    JButton white = new JButton(new ImageIcon("images/buttons/white.jpg"));
	    white.setBackground(Color.WHITE);
	    white.setOpaque(true);
	    
	    
	    rightToolBar.add(blue);
	    rightToolBar.add(green);
	    rightToolBar.add(red);
	    rightToolBar.add(yellow);
	    rightToolBar.add(magenta);
	    rightToolBar.add(black);
	    rightToolBar.add(white);
	    right.setLayout(new BorderLayout());
	    right.add(rightToolBar, BorderLayout.CENTER);
	    rightToolBar.setBackground(Color.LIGHT_GRAY);
	    rightToolBar.setOpaque(true);
	    
	    //end toolbar
	    
	    window.setLayout(new BorderLayout());
	    window.add(b, BorderLayout.NORTH);
	    window.add(leftToolBar, BorderLayout.WEST);
	    /*window.setContentPane(content);*/
	    window.add(center, BorderLayout.CENTER);
	    window.add(right, BorderLayout.EAST);
	    center.setLayout(new BorderLayout());
	    center.add(image, BorderLayout.CENTER);
	    image.setLayout(new BorderLayout());
	    window.setSize(600,515);
	    window.setLocation(200,200);
	    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    window.setVisible(true);

	   /* ArrayList<String> list = new ArrayList<String>();*/   
	    /*Stack<String> stack = new Stack<>();*/
	    filledRectangle.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		image.removeAll();
	    		SolidRectangles temp = new SolidRectangles();
	    		/*stack.push(temp);
	    		list.add("temp");*/
	    		image.remove(temp);
	    		image.revalidate();
	    		image.repaint();
	    		image.add(temp, BorderLayout.CENTER);
	    	}
	    });
	    
	    emptyRectangle.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		image.removeAll();
	    		/*image.remove(list.get(list.size()-1));*/
	    		EmptyRectangles temp1 = new EmptyRectangles();
	    		image.revalidate();
	    		image.repaint();
	    		image.add(temp1, BorderLayout.CENTER);
	    	}
	    });
	    
	    filledOval.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		image.removeAll();
	    		SolidOval temp = new SolidOval();
	    		image.remove(temp);
	    		image.revalidate();
	    		image.repaint();
	    		image.add(temp, BorderLayout.CENTER);
	    	}
	    });
	    
	    emptyOval.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		image.removeAll();
	    		EmptyOval temp = new EmptyOval();
	    		image.remove(temp);
	    		image.revalidate();
	    		image.repaint();
	    		image.add(temp, BorderLayout.CENTER);
	    	}
                        });
	    
	    drawLine.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		image.removeAll();
	    		Line temp = new Line();
	    		image.remove(temp);
	    		image.revalidate();
	    		image.repaint();
	    		image.add(temp, BorderLayout.CENTER);
	    	}
	    });
	    
	    
	    freeDraw.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		image.removeAll();
	    		FreeDraw temp = new FreeDraw();
	    		image.remove(temp);
	    		image.revalidate();
	    		image.repaint();
	    		image.add(temp, BorderLayout.CENTER);
	    	}
	    });
	    
	    
	    clear.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		image.removeAll();
	    		image.revalidate();
	    		image.repaint();
	    	}
	    });
	}
	
	public void init() {
		setContentPane( new PaintPanel() );
	}


}