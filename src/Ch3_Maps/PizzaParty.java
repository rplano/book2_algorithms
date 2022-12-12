import java.util.HashSet;
import java.util.Set;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * PizzaParty
 * 
 * Example demonstrates how to use the Set data structures.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class PizzaParty extends ConsoleProgram {
	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-18");

		// init set
		Set<String> pizza = initPizza();
		println(pizza);

		Set<String> allergic = new HashSet<String>();
		allergic.add("anchovies");
		allergic.add("peanuts");
		println(allergic);

		pizza.removeAll(allergic);
		// pizza.addAll(allergic);
		// pizza.retainAll(allergic);
		println(pizza);
	}

	private Set<String> initPizza() {
		Set<String> pizza = new HashSet<String>();

		// add entries
		pizza.add("tomato");
		pizza.add("olives");
		pizza.add("cheese");
		pizza.add("anchovies");
		return pizza;
	}
}
