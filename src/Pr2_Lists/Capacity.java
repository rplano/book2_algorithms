import java.util.*;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Capacity
 * 
 * This program will add 4 MByte at a time to an ArrayList. After each addition,
 * it will print how much time it took, and how much memory the ArrayList
 * consumes. After a while you should get a "java.lang.OutOfMemoryError".
 * Notice, how most inserts take a few milliseconds, but some inserts take
 * significantly longer.
 * 
 * To increase the heap memory, go to "Run->Run Configuration", click the
 * Arguments tab then add: "-Xmx6000M" to the VM arguments. (assuming you have 6
 * GByte of RAM memory.)
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Capacity {

	private static final long ONE_MEGABYTE = 1024 * 1024;

	private static void addSomeEntries(List<Integer> al) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			al.add(42);
		}
		long end = System.currentTimeMillis();
		System.out.println("Time: " + (end - start) + " ms (" + al.size() * 4
				/ ONE_MEGABYTE + " MByte)");
	}

	private static long usedMemory() {
		Runtime runtime = Runtime.getRuntime();
		return runtime.totalMemory() - runtime.freeMemory();
	}

	public static void main(String[] args) {
		List<Integer> al = new ArrayList<Integer>();
		// List<Integer> al = new LinkedList<Integer>();
		long startMem = usedMemory();
		for (int i = 0; i < 80; i++) {
			addSomeEntries(al);
		}
		long endMem = usedMemory();

		System.out.println("Really used: " + (endMem - startMem) / ONE_MEGABYTE
				+ " MByte");
	}
}
