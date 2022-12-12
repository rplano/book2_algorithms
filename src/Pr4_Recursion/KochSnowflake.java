import acm.graphics.GLine;
import acm.program.GraphicsProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * KochSnowflake
 * 
 * 1. start with an equilateral triangle <br/>
 * 2. divide the line segment into three segments of equal length. <br/>
 * 3. draw an equilateral triangle that has the middle segment from step 1 as
 * its base and points outward. <br/>
 * 4. remove the line segment that is the base of the triangle from step 2. <br/>
 * 5. repeat <br/>
 * 
 * Koch snowflake, https://en.wikipedia.org/wiki/Koch_snowflake
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class KochSnowflake extends GraphicsProgram {
	private final int WIDTH = 300;
	private final int HEIGHT = 300;
	private int iteration_depth = 300;

	public void run() {
		setSize(WIDTH, HEIGHT + 47);
		while (iteration_depth >= 1) {
			removeAll();
			drawKochSnowflake(50, 90, 200);
			pause(1000);
			iteration_depth /= 3;
		}
	}

	private void drawKochSnowflake(int x, int y, int length) {
		drawKochLine(x, y, length, 0);
		drawKochLine(x + length, y, length, -120);
		double x1 = x + length * Math.cos(-60 * Math.PI / 180);
		double y1 = y - length * Math.sin(-60 * Math.PI / 180);
		drawKochLine(x1, y1, length, 120);
	}

	private void drawKochLine(double x0, double y0, double length, double angle) {
		// base case:
		if (length < iteration_depth) {
			double x1 = x0 + length * Math.cos(angle * Math.PI / 180);
			double y1 = y0 - length * Math.sin(angle * Math.PI / 180);
			GLine line = new GLine(x0, y0, x1, y1);
			add(line);
			return;

			// recursive case:
		} else {
			double len = length / 3;
			double ang = angle;
			drawKochLine(x0, y0, len, ang + 0);
			double x1 = x0 + len * Math.cos(ang * Math.PI / 180);
			double y1 = y0 - len * Math.sin(ang * Math.PI / 180);
			drawKochLine(x1, y1, len, ang + 60);
			ang = ang + 60;
			double x2 = x1 + len * Math.cos(ang * Math.PI / 180);
			double y2 = y1 - len * Math.sin(ang * Math.PI / 180);
			drawKochLine(x2, y2, len, ang - 120);
			ang = ang - 120;
			double x3 = x2 + len * Math.cos(ang * Math.PI / 180);
			double y3 = y2 - len * Math.sin(ang * Math.PI / 180);
			drawKochLine(x3, y3, len, ang + 60);
		}
	}

}