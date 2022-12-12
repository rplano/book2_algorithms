import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * EuclidSimple
 * 
 * A simple implementation of Euclids greatest common divisor (GCD) algorithm.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class EuclidSimple extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-24");

		int a = readInt("Enter width (e.g. 299): ");
		int b = readInt("Enter height (e.g. 115): ");
		println(gcd(a, b));
	}

	private int gcd(int a, int b) {
		while (b != 0) {
			if (a > b) {
				a = a - b;
			} else {
				b = b - a;
			}
		}
		return a;
	}

}
