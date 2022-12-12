import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import graph.DiGraphEdgeList;
import graph.DiGraphParser;
import graph.EdgeDirected;
import graph.Vertex;
import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Adventure
 * 
 * A console program, used for demonstrating navigation through di-graphs.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Adventure extends ConsoleProgram {

	private final String FILE_NAME = "adventure.txt";

	private DiGraphEdgeList<String, String> diGraph;

	public void init() {
		setSize(400, 400);
		setFont("Times New Roman-bold-20");
		diGraph = new DiGraphParser().parseGraph(new File(FILE_NAME));
	}

	public void run() {
		startAdventure("kitchen");
	}

	private void startAdventure(String currentRoom) {
		Vertex<String> currentVertex = diGraph.findVertex(currentRoom);
		while (true) {
			println("You are currently in " + currentVertex.getElement() + ".");
			List<String> roomsToGo = findRoomsToGoTo(currentVertex);
			if (roomsToGo.size() > 0) {
				println("You can go to: " + roomsToGo);
			} else {
				println("You can not go anywhere!");
				break;
			}
			// ask user where to go next
			currentVertex = askUserWhereToGo();
		}
		println("Thank you for playing!");
	}

	private Vertex<String> askUserWhereToGo() {
		Vertex<String> currentVertex;
		do {
			String currentRoom = readLine("Where do you want to go? ");
			currentVertex = diGraph.findVertex(currentRoom);
		} while (currentVertex == null);
		return currentVertex;
	}

	private List<String> findRoomsToGoTo(Vertex<String> currentVertex) {
		List<String> roomsToGo = new ArrayList<String>();
		Collection<EdgeDirected<String>> outEdgs = diGraph
				.outgoingEdges(currentVertex);
		for (EdgeDirected<String> edge : outEdgs) {
			roomsToGo.add(diGraph.opposite(currentVertex, edge).getElement());
		}
		return roomsToGo;
	}

}
