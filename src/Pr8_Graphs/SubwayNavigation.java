import graph.DiGraphEdgeList;
import graph.DiGraphParser;
import graph.Dijkstra;
import graph.GraphEdgeList;
import graph.Vertex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * SubwayNavigation
 * 
 * A console program getting you from A to B in a subway network using the
 * Dijkstra algorithm.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class SubwayNavigation extends ConsoleProgram {

	private final String FILE_NAME = "subway_nbg.txt";

	private GraphEdgeList<Integer, String> graph;
	private Map<String, Vertex<String>> vertices;

	public void init() {
		setSize(400, 300);
		setFont("Times New Roman-bold-20");

		loadSubwayGraphFromFile(new File(FILE_NAME));
	}

	public void run() {
		println("Welcome to SubwayNavigation.");
		Vertex<String> sourceVertex = getVertex("Leaving from: ");
		Vertex<String> destinationVertex = getVertex("Going to: ");

		try {
			Dijkstra<Integer, String> dijk = new Dijkstra<Integer, String>(
					graph);
			println("Shortest distance: "
					+ dijk.shortestPath(sourceVertex, destinationVertex));

			// fastest route from B to A:
			println("Fastest route from " + sourceVertex.getElement() + " to "
					+ destinationVertex.getElement() + ": ");
			Map<Vertex<String>, Vertex<String>> predcrs = dijk
					.getAllPredecessors(sourceVertex);
			Vertex<String> vTmp = destinationVertex;
			while (vTmp != sourceVertex) {
				print(vTmp.getElement() + " -> ");
				vTmp = predcrs.get(vTmp);
			}
			println(vTmp.getElement());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Vertex<String> getVertex(String msg) {
		while (true) {
			String source = readLine(msg);
			Vertex<String> sourceVertex = graph.findVertex(source);
			if (sourceVertex != null) {
				return sourceVertex;
			}
		}
	}

	private void loadSubwayGraphFromFile(File file) {
		graph = new GraphEdgeList<Integer, String>();
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

			// all subway station are separated by 1 mile (or 1 minute)
			graph.insertEdge(vertices.get(from), vertices.get(to), 1);

			from = to;
		}
	}

	private void addVertexToGraph(String from) {
		if (!vertices.containsKey(from)) {
			Vertex<String> vtx = graph.insertVertex(new Vertex<String>(from));
			vertices.put(from, vtx);
		}
	}
}
