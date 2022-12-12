import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * TreeSetVsHashSet
 * 
 * Shows that a hash set is a little faster than a tree set.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class TreeSetVsHashSet extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-24");

		println("HashSet search time: " + testSearch(new HashSet<Integer>()) + " ms");
		println("TreeSet search time: " + testSearch(new TreeSet<Integer>()) + " ms");
	}

	private static long testSearch(Set<Integer> set) {
		// fill list with some random dummy data:
		for (int i = 0; i < 10000000; i++) {
			int randomPos = (int) (100000.0 * Math.random());
			set.add(randomPos);
		}

		// start the read test:
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			// read an element at a random position:
			int randomPos = (int) (100000.0 * Math.random());
			set.contains(randomPos);
		}
		long end = System.currentTimeMillis();
		return (end - start);
	}

}
