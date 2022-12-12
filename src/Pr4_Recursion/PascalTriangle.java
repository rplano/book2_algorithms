import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * PascalTriangle
 * 
 * Pascal's triangle is recursive way to calculate the binomial coefficients.
 *      
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class PascalTriangle extends ConsoleProgram {
	private final int N = 5;
	
	public void run() {
		setSize(400, 200);
		setFont("Courier New-bold-24");
		
		for (int n = 0; n < N; n++) {
			printSpaces(N-n);
			for (int k = 0; k <= n; k++) {
				print(pascalRecursion(n, k) + " ");
			}
			println();
		}

//		for (int n = 0; n < N; n++) {
//			for (int k = 0; k <= n; k++) {
//				print(pascalFactorial(n, k) + " ");
//			}
//			println();
//		}
	}

	private void printSpaces(int n) {
		for (int i = 0; i < n; i++) {
			print(" ");
		}
	}

	private int pascalRecursion(int n, int k) {
		if (k == 0 || k == n) { // base case
			return 1;
		} else { // recursive case
			return pascalRecursion(n - 1, k - 1) + pascalRecursion(n - 1, k);
		}
	}

	private int pascalFactorial(int n, int k) {
		return factorial(n) / (factorial(k) * factorial(n - k));
	}

	private int factorial(int n) {
		int fac = 1;
		for (int i = 1; i <= n; i++) {
			fac *= i;
		}
		return fac;
	}

}
