import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * ACMGraphics
 * 
 * This applet demonstrates how to implement the ACM graphics classes in a
 * simplistic way.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class ACMGraphics extends Applet {

	private ArrayList<GRect> gObjects = new ArrayList<GRect>();

	/**
	 * Similar to GraphicsProgram init() method, here we can write our code with
	 * GRects, etc.
	 */
	public void init() {
		setBackground(Color.WHITE);
		setSize(300, 150);

		GRect fritz = new GRect(120, 70, 30, 10);
		fritz.setColor(Color.RED);
		add(fritz);

		GRect lisa = new GRect(150, 70, 30, 10);
		lisa.setColor(Color.RED);
		add(lisa);
	}

	/**
	 * Adding a graphics object to the ArrayList.
	 * 
	 * @param r
	 */
	private void add(GRect r) {
		gObjects.add(r);
	}

	/**
	 * The applets standard paint() method, draws all the graphics objects. It
	 * gets called by the system, after a call to the repaint() method.
	 */
	@Override
	public void paint(Graphics g) {
		for (GRect rect : gObjects) {
			rect.draw(g);
		}
	}

	/**
	 * A simple version of the ACM GRect class. You can add additional
	 * functionality.
	 */
	class GRect {
		private int x, y, w, h;
		private Color color;

		/**
		 * Constructor, initializes the GRect.
		 */
		public GRect(int x, int y, int w, int h) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
		}

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}

		/**
		 * This method is called from the paint() method of the applet. It does
		 * the drawing of the rect.
		 */
		public void draw(Graphics g) {
			g.setColor(color);
			g.drawRect(x, y, w, h);
		}

	}

}