package graph;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * GraphDrawingAlgorithmBFSRadialMostImportant
 * 
 * Traverses a graph using a bfs traversal, but starting from the most important
 * vertex, radially outwards.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class GraphDrawingAlgorithmBFSRadialMostImportant<E extends Comparable, V>
		extends GraphDrawingAlgorithm<E, V> {

	protected GraphDrawerCanvas<E, V> canvas;
	protected double angle = 0;
	protected double deltaAngle = 0;
	protected double radialDistance = 0;

	public GraphDrawingAlgorithmBFSRadialMostImportant(
			AbstractGraphInterface<E, V> graph, int nodeSeparationX) {
		super(graph);
		// another dirty trick
		this.canvas = new GraphDrawerCanvas<E, V>(null);
		this.canvas.setNodeSeparationX(nodeSeparationX);
	}
	
	public GraphDrawingAlgorithmBFSRadialMostImportant(
			AbstractGraphInterface<E, V> graph, GraphDrawerCanvas<E, V> canvas) {
		super(graph);
		this.canvas = canvas;
	}

	@Override
	public void execute() {
		positions = new HashMap<Vertex<V>, Point>();
		Vertex<V> mostImportantVertex = (Vertex<V>) findMostImportantVertex(graph);
		bfsTraversalRadial(graph, mostImportantVertex);
		// dirty trick:
		canvas.nodeSeparationX = 1;
		canvas.nodeSeparationY = 1;
		
		recalculateMinAndMax(positions);
	}

	private void bfsTraversalRadial(AbstractGraphInterface<E, V> graph,
			Vertex<V> startVertex) {
		for (Vertex<V> vertex : graph.vertices()) {
			vertex.setLabel("UNEXPLORED");
		}
		for (AbstractEdge<E> edge : graph.edges()) {
			edge.setLabel("UNEXPLORED");
		}
		if (startVertex.getLabel().equals("UNEXPLORED")) {
			bfsIterateRadial(startVertex);
		}
	}

	private void bfsIterateRadial(Vertex<V> startVertex) {
		List<Vertex<V>>[] L = new List[40]; // better to use List of List

		int i = 0;
		L[i] = new ArrayList<Vertex<V>>();
		L[i].add(startVertex);

		startVertex.setLabel("VISITED");

		// do the bfs
		while (!L[i].isEmpty()) {
			L[i + 1] = new ArrayList<Vertex<V>>();

			for (Vertex<V> v : L[i]) {
				Collection<AbstractEdge<E>> incidentEdges = graph
						.incidentEdges(v);
				// deltaAngle = 360.0 / incidentEdges.size();
				for (AbstractEdge<E> e : incidentEdges) {
					if (e.getLabel().equals("UNEXPLORED")) {

						Vertex<V> w = graph.opposite(v, e);
						if (w.getLabel().equals("UNEXPLORED")) {
							e.setLabel("DISCOVERY");

							w.setLabel("VISITED");
							// visitRadial(w);
							// angle += deltaAngle;

							L[i + 1].add(w);

						} else {
							e.setLabel("CROSS");
						}
					}
				}
			}
			i++;
			// radialDistance += canvas.nodeSeparationX;
			// angle = 0;
		}

		// do the drawing
		radialDistance = 0;
		for (int lvl = 0; lvl < L.length; lvl++) {
			List<Vertex<V>> levels = L[lvl];
			if (levels != null) {
				int nrOfNodesAtLevel = levels.size();
				deltaAngle = 360.0 / nrOfNodesAtLevel;
				for (Vertex<V> vertx : levels) {
					if (lvl == 0) {
						visitRadial(vertx);
					} else {
						visitRadial(vertx);
						angle += deltaAngle;
					}
				}
				radialDistance += canvas.nodeSeparationX;
			}
		}
	}

	private void visitRadial(Vertex<V> p) {
		int x = (int) (radialDistance * Math.cos(Math.toRadians(angle)) + 400);
		int y = (int) (radialDistance * Math.sin(Math.toRadians(angle)) + 400);

		positions.put(p, new Point(x, y));

		if (x > horPosMax) {
			horPosMax = x;
		}
		if (y > vertPosMax) {
			vertPosMax = y;
		}
	}
}
