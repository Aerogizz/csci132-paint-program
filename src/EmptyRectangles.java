import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class EmptyRectangles extends JComponent {
		ArrayList<Shape> shapes = new ArrayList<Shape>();

		Point startDrag, endDrag;

		public EmptyRectangles() {
			this.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					startDrag = new Point(e.getX(), e.getY());
					endDrag = startDrag;
					repaint();
				}

				public void mouseReleased(MouseEvent e) {
					Shape r = makeRectangle(startDrag.x, startDrag.y, e.getX(), e.getY());
					shapes.add(r);
					startDrag = null;
					endDrag = null;
					repaint();
				}
			});

				this.addMouseMotionListener(new MouseMotionAdapter() {
					public void mouseDragged(MouseEvent e) {
						endDrag = new Point(e.getX(), e.getY());
						repaint();
					}
				});
		}
		private void backgroundColor(Graphics2D g2){
			g2.setPaint(Color.WHITE);
		}
		
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			backgroundColor(g2);
			Color[] colors = { Color.WHITE};
			int colorIndex = 0;

			g2.setStroke(new BasicStroke(5));
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

			for (Shape s : shapes) {
				g2.setPaint(Color.BLACK);
				g2.draw(s);
				g2.setPaint(colors[(colorIndex++) % 1]);
			}

			if (startDrag != null && endDrag != null) {
				g2.setPaint(Color.LIGHT_GRAY);
				Shape r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
				g2.draw(r);
			}
		}

		private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
			return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
		}
	}