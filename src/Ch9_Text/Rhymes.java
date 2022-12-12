import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Rhymes
 * 
 * Use the trie datastructure and a dictionary to find rhymes. <br/>
 * 1) read a dictionary from the file to fill the trie data structure with
 * values. You can either make english or german rhymes.<br/>
 * 2) before putting the words into the trie, reverse their order (why?)<br/>
 * 3) ask the user to enter a word. Take the last three letters of that word and
 * use the trie.nodesWithPrefix() method to find words that rhyme.<br/>
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Rhymes extends ConsoleProgram {

	private SimpleTrie trie = new SimpleTrie();

	public void run() {
		setSize(400, 400);
		setFont("Times New Roman-bold-24");

		loadLexiconFromFile("dictionary_en_de.txt");

		String word = readLine("Enter word to rhyme: ");
		for (String s : trie.nodesWithPrefix(reverseString(word))) {
			if (s.length() < 7) {
				println(reverseString(s));
			}
		}
	}

	private String reverseString(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = s.length() - 1; i >= 0; i--) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}

	/**
	 * This method should load the dictionary from file and store it in a trie.
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
				// maybe some of your code goes here
				trie.add(reverseString(en.toLowerCase()));
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\nDone loading file");
	}
}
