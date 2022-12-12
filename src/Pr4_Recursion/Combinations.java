import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Combinations
 * 
 * Lists all combinations of a given string.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Combinations extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-24");

		// while (true) {
		String word = readLine("Enter a word (abcde): ");
		int k = readInt("Enter k: ");
		combinations(word.length(), k, "", word);
		// }
	}

	private void combinations(int n, int k, String picked, String remaining) {
		// base case
		if (k == 0) {
			print(picked + ", ");
		} else if ((k == n)) {
			print(picked + remaining + ", ");

			// recursive case
		} else {
			char pick = remaining.charAt(0); // pick first letter
			combinations(n - 1, k, picked, remaining.substring(1));
			combinations(n - 1, k - 1, picked + pick, remaining.substring(1));
		}
	}
}
