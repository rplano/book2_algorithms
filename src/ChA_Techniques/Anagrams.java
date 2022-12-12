import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.StringTokenizer;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Anagrams
 * 
 * We use the backtracking algorithm to find one anagram to a given word. We
 * need to load a lexicon with valid word first.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Anagrams extends ConsoleProgram {
	private HashSet<String> lexicon = new HashSet<String>();

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-24");

		loadLexiconFromFile("dictionary_en_de.txt");

		String word = readLine("Enter a word (react): ");
		permuteBacktracking("", word);
	}

	private boolean permuteBacktracking(String picked, String remaining) {
		// base case
		if (remaining.length() == 1) {
			String tmp = picked + remaining;
			if (lexicon.contains(tmp)) {
				print(tmp + ", ");
				return true;
			}
		}

		// recursive case
		for (int i = 0; i < remaining.length(); i++) {
			char pick = remaining.charAt(i); // pick a letter
			String front = remaining.substring(0, i);
			String back = remaining.substring(i + 1);
			if (permuteBacktracking(picked + pick, front + back)) {
				return true;
			}
		}
		return false;
	}

	private void permuteBruteForce(String picked, String remaining) {
		// base case
		if (remaining.length() == 1) {
			String tmp = picked + remaining;
			if (lexicon.contains(tmp)) {
				print(tmp + ", ");
			}
		}

		// recursive case
		for (int i = 0; i < remaining.length(); i++) {
			char pick = remaining.charAt(i); // pick a letter
			String front = remaining.substring(0, i);
			String back = remaining.substring(i + 1);
			permuteBruteForce(picked + pick, front + back);
		}
	}

	/**
	 * This method should load the dictionary from file and store it either in
	 * two Lists or a Map
	 * 
	 * @param fileName
	 */
	private void loadLexiconFromFile(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while (true) {
				String words = br.readLine();
				if (words == null)
					break;
				StringTokenizer st = new StringTokenizer(words, "=");
				String en = st.nextToken();
				String de = st.nextToken();
				lexicon.add(en);
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		println("Done loading file");
	}
}
