import java.awt.Graphics;
import java.awt.Point;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import graph.AbstractEdge;
import graph.GraphDrawingAlgorithm;
import graph.GraphDrawingAlgorithmSpringForce;
import graph.GraphEdgeList;
import graph.Vertex;
import acm.graphics.GImage;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GRectangle;
import acm.program.GraphicsProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * ElectricCircuit
 * 
 * Takes a string like "EC_Battery-EC_LED-EC_Resistor-EC_Switch" and draws an
 * electric circuit. It does not worry about directions, for this a di-graph
 * needs to be used.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class ElectricCircuit extends GraphicsProgram {

	private static final int EDGE_SIZE = 100;

	public void init() {
		setSize(400, 350);
		GraphEdgeList<GImageComparable, String> graph = createGraph("EC_Battery-EC_LED-EC_Resistor-EC_LED-EC_Resistor-EC_Switch");
		drawGraph(graph);
	}

	private void drawGraph(GraphEdgeList<GImageComparable, String> graph) {
		GraphDrawingAlgorithm<GImageComparable, String> algorithm = new GraphDrawingAlgorithmSpringForce<GImageComparable, String>(
				graph);
		algorithm.execute();

		// drawEdges:
		Collection<AbstractEdge<GImageComparable>> edges = graph.edges();
		for (AbstractEdge<GImageComparable> edge : edges) {
			Vertex<String>[] vtcs = (Vertex<String>[]) edge.getVertices();
			HashMap<Vertex<String>, Point> positions = algorithm.getPositions();
			drawEdge(positions.get(vtcs[0]), positions.get(vtcs[1]),
					edge.getElement());
		}

	}

	private void drawEdge(Point p1, Point p2, GImageComparable image) {
		int x0 = EDGE_SIZE * p1.x;
		int y0 = EDGE_SIZE * p1.y;
		int x1 = EDGE_SIZE * p2.x;
		int y1 = EDGE_SIZE * p2.y;

		GObject edge = new GLine(x0, y0, x1, y1);
		add(edge);
		edge.sendToBack();

		double imgSize = image.getWidth() / 2;
		if (x0 == x1) { // vertical
			image.setOrientation(GImageComparable.ORIENTATION_VERTICAL);
			image.setLocation(x0 - imgSize, (y0 + y1) / 2 - imgSize);
			add(image);
		} else { // horizontal
			image.setOrientation(GImageComparable.ORIENTATION_HORIZONTAL);
			image.setLocation((x0 + x1) / 2 - imgSize, y0 - imgSize);
			add(image);
		}
	}

	private GraphEdgeList<GImageComparable, String> createGraph(String edges) {
		GraphEdgeList<GImageComparable, String> graph = new GraphEdgeList<GImageComparable, String>();

		String edge = "";
		int vertexNr = 0;
		Map<String, Vertex<String>> vertices = new HashMap<String, Vertex<String>>();
		StringTokenizer st = new StringTokenizer(edges, "-");
		String vrtxName = "" + vertexNr;
		Vertex<String> first = graph.insertVertex(new Vertex<String>(vrtxName));
		vertices.put(vrtxName, first);
		Vertex<String> from = first;
		while (st.hasMoreTokens()) {
			edge = st.nextToken();
			vertexNr++;
			vrtxName = "" + vertexNr;
			if (st.hasMoreTokens()) {
				Vertex<String> to = null;
				if (!vertices.containsKey(vrtxName)) {
					to = graph.insertVertex(new Vertex<String>(vrtxName));
					vertices.put(vrtxName, to);
				} else {
					to = vertices.get(vrtxName);
				}
				graph.insertEdge(from, to, new GImageComparable(edge));
				from = to;
			}
		}
		// we need to close circuit
		graph.insertEdge(from, first, new GImageComparable(edge));

		return graph;
	}

	private class GImageComparable extends GObject implements Comparable {
		public static final int ORIENTATION_HORIZONTAL = 0;
		public static final int ORIENTATION_VERTICAL = 1;
		private GImage image;
		private GImage imageHorizontal;
		private GImage imageVertical;

		public GImageComparable(String imageName) {
			super();
			imageHorizontal = new GImage(imageName + "H.png");
			imageHorizontal.scale(0.5);
			imageVertical = new GImage(imageName + "V.png");
			imageVertical.scale(0.5);
			image = imageHorizontal;
		}

		public void setOrientation(int orientation) {
			if (orientation == ORIENTATION_HORIZONTAL) {
				image = imageHorizontal;
			} else {
				image = imageVertical;
			}
		}

		@Override
		public int compareTo(Object o) {
			return 0;
		}

		@Override
		public GRectangle getBounds() {
			return image.getBounds();
		}

		@Override
		public void setLocation(double x, double y) {
			image.setLocation(x, y);
		}

		@Override
		public void paint(Graphics g) {
			image.paint(g);
		}
	}
}
