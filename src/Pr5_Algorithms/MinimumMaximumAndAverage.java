import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * MinimumMaximumAndAverage
 * 
 * Find the minimum, maximum and average of a give array of integers.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class MinimumMaximumAndAverage extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-24");
		
		int[] arrOfInts = { 5, 55, 2, 7, 45, 3, 1, 8, 23, 12 };
		
		println("Minimum: " + findMinimum(arrOfInts));
		println("Maximum: " + findMaximum(arrOfInts));
		println("Average: " + findAverage(arrOfInts));
	}

	private int findMinimum(int[] arrOfInts) {
		int min = arrOfInts[0];
		for (int i = 0; i < arrOfInts.length; i++) {
			if (arrOfInts[i] < min) {
				min = arrOfInts[i];
			}
		}
		return min;
	}

	private int findMaximum(int[] arrOfInts) {
		int max = arrOfInts[0];
		for (int i = 0; i < arrOfInts.length; i++) {
			if (arrOfInts[i] > max) {
				max = arrOfInts[i];
			}
		}
		return max;
	}

	private double findAverage(int[] arrOfInts) {
		double avg = 0;
		for (int i = 0; i < arrOfInts.length; i++) {
			avg += arrOfInts[i];
		}
		return avg / arrOfInts.length;
	}
}
