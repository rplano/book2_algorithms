import acm.graphics.GPolygon;
import acm.program.GraphicsProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * SierpinskiTriangle
 * 
 * Draw a Sierpinski triangle.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class SierpinskiTriangle extends GraphicsProgram {
	private final int SIZE = 400;

	public void run() {
		setSize(SIZE, SIZE + 47);
		drawSierpinski(0, 0, SIZE - 4, SIZE);
	}

	void drawSierpinski(double x, double y, double w, double h) {

		drawTriangle(x, y, w, h);

		// base case
		if ((w < 2.0) || (h < 2.0)) {
			return;
		}

		// recursive case
		double h2 = h / 2;
		double w2 = w / 2;
		drawSierpinski(x, y, w2, h2);
		drawSierpinski(x + w2 / 2, y + h2, w2, h2);
		drawSierpinski(x + w2, y, w2, h2);
	}

	private void drawTriangle(double x, double y, double w, double h) {
		GPolygon triangle = new GPolygon(x, y);
		triangle.addVertex(0, 0);
		triangle.addVertex(w, 0);
		triangle.addVertex(w / 2, h);
		add(triangle);
	}
}
