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
	    PaintPanel content = new PaintPanel();
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
		setContentPane( new PaintPanel() );
	}
	
	
	
}