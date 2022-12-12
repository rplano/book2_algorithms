import java.awt.Color;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * SierpinskiCarpet
 * 
 * Draw a Sierpinski carpet.
 * 
 * Sierpinski carpet, https://en.wikipedia.org/wiki/Sierpinski_carpet also see * 
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class SierpinskiCarpet extends GraphicsProgram {
	private final int WIDTH = 300;
	private final int HEIGHT = 300;

	public void run() {
		setSize(WIDTH, HEIGHT + 47);
		setBackground(Color.BLACK);
		removeCenter(WIDTH / 2, HEIGHT / 2, WIDTH / 3);
	}

	private void removeCenter(int x, int y, int size) {
		// base case:
		if (size < 2) {
			return;

			// recursive case:
		} else {
			int delta = size / 2;
			GRect square = new GRect(size-1, size-1);
			square.setFilled(true);
			square.setColor(Color.WHITE);
			add(square, x - delta, y - delta);

			removeCenter(x - size, y - size, size / 3);
			removeCenter(x - size, y, size / 3);
			removeCenter(x - size, y + size, size / 3);
			removeCenter(x, y - size, size / 3);
			removeCenter(x, y + size, size / 3);
			removeCenter(x + size, y - size, size / 3);
			removeCenter(x + size, y, size / 3);
			removeCenter(x + size, y + size, size / 3);
		}
	}
}