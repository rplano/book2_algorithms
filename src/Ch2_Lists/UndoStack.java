import java.util.Stack;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * UndoStack
 * 
 * Shows how to use a Stack.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class UndoStack extends ConsoleProgram {

	public void run() {
		setSize(400, 300);
		setFont("Times New Roman-bold-18");

		// init stack
		Stack<String> cities = new Stack<String>();

		// add cities
		cities.push("Nuremberg");
		cities.push("Munich");
		cities.push("Hamburg");
		cities.push("Berlin");
		cities.push("Frankfurt");
		println("There is a total of " + cities.size()
				+ " cities on the stack.");

		// whats on top of the stack
		println("Topmost city: " + cities.peek());
		println("There is a total of " + cities.size()
				+ " cities on the stack.");

		// remove one by one the top element from the stack
		println("Traveling back the way we came:");
		println(cities.pop());
		println(cities.pop());
		println(cities.pop());
		println(cities.pop());
		println(cities.pop());
		println("There is a total of " + cities.size()
				+ " cities on the stack.");
	}
}
