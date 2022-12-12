import java.awt.Color;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * QuadraticCross
 * 
 * Remove the four corner squares recursively.
 * 
 * Vicsek fractal, https://en.wikipedia.org/wiki/Vicsek_fractal
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class QuadraticCross extends GraphicsProgram {
	private final int WIDTH = 245;
	private final int HEIGHT = WIDTH;

	public void run() {
		setSize(WIDTH, HEIGHT + 45);
		setBackground(Color.BLACK);
		removeCornerSquares(0, 0, WIDTH / 3);
	}

	private void removeCornerSquares(int x, int y, int size) {
		println(size);
		// base case:
		if (size < 3) {
			return;

			// recursive case:
		} else {
			removeRect(x, y, size);
			removeRect(x, y + 2 * size, size);
			removeRect(x + 2 * size, y, size);
			removeRect(x + 2 * size, y + 2 * size, size);

			removeCornerSquares(x + size, y, size / 3);
			removeCornerSquares(x + size, y + 2 * size, size / 3);
			// comment this one, looks nice too
			removeCornerSquares(x + size, y + size, size / 3);
			removeCornerSquares(x, y + size, size / 3);
			removeCornerSquares(x + 2 * size, y + size, size / 3);
		}
	}

	private void removeRect(int x, int y, int size) {
		GRect square = new GRect(size, size);
		square.setFilled(true);
		square.setColor(Color.WHITE);
		add(square, x, y);
	}
}
