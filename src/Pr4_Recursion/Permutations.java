import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Permutations
 * 
 * Lists all permutations of a given string.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Permutations extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-24");

		String word = readLine("Enter a word (abc): ");
		permute("", word);
	}

	private void permute(String picked, String remaining) {
		// base case
		if (remaining.length() == 1) {
			print(picked + remaining + ", ");
		}

		// recursive case
		for (int i = 0; i < remaining.length(); i++) {
			char pick = remaining.charAt(i); // pick a letter
			String front = remaining.substring(0, i);
			String back = remaining.substring(i + 1);
			permute(picked + pick, front + back);
		}
	}

}
