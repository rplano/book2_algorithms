import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Subsets
 * 
 * Lists all subsets of a given string.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Subsets extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-24");

		String word = readLine("Enter a word (abc): ");
		subset("", word);
	}

	private void subset(String picked, String remaining) {
		// base case
		if (remaining.length() == 0) {
			print(picked + ", ");

		// recursive case
		} else {
			char pick = remaining.charAt(0); // pick first letter
			subset(picked + pick, remaining.substring(1));
			subset(picked, remaining.substring(1));
		}
	}
}
