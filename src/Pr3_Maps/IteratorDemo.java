import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * IteratorDemo
 * 
 * Shows how an iterator works.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class IteratorDemo extends ConsoleProgram {

	public void run() {
		setSize(400, 300);
		setFont("monospaced-16");

		List<String> cities = new ArrayList<String>();

		cities.add("Nuremberg");
		cities.add("Munich");
		cities.add("Hamburg");
		cities.add("Berlin");

		println("List all cities via normal loop:");
		for (int i = 0; i < cities.size(); i++) {
			String city = cities.get(i);
			print(city + ", ");
		}
		println("");

		println("\nList all cities via for-each loop:");
		for (String city : cities) {
			print(city + ", ");
		}
		println("");

		println("\nList all cities via iterator:");
		Iterator iter = cities.iterator();
		while (iter.hasNext()) {
			String city = (String) iter.next();
			print(city + ", ");
		}
	}
}
