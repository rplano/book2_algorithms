import java.util.Map;
import java.util.Set;

import graph.AbstractEdge;
import graph.GraphEdgeList;
import graph.Kruskal;
import graph.Prim;
import graph.Vertex;
import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * DirtyCity
 * 
 * A console program for demonstrating Prims and Kruskals algorithms to find the
 * minimum spanning tree.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class DirtyCity extends ConsoleProgram {

	public void init() {
		setSize(400, 300);
		setFont("Times New Roman-bold-20");
	}

	public void run() {
		GraphEdgeList<Integer, String> graph = new GraphEdgeList<Integer, String>();

		// from https://de.wikipedia.org/wiki/Algorithmus_von_Prim
		Vertex<String> vA = graph.insertVertex(new Vertex<String>("A"));
		Vertex<String> vB = graph.insertVertex(new Vertex<String>("B"));
		Vertex<String> vC = graph.insertVertex(new Vertex<String>("C"));
		Vertex<String> vD = graph.insertVertex(new Vertex<String>("D"));
		Vertex<String> vE = graph.insertVertex(new Vertex<String>("E"));
		Vertex<String> vF = graph.insertVertex(new Vertex<String>("F"));
		Vertex<String> vG = graph.insertVertex(new Vertex<String>("G"));

		AbstractEdge<Integer> e1 = graph.insertEdge(vA, vB, 1);
		AbstractEdge<Integer> e2 = graph.insertEdge(vA, vD, 2);
		AbstractEdge<Integer> e3 = graph.insertEdge(vB, vC, 3);
		AbstractEdge<Integer> e4 = graph.insertEdge(vB, vD, 4);
		AbstractEdge<Integer> e5 = graph.insertEdge(vB, vE, 2);
		AbstractEdge<Integer> e6 = graph.insertEdge(vB, vF, 2);
		AbstractEdge<Integer> e7 = graph.insertEdge(vC, vE, 2);
		AbstractEdge<Integer> e8 = graph.insertEdge(vD, vF, 3);
		AbstractEdge<Integer> e9 = graph.insertEdge(vE, vF, 2);
		AbstractEdge<Integer> e10 = graph.insertEdge(vE, vG, 1);
		AbstractEdge<Integer> e11 = graph.insertEdge(vF, vG, 3);

		try {
			Prim<Integer, String> prim = new Prim<Integer, String>(graph);

			println("Minimum spanning tree using Prim: ");
			Map<Vertex<String>, Vertex<String>> tree = prim.findMSP();
			for (Vertex<String> vx : tree.keySet()) {
				print(vx.getElement() + " > " + tree.get(vx).getElement()
						+ ", ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		println("\n");
		try {
			Kruskal<Integer, String> kruskal = new Kruskal<Integer, String>(
					graph);

			println("Minimum spanning tree using Kruskal: ");
			Set<AbstractEdge<Integer>> edgs = kruskal.findMSP();
			for (AbstractEdge<Integer> edge : edgs) {
				Vertex<String>[] vtcs = (Vertex<String>[]) edge.getVertices();
				print(vtcs[0].getElement() + " > " + vtcs[1].getElement()
						+ ", ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
