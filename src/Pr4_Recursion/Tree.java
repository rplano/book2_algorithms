import java.awt.Color;

import tree.GThickLine;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Tree
 * 
 * Draws a nice looking random tree.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Tree extends GraphicsProgram {
	private static final int WIDTH = 400;
	private static final int HEIGHT = 300;

	private RandomGenerator rgen = new RandomGenerator();

	public void run() {
		setSize(WIDTH, HEIGHT + 48);

		drawBranch(WIDTH / 2, HEIGHT, Math.toRadians(90), 80);
	}

	private void drawBranch(double x0, double y0, double angle,
			double length) {
		double x1 = x0 - Math.cos(angle) * length;
		double y1 = y0 - Math.sin(angle) * length;
		drawLine(x0, y0, x1, y1, length);

		// base case
		if (length < 10)
			return;

		// recursive case
		double bendAngle = Math.toRadians(rgen.nextDouble(-10, 10));
		double branchAngle = Math.toRadians(rgen.nextDouble(-30, 30));
		drawBranch(x1, y1, angle + bendAngle - branchAngle,
				length * rgen.nextDouble(0.6, 0.8));
		drawBranch(x1, y1, angle + bendAngle + branchAngle,
				length * rgen.nextDouble(0.6, 0.8));
	}

	private void drawLine(double x1, double y1, double x2, double y2, double len) {
		int thick = (int) (len / 10);
		Color col = new Color(101, 67, 33); // Color.BLACK;
		if (len < 10) {
			col = Color.GREEN;
			thick = 2;
		}
		GThickLine line = new GThickLine(x1, y1, x2, y2, thick);
		line.setColor(col);
		add(line);
	}
}
