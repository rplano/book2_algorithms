import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * StopWords
 * 
 * Generate 6 unique random numbers (no duplicates) between 1 and 49 using a
 * Set.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class StopWords extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-24");

		Set<String> stopWords = buildIndexFromFile("StopWords.txt");
		//println(stopWords.size());

		String sentence = readLine("Enter a sentence: ");
		StringTokenizer st = new StringTokenizer(sentence);
		while (st.hasMoreTokens()) {
			String word = st.nextToken().toLowerCase();
			if (!stopWords.contains(word)) {
				print(word + " ");
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
				StringTokenizer st = new StringTokenizer(line, " ");
				while (st.hasMoreTokens()) {
					String sw = st.nextToken().trim().toLowerCase();
					al.add(sw);
				}
			}
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return al;
	}
}
