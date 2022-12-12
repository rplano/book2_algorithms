package graph;

import java.awt.Point;

import acm.graphics.GLine;
import acm.graphics.GObject;

public class DiGraphDrawerCanvas<E extends Comparable, V> extends
		GraphDrawerCanvas<E, V> {

	public DiGraphDrawerCanvas(AbstractGraphInterface<E, V> graph) {
		super(graph);
	}

	// @Override
	// protected void drawEdges() {
	// Collection<AbstractEdge<E>> edges = graph.edges();
	// for (AbstractEdge<E> edge : edges) {
	// Vertex<V>[] vtcs = (Vertex<V>[]) edge.getVertices();
	// HashMap<Vertex<V>, Point> positions = algorithm.getPositions();
	// drawEdge(positions.get(vtcs[0]), positions.get(vtcs[1]),
	// edge.getElement());
	// }
	// }

	@Override
	protected void drawEdgeDown(int x0, int y0, int x1, int y1, E element) {
		// GObject edge = null;
		if (edgeStyle == EDGE_STYLE_DIRECT) {
			Point tip = findBestTipPosition(x0, y0, x1, y1);
			GObject edge = new GArrow(x0, y0, tip.x, tip.y);
			edge.setColor(colorEdges);
			add(edge);
			 edge.sendToBack();
		} else if (edgeStyle == EDGE_STYLE_AVOID_OVERLAP) {
			if (noCollision(x0, y0, x1, y1)) {
				GObject edge = new GLine(x0, y0, x1, y1);
				edge.setColor(colorEdges);
				add(edge);
				edge.sendToBack();
				if (showEdgeLabels) {
					drawEdgeLabel((x0 + x1) / 2, (y0 + y1) / 2, element);
				}
			} else {
				// System.out.println("Collision!!!");
				int width = (x1 - x0) / 2;
				int height = (y1 - y0) / 2;
				GObject edge = new GQuadLine(x0, y0, x0 + width + height, y0
						+ width + height, x1, y1);
				// edge.setColor(Color.YELLOW);
				edge.setColor(colorEdges);
				add(edge, x0, y0);
				edge.sendToBack();
				if (showEdgeLabels) {
					// if (y1 <= y0) {
					drawEdgeLabel(x0 - (width + height) / 2, y0 + width
							+ height, element);
					// } else {
					// drawEdgeLabel(x0 + (width + height) / 2, y0 + width
					// + height, element);
					// }
				}
			}
		}
	}

	private Point findBestTipPosition(int x0, int y0, int x1, int y1) {
		int dx = x1 - x0;
		int dy = y1 - y0;
		int radius = (int) Math.sqrt(dx * dx + dy * dy);
		double angle = Math.atan2(dy, dx);
		for (int r = radius; r >= 0; r--) {
			int x = x0 + (int) Math.round(r * Math.cos(angle));
			int y = y0 + (int) Math.round(r * Math.sin(angle));
			System.out.println(x + "," + y);
			GObject obj = getElementAt(x, y);
			if (obj != null) {
				if (obj instanceof GLine) {
					r -= 5;	// another dirty trick, make arrow a little more short
					x = x0 + (int) Math.round(r * Math.cos(angle));
					y = y0 + (int) Math.round(r * Math.sin(angle));
					return new Point(x, y);					
				}
			} else {
				r -= 5;	// another dirty trick, make arrow a little more short
				x = x0 + (int) Math.round(r * Math.cos(angle));
				y = y0 + (int) Math.round(r * Math.sin(angle));
				return new Point(x, y);
			}
		}
		return new Point(x1, y1);
	}

}
