import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * SearchArrayList
 * 
 * A simple implementation of a binary search tree, showing that search is
 * logarithmic in time.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class SearchArrayList extends ConsoleProgram {

	private List<String> al = new ArrayList<String>();

	public void run() {
		setSize(400, 300);
		setFont("Times New Roman-bold-18");

		print("SearchArrayList");

		loadLexiconFromFile("dictionary_en_de.txt");

		while (true) {

			String searchWord = readLine("Enter German word to search: ");
			if (searchWord.length() == 0)
				break;

			boolean found = false;
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < 10; i++) {
				found = al.contains(searchWord.toLowerCase().trim());
			}
			long endTime = System.currentTimeMillis();

			if (found) {
				println("Word found in " + (endTime - startTime) + " ms.");
			} else {
				println("Word NOT found in " + (endTime - startTime) + " ms.");
			}
		}
		println("Thank you for playing!");
	}

	/**
	 * This method should load the dictionary from file and store it either in
	 * two Lists or a Map
	 * 
	 * @param fileName
	 */
	private void loadLexiconFromFile(String fileName) {
		print("loading file... ");
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
				al.add(de.toLowerCase().trim());
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		println("done!");
	}
}
