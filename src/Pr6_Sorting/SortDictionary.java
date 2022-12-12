import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * SortDictionary
 * 
 * Demonstrates how to use a TreeMap to sort a dictionary.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class SortDictionary extends ConsoleProgram {

	private Map<String, String> dictionary = new TreeMap<String, String>();

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-18");

		loadLexiconFromFile("dictionary_en_de.txt");
		printMap();
	}

	private void printMap() {
		for (String en : dictionary.keySet()) {
			println(en + ": " + dictionary.get(en));
		}
	}

	/**
	 * This method should load the dictionary from file.
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
				String en = st.nextToken().toLowerCase().trim();
				String de = st.nextToken().toLowerCase().trim();
				dictionary.put(en, de);
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done loading file");
	}

}
