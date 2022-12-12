import java.awt.Color;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Randomness
 * 
 * A weak random number generator will show lines (or other patterns). To see
 * the effect, modify the initial values of the variables a and m.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Randomness extends GraphicsProgram {
	private final int SIZE = 400;

	private long a = 7 * 7 * 7 * 7 * 7;// * 7 * 7 * 7; // use 7 or 7*7
	private long m = 2147483647L;
	private long z = System.currentTimeMillis();

	public void run() {
		setSize(SIZE, SIZE);
		for (int i = 0; i < 10000; i++) {
			int x = nextInt(SIZE);
			int y = nextInt(SIZE);
			setPixel(x, y, Color.RED);
		}
	}

	/**
	 * @return a random number between 0 <= r < n
	 */
	public int nextInt(int n) {
		z = nextInt();
		return (int) (z % n);
	}

	/**
	 * @return a random number between 0 and 2147483646
	 */
	public int nextInt() {
		z = a * z % m;
		return (int) z;
	}

	private void setPixel(int x, int y, Color color) {
		GRect r = new GRect(1, 1);
		r.setColor(color);
		add(r, x, y);
	}

}
