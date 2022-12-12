import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import acm.program.ConsoleProgram;
import tree.BinaryNode;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * SearchBinaryTree
 * 
 * A simple implementation of a binary search tree, showing that search is
 * logarithmic in time.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class SearchBinaryTree extends ConsoleProgram {

	private SearchBinaryNode<String> root;

	public void run() {
		setSize(400, 300);
		setFont("Times New Roman-bold-18");

		print("SearchBinaryTree: ");

		loadLexiconFromFile("dictionary_en_de.txt");

		while (true) {

			String searchWord = readLine("Enter German word to search: ");
			if (searchWord.length() == 0)
				break;

			boolean found = false;
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < 10; i++) {
				found = contains(searchWord.toLowerCase().trim());				
			}
			long endTime = System.currentTimeMillis();

			if (found) {
				println("Word found in " + (endTime - startTime) + " ms, it took "+nrOfQueries+" queries.");
			} else {
				println("Word NOT found in " + (endTime - startTime) + " ms, it took "+nrOfQueries+" queries.");
			}
		}
		println("Thank you for playing!");
	}

	private int nrOfQueries = 0;
	public boolean contains(String word) {
		nrOfQueries = 0;
		SearchBinaryNode<String> current = root;
		while (true) { // loop and a half
			nrOfQueries++;
			String x = current.getElement();
			int comparison = x.compareTo(word);
			if (comparison == 0) {
				// we found it
				return true;
			} else if (comparison > 0) {
				// go to right
				if (current.hasRight()) {
					current = current.getRight();
				} else {
					break;
				}
			} else {
				// go to left
				if (current.hasLeft()) {
					current = current.getLeft();
				} else {
					break;
				}
			}
		}
		return false;
	}

	public void add(String word) {
		if (root == null) {
			root = new SearchBinaryNode<String>(word);
		} else {
			SearchBinaryNode<String> current = root;

			// first find if word is already in tree,
			// and navigate to approp position
			int comparison = 0;
			while (true) { // loop and a half
				String x = current.getElement();
				comparison = x.compareTo(word);
				if (comparison == 0) {
					// do nothing already in tree
					break;
				} else if (comparison > 0) {
					// go to right
					if (current.hasRight()) {
						current = current.getRight();
					} else {
						break;
					}
				} else {
					// go to left
					if (current.hasLeft()) {
						current = current.getLeft();
					} else {
						break;
					}
				}
			}

			// insert element if not already in tree
			//String x = current.getElement();
			if (comparison > 0) {
				// add to right
				current.setRight(new SearchBinaryNode<String>(word));
			} else if (comparison < 0) {
				// add to left
				current.setLeft(new SearchBinaryNode<String>(word));
			}
		}
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
				add(de.toLowerCase().trim());
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		println("done!");
	}

}
