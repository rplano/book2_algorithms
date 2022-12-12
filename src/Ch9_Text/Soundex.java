import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Soundex
 * 
 * Implements the simple Soundex as originally developed by developed by Robert
 * C. Russell and Margaret King Odell.
 * 
 * Test with the following: <br/>
 * "Robert" and "Rupert": R163 <br/>
 * "Rubin": R150 <br/>
 * "Ashcraft" and "Ashcroft": A261 <br/>
 * "Tymczak": T522 <br/>
 * "Pfister": P236 <br/>
 * 
 * @see Soundex, https://en.wikipedia.org/wiki/Soundex
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Soundex extends ConsoleProgram {

	private String[] replaceTable = { "BFPV", "CGJKQSXZ", "DT", "L", "MN", "R" };
	private Map<String, Set<String>> allSoundexes = new HashMap<String, Set<String>>();

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-24");

		loadLexiconFromFile("dictionary_en_de.txt");

		while (true) {
			String word = readLine("Enter a word (Robert): ");
			if (word.length() == 0)
				break;
			String sndx = encode(word);
			println(sndx);
			println(allSoundexes.get(sndx));
		}
	}

	private String encode(String word) {
		String encoded = "";

		// Save the first letter.
		char first = word.toUpperCase().charAt(0);
		String rest = word.substring(1).toUpperCase();

		// Remove all occurrences of 'h' and 'w' except first letter.
		rest = rest.replace("H", "");
		rest = rest.replace("W", "");

		// Replace all consonants (include the first letter) with digits.
		rest = first + rest;

		for (int i = 0; i < rest.length(); i++) {
			char c = rest.charAt(i);
			if ("AEIOUY".contains("" + c)) {
				encoded += c;
			} else {
				for (int j = 0; j < replaceTable.length; j++) {
					if (replaceTable[j].contains("" + c)) {
						encoded += (j + 1);
					}
				}
			}
		}
		rest = encoded;

		// Replace all adjacent same digits with one digit.
		String tmp = rest;
		int i = 0;
		while (i < tmp.length() - 1) {
			if (tmp.charAt(i) == tmp.charAt(i + 1)) {
				tmp = tmp.substring(0, i) + tmp.substring(i + 1);
			} else {
				i++;
			}
		}
		rest = tmp;

		// Remove all occurrences of a, e, i, o, u, y except first letter.
		rest = rest.substring(1);
		rest = rest.replace("A", "");
		rest = rest.replace("E", "");
		rest = rest.replace("I", "");
		rest = rest.replace("O", "");
		rest = rest.replace("U", "");
		rest = rest.replace("Y", "");

		// If first symbol is a digit replace it with letter saved on step 1.
		encoded = first + rest;

		// Append 3 zeros if result contains less than 3 digits.
		encoded = encoded + "000";

		// Remove all except first letter and 3 digits after it
		encoded = encoded.substring(0, 4);

		return encoded;
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
				String soundex = encode(en);
				if (!allSoundexes.containsKey(soundex)) {
					allSoundexes.put(soundex, new HashSet<String>());
				}
				Set<String> similarWords = allSoundexes.get(soundex);
				similarWords.add(en);
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//println("Done loading file");
	}
}
