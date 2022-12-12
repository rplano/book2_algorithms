import graph.AbstractEdge;
import graph.DiGraphEdgeList;
import graph.Dijkstra;
import graph.GraphParser;
import graph.TopologicalSort;
import graph.Vertex;

import java.util.Collection;
import java.util.Map;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * ProjectManagement
 * 
 * A console program showing how to use topological sort to find the critical
 * path for a project.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class ProjectManagement extends ConsoleProgram {

	public void init() {
		setSize(400, 300);
		setFont("Times New Roman-bold-20");
	}

	public void run() {
		DiGraphEdgeList<Integer, String> graph = new DiGraphEdgeList<Integer, String>();

		Vertex<String> v3 = graph.insertVertex(new Vertex<String>("T3"));
		Vertex<String> v8 = graph.insertVertex(new Vertex<String>("T8"));
		Vertex<String> v5 = graph.insertVertex(new Vertex<String>("T5"));
		Vertex<String> v1 = graph.insertVertex(new Vertex<String>("T1"));
		Vertex<String> v2 = graph.insertVertex(new Vertex<String>("T2"));
		Vertex<String> v6 = graph.insertVertex(new Vertex<String>("T6"));
		Vertex<String> v7 = graph.insertVertex(new Vertex<String>("T7"));
		Vertex<String> v4 = graph.insertVertex(new Vertex<String>("T4"));

		AbstractEdge<Integer> e1 = graph.insertEdge(v1, v2, 1);
		AbstractEdge<Integer> e2 = graph.insertEdge(v1, v4, 1);
		AbstractEdge<Integer> e3 = graph.insertEdge(v2, v7, 1);
		AbstractEdge<Integer> e4 = graph.insertEdge(v2, v3, 1);
		AbstractEdge<Integer> e5 = graph.insertEdge(v4, v5, 1);
		AbstractEdge<Integer> e6 = graph.insertEdge(v4, v6, 1);
		AbstractEdge<Integer> e7 = graph.insertEdge(v5, v8, 1);
		AbstractEdge<Integer> e8 = graph.insertEdge(v6, v7, 1);
		AbstractEdge<Integer> e9 = graph.insertEdge(v7, v8, 1);

		GraphParser gp = new GraphParser();
		System.out.println(gp.createStringFromGraph(graph, true));

		try {
			TopologicalSort<Integer, String> toso = new TopologicalSort<Integer, String>(
					graph);
			println("First sort: ");
			Collection<Vertex<String>> verts = toso.sort();
			for (Vertex<String> vertex : verts) {
				print(vertex.getElement() + " > ");
			}

			println("\nSecond sort: ");
			Collection<Vertex<String>> verts2 = toso.sort2();
			for (Vertex<String> vertex : verts2) {
				print(vertex.getElement() + " > ");
			}

			println("\nThird sort: ");
			Collection<Vertex<String>> verts3 = toso.sort3();
			for (Vertex<String> vertex : verts3) {
				print(vertex.getElement() + " > ");
			}

			// for critical path use Dijkstra:
			println("\nCritical path: ");
			Vertex<String> startVertex = verts3.iterator().next();
			Vertex<String> endVertex = null;
			for (Vertex<String> vertex : verts3) {
				endVertex = vertex;
			}

			// fastest route from B to A:
			Dijkstra<Integer, String> dijK = new Dijkstra<Integer, String>(
					graph);
			// we need to do this here, because Dijkstra uses the labels
			v1.setLabel("5");
			v2.setLabel("5");
			v3.setLabel("10");
			v4.setLabel("15");
			v5.setLabel("5");
			v6.setLabel("10");
			v7.setLabel("15");
			v8.setLabel("10");
			int totalTime = 0;
			Map<Vertex<String>, Vertex<String>> predcrs = dijK
					.getAllPredecessors(startVertex);
			Vertex<String> vTmp = endVertex;
			while (vTmp != startVertex) {
				print(vTmp.getElement() + " < ");
				totalTime += Integer.parseInt(vTmp.getLabel());
				vTmp = predcrs.get(vTmp);
			}
			println(vTmp.getElement());
			totalTime += Integer.parseInt(vTmp.getLabel());
			println("Minimum time for project: " + totalTime + " days.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
