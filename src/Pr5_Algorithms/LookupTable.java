/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * LookupTable
 * 
 * Calculate the sine using three different methods, one of them being lookup
 * tables.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class LookupTable {
	private static final long NR_OF_ITERATIONS = 100000000;
	private static double x = 0;

	private static final int SIZE_LOOKUP_TABLE = 50;
	private static double[] sineLookupTable = new double[SIZE_LOOKUP_TABLE];
	private static double sineSteps = (Math.PI / 2) / SIZE_LOOKUP_TABLE;

	public static void main(String[] args) {

		prepareLookupTable();

		System.out.println("  x  ,  sin1 ,  sin2 ,  sin3");
		for (double x = 0; x < Math.PI / 2; x += 0.2) {

			double sin1 = Math.sin(x);
			double sin2 = sineTaylor(x);
			double sin3 = sineLookup(x);

			System.out.println("x=" + String.format("%02.1f", x) + ", "
					+ String.format("%02.4f", sin1) + ", "
					+ String.format("%02.4f", sin2) + ", "
					+ String.format("%02.4f", sin3));
		}

		// measure time
		long start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			x = Math.sin(Math.random());
		}
		long duration = System.currentTimeMillis() - start;
		System.out.println("time Math.sin(): " + duration);

		start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			x = sineTaylor(Math.random());
		}
		duration = System.currentTimeMillis() - start;
		System.out.println("time sineTaylor(): " + duration);

		start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			x = sineLookup(Math.random());
		}
		duration = System.currentTimeMillis() - start;
		System.out.println("time sineLookup(): " + duration);
	}

	private static void prepareLookupTable() {
		for (int i = 0; i < SIZE_LOOKUP_TABLE; i++) {
			sineLookupTable[i] = Math.sin(i * sineSteps);
		}
	}

	private static double sineTaylor(double x) {
		double result = x - x * x * x / 6.0 + x * x * x * x * x / 120.0 - x * x
				* x * x * x * x * x / 5040.0;
		return result;
	}

	// this is not prefect, will only work for x between 0 and pi/2
	private static double sineLookup(double x) {
		return sineLookupTable[(int) (x / sineSteps)];
	}

}
