import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Search
 * 
 * A brute force search algorithm.
 * 
 * text = "ababbbcabaabbbabababbbababbba"; pattern = "ababbba";
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Search extends ConsoleProgram {

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
	 * A brute-force search algorithm.
	 */
	private int search(String txt, String pat, int shift) {
		final int m = pat.length();
		final int n = txt.length();

		for (int i = shift; i <= (n - m); i++) {
			if (txt.substring(i, i + m).equals(pat)) {
				return i;
			}
		}

		return -1;
	}
}
