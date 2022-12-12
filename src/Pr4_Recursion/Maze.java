import java.awt.Color;

import tree.GThickLine;
import acm.graphics.GLine;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Maze
 * 
 * Recursive division method: "For example, in a rectangular maze, build at
 * random points two walls that are perpendicular to each other. These two walls
 * divide the large chamber into four smaller chambers separated by four walls.
 * Choose three of the four walls at random, and open a one cell-wide hole at a
 * random point in each of the three. Continue in this manner recursively, until
 * every chamber has a width of one cell in either of the two directions."
 * 
 * @see https://en.wikipedia.org/wiki/Maze_generation_algorithm
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Maze extends GraphicsProgram {
	private final int SIZE = 400;
	private final int OFFSET = 44;
	private final int MIN_WIDTH = 40;
	private final Color col = Color.RED;
	private RandomGenerator rgen = new RandomGenerator();

	public void run() {
		setSize(SIZE, SIZE + OFFSET);
		drawBorder();
		divisionByTwoWalls(0, SIZE, 0, SIZE);
	}

	private void divisionByTwoWalls(int x0, int x1, int y0, int y1) {

		// base case
		if ((x1 - x0) < 2 * MIN_WIDTH || (y1 - y0) < 2 * MIN_WIDTH) {
			return;
		}
		waitForClick();

		// recursive case
		int x = rgen.nextInt(x0 + MIN_WIDTH, x1 - MIN_WIDTH) / MIN_WIDTH
				* MIN_WIDTH;
		int y = rgen.nextInt(y0 + MIN_WIDTH, y1 - MIN_WIDTH) / MIN_WIDTH
				* MIN_WIDTH;
		// println(x0+","+x+","+x1+","+y0+","+y+","+y1);

		int noHole = rgen.nextInt(0, 3);
		drawLineWithRandomOpening(x, y0, x, y, noHole == 0);
		drawLineWithRandomOpening(x, y, x, y1, noHole == 1);
		drawLineWithRandomOpening(x0, y, x, y, noHole == 2);
		drawLineWithRandomOpening(x, y, x1, y, noHole == 3);

		divisionByTwoWalls(x0, x, y0, y);
		divisionByTwoWalls(x, x1, y0, y);
		divisionByTwoWalls(x0, x, y, y1);
		divisionByTwoWalls(x, x1, y, y1);
	}

	private void drawBorder() {
		drawLine(MIN_WIDTH, 1, SIZE, 1);
		drawLine(0, SIZE - 4, SIZE - MIN_WIDTH, SIZE - 4);
		drawLine(1, 0, 1, SIZE);
		drawLine(SIZE - 4, 0, SIZE - 4, SIZE);
	}

	private void drawLineWithRandomOpening(int x0, int y0, int x1, int y1,
			boolean withNoOpening) {
		if (withNoOpening) {
			drawLine(x0, y0, x1, y1);
		} else {
			if (x0 == x1) { // vertical
				int y = rgen.nextInt(y0, y1 - MIN_WIDTH) / MIN_WIDTH
						* MIN_WIDTH;
				drawLine(x0, y0, x1, y);
				drawLine(x0, y + MIN_WIDTH, x1, y1);

			} else {// horizontal
				int x = rgen.nextInt(x0, x1 - MIN_WIDTH) / MIN_WIDTH
						* MIN_WIDTH;
				drawLine(x0, y0, x, y1);
				drawLine(x + MIN_WIDTH, y0, x1, y1);

			}
		}
	}

	private void drawLine(int x0, int y0, int x1, int y1) {
		GThickLine line = new GThickLine(x0, y0, x1, y1, 2);
		line.setColor(col);
		add(line);
	}
}
