import java.awt.Color;

import javax.swing.JScrollPane;

import graph.AbstractEdge;
import graph.AbstractGraphEdgeList;
import graph.DiGraphDrawerCanvas;
import graph.DiGraphEdgeList;
import graph.GraphDrawerCanvas;
import graph.GraphDrawingAlgorithm;
import graph.Vertex;
import acm.program.Program;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * PirateIsland
 * 
 * A simple program, demonstrating how to create a digraph.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class PirateIsland extends Program {

	public void init() {
		setSize(600, 450);

		DiGraphEdgeList<String, String> graph = new DiGraphEdgeList<String, String>();

		Vertex<String> v1 = graph.insertVertex(new Vertex<String>(
				"Pirate Island"));
		Vertex<String> v2 = graph.insertVertex(new Vertex<String>(
				"Island of Death"));
		Vertex<String> v3 = graph.insertVertex(new Vertex<String>(
				"Treasure Island"));

		AbstractEdge<String> e1 = graph.insertEdge(v1, v2, "");
		AbstractEdge<String> e2 = graph.insertEdge(v1, v3, "");
		AbstractEdge<String> e3 = graph.insertEdge(v3, v1, "");

		System.out.println(graph.size());

		drawRandomGraph(graph);
	}

	private void drawRandomGraph(AbstractGraphEdgeList<String, String> graph) {
		DiGraphDrawerCanvas<String, String> canvas = new DiGraphDrawerCanvas<String, String>(
				graph);
		canvas.setShapeNode(GraphDrawerCanvas.SHAPE_ROUNDRECT);
		canvas.setColorVertices(Color.BLUE);
		canvas.setColorVerticesFill(new Color(230, 230, 230));
		canvas.setNodeSeparationX(200);
		canvas.setNodeSeparationY(100);
		canvas.setFont("Courier new-bold-18");
		canvas.setTraversalType(GraphDrawingAlgorithm.TRAVERSAL_BFS);
		canvas.execute();
		add(new JScrollPane(canvas), CENTER);
	}

}
