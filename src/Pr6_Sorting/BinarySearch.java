import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * BinarySearch
 * 
 * Demonstrates how to do a binary search with a list.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class BinarySearch {

	private List<String> al = new ArrayList<String>();

	/**
	 * Constructor
	 */
	public BinarySearch() {
		System.out.println("BinarySearch");
		al.add("bill");
		al.add("larry");
		al.add("linus");
		al.add("richard");

		long startTime = System.currentTimeMillis();
		System.out.println(binarySearch(al, 0, 3, "linus"));
		long endTime = System.currentTimeMillis();

		System.out.println("Searching took " + (endTime - startTime) + " ms");
	}

	public int binarySearch(List<String> al, int start, int stop, String key) {
		if (start > stop) {
			return -1;
		}

		int mid = (start + stop) / 2;
		if (key.equals(al.get(mid))) {
			return mid;
		} else if (key.compareTo((String) al.get(mid)) < 0) {
			return binarySearch(al, start, mid - 1, key);
		} else {
			return binarySearch(al, mid + 1, stop, key);
		}
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
				// maybe some of your code goes here

			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done loading file");
	}

	public static void main(String[] args) {
		BinarySearch bs = new BinarySearch();
	}

}
