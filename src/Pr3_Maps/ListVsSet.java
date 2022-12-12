import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * ListVsSet
 * 
 * Shows that ArrayList.contains() is not the fastest on the planet.<br/>
 * To see that contains() is the culprit, comment the if...contains line.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class ListVsSet extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-24");

		String text = readTextFromFile("Ulysses.txt"); // TomSawyer.txt
		// String text = readTextFromFile("TomSawyer.txt"); // TomSawyer.txt

		List<String> indexList = buildIndexUsingList(text);
		println("Size List: " + indexList.size());		
		println("Contains with List: " + testContainsList(indexList) + " ms");

		Set<String> indexSet = buildIndexUsingSet(text);
		println("Size Set: " + indexSet.size());		
		println("Contains with Set: " + testContainsSet(indexSet) + " ms");
	}

	private long testContainsList(List<String> al) {
		// start the read test:
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			if (al.contains("marvel")) {
				// we found it! 
			}
		}
		long end = System.currentTimeMillis();
		return (end - start);
	}

	private long testContainsSet(Set<String> al) {
		// start the read test:
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			if (al.contains("marvel")) {
				// we found it! 
			}
		}
		long end = System.currentTimeMillis();
		return (end - start);
	}

	private List<String> buildIndexUsingList(String text) {
		ArrayList<String> al = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(text);
		long startTime = System.currentTimeMillis();
		while (st.hasMoreTokens()) {
			String word = st.nextToken();
			if (!al.contains(word)) {
				al.add(word);
			}
		}
		long time = System.currentTimeMillis() - startTime;
		println("Time with List: " + time + " ms.");
		return al;
	}

	private Set<String> buildIndexUsingSet(String text) {
		HashSet<String> al = new HashSet<String>();
		StringTokenizer st = new StringTokenizer(text);
		long startTime = System.currentTimeMillis();
		while (st.hasMoreTokens()) {
			String word = st.nextToken();
			al.add(word);
		}
		long time = System.currentTimeMillis() - startTime;
		println("Time with Set: " + time + " ms.");
		return al;
	}

	private String readTextFromFile(String fileName) {
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				sb.append(line);
			}
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return sb.toString();
	}
}
