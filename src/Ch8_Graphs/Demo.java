import java.awt.Color;

import javax.swing.JScrollPane;

import graph.GraphDrawerCanvas;
import graph.GraphDrawingAlgorithm;
import graph.GraphEdgeList;
import graph.GraphParser;
import acm.program.Program;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Demo
 * 
 * A graphics program, demonstrating how to create a simple graph and display it
 * graphically.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Demo extends Program {

	public void init() {
		setSize(300, 450);
		String edges = "U-V-a,V-W-b,W-X-e,X-U-f,V-X-g,W-Z-c,Z-Z-h,Z-W-d";
		GraphEdgeList<String, String> graph = new GraphParser()
				.parseGraph(edges);
		System.out.println(graph.size());
		drawRandomGraph(graph);
	}

	private void drawRandomGraph(GraphEdgeList<String, String> graph) {
		GraphDrawerCanvas<String, String> canvas = new GraphDrawerCanvas<String, String>(
				graph);
		canvas.setShapeNode(GraphDrawerCanvas.SHAPE_ROUNDRECT);
		canvas.setOrientation(GraphDrawerCanvas.VERTICAL);
		canvas.setEdgeStyle(GraphDrawerCanvas.EDGE_STYLE_AVOID_OVERLAP);
		canvas.setShowEdgeLabels(GraphDrawerCanvas.EDGE_LABELS_SHOW);
		canvas.setColorVertices(Color.BLUE);
		canvas.setColorVerticesFill(new Color(230, 230, 230));
		canvas.setNodeSeparationX(90);
		canvas.setNodeSeparationY(90);
		canvas.setGridOn(false);
		canvas.setFont("Courier new-bold-18");
		canvas.setTraversalType(GraphDrawingAlgorithm.TRAVERSAL_SPRING_FORCE);
		canvas.execute();
		add(new JScrollPane(canvas), CENTER);
	}
}
