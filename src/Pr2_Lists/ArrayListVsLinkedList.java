import java.util.*;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * ArrayListVsLinkedList
 * 
 * Measures read and write timings for LinkedList and ArrayList.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class ArrayListVsLinkedList extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-24");

		println("ArrayList read time: " + testRead(new ArrayList<Integer>())
				+ " ms");
		println("ArrayList write time: " + testWrite(new ArrayList<Integer>())
				+ " ms");
		println("LinkedList read time: " + testRead(new LinkedList<Integer>())
				+ " ms");
		println("LinkedList write time: "
				+ testWrite(new LinkedList<Integer>()) + " ms");
	}

	private static long testRead(List<Integer> al) {
		// fill list with some dummy data:
		for (int i = 0; i < 1000000; i++) {
			al.add(42);
		}

		// start the read test:
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			// read an element at a random position:
			int randomPos = (int) (100000.0 * Math.random());
			al.get(randomPos);
		}
		long end = System.currentTimeMillis();
		return (end - start);
	}

	private static long testWrite(List<Integer> al) {
		// fill list with some dummy data:
		for (int i = 0; i < 1000000; i++) {
			al.add(42);
		}

		// start the write test:
		long start = System.currentTimeMillis();
		for (int i = 0; i < 20000; i++) {
			int randomPos = (int) (100000.0 * Math.random());
			al.add(randomPos, 42);
		}
		long end = System.currentTimeMillis();
		return (end - start);
	}

}
