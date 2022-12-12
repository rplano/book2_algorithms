import acm.graphics.GRect;
import acm.program.GraphicsProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * TSquare
 * 
 * 1: Start with a square. <br/>
 * 2: At each corner, place another square, centered at that corner, with half
 * the side length of the previous square. <br/>
 * 3: Repeat step 2. <br/>
 * 
 * @see https://en.wikipedia.org/wiki/T-square_(fractal)
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class TSquare extends GraphicsProgram {
	private final int WIDTH = 400;
	private final int HEIGHT = 400;
	private final int SIZE = WIDTH / 2;

	public void run() {
		setSize(WIDTH, HEIGHT + 47);
		drawCenteredSquare(WIDTH / 2, HEIGHT / 2, SIZE);
	}

	private void drawCenteredSquare(int x, int y, int size) {
		// base case:
		if (size < 4) {
			return;

			// recursive case:
		} else {
			int delta = size / 2;
			GRect square = new GRect(size - 1, size - 1);
			square.setFilled(true);
			add(square, x - delta, y - delta);

			drawCenteredSquare(x + delta, y + delta, delta);
			drawCenteredSquare(x + delta, y - delta, delta);
			drawCenteredSquare(x - delta, y + delta, delta);
			drawCenteredSquare(x - delta, y - delta, delta);
		}

	}
}