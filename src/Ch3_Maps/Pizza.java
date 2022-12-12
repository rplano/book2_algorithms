import java.util.HashSet;
import java.util.Set;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Pizza
 * 
 * Example demonstrates how to use the Set data structures.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Pizza extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-18");

		// init set
		Set<String> pizza = new HashSet<String>();

		// add entries
		pizza.add("tomato");
		pizza.add("olives");
		pizza.add("cheese");
		pizza.add("anchovies");
		println("Pizza has " + pizza.size() + " toppings.");

		println("Pizza has anchovies: " + pizza.contains("anchovies"));

		// remove one topping
		pizza.remove("olives");
		println("Pizza has " + pizza.size() + " toppings.");

		// list all remaining toppings
		println("All remaining toppings:");
		for (String topping : pizza) {
			print(topping + ", ");
		}

	}
}
