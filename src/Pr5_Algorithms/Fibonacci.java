/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Fibonacci
 * 
 * Each number is the sum of the two preceding numbers: 1, 1, 2, 3, 5, 8, 13,
 * 21, 34, 55, 89, 144, ...
 * 
 * You also learn about use of static, and about making timing measurements and
 * its intricacies.
 * 
 * @see https://en.wikibooks.org/wiki/Algorithms/Dynamic_Programming
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Fibonacci {

	public static long fibonacciIterative(int n) {
		if ((n < 0) || (n > 91)) {
			throw new ArithmeticException("n is out of range");
		}

		if (n == 0) {
			return 0;
		} else if ((n == 1) || (n == 2)) {
			return 1;

		} else {
			long current = 1;
			long old = 1;
			long older = 0;

			for (int i = 3; i <= n; i++) {
				older = old;
				old = current;
				current = older + old;
			}
			return current;
		}
	}

	public static long fibonacciRecursive(int n) {
		switch (n) {
		case 0:
			return 0;
		case 1:
			return 1;
		default:
			return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
		}
	}

	public static long[] fibonacciDynamicProgrammingTable = new long[91 + 1];

	// based on, but not using fibonacciRecursive
	private static long fibonacciDynamicProgramming(int n) {
		if (n == 0) {
			return 0;
		} else if (fibonacciDynamicProgrammingTable[n] > 0) {
			 // we have done this calculation before
			return fibonacciDynamicProgrammingTable[n];
		} else {
			long result = 1;
			switch (n) {
			case 1:
				break;
			default:
				result = fibonacciDynamicProgramming(n - 1)
						+ fibonacciDynamicProgramming(n - 2);
			}
			// remember for future use, in case we need it again
			fibonacciDynamicProgrammingTable[n] = result;
			return result;
		}
	}

	// @see https://www.brpreiss.com/books/opus4/html/page457.html
	private static long fibonacciDC(int n) {
		if (n == 0) {
			return 0;
		} else if ((n == 1) || (n == 2)) {
			return 1;

		} else {
			long a = fibonacciDC((n + 1) / 2);
			long b = fibonacciDC((n + 1) / 2 - 1);
			if (n % 2 == 0) {
				return a * (a + 2 * b);
			} else {
				return a * a + b * b;
			}

		}
	}

	public static long fibonacciApproximation(int n) {
		double phi = (1.0 + Math.sqrt(5.0)) / 2.0;
		double fibN = (Math.pow(phi, n) - Math.pow((- phi), -n))
				/ Math.sqrt(5.0);
		return (long) fibN;
	}

	public static long[] fibonacciLookupTable;

	public static void initFibonacciLookupTable() {
		fibonacciLookupTable = new long[91 + 1];
		for (int i = 1; i < fibonacciLookupTable.length; i++) {
			fibonacciLookupTable[i] = fibonacciIterative(i);
		}
	}

	public static long fibonacciLookupTable(int n) {
		return fibonacciLookupTable[n];
	}

	// needed to avoid compiler optimizations
	private static final int NR_OF_ITERATIONS = 10000000;
	private static long x = 0;

	public static void main(String[] args) {
		initFibonacciLookupTable();
		int n = 91;
		System.out.println(Fibonacci.fibonacciIterative(n));
		// System.out.println(Fibonacci.fibonacciRecursive(n));
		System.out.println(Fibonacci.fibonacciDC(n));
		System.out.println(Fibonacci.fibonacciDynamicProgramming(n));
		System.out.println(Fibonacci.fibonacciLookupTable(n));
		System.out.println(Fibonacci.fibonacciApproximation(n));

		// measure time
		long start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			x = Fibonacci.fibonacciIterative(91);
		}
		long duration = System.currentTimeMillis() - start;
		System.out.println("time iterative: " + duration);

		// way too slow:
		// start = System.currentTimeMillis();
		// for (int i = 0; i < NR_OF_ITERATIONS; i++) {
		// x = Fibonacci.fibonacciRecursive(30);
		// }
		// duration = System.currentTimeMillis() - start;
		// System.out.println("time recursive: " + duration);

		start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			x = Fibonacci.fibonacciDC(91);
		}
		duration = System.currentTimeMillis() - start;
		System.out.println("time divide and conquer: " + duration);

		start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			x = Fibonacci.fibonacciDynamicProgramming(91);
		}
		duration = System.currentTimeMillis() - start;
		System.out.println("time dynamic programming: " + duration);

		start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			x = Fibonacci.fibonacciLookupTable(91);
		}
		duration = System.currentTimeMillis() - start;
		System.out.println("time lookup table: " + duration);

		start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			x = Fibonacci.fibonacciApproximation(91);
		}
		duration = System.currentTimeMillis() - start;
		System.out.println("time approximation: " + duration);

	}
}
