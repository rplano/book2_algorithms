import java.util.Arrays;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * MergeSort
 * 
 * Demonstrates the MergeSort algorithm.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class MergeSort implements SortAlgorithm {

	private void merge(int[] arr, int[] left, int[] right) {
		int pos = 0;
		int leftPos = 0;
		int rightPos = 0;

		// main merge loop
		while (leftPos < left.length && rightPos < right.length) {
			if (left[leftPos] < right[rightPos]) {
				arr[pos] = left[leftPos];
				leftPos++;
			} else {
				arr[pos] = right[rightPos];
				rightPos++;
			}
			pos++;
		}

		// copy rest of left half, if needed
		while (leftPos < left.length) {
			arr[pos] = left[leftPos];
			leftPos++;
			pos++;
		}

		// copy rest of right half, if needed
		while (rightPos < right.length) {
			arr[pos] = right[rightPos];
			rightPos++;
			pos++;
		}
	}

	private void mergeSort(int[] arr) {
		if (arr.length > 1) {
			int middle = arr.length / 2;
			int[] left = Arrays.copyOfRange(arr, 0, middle);
			int[] right = Arrays.copyOfRange(arr, middle, arr.length);
			mergeSort(left);
			mergeSort(right);
			merge(arr, left, right);
		}
	}

	public void sort(int arr[]) {
		mergeSort(arr);
	}

	public static void main(String[] args) {

		int[] arrOfInts = { 5, 55, 2, 7, 45, 3, 1, 8, 23, 12 };
		new MergeSort().sort(arrOfInts);

		for (int i = 0; i < arrOfInts.length; i++) {
			System.out.print(arrOfInts[i] + ",");
		}
	}
}
