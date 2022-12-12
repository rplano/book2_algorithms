import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Factorial
 * 
 * Shows how to calculate factorial using iteration and recursion.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Factorial extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-24");

		int n = readInt("Enter a number: ");
		println("The factorial is: " + factorialRecursive(n));
	}

	private long factorialIterative(int n) {
		long factorial = 1;
		for (long i = 1; i <= n; i++) {
			factorial *= i;
		}
		return factorial;
	}

	private long factorialRecursive(int n) {
		if (n == 0)
			return 1;
		else
			return n * factorialRecursive(n - 1);
	}
}
