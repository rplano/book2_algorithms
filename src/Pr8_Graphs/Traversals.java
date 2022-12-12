import graph.GraphDrawerCanvas;
import graph.GraphDrawingAlgorithm;
import graph.GraphEdgeList;
import graph.GraphParser;
import graph.Vertex;
import graph.VisitorInterface;

import java.awt.Color;

import javax.swing.JScrollPane;

import acm.program.Program;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Traversals
 * 
 * Show that dfs is like pre-order traversal and bfs is like level-order
 * traversal. We start turning a tree of which we know the traversal behavior
 * and turn it into a graph.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Traversals extends Program {

	public void init() {
		setSize(400, 400);

		// doDFSTraversal();
		doBFSTraversal();
	}

	private void doBFSTraversal() {

		String str = "A{B{C,D},E{F,G,H},I}";
		GraphEdgeList<String, String> graph = new GraphParser().parseTree(str);

		graph.bfs(new VisitorInterface<Vertex<String>>() {
			int counter = 1;

			public void visit(Vertex<?> p) {
				((Vertex<String>) p).setElement("" + counter++);
				System.out.println(p);
			}
		});

		GraphDrawerCanvas<String, String> canvas = new GraphDrawerCanvas<String, String>(
				graph);
		canvas.setShapeNode(GraphDrawerCanvas.SHAPE_ROUNDRECT);
		canvas.setOrientation(GraphDrawerCanvas.VERTICAL);
		canvas.setEdgeStyle(GraphDrawerCanvas.EDGE_STYLE_DIRECT);
		canvas.setColorVertices(Color.BLUE);
		canvas.setColorVerticesFill(new Color(230, 230, 230));
		canvas.setNodeSeparationX(80);
		canvas.setNodeSeparationY(80);
		canvas.setGridOn(true);
		canvas.setFont("Courier new-bold-18");
		canvas.setTraversalType(GraphDrawingAlgorithm.TRAVERSAL_DFS);
		canvas.execute();
		add(new JScrollPane(canvas), CENTER);
	}

	private void doDFSTraversal() {

		String str = "A{B{C,D},E{F,G,H},I}";
		GraphEdgeList<String, String> graph = new GraphParser().parseTree(str);

		graph.dfs(new VisitorInterface<Vertex<String>>() {
			int counter = 1;

			public void visit(Vertex<?> p) {
				((Vertex<String>) p).setElement("" + counter++);
				System.out.println(p);
			}
		});

		GraphDrawerCanvas<String, String> canvas = new GraphDrawerCanvas<String, String>(
				graph);
		canvas.setShapeNode(GraphDrawerCanvas.SHAPE_ROUNDRECT);
		canvas.setOrientation(GraphDrawerCanvas.VERTICAL);
		canvas.setEdgeStyle(GraphDrawerCanvas.EDGE_STYLE_DIRECT);
		canvas.setColorVertices(Color.BLUE);
		canvas.setColorVerticesFill(new Color(230, 230, 230));
		canvas.setNodeSeparationX(80);
		canvas.setNodeSeparationY(80);
		canvas.setGridOn(true);
		canvas.setFont("Courier new-bold-18");
		canvas.setTraversalType(GraphDrawingAlgorithm.TRAVERSAL_DFS);
		canvas.execute();
		add(new JScrollPane(canvas), CENTER);
	}

}
