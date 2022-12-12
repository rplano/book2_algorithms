/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Power
 * 
 * We calculate the power of x to the n in four different ways. We also show how
 * to do time measurements.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Power {

	public static long powerIterative(int x, int n) {
		long power = x;
		for (int i = 1; i < n; i++) {
			power *= x;
		}
		return power;
	}

	public static long powerRecursive(int x, int n) {
		if (n == 1)
			return x;
		else
			return x * powerRecursive(x, n - 1);
	}

	public static long powerDivideAndConquer(int x, int n) {
		if (n == 1)
			return x;
		else {
			long temp = powerDivideAndConquer(x, n / 2);
			if (n % 2 == 0)
				return temp * temp;
			else
				return x * temp * temp;
		}
	}

	public static long powerApproximation(int x, int n) {
		return (long) Math.pow(x, n);
	}

	// needed to avoid compiler optimizations
	private static final int NR_OF_ITERATIONS = 10000000;
	private static long x = 0;

	public static void main(String[] args) {
		System.out.println(Power.powerIterative(2, 62));
		System.out.println(Power.powerRecursive(2, 62));
		System.out.println(Power.powerDivideAndConquer(2, 62));
		System.out.println(Power.powerApproximation(2, 62));

		// measure time
		long start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			x = Power.powerIterative(2, 62);
		}
		long duration = System.currentTimeMillis() - start;
		System.out.println("time iterative: " + duration);

		start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			x = Power.powerRecursive(2, 62);
		}
		duration = System.currentTimeMillis() - start;
		System.out.println("time recursive: " + duration);

		start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			x = Power.powerDivideAndConquer(2, 62);
		}
		duration = System.currentTimeMillis() - start;
		System.out.println("time divide & conquer: " + duration);

		start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			x = Power.powerApproximation(2, 62);
		}
		duration = System.currentTimeMillis() - start;
		System.out.println("time approximation: " + duration);

	}
}
