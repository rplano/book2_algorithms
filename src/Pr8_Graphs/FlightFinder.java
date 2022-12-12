import java.util.Map;

import graph.AbstractEdge;
import graph.Dijkstra;
import graph.GraphEdgeList;
import graph.Vertex;
import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * FlightFinder
 * 
 * A console program finding the shortest air route from Nuremberg to Paris.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class FlightFinder extends ConsoleProgram {

	public void init() {
		setSize(400, 300);
		setFont("Times New Roman-bold-20");
	}

	public void run() {
		GraphEdgeList<Integer, String> graph = new GraphEdgeList<Integer, String>();

		Vertex<String> v1 = graph.insertVertex(new Vertex<String>("NUE"));
		Vertex<String> v2 = graph.insertVertex(new Vertex<String>("AMS"));
		Vertex<String> v3 = graph.insertVertex(new Vertex<String>("MAD"));
		Vertex<String> v4 = graph.insertVertex(new Vertex<String>("CDG"));
		AbstractEdge<Integer> e1 = graph.insertEdge(v1, v2, 542);
		AbstractEdge<Integer> e2 = graph.insertEdge(v2, v3, 1462);
		AbstractEdge<Integer> e3 = graph.insertEdge(v1, v3, 1524);
		AbstractEdge<Integer> e4 = graph.insertEdge(v2, v4, 399);

		try {
			Dijkstra<Integer, String> dijK = new Dijkstra<Integer, String>(
					graph);

			Map<Vertex<String>, Integer> dists = dijK.getAllDistances(v1);
			for (Vertex<String> vx : dists.keySet()) {
				println("" + v1.getElement() + " -> " + vx.getElement() + ": "
						+ dists.get(vx));
			}

			// fastest route from B to A:
			println("Fastest route from " + v1.getElement() + " to "
					+ v4.getElement() + ": ");
			Map<Vertex<String>, Vertex<String>> predcrs = dijK
					.getAllPredecessors(v1);
			Vertex<String> vTmp = v4;
			while (vTmp != v1) {
				print(vTmp.getElement() + " -> ");
				vTmp = predcrs.get(vTmp);
			}
			println(vTmp.getElement());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
