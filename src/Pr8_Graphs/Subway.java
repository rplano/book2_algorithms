import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.JScrollPane;

import graph.GraphDrawerCanvas;
import graph.GraphDrawingAlgorithm;
import graph.GraphEdgeList;
import graph.Vertex;
import acm.program.Program;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Subway
 * 
 * A graphics program visualizing a subway network.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Subway extends Program {

	private final String FILE_NAME = "subway_nbg.txt";

	private GraphEdgeList<String, String> graph;
	private Map<String, Vertex<String>> vertices;

	public void init() {
		setSize(800, 800);

		loadSubwayGraphFromFile(new File(FILE_NAME));

		drawRandomGraph(graph);
	}

	private void loadSubwayGraphFromFile(File file) {
		graph = new GraphEdgeList<String, String>();
		vertices = new HashMap<String, Vertex<String>>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				if (!line.startsWith("#")) { // ignore comments
					analyze(line);
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void analyze(String line) {
		StringTokenizer st = new StringTokenizer(line, ",:", false);
		String subwayLine = st.nextToken();

		String from = st.nextToken().trim();
		addVertexToGraph(from);

		while (st.hasMoreTokens()) {
			String to = st.nextToken().trim();
			addVertexToGraph(to);

			graph.insertEdge(vertices.get(from), vertices.get(to), subwayLine);

			from = to;
		}
	}

	private void addVertexToGraph(String from) {
		if (!vertices.containsKey(from)) {
			Vertex<String> vtx = graph.insertVertex(new Vertex<String>(from));
			vertices.put(from, vtx);
		}
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
		canvas.setNodeSeparationX(200);
		canvas.setNodeSeparationY(60);
		canvas.setGridOn(false);
		canvas.setFont("Courier new-bold-18");
		canvas.setTraversalType(GraphDrawingAlgorithm.TRAVERSAL_SPRING_FORCE);
		canvas.execute();
		add(new JScrollPane(canvas), CENTER);
	}
}
