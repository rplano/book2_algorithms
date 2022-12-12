import java.awt.Color;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Pi
 * 
 * Calculate Pi using random points.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Pi extends GraphicsProgram {
	private final static int SIZE = 400;

	private RandomGenerator rgen = RandomGenerator.getInstance();

	private int totalPoints = 0;
	private int insidePoints = 0;

	public void run() {
		this.setSize(SIZE, SIZE + 44);

		while (true) {
			drawRandomPoint();
			double pi = 4.0 * insidePoints / totalPoints;
			System.out.println("Pi = " + pi);
			pause(10);
		}
	}

	private void drawRandomPoint() {
		double x = rgen.nextDouble();
		double y = rgen.nextDouble();
		totalPoints++;
		GRect point = new GRect(x * SIZE, SIZE - y * SIZE, 2, 2);
		point.setFilled(true);
		if ((x * x + y * y) < 1.0) {
			insidePoints++;
			point.setColor(Color.GREEN);
		} else {
			point.setColor(Color.BLUE);
		}
		add(point);
	}
}
