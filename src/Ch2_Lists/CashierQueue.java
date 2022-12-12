import java.util.LinkedList;
import java.util.Queue;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * CashierQueue
 * 
 * Simulates people waiting in a line using the Queue data structure.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class CashierQueue extends ConsoleProgram {

	public void run() {
		setSize(400, 300);
		setFont("Times New Roman-bold-18");

		// init queue
		Queue<String> lineOfPeople = new LinkedList<String>();

		// add people
		lineOfPeople.add("fritz");
		lineOfPeople.add("lisa");
		lineOfPeople.add("hans");
		println("There is a total of " + lineOfPeople.size()
				+ " people waiting in line.");

		// who is in front of the queue
		println("First person in line: " + lineOfPeople.peek());
		println("There is a total of " + lineOfPeople.size()
				+ " people waiting in line.");

		// remove one by one the top element from the stack
		println("Customers checking out:");
		println(lineOfPeople.remove());
		println(lineOfPeople.remove());
		println(lineOfPeople.remove());
		println("There is a total of " + lineOfPeople.size()
				+ " people waiting in line.");
	}
}
