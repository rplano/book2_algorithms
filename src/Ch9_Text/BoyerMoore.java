import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * BoyerMoore
 * 
 * A simple version of the BoyerMoore algorithm using bad character heuristic.
 * 
 * text = "ababbbcabaabbbabababbbababbba"; pattern = "ababbba";
 * 
 * @see Pattern Searching | Set 7 (Boyer Moore Algorithm – Bad Character
 *      Heuristic),
 *      www.geeksforgeeks.org/pattern-searching-set-7-boyer-moore-algorithm
 *      -bad-character-heuristic/
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class BoyerMoore extends ConsoleProgram {

	private final int NO_OF_CHARS = 256;

	public void run() {
		setSize(400, 300);
		setFont("Times New Roman-bold-24");

		String text = readLine("Enter text: ");
		String pattern = readLine("Enter pattern: ");

		int pos = -1;
		while (true) {
			pos = search(text, pattern, pos + 1);
			if (pos < 0)
				break;
			println(pos + ": " + text.substring(pos, pos + pattern.length()));
		}
	}

	/**
	 * A pattern searching function that uses Bad Character Heuristic of Boyer
	 * Moore Algorithm.
	 */
	private int search(String txt, String pat) {
		return search(txt, pat, 0);
	}

	/**
	 * A pattern searching function that uses Bad Character Heuristic of Boyer
	 * Moore Algorithm.
	 */
	private int search(String txt, String pat, int shift) {
		final int m = pat.length();
		final int n = txt.length();

		int[] badchar = new int[NO_OF_CHARS];

		initBadCharHeuristic(pat, m, badchar);

		while (shift <= (n - m)) {
			int j = m - 1;

			// chars of pattern and text are matching
			while (j >= 0 && pat.charAt(j) == txt.charAt(shift + j)) {
				j--;
			}

			if (j < 0) {
				return shift; // we found pattern

			} else {
				// shift the pattern so that the bad character in text aligns
				// with the last occurrence of it in pattern, but make sure
				// there is no negative shift
				shift += Math.max(1, j - badchar[txt.charAt(shift + j)]);
			}
		}
		return -1;
	}

	/**
	 * The preprocessing function for Boyer Moore's bad character heuristic.
	 */
	private void initBadCharHeuristic(String str, int size, int[] badchar) {
		// initialize all occurrences as -1
		for (int i = 0; i < NO_OF_CHARS; i++) {
			badchar[i] = -1;
		}

		// fill the actual value of last occurrence of a character
		for (int i = 0; i < size; i++) {
			badchar[str.charAt(i)] = i;
		}
	}

}
