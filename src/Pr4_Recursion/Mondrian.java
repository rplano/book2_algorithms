import java.awt.Color;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Mondrian
 * 
 * To create Mondrians follow this procedure:
 * 
 * 1) either split canvas horizontally, vertically or do nothing <br/>
 * 2) repeat step 1 with the smaller canvases, until canvases are to small.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Mondrian extends GraphicsProgram {
	private static final int MIN_SIZE = 40;
	private RandomGenerator rgen = RandomGenerator.getInstance();

	public void run() {
		setSize(400, 400);

		while (true) {
			removeAll();
			drawMondrian(0, 0, getWidth(), getHeight());
			waitForClick();
		}
	}

	private void drawMondrian(int i, int j, int width, int height) {
		// base case
		if ((width < MIN_SIZE) || (height < MIN_SIZE)) {
			return;
		}

		// recursive case
		int choice = rgen.nextInt(0, 2);
		switch (choice) {
		case 0: // divide canvas horizontally
			drawMondrian(i, j, width / 2, height);
			drawMondrian(i + width / 2, j, width / 2, height);
			break;
		case 1: // divide canvas vertically
			drawMondrian(i, j, width, height / 2);
			drawMondrian(i, j + height / 2, width, height / 2);
			break;
		default: // do nothing
			drawRectangle(i, j, width, height);
			break;
		}
	}

	private void drawRectangle(int i, int j, int width, int height) {
		GRect rect = new GRect(i, j, width, height);
		rect.setColor(Color.BLACK);
		rect.setFillColor(getRandomColor());
		rect.setFilled(true);
		add(rect);
		GRect rect2 = new GRect(i + 1, j + 1, width - 2, height - 2);
		rect2.setColor(Color.BLACK);
		rect2.setFillColor(getRandomColor());
		rect2.setFilled(true);
		add(rect2);
	}

	private Color getRandomColor() {
		int choice = rgen.nextInt(0, 3);
		switch (choice) {
		case 0:
			return Color.BLUE;
		case 1:
			return Color.RED;
		case 2:
			return Color.YELLOW;
		default:
			return Color.WHITE;
		}
	}
}
