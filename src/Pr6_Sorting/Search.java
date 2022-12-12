import java.util.Arrays;
import java.util.HashSet;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Search
 * 
 * Compares performance of a sequential, a binary and a hash search on an array.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Search {

	private static void printArray(int[] arrOfInts) {
		for (int i = 0; i < arrOfInts.length; i++) {
			System.out.print(arrOfInts[i] + ",");
		}
		System.out.println();
	}

	// needed to avoid compiler optimizations
	private static final int NR_OF_ITERATIONS = 100000;
	private static final int MAX = 100000;

	public static void main(String[] args) {
		long start, duration;

		int[] arr1 = { 44, 88, 17, 32, 97, 65, 28, 82, 29, 76, 54, 80 };
		Arrays.sort(arr1);
		printArray(arr1);

		// create an array of nrs and shuffle it.
		int[] arr = init(MAX);

		// sequential search, unsorted array
		start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			int random = (int) (Math.random() * MAX);
			sequentialSearch(random, arr);
			// System.out.println(sequentialSearch(random, arr));
		}
		duration = System.currentTimeMillis() - start;
		System.out.println("time to sequential search unsorted array: "
				+ duration);

		// sequential search, sorted array
		Arrays.sort(arr);
		start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			int random = (int) (Math.random() * MAX);
			sequentialSearch(random, arr);
			// System.out.println(sequentialSearch(random, arr));
		}
		duration = System.currentTimeMillis() - start;
		System.out.println("time to sequential search sorted array: "
				+ duration);

		// binary search
		Arrays.sort(arr);
		start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			int random = (int) (Math.random() * MAX);
			binarySearch(random, arr);
			// System.out.println(binarySearch(random, arr));
		}
		duration = System.currentTimeMillis() - start;
		System.out.println("time to binary search sorted array: " + duration);

		// hash based search
		arr = init(MAX); // start with new array
		HashSet<Integer> hs = new HashSet(Arrays.asList(arr));
		start = System.currentTimeMillis();
		for (int i = 0; i < NR_OF_ITERATIONS; i++) {
			int random = (int) (Math.random() * MAX);
			boolean b = hs.contains(random);
			// System.out.println(binarySearch(random, arr));
		}
		duration = System.currentTimeMillis() - start;
		System.out.println("time to hash search: " + duration);

	}

	private static int binarySearch(int key, int[] arr) {
		return binarySearch(key, arr, 0, arr.length);
	}

	private static int binarySearch(int key, int[] arr, int start, int stop) {
		// base case
		if (start > stop)
			return -1;

		// recursive case
		int mid = (start + stop) / 2;
		if (key == arr[mid]) {
			return mid;
		} else if (key < arr[mid]) {
			return binarySearch(key, arr, start, mid - 1);
		} else {
			return binarySearch(key, arr, mid + 1, stop);
		}
	}

	private static int sequentialSearch(int key, int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == key)
				return i;
		}
		return -1;
	}

	private static int[] init(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = (int) (Math.random() * 2 * MAX);
		}
		return arr;
	}
}
