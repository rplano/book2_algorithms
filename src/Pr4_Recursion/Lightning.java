import java.awt.Color;

import tree.GThickLine;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Lightning
 * 
 * Recursively splitting a line in half, and displacing the midpoint, leads to
 * something that looks like lightening.
 * 
 * @see http://krazydad.com/bestiary/bestiary_lightning.html
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Lightning extends GraphicsProgram {
	private final int WIDTH = 400;
	private final int HEIGHT = 400;
	private final int OFFSET = 30;

	private final int nrOfBolts = 4;
	private final int displacement = 200;
	private final int RADIUS = 20;

	public void run() {
		setSize(WIDTH, HEIGHT);

		setBackground(Color.BLACK);
		while (true) {
			removeAll();

			for (int i = 0; i < nrOfBolts; ++i) {
				drawLightning(50, HEIGHT / 2 - OFFSET, WIDTH - 50, HEIGHT / 2
						- OFFSET, displacement);
			}

			drawOval(50, HEIGHT / 2 - OFFSET);
			drawOval(WIDTH - 50, HEIGHT / 2 - OFFSET);

			waitForClick();
		}
	}

	private void drawLightning(double x1, double y1, double x2, double y2,
			double displace) {
		// base case
		if (displace < 2) {
			drawLine(x1, y1, x2, y2);

			// recursive case
		} else {
			double mid_x = (x2 + x1) / 2.0;
			double mid_y = (y2 + y1) / 2.0;
			mid_x += (Math.random() - 0.5) * displace;
			mid_y += (Math.random() - 0.5) * displace;
			drawLightning(x1, y1, mid_x, mid_y, displace / 2);
			drawLightning(x2, y2, mid_x, mid_y, displace / 2);
		}
	}

	private void drawLine(double x1, double y1, double x2, double y2) {
		GThickLine line = new GThickLine(x1, y1, x2, y2,2);
		line.setColor(Color.WHITE);
		add(line);
	}

	private void drawOval(int x, int y) {
		GOval sphere = new GOval(x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS);
		sphere.setFilled(true);
		sphere.setFillColor(Color.LIGHT_GRAY);
		add(sphere);

	}
}
