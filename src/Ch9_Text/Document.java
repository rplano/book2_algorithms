import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Document
 * 
 * Store a whole document in a trie data structure keeping the position
 * information of each string. We then try to reconstruct the whole document.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Document extends ConsoleProgram {

	private Trie<Integer> trie = new Trie<Integer>();

	// keeps line, position and word
	Map<Integer, Map<Integer, String>> document = new HashMap<Integer, Map<Integer, String>>();

	public void run() {
		setSize(400, 300);
		setFont("Times New Roman-bold-24");

		loadLexiconFromFile("Faust.txt"); // Ulysses

		reconstructDocument();

		int lineNr = readInt("Enter line number (1707): ");
		printOneLineBeforeAndOneLineAfter(lineNr);

	}

	private void printOneLineBeforeAndOneLineAfter(int lineNr) {
		for (int i = lineNr - 1; i <= lineNr + 1; i++) {
			Map<Integer, String> sentence = document.get(i);
			print("\"");
			if (sentence != null) {
				for (int pos : sentence.keySet()) {
					print(sentence.get(pos) + " ");
				}
			}
			println("\"");
		}
	}

	private void reconstructDocument() {
		// fill map from trie
		for (char c = 'a'; c <= 'z'; c++) {
			Set<String> words = trie.nodesWithPrefix("" + c);
			for (String word : words) {
				Set<Integer> linesAndPositions = trie.get(word);
				for (int lineAndPosition : linesAndPositions) {
					int line = lineAndPosition / 100;
					int pos = lineAndPosition % 100;
					Map<Integer, String> sentence = document.get(line);
					if (sentence == null) {
						sentence = new TreeMap<Integer, String>();
					}
					sentence.put(pos, word);
					document.put(line, sentence);
				}
			}
		}
	}

	/**
	 * This method should load the dictionary from file and store it in a trie.
	 * 
	 * @param fileName
	 */
	private void loadLexiconFromFile(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			int lineNr = 1;
			while (true) {
				String words = br.readLine();
				if (words == null)
					break;

				int position = 1;
				StringTokenizer st = new StringTokenizer(words, " ,.:;'!?-()\"");
				while (st.hasMoreTokens()) {
					String token = st.nextToken().toLowerCase();
					token = token.replace("ä", "ae");
					token = token.replace("ö", "oe");
					token = token.replace("ü", "ue");
					token = token.replace("ß", "ss");

					// we assume lines are less than 100 chars!
					trie.add(token, lineNr * 100 + position);
					position += token.length() + 1;
				}
				lineNr++;
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done loading file");
	}
}
