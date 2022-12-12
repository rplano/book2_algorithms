import java.util.HashMap;
import java.util.Map;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Dictionary
 * 
 * A German-English dictionary with 3 word pairs.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Dictionary extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-18");

		// init map
		Map<String, String> dictionary = new HashMap<String, String>();

		// add words
		dictionary.put("hund", "dog");
		dictionary.put("katze", "cat");
		dictionary.put("fisch", "fish");
		println("There is a total of " + dictionary.size()
				+ " words in the dictionary.");

		// translate a word
		println("'hund' translates to: " + dictionary.get("hund"));

		// remove a word
		dictionary.remove("hund");

		// list all remaining words
		println("All remaining words:");
		for (String word : dictionary.keySet()) {
			println(word + ": " + dictionary.get(word));
		}
		println("There is a total of " + dictionary.size()
				+ " words in the dictionary.");

	}

}