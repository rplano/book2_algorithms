import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * CityList
 * 
 * Shows how to use an ArrayList.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class CityList extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-18");

		// init list
		List<String> cities = new ArrayList<String>();

		// add cities
		cities.add("Nuremberg");
		cities.add("Munich");
		cities.add("Hamburg");
		cities.add("Berlin");
		cities.add("Frankfurt");

		// get the third city
		println("Third city: " + cities.get(2));

		// remove the third city
		cities.remove(2);

		// replace the second city by another city
		cities.set(1, "Stuttgart");

		// search for Stuttgart
		println("Stuttgart is at position: " + cities.indexOf("Stuttgart"));

		// list all remaining cities
		println("All remaining cities:");
		for (String city : cities) {
			print(city + ", ");
		}
		println("\nThere is a total of " + cities.size() + " cities.");
	}
}
