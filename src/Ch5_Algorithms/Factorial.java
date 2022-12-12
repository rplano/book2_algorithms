/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Factorial
 * 
 * We calculate factorial in four different ways. We also show how to do time
 * measurements.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Factorial {

	public static long factorialIterative(int n) {
		long factorial = 1;
		for (long i = 1; i <= n; i++) {
			factorial *= i;
		}
		return factorial;
	}

	public static long factorialRecursive(int n) {
		if (n == 0)
			return 1;
		else
			return n * factorialRecursive(n - 1);
	}

	// Stirling's approximation,
	// https://en.wikipedia.org/wiki/Stirling's_approximation
	public static long factorialApproximation(int n) {
		double factorial = Math.sqrt(2.0 * Math.PI * n)
				* Math.pow(n / Math.E, n);
		return (long) factorial;
	}

	public static long[] factorialLookupTable;

	public static void initFactorialLookupTable() {
		factorialLookupTable = new long[20 + 1];
		for (int i = 1; i < factorialLookupTable.length; i++) {
			factorialLookupTable[i] = factorialIterative(i);
		}
	}

	public static long factorialLookupTable(int n) {
		return factorialLookupTable[n];
	}

	// needed to avoid compiler optimizations
	private static final int NR_OF_ITERATIONS = 100000000;
	private static long x = 0;

	public static void main(String[] args) {
		initFactorialLookupTable();
		System.out.println(Factorial.factorialIterative(20));
		System.out.println(Factorial.factorialRecursive(20));
		System.out.println(Factorial.factorialLookupTable(20));
		System.out.println(Factorial.factorialApproximation(20));

		// measure time
		long start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			x = Factorial.factorialIterative(20);
		}
		long duration = System.currentTimeMillis() - start;
		System.out.println("time iterative: " + duration);

		start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			x = Factorial.factorialRecursive(20);
		}
		duration = System.currentTimeMillis() - start;
		System.out.println("time recursive: " + duration);

		start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			x = Factorial.factorialLookupTable(20);
		}
		duration = System.currentTimeMillis() - start;
		System.out.println("time lookup table: " + duration);

		start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			x = Factorial.factorialApproximation(20);
		}
		duration = System.currentTimeMillis() - start;
		System.out.println("time approximation: " + duration);

	}
}
