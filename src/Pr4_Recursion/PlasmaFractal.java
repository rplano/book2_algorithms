import java.awt.Color;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;

/**
 * PlasmaFractal
 * 
 * Simple plasma fractal implementation.
 * 
 * @see https://github.com/jseyster/plasmafractal/blob/master/Plasma.java
 * 
 *      Justin Seyster, 2002
 *
 *      Permission is granted by the author to use the source code provided in
 *      this file for any purpose, on its own or as part of a derivative work,
 *      and with no restrictions. Enjoy!
 */
public class PlasmaFractal extends GraphicsProgram {
	private final int SIZE = 400;

	public void run() {
		setSize(SIZE, SIZE + 44);
		while (true) {
			drawPlasma(SIZE, SIZE);
			waitForClick();
		}
	}

	/**
	 * Randomly displaces color value for midpoint depending on size of grid
	 * piece.
	 * 
	 * @param num
	 * @return
	 */
	private float displace(float num) {
		float max = num / (float) (getSize().width + getSize().height) * 3;
		return ((float) Math.random() - 0.5f) * max;
	}

	/**
	 * Returns a color based on a color value, c.
	 * 
	 * @param c
	 * @return
	 */
	private Color computeColor(float c) {
		float red = 0;
		float green = 0;
		float blue = 0;

		if (c < 0.5f) {
			red = c * 2;
		} else {
			red = (1.0f - c) * 2;
		}

		if (c >= 0.3f && c < 0.8f) {
			green = (c - 0.3f) * 2;
		} else if (c < 0.3f) {
			green = (0.3f - c) * 2;
		} else {
			green = (1.3f - c) * 2;
		}

		if (c >= 0.5f) {
			blue = (c - 0.5f) * 2;
		} else {
			blue = (0.5f - c) * 2;
		}

		return new Color(red, green, blue);
	}

	/**
	 * This is something of a "helper function" to create an initial grid before
	 * the recursive function is called.
	 * 
	 * @param width
	 * @param height
	 */
	private void drawPlasma(int width, int height) {
		float c1, c2, c3, c4;

		// Assign the four corners of the intial grid random color values
		// These will end up being the colors of the four corners of the applet.
		c1 = (float) Math.random();
		c2 = (float) Math.random();
		c3 = (float) Math.random();
		c4 = (float) Math.random();

		divideGrid(0, 0, width, height, c1, c2, c3, c4);
	}

	/**
	 * This is the recursive function that implements the random midpoint
	 * displacement algorithm. It will call itself until the grid pieces become
	 * smaller than one pixel.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param c1
	 * @param c2
	 * @param c3
	 * @param c4
	 */
	private void divideGrid(float x, float y, float width, float height,
			float c1, float c2, float c3, float c4) {

		// base case
		if (width < 2 || height < 2) {
			GRect rect = new GRect(1, 1);
			// average four corners of grid and drawn as a single pixel.
			rect.setColor(computeColor((c1 + c2 + c3 + c4) / 4));
			rect.setFilled(true);
			add(rect, (int) x, (int) y);

		// recursive case
		} else {
			float newWidth = width / 2;
			float newHeight = height / 2;

			// randomly displace midpoint
			float middle = (c1 + c2 + c3 + c4) / 4
					+ displace(newWidth + newHeight);
			// make sure midpoint stays between 0 and 1
			if (middle < 0) {
				middle = 0;
			} else if (middle > 1.0f) {
				middle = 1.0f;
			}

			float edge1 = (c1 + c2) / 2; // Calculate the edges by averaging the
											// two
			// corners of each edge.
			float edge2 = (c2 + c3) / 2;
			float edge3 = (c3 + c4) / 2;
			float edge4 = (c4 + c1) / 2;

			// do the operation over again for each of the four new grids.
			divideGrid(x, y, newWidth, newHeight, c1, edge1, middle, edge4);
			divideGrid(x + newWidth, y, newWidth, newHeight, edge1, c2, edge2,
					middle);
			divideGrid(x + newWidth, y + newHeight, newWidth, newHeight,
					middle, edge2, c3, edge3);
			divideGrid(x, y + newHeight, newWidth, newHeight, edge4, middle,
					edge3, c4);
		}
	}
}
