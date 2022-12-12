import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Levenshtein
 * 
 * Calculates Levenshtein distance between two strings.
 * 
 * between "kitten" and "sitting" Levenshtein distance is 3 <br/>
 * between "flaw" and "lawn" Levenshtein distance is 2 <br/>
 * 
 * @see Levenshtein distance, https://en.wikipedia.org/wiki/Levenshtein_distance
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Levenshtein extends ConsoleProgram {

	private Map<Integer, Set<String>> distances = new TreeMap<Integer, Set<String>>();

	public void run() {
		setSize(400, 300);
		setFont("Times New Roman-bold-24");

		String word1 = readLine("Enter first word (kitten): ");
		String word2 = readLine("Enter second word (sitting): ");
		println("Levenshtein distance: " + distanceSlow(word1, word2));

		// spell check:
		String word = readLine("Enter a word for spell checking (kiten): ");

		loadLexiconFromFile("dictionary_en_de.txt", word);

		Iterator it = distances.entrySet().iterator();
		if (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			int distance = (int) pair.getKey();
			println("" + distance + ": " + pair.getValue());
		} else {
			println("no match found!");
		}

	}

	private int distanceSlow(String s, String t) {
		int cost;
		int len_s = s.length();
		int len_t = t.length();

		// base case:
		if (len_s == 0 || len_t == 0) {
			return len_s + len_t;
		}

		// test if last characters of the strings match
		if (s.charAt(len_s - 1) == t.charAt(len_t - 1)) {
			cost = 0;
		} else {
			cost = 1;
		}

		// return minimum of delete char from s, delete char from t, and delete
		// char from both
		return min(
				distanceSlow(s.substring(0, len_s - 1), t) + 1,
				distanceSlow(s, t.substring(0, len_t - 1)) + 1,
				distanceSlow(s.substring(0, len_s - 1),
						t.substring(0, len_t - 1))
						+ cost);
	}

	/**
	 * Hjelmqvist, Sten (26 Mar 2012), Fast, memory efficient Levenshtein
	 * algorithm,
	 * https://www.codeproject.com/articles/13525/fast-memory-efficient
	 * -levenshtein-algorithm
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	private int distanceFast(String s, String t) {
		// degenerate cases
		if (s.equals(t))
			return 0;
		if (s.length() == 0)
			return t.length();
		if (t.length() == 0)
			return s.length();

		// create two work vectors of integer distances
		int[] v0 = new int[t.length() + 1];
		int[] v1 = new int[t.length() + 1];

		// initialize v0 (the previous row of distances)
		// this row is A[0][i]: edit distance for an empty s
		// the distance is just the number of characters to delete from t
		for (int i = 0; i < v0.length; i++) {
			v0[i] = i;
		}

		for (int i = 0; i < s.length(); i++) {
			// calculate v1 (current row distances) from the previous row v0

			// first element of v1 is A[i+1][0]
			// edit distance is delete (i+1) chars from s to match empty t
			v1[0] = i + 1;

			// use formula to fill in the rest of the row
			for (int j = 0; j < t.length(); j++) {
				int cost = (s.charAt(i) == t.charAt(j)) ? 0 : 1;
				v1[j + 1] = min(v1[j] + 1, v0[j + 1] + 1, v0[j] + cost);
			}

			// copy v1 (current row) to v0 (previous row) for next iteration
			for (int j = 0; j < v0.length; j++) {
				v0[j] = v1[j];
			}
		}

		return v1[t.length()];
	}

	private int min(int i, int j, int k) {
		return Math.min(i, Math.min(j, k));
	}

	/**
	 * This method should load the dictionary from file and store it either in
	 * two Lists or a Map
	 * 
	 * @param fileName
	 */
	private void loadLexiconFromFile(String fileName, String word) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while (true) {
				String words = br.readLine();
				if (words == null)
					break;
				StringTokenizer st = new StringTokenizer(words, "=");
				String en = st.nextToken();
				String de = st.nextToken();

				if (en.length() < 10) {
					int dist = distanceFast(word, en);
					// int dist = distanceSlow(word, en);

					if (!distances.containsKey(dist)) {
						distances.put(dist, new TreeSet<String>());
					}
					Set<String> similarWords = distances.get(dist);
					similarWords.add(en);
				}
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// println("Done loading file");
	}
}
