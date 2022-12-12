import java.util.Arrays;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * SimpleSort
 * 
 * Uses Java's Arrays class to do the sorting.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class SimpleSort {

	public static void main(String[] args) {

		int[] arrOfInts = { 5, 55, 2, 7, 45, 3, 1, 8, 23, 12 };
		Arrays.sort(arrOfInts);

		for (int i = 0; i < arrOfInts.length; i++) {
			System.out.print(arrOfInts[i] + ",");
		}
	}

}
