import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Lehmer
 * 
 * A simple example of Lehmer's algorithm generating pseudo-random numbers
 * between 0 and 15.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Lehmer extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-20");
		int a = 13;
		int c = 1;
		int m = 16;
		int x = 3;

		for (int i = 0; i < 20; i++) {
			print(x + ",");
			x = (a * x + c) % m;
		}
	}
}
