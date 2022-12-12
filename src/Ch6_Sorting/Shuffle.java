/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Shuffle
 * 
 * Shuffles a given array using the Fisher–Yates shuffle.
 * Time complexity to O(n).
 * 
 * @see Fisher–Yates shuffle, https://en.wikipedia.org/wiki/Fisher–Yates_shuffle#The_modern_algorithm
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Shuffle {

	// needed to avoid compiler optimizations
	private static final int NR_OF_ITERATIONS = 100000;
	
	public static void main(String[] args) {

		int[] arrOfInts = init(10000);
		printArray(arrOfInts);
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			shuffleSlow(arrOfInts);
		}
		long duration = System.currentTimeMillis() - start;
		System.out.println("time shuffle1: " + duration);

		 start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			shuffleFast(arrOfInts);
		}
		 duration = System.currentTimeMillis() - start;
		System.out.println("time shuffle2: " + duration);
	}
	
	private static void shuffleSlow(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			int j = (int)(Math.random() * n);
			int tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}
	}
	
	private static void shuffleFast(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			int j = i+ (int)(Math.random() * (n-i));
			int tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}
	}
	
	private static int[] init(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = i;
		}
		return arr;
	}

	private static void printArray(int[] arrOfInts) {
		for (int i = 0; i < arrOfInts.length; i++) {
			System.out.print(arrOfInts[i] + ",");
		}
		System.out.println();
	}
}
