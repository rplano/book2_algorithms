import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * SpellChecker
 * 
 * First loads a lot of English words into a set, and then simply checks if the
 * searched word is in the set.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class SpellChecker extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-24");

		Set<String> words = buildIndexFromFile("dictionary_en_de.txt");

		while (true) {
			String word = readLine("Enter word to check: ");
			if (words.contains(word.toLowerCase())) {
				println("Spelling is correct.");
			} else {
				println("Spelling is NOT correct.");
			}
		}
	}

	private Set<String> buildIndexFromFile(String fileName) {
		HashSet<String> al = new HashSet<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				StringTokenizer st = new StringTokenizer(line, "=");
				String en = st.nextToken().trim().toLowerCase();
				String de = st.nextToken().trim().toLowerCase();
				al.add(en);
			}
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return al;
	}
}
