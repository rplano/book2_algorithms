import graph.AbstractEdge;
import graph.GraphDrawingAlgorithm;
import graph.GraphDrawingAlgorithmBFSRadialMostImportant;
import graph.GraphEdgeList;
import graph.GraphParser;
import graph.Vertex;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GRoundRect;
import acm.program.GraphicsProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * SynonymsBrowser
 * 
 * A graphics program, demonstrating graphical navigation through a graph.
 * 
 * The data in "synonyms.txt" is based on Princeton Universities WordNet:
 * Princeton University "About WordNet." WordNet. Princeton University. 2010.
 * <http://wordnet.princeton.edu>
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class SynonymsBrowser extends GraphicsProgram {

	private static final int EDGE_LENGTH = 100;
	private static final int OFFSET_X = 100;
	private static final int OFFSET_Y = 100;
	private static final String FILE_NAME = "synonyms.txt";

	private GraphEdgeList<String, String> graph;

	public void init() {
		setSize(400, 400);
		graph = new GraphParser().parseGraph(new File(FILE_NAME));
		drawGraph(getSubGraph("grass"));
	}

	private void reDrawGraph(String word) {
		String[] words = word.split(" ");
		for (String wrd : words) {
			if (graph.findVertex(wrd) != null) {
				drawGraph(getSubGraph(wrd));
				break;
			}
		}
	}

	private void drawGraph(GraphEdgeList<Integer, String> subGraph) {
		GraphDrawingAlgorithm<Integer, String> algorithm = new GraphDrawingAlgorithmBFSRadialMostImportant<Integer, String>(
				subGraph, EDGE_LENGTH);
		algorithm.execute();

		removeAll();

		// drawNodes:
		Collection<Vertex<String>> vertices = subGraph.vertices();
		for (Vertex<String> vertex : vertices) {
			HashMap<Vertex<String>, Point> positions = algorithm.getPositions();
			Point pt = positions.get(vertex);
			String tmp = vertex.getElement().toString();
			drawElement(tmp, pt.x, pt.y);
		}

		// drawEdges:
		Collection<AbstractEdge<Integer>> edges = subGraph.edges();
		for (AbstractEdge<Integer> edge : edges) {
			Vertex<String>[] vtcs = (Vertex<String>[]) edge.getVertices();
			HashMap<Vertex<String>, Point> positions = algorithm.getPositions();
			drawEdge(positions.get(vtcs[0]), positions.get(vtcs[1]));
		}
	}

	private void drawElement(String element, int x, int y) {
		GLabel nodeLbl = new GLabel(element.trim());
		nodeLbl.setFont("Courier new-bold-18");
		nodeLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// System.out.println(((GLabel)e.getSource()).getLabel());
				reDrawGraph(((GLabel) e.getSource()).getLabel());
			}
		});
		double lblW = nodeLbl.getWidth();
		double lblH = nodeLbl.getHeight();
		double lblA = nodeLbl.getAscent();

		int dx = ((int) lblW + 2 * 7);
		int dy = ((int) lblH + 2 * 2);

		GRoundRect node = new GRoundRect(dx, dy);
		node.setFilled(true);
		node.setFillColor(new Color(230, 230, 230));
		// node.setColor(colorEdge);

		add(node, x - dx / 2 - 1 + OFFSET_X, y - dy / 2 + OFFSET_Y);
		add(nodeLbl, x - lblW / 2 + OFFSET_X, y + lblA / 2 - 1 + OFFSET_Y);
	}

	private void drawEdge(Point p1, Point p2) {
		GObject edge = new GLine(p1.x + OFFSET_X, p1.y + OFFSET_Y, p2.x
				+ OFFSET_X, p2.y + OFFSET_Y);
		add(edge);
		edge.sendToBack();
	}

	private GraphEdgeList<Integer, String> getSubGraph(String startWord) {
		GraphEdgeList<Integer, String> subGraph = new GraphEdgeList<Integer, String>();

		Vertex<String> startVertex = graph.findVertex(startWord);
		subGraph.insertVertex(startVertex);
		List<Vertex<String>> wordsToGo = findWordsToGoTo(startVertex);
		for (Vertex<String> vertex : wordsToGo) {
			if (!subGraph.containsVertex(vertex)) {
				subGraph.insertVertex(vertex);
			}
			subGraph.insertEdge(startVertex, vertex, null);
		}

		return subGraph;
	}

	private List<Vertex<String>> findWordsToGoTo(Vertex<String> currentVertex) {
		List<Vertex<String>> wordsToGo = new ArrayList<Vertex<String>>();
		Collection<AbstractEdge<String>> outEdgs = graph
				.incidentEdges(currentVertex);
		for (AbstractEdge<String> edge : outEdgs) {
			wordsToGo.add(graph.opposite(currentVertex, edge));
		}
		return wordsToGo;
	}
}
