import java.util.Arrays;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * QuickSort
 * 
 * Demonstrates the QuickSort algorithm.
 * 
 * This is not an effective implementation, it is intended for instruction only.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class QuickSort implements SortAlgorithm {

	private void quickSort(int[] arr) {
		if (arr.length > 1) {
			// pick the pivot out of three values:
			int pivot = (arr[0] + arr[arr.length / 2] + arr[arr.length - 1]) / 3;

			// split in lower half and upper half
			int[] low = new int[arr.length];
			int[] high = new int[arr.length];
			int lowPos = 0;
			int highPos = 0;
			for (int i = 0; i < arr.length; i++) {
				// put all values less than pivot in low[]
				if (arr[i] <= pivot) {
					low[lowPos] = arr[i];
					lowPos++;
				} else {
					// put all values less than pivot in high[]
					high[highPos] = arr[i];
					highPos++;
				}
			}
			int[] lowA = Arrays.copyOfRange(low, 0, lowPos);
			int[] highA = Arrays.copyOfRange(high, 0, highPos);

			// recursively call quickSort with low and high halves
			quickSort(lowA);
			quickSort(highA);

			// join the two halves together
			join(arr, lowA, highA);
		}
	}

	private void join(int[] arr, int[] lowA, int[] highA) {
		int pos = 0;
		for (int i = 0; i < lowA.length; i++) {
			arr[pos] = lowA[i];
			pos++;
		}
		for (int i = 0; i < highA.length; i++) {
			arr[pos] = highA[i];
			pos++;
		}
	}

	public void sort(int arr[]) {
		quickSort(arr);
	}

	public static void main(String[] args) {

		int[] arrOfInts = { 5, 55, 2, 7, 45, 3, 1, 8, 23, 12 };
		// int[] arrOfInts = { 2, 3, 1 };
		new QuickSort().sort(arrOfInts);

		for (int i = 0; i < arrOfInts.length; i++) {
			System.out.print(arrOfInts[i] + ",");
		}
	}
}
