import java.awt.Color;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.program.GraphicsProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * FunctionPlot
 * 
 * Visually represent the following mathematical functions <br/>
 * - log(n) <br/>
 * - n <br/>
 * - n * log(n) <br/>
 * - n² <br/>
 * - 2^n <br/>
 * - n! <br/>
 * 
 * for the values n = 0 to 40.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class FunctionPlot extends GraphicsProgram {
	private static final double SIZE = 400;
	private static final double SCALE = 0.1;

	public void run() {
		setSize(400, 400 + 50);
		plotConstant();
		plotLogarithmic();
		plotLinear();
		plotLinearithmic();
		plotQuadratic();
		//plotQubic();
		plotExponential();
		plotFactorial();
		drawLabels();
		plotConstant();
	}

	private void drawLabels() {
		drawLabel("O(1)", 340, 395, Color.BLACK);
		drawLabel("O(log(n))", 300, 360, Color.GREEN);
		drawLabel("O(n)", 230,190, Color.BLUE);
		drawLabel("O(n log(n))", 120,130, Color.CYAN);
		drawLabel("O(n²)", 60,90, Color.YELLOW);
		drawLabel("O(exp(n))", 5,30, Color.ORANGE);
		drawLabel("O(n!)", 5,55, Color.RED);
	}

	private void plotConstant() {
		double x0 = 0;
		double y0 = SIZE;
		for (double x = 1; x < SIZE; x++) {
			double x1 = 0 + x * SCALE;
			double y = (SIZE);
			if (y < 0)
				break;
			drawLine(x0, y0, x, y, Color.BLACK);
			x0 = x;
			y0 = y;
		}
		drawLine(0, 0, 0, SIZE, Color.BLACK);
	}

	private void plotLogarithmic() {
		double x0 = 0;
		double y0 = SIZE;
		for (double x = 0; x < SIZE; x++) {
			double x1 = 0 + x * SCALE;
			double y = (SIZE - Math.log(x1) / SCALE);
			if (y < 0)
				break;
			drawLine(x0, y0, x, y, Color.GREEN);
			x0 = x;
			y0 = y;
		}
	}

	private void plotLinear() {
		double x0 = 0;
		double y0 = SIZE;
		for (double x = 0; x < SIZE; x++) {
			double x1 = 0 + x * SCALE;
			double y = (SIZE - x1 / SCALE);
			if (y < 0)
				break;
			drawLine(x0, y0, x, y, Color.BLUE);
			x0 = x;
			y0 = y;
		}
	}

	private void plotLinearithmic() {
		double x0 = 0;
		double y0 = SIZE;
		for (double x = 0; x < SIZE; x++) {
			double x1 = 0 + x * SCALE;
			double y = (SIZE - x1 * Math.log(x1) / SCALE);
			if (y < 0)
				break;
			drawLine(x0, y0, x, y, Color.CYAN);
			x0 = x;
			y0 = y;
		}
	}

	private void plotQuadratic() {
		double x0 = 0;
		double y0 = SIZE;
		for (double x = 0; x < SIZE; x++) {
			double x1 = 0 + x * SCALE;
			double y = (SIZE - x1 * x1 / SCALE);
			if (y < 0)
				break;
			drawLine(x0, y0, x, y, Color.YELLOW);
			x0 = x;
			y0 = y;
		}
	}

//	private void plotQubic() {
//		double x0 = 0;
//		double y0 = SIZE;
//		for (double x = 0; x < SIZE; x++) {
//			double x1 = 0 + x * SCALE;
//			double y = (SIZE - x1 * x1 * x1 / SCALE);
//			if (y < 0)
//				break;
//			drawLine(x0, y0, x, y, Color.ORANGE);
//			x0 = x;
//			y0 = y;
//		}
//	}

	private void plotExponential() {
		double x0 = 0;
		double y0 = SIZE;
		for (double x = 0; x < SIZE; x++) {
			double x1 = 0 + x * SCALE;
			double y = (SIZE - Math.exp(x1) / SCALE);
			if (y < 0)
				break;
			drawLine(x0, y0, x, y, Color.ORANGE);
			x0 = x;
			y0 = y;
		}
	}

	private void plotFactorial() {
		double x0 = 0;
		double y0 = SIZE;
		for (double x = 0; x < SIZE; x++) {
			double x1 = 0 + x * SCALE;
			double y = (SIZE - Math.pow(x1, x1) / SCALE);
			if (y < 0)
				break;
			drawLine(x0, y0, x, y, Color.RED);
			x0 = x;
			y0 = y;
		}
	}

	private void drawLine(double x0, double y0, double x, double y, Color col) {
		GLine line = new GLine(x0, y0, x, y);
		line.setColor(col);
		add(line);
	}

	private void drawLabel(String string, int i, int j, Color red) {
		GLabel lbl = new GLabel(string);
		lbl.setFont("Serif-bold-14");
		lbl.setColor(red);
		add(lbl, i, j);
	}
}
