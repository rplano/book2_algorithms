import java.util.ArrayList;
import java.util.Collections;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * SortDogs
 * 
 * Demonstrates how to use the sort method of the Collection class to sort a
 * collection.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class SortDogs extends ConsoleProgram {

	public SortDogs() {
		setSize(400, 200);
		setFont("Times New Roman-bold-18");

		ArrayList<Dog> dogs = new ArrayList<Dog>();
		dogs.add(new Dog(42, "Lassie"));
		dogs.add(new Dog(5, "Nike"));
		dogs.add(new Dog(32, "Garfield"));

		listDogs(dogs);
		println();
		
		Collections.sort(dogs);
		
		listDogs(dogs);
	}

	private void listDogs(ArrayList<Dog> dogs) {
		for (Dog dog : dogs) {
			println(dog);
		}
	}

}
