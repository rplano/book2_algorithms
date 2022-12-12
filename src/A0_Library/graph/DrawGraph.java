package graph;
import java.awt.Color;
import java.io.File;

import javax.swing.JScrollPane;

import acm.program.Program;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * DrawGraph
 * 
 * This is a simple demo on how to use GraphParser and the GraphDrawerCanvas to
 * draw nice looking graph.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class DrawGraph extends Program {

	public void init() {
		setSize(400, 400);

		GraphEdgeList<String, String> graph = createGraph();

		drawRandomGraph(graph);
		// drawGraphUsingBFS(graph);
		// drawGraphUsingDFS(graph);
		// drawGraphUsingLinear(graph);
		// drawGraphUsingBFSStartingFromMostImportantVertex(graph);
		// drawGraphUsingDFSStartingFromMostImportantVertex(graph);
		//drawGraphUsingBFSRadialStartingFromMostImportantVertex(graph);
	}

	private GraphEdgeList<String, String> createGraph() {
		String edges = "T5-T8,T2-T3,T2-T7,T6-T7,T4-T6,T4-T5,T7-T8,T1-T2,T1-T4";
		//String edges = "joe-food,joe-dog,joe-tea,joe-cat,joe-table,table-plate,plate-food,food-mouse,food-dog,mouse-cat,table-cup,cup-tea,dog-cat,cup-spoon,plate-fork,dog-flea1,dog-flea2,flea1-flea2,plate-knife";
		// String  edges ="zero-one,one-two,two-three,three-four,four-five,five-six,six-seven,seven-zero";
		// String edges ="zero-one,zero-two,zero-three,zero-four,zero-five,zero-six,zero-seven,zero-eight,zero-nine,one-ten,two-twenty,three-thirty,four-fourty,five-fifty,six-sixty,seven-seventy,eight-eighty,nine-ninety,ten-twenty,twenty-thirty,thirty-fourty,fourty-fifty,fifty-sixty,sixty-seventy,seventy-eighty,eighty-ninety,ninety-ten,one-two,two-three,three-four,four-five,five-six,six-seven,seven-eight,eight-nine,nine-one";
		// String edges ="a1-a2,a2-a3,a3-a4,a4-a5,a5-a6,b1-b2,b2-b3,b3-b4,b4-b5,b5-b6,c1-c2,c2-c3,c3-c4,c4-c5,c5-c6,x-a1,x-b1,x-c1,x-a6,x-b6,x-c6";
		// String edges = "u-v,v-w,w-x,x-y,y-z";
		// String edges = "u-v,v-w,w-x,x-y,w-z,z-x";
		// String edges = "1-2,2-3,3-4,4-1";
		// String edges = "1-2,2-3,3-4,4-1, 5-6,6-7,7-8,8-5"; // problem, only
		// draws first part
		// String edges = "1-2,2-3,3-4,4-1, 4-5, 5-6,6-7,7-8,8-5";
		// String edges =
		// "a-b,b-c,c-d,d-a, a-o, e-f,f-g,g-h,h-e, e-o, i-j,j-k,k-l,l-i, i-o";
		 //String edges = "0-1,0-2,0-3,0-4, 2-21,3-31,3-32,4-41,4-42,4-43 "; // for radial test
		// String edges = "0-1,0-2,0-3,0-4, 1-11,1-12,1-13, 2-21,2-22,2-23, 3-31,3-32,3-33 ,4-41,4-42,4-43 "; // for radial test
		 //String edges = "battery-LED, LED-resistor, resistor-switch, switch-battery";
		 //String edges = "battery-wire1, wire1-LED1, LED1-resistor1, resistor1-switch, switch-battery, wire1-wire2, wire2-LED2, LED2-resistor2, resistor2-wire3, wire3-switch";
		
		 GraphEdgeList<String, String> graph = new GraphParser()
		// .parseGraph(new File("kruskal.txt")); // "flights.txt",
		// "shortest_path.txt", "kruskal.txt"
				.parseGraph(edges);
		return graph;
	}

	private void drawRandomGraph(GraphEdgeList<String, String> graph) {
		GraphDrawerCanvas<String, String> canvas = new GraphDrawerCanvas<String, String>(
				graph);
		canvas.setShapeNode(GraphDrawerCanvas.SHAPE_ROUNDRECT);
		canvas.setOrientation(GraphDrawerCanvas.VERTICAL);
		// canvas.setEdgeStyle(GraphDrawerCanvas.EDGE_STYLE_DIRECT);
		canvas.setEdgeStyle(GraphDrawerCanvas.EDGE_STYLE_AVOID_OVERLAP);
		canvas.setColorVertices(Color.BLUE);
		canvas.setColorVerticesFill(new Color(230, 230, 230));
		canvas.setNodeSeparationX(90);
		canvas.setNodeSeparationY(90);
		canvas.setGridOn(true);
		canvas.setFont("Courier new-bold-18");
		canvas.setTraversalType(GraphDrawingAlgorithm.TRAVERSAL_SPRING_FORCE);
		// canvas.positionVertices();
		// canvas.simulatedAnnealing();
		canvas.execute();
		add(new JScrollPane(canvas), CENTER);
	}

	private void drawGraphUsingLinear(GraphEdgeList<Integer, String> graph) {
		GraphDrawerCanvas<Integer, String> canvas = new GraphDrawerCanvas<Integer, String>(
				graph);
		canvas.setShapeNode(GraphDrawerCanvas.SHAPE_ROUNDRECT);
		canvas.setOrientation(GraphDrawerCanvas.VERTICAL);
		// canvas.setEdgeStyle(GraphDrawerCanvas.EDGE_STYLE_DIRECT);
		canvas.setEdgeStyle(GraphDrawerCanvas.EDGE_STYLE_AVOID_OVERLAP);
		canvas.setColorVertices(Color.BLUE);
		canvas.setColorVerticesFill(new Color(230, 230, 230));
		canvas.setNodeSeparationX(90);
		canvas.setNodeSeparationY(90);
		canvas.setGridOn(true);
		canvas.setFont("Courier new-bold-18");
		canvas.setTraversalType(GraphDrawingAlgorithm.TRAVERSAL_LINEAR);
		canvas.execute();
		add(new JScrollPane(canvas), CENTER);
	}

	private void drawGraphUsingBFS(GraphEdgeList<Integer, String> graph) {
		GraphDrawerCanvas<Integer, String> canvas = new GraphDrawerCanvas<Integer, String>(
				graph);
		canvas.setShapeNode(GraphDrawerCanvas.SHAPE_ROUNDRECT);
		canvas.setOrientation(GraphDrawerCanvas.VERTICAL);
		// canvas.setEdgeStyle(GraphDrawerCanvas.EDGE_STYLE_DIRECT);
		canvas.setEdgeStyle(GraphDrawerCanvas.EDGE_STYLE_AVOID_OVERLAP);
		canvas.setColorVertices(Color.BLUE);
		canvas.setColorVerticesFill(new Color(230, 230, 230));
		canvas.setNodeSeparationX(90);
		canvas.setNodeSeparationY(90);
		canvas.setGridOn(true);
		canvas.setFont("Courier new-bold-18");
		canvas.setTraversalType(GraphDrawingAlgorithm.TRAVERSAL_BFS);
		canvas.execute();
		add(new JScrollPane(canvas), CENTER);
	}

	private void drawGraphUsingBFSStartingFromMostImportantVertex(
			GraphEdgeList<Integer, String> graph) {
		GraphDrawerCanvas<Integer, String> canvas = new GraphDrawerCanvas<Integer, String>(
				graph);
		canvas.setShapeNode(GraphDrawerCanvas.SHAPE_ROUNDRECT);
		canvas.setOrientation(GraphDrawerCanvas.VERTICAL);
		// canvas.setEdgeStyle(GraphDrawerCanvas.EDGE_STYLE_DIRECT);
		canvas.setEdgeStyle(GraphDrawerCanvas.EDGE_STYLE_AVOID_OVERLAP);
		canvas.setColorVertices(Color.BLUE);
		canvas.setColorVerticesFill(new Color(230, 230, 230));
		canvas.setNodeSeparationX(90);
		canvas.setNodeSeparationY(90);
		canvas.setGridOn(true);
		canvas.setFont("Courier new-bold-18");
		canvas.setTraversalType(GraphDrawingAlgorithm.TRAVERSAL_BFS_MOST_IMPORTANT);
		canvas.execute();
		add(new JScrollPane(canvas), CENTER);
	}

	private void drawGraphUsingDFS(GraphEdgeList<Integer, String> graph) {
		GraphDrawerCanvas<Integer, String> canvas = new GraphDrawerCanvas<Integer, String>(
				graph);
		canvas.setShapeNode(GraphDrawerCanvas.SHAPE_ROUNDRECT);
		canvas.setOrientation(GraphDrawerCanvas.VERTICAL);
		// canvas.setEdgeStyle(GraphDrawerCanvas.EDGE_STYLE_DIRECT);
		canvas.setEdgeStyle(GraphDrawerCanvas.EDGE_STYLE_AVOID_OVERLAP);
		canvas.setColorVertices(Color.BLUE);
		canvas.setColorVerticesFill(new Color(230, 230, 230));
		canvas.setNodeSeparationX(90);
		canvas.setNodeSeparationY(90);
		canvas.setGridOn(true);
		canvas.setFont("Courier new-bold-18");
		canvas.setTraversalType(GraphDrawingAlgorithm.TRAVERSAL_DFS);
		canvas.execute();
		add(new JScrollPane(canvas), CENTER);
	}

	private void drawGraphUsingDFSStartingFromMostImportantVertex(
			GraphEdgeList<Integer, String> graph) {
		GraphDrawerCanvas<Integer, String> canvas = new GraphDrawerCanvas<Integer, String>(
				graph);
		canvas.setShapeNode(GraphDrawerCanvas.SHAPE_ROUNDRECT);
		canvas.setOrientation(GraphDrawerCanvas.VERTICAL);
		// canvas.setEdgeStyle(GraphDrawerCanvas.EDGE_STYLE_DIRECT);
		canvas.setEdgeStyle(GraphDrawerCanvas.EDGE_STYLE_AVOID_OVERLAP);
		canvas.setColorVertices(Color.BLUE);
		canvas.setColorVerticesFill(new Color(230, 230, 230));
		canvas.setNodeSeparationX(90);
		canvas.setNodeSeparationY(90);
		canvas.setGridOn(true);
		canvas.setFont("Courier new-bold-18");
		canvas.setTraversalType(GraphDrawingAlgorithm.TRAVERSAL_DFS_MOST_IMPORTANT);
		canvas.execute();
		add(new JScrollPane(canvas), CENTER);
	}

	private void drawGraphUsingBFSRadialStartingFromMostImportantVertex(
			GraphEdgeList<Integer, String> graph) {
		GraphDrawerCanvas<Integer, String> canvas = new GraphDrawerCanvas<Integer, String>(
				graph);
		canvas.setShapeNode(GraphDrawerCanvas.SHAPE_ROUNDRECT);
		canvas.setOrientation(GraphDrawerCanvas.VERTICAL);
		// canvas.setEdgeStyle(GraphDrawerCanvas.EDGE_STYLE_DIRECT);
		canvas.setEdgeStyle(GraphDrawerCanvas.EDGE_STYLE_AVOID_OVERLAP);
		canvas.setColorVertices(Color.BLUE);
		canvas.setColorVerticesFill(new Color(230, 230, 230));
		canvas.setNodeSeparationX(90);
		canvas.setNodeSeparationY(90);
		canvas.setGridOn(false);
		canvas.setFont("Courier new-bold-18");
		// canvas.setTraversalType(GraphDrawerCanvas2.TRAVERSAL_BFS_RADIAL);
		canvas.setTraversalType(GraphDrawingAlgorithm.TRAVERSAL_BFS_RADIAL);
		canvas.execute();
		add(new JScrollPane(canvas), CENTER);
	}

}
