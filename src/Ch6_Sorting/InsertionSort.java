/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * InsertionSort
 * 
 * Demonstrates the InsertionSort algorithm.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class InsertionSort implements SortAlgorithm {

	public void sort(int arr[]) {
		for (int i = 1; i < arr.length; i++) {
			int cur = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > cur) {
				arr[j + 1] = arr[j];
				arr[j] = cur;
				j--;
			}
		}
	}

	public static void main(String[] args) {

		int[] arrOfInts = { 5, 55, 2, 7, 45, 3, 1, 8, 23, 12 };
		new InsertionSort().sort(arrOfInts);

		for (int i = 0; i < arrOfInts.length; i++) {
			System.out.print(arrOfInts[i] + ",");
		}
	}
}
