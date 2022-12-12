import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * FibonacciGraphics
 * 
 * Visualize Fibonacci numbers as a Fibonacci spiral.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class FibonacciGraphics extends GraphicsProgram {
	private final int SIZE = 400;
	private final int BLOCK_SIZE = 10;

	private RandomGenerator rgen = new RandomGenerator();

	private int x = SIZE / 2 + 65;
	private int y = SIZE / 2 + 10;
	private int direction = 3;

	public void run() {
		setSize(SIZE, SIZE);
		int fm1 = 0;
		int f0 = 0;
		for (int i = 1; i < 9; i++) {
			int f = fibo(i);
			drawBlock(fm1 * BLOCK_SIZE, f0 * BLOCK_SIZE, f * BLOCK_SIZE);
			println(f+","+f0+","+fm1);
			fm1 = f0;
			f0 = f;
			pause(100);
		}
	}

	private void drawBlock(int sizem1,int size0, int size) {
		switch (direction) {
		case 0:
			x +=size0;
			break;
		case 1:
			x -=sizem1;
			y -=size0;
			break;
		case 2:
			x -=size;
			y +=sizem1;
			break;
		case 3:
			y +=size;
			break;
		}
		direction++;
		direction = direction % 4;

		drawRect(size);
	}

	private void drawRect(int size) {
		GRect r = new GRect(size, size);
		r.setFilled(true);
		r.setFillColor(rgen.nextColor());
		add(r, x, y-size);
	}

	private int fibo(int n) {
		switch (n) {
		case 0:
			return 0;
		case 1:
			return 1;
		default:
			return fibo(n - 1) + fibo(n - 2);
		}
	}
}
