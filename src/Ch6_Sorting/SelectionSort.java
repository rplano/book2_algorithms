import java.util.Arrays;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * SelectionSort
 * 
 * Demonstrates the SelectionSort algorithm.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class SelectionSort implements SortAlgorithm {

	public void sort(int arr[])  {
		for (int i = 0; i < arr.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			swap(arr, i, min);
		}
	}

	private void swap(int arr[], int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	public static void main(String[] args) {
		
		int[] arrOfInts = { 5, 55, 2, 7, 45, 3, 1, 8, 23, 12 };
		new SelectionSort().sort(arrOfInts);
		
		for (int i = 0; i < arrOfInts.length; i++) {
			System.out.print( arrOfInts[i] +"," );
		}
	}
}
