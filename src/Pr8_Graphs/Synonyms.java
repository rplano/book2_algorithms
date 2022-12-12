import graph.AbstractEdge;
import graph.GraphEdgeList;
import graph.GraphParser;
import graph.Vertex;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Synonyms
 * 
 * A console program, used for demonstrating navigation through a graph.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Synonyms extends ConsoleProgram {

	private final String FILE_NAME = "synonyms.txt";

	private GraphEdgeList<String, String> graph;

	public void init() {
		setSize(400, 400);
		setFont("Times New Roman-bold-20");
		graph = new GraphParser().parseGraph(new File(FILE_NAME));
	}

	public void run() {
		startAdventure("grass");
	}

	private void startAdventure(String currentRoom) {
		Vertex<String> currentVertex = graph.findVertex(currentRoom);
		while (true) {
			println("The current word is: " + currentVertex.getElement() + ".");
			List<String> roomsToGo = findRoomsToGoTo(currentVertex);
			if (roomsToGo.size() > 0) {
				println("Synonyms are: " + roomsToGo);
			} else {
				println("There are no synonyms for: " + currentRoom);
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
			currentVertex = graph.findVertex(currentRoom);
		} while (currentVertex == null);
		return currentVertex;
	}

	private List<String> findRoomsToGoTo(Vertex<String> currentVertex) {
		List<String> roomsToGo = new ArrayList<String>();
		Collection<AbstractEdge<String>> outEdgs = graph
				.incidentEdges(currentVertex);
		for (AbstractEdge<String> edge : outEdgs) {
			roomsToGo.add(graph.opposite(currentVertex, edge).getElement());
		}
		return roomsToGo;
	}
}
