import java.util.Collection;

import acm.program.ConsoleProgram;
import graph.AbstractEdge;
import graph.GraphEdgeList;
import graph.Vertex;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * SimpleFlights
 * 
 * A console program, demonstrating how to create a simple graph.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class SimpleFlights extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-40");

		GraphEdgeList<Integer, String> graph = new GraphEdgeList<Integer, String>();

		Vertex<String> v1 = graph.insertVertex(new Vertex<String>("NUE"));
		Vertex<String> v2 = graph.insertVertex(new Vertex<String>("AMS"));
		Vertex<String> v3 = graph.insertVertex(new Vertex<String>("MAD"));

		AbstractEdge<Integer> e1 = graph.insertEdge(v1, v2, 542);
		AbstractEdge<Integer> e2 = graph.insertEdge(v2, v3, 1462);
		AbstractEdge<Integer> e3 = graph.insertEdge(v1, v3, 1524);

		println(listVertices(graph.vertices()));
		println("Size: " + graph.size());
		println("isConnected: " + graph.isConnected());
		println("isTree: " + graph.isTree());
		try {
			println("hasCycle: " + graph.hasCycle());
		} catch (Exception e) {
			e.printStackTrace();
		}

		findDistance(graph, "NUE", "MAD");
	}

	private void findDistance(GraphEdgeList<Integer, String> graph,
			String city1, String city2) {
		Vertex<String> v1 = graph.findVertex(city1);
		Vertex<String> v2 = graph.findVertex(city2);
		Collection<AbstractEdge<Integer>> connections = graph.incidentEdges(v1);
		for (AbstractEdge<Integer> edge : connections) {
			Vertex<String> v3 = graph.opposite(v1, edge);
			if (v2 == v3) {
				println(edge.getElement());
			}
		}
	}

	private String listVertices(Collection<Vertex<String>> vertices) {
		String verts = "Vertices: ";
		for (Vertex<String> vertex : vertices) {
			verts += vertex.getElement() + " - ";
		}
		return verts;
	}

}
