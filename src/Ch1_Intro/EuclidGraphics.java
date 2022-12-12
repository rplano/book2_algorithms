import acm.graphics.GRect;
import acm.io.IODialog;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * EuclidGraphics
 * 
 * A graphical visualization of Euclids greatest common divisor (GCD) algorithm.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class EuclidGraphics extends GraphicsProgram {

	private RandomGenerator rgen = RandomGenerator.getInstance();

	public void run() {
		IODialog dialog = getDialog();
		int w = dialog.readInt("Enter width (e.g. 299): ");
		int h = dialog.readInt("Enter height (e.g. 115): ");
		setSize(w, h + 44);

		while (true) {
			removeAll();
			int x = gcd(w, h);
			dialog.println("GCD is:" + x);
			pause(30000);
		}
	}

	private int gcd(int a, int b) {
		while (b != 0) {
			if (a > b) {
				a = a - b;
				drawRect(a, 0, b, b);
			} else {
				b = b - a;
				drawRect(0, b, a, a);
			}
			pause(1000);
		}
		return a;
	}

	private void drawRect(int a, int b, int w, int h) {
		GRect rect = new GRect(a, b, w, h);
		rect.setFilled(true);
		rect.setFillColor(rgen.nextColor());
		add(rect);
	}
}
