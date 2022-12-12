import acm.graphics.GLine;
import acm.program.GraphicsProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * HTree
 * 
 * Draw perpendicular line segments, each smaller by a factor of the square root
 * of 2 from the next larger adjacent segment.
 * 
 * H tree, https://en.wikipedia.org/wiki/H_tree
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class HTree extends GraphicsProgram {
	private final int WIDTH = 300;
	private final int HEIGHT = 300;
	private final int SIZE = 200;
	private final double SQRT_TWO = Math.sqrt(2);
	private double iteration_depth = SIZE;

	public void run() {
		setSize(WIDTH, HEIGHT + 47);
		while (iteration_depth >= 15) {
			removeAll();
			drawHTree(WIDTH / 2, HEIGHT / 2, SIZE / SQRT_TWO, true);
			pause(200);
			iteration_depth /= SQRT_TWO;
		}
	}

	private void drawHTree(double centerX, double centerY, double length,
			boolean horizontal) {
		// always draw the line
		if (horizontal) {
			GLine line = new GLine(centerX - length / 2, centerY, centerX
					+ length / 2, centerY);
			add(line);
		} else {
			GLine line = new GLine(centerX, centerY - length / 2, centerX,
					centerY + length / 2);
			add(line);
		}

		// base case:
		if (length < iteration_depth) {
			return;

			// recursive case:
		} else {
			double len = length / SQRT_TWO;
			if (horizontal) {
				drawHTree(centerX - length / 2, centerY, len, false);
				drawHTree(centerX + length / 2, centerY, len, false);
			} else {
				drawHTree(centerX, centerY - length / 2, len, true);
				drawHTree(centerX, centerY + length / 2, len, true);
			}
		}

	}
}
