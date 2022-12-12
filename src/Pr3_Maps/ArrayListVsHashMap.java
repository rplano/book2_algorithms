import java.util.ArrayList;
import java.util.HashMap;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * ArrayListVsHashMap
 * 
 * Measures read and write timings for ArrayList and HashMap.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class ArrayListVsHashMap  extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-24");

		println("ArrayList read time: " + testReadArrayList() + " ms");
		println("ArrayList write time: " + testWriteArrayList() + " ms");
		println("HashMap read time: " + testReadHashMap() + " ms");
		println("HashMap write time: " + testWriteHashMap() + " ms");
	}

	private static long testReadArrayList() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		//HashMap<Integer,Integer> al = new HashMap<Integer,Integer>();

		// fill list with some dummy data:
		for (int i = 0; i < 1000000; i++) {
			al.add(42);
			//al.put(i, 42);
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

	private static long testWriteArrayList() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		//HashMap<Integer,Integer> al = new HashMap<Integer,Integer>();

		// fill list with some dummy data:
		for (int i = 0; i < 1000000; i++) {
			al.add(42);
			//al.put(i, 42);
		}

		// start the write test:
		long start = System.currentTimeMillis();
		for (int i = 0; i < 20000; i++) {
			int randomPos = (int) (100000.0 * Math.random());
			al.add(randomPos, 42);
			//al.put(randomPos, 42);
		}
		long end = System.currentTimeMillis();
		return (end - start);
	}

	private static long testReadHashMap() {
		//ArrayList<Integer> al = new ArrayList<Integer>();
		HashMap<Integer,Integer> al = new HashMap<Integer,Integer>();

		// fill list with some dummy data:
		for (int i = 0; i < 1000000; i++) {
			//al.add(42);
			al.put(i, 42);
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

	private static long testWriteHashMap() {
		//ArrayList<Integer> al = new ArrayList<Integer>();
		HashMap<Integer,Integer> al = new HashMap<Integer,Integer>();

		// fill list with some dummy data:
		for (int i = 0; i < 1000000; i++) {
			//al.add(42);
			al.put(i, 42);
		}

		// start the write test:
		long start = System.currentTimeMillis();
		for (int i = 0; i < 20000; i++) {
			int randomPos = (int) (100000.0 * Math.random());
			//al.add(randomPos, 42);
			al.put(randomPos, 42);
		}
		long end = System.currentTimeMillis();
		return (end - start);
	}

}
