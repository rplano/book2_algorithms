import acm.graphics.GImage;
import acm.program.GraphicsProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * RecursiveKarel
 * 
 * A simple way to visualize recursion using an image.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class RecursiveKarel extends GraphicsProgram {
	private final int SIZE = 400;

	public void run() {
		this.setSize(SIZE, SIZE);
		drawKarel(4.0, 10, -40);
	}

	private void drawKarel(double scale, int x, int y) {
		GImage karel = new GImage("Karel0.png");
		karel.scale(scale);
		add(karel, (SIZE-karel.getWidth())/2 + x, (SIZE-karel.getHeight())/2 + y);

		if (karel.getWidth() < 2) { // base case
			return;
		} else { // recursive case
			drawKarel(scale / 2, x+=(5*scale), y-=(2.5*scale));
		}
	}
}
