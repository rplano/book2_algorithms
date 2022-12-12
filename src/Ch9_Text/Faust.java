import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Faust
 * 
 * You enter a two letter quote from your favorite book and it will show you
 * where this quote is to be found in the book. In addition it will show you the
 * line before and after the quote.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Faust extends ConsoleProgram {

	private Trie<Integer> trie = new Trie<Integer>();
	private List<String> text = new ArrayList<String>();

	public void run() {
		setSize(400, 300);
		setFont("Times New Roman-bold-24");

		loadLexiconFromFile("Faust.txt"); // Ulysses

		String searchWords = readLine("Enter two words: ");
		String[] words = searchWords.toLowerCase().split(" ");

		Set<Integer> lineNrs = trie.get(words[0].trim() + words[1].trim());
		println("The words occur in line(s): " + lineNrs);

		println();
		for (int nr : lineNrs) {
			println("\"" + text.get(nr - 2) + "\"");
			println("\"" + text.get(nr - 1) + "\"");
			println("\"" + text.get(nr - 0) + "\"");
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
				text.add(words);
				StringTokenizer st = new StringTokenizer(words, " ,.:;'!?-()\"");
				String previousToken = "";
				while (st.hasMoreTokens()) {
					String token = st.nextToken().toLowerCase();
					token = token.replace("ä", "ae");
					token = token.replace("ö", "oe");
					token = token.replace("ü", "ue");
					token = token.replace("ß", "ss");
					if (previousToken.length() > 0) {
						trie.add(previousToken + "" + token, lineNr);
					}
					previousToken = token;
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
