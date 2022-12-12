/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Average
 * 
 * Find the average of an array using random points.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Average {
	public static void main(String[] args) {
		int[] arr = { 5, 55, 2, 7, 45, 3, 1, 8, 23, 12 };

		System.out.println(averageExact(arr));
		System.out.println(averageFirstMiddleLast(arr));
		System.out.println(averageRandom(arr));
	}

	private static double averageRandom(int[] arr) {
		int total = 0;
		int len = arr.length;
		for (int i = 0; i < 3; i++) {
			total += arr[(int) (len * Math.random())];
		}
		double average = total / 3.0;
		return average;
	}

	private static double averageFirstMiddleLast(int[] arr) {
		double average = (arr[0] + arr[arr.length / 2] + arr[arr.length - 1]) / 3.0;
		return average;
	}

	private static double averageExact(int[] arr) {
		int total = 0;
		for (int i = 0; i < arr.length; i++) {
			total += arr[i];
		}
		double average = total / (double) arr.length;
		return average;
	}

}
