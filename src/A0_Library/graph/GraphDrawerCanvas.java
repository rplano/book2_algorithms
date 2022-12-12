package graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import acm.graphics.GCanvas;
import acm.graphics.GFillable;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.graphics.GRoundRect;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * GraphDrawerCanvas
 * 
 * Use GRects or GOvals and bfs or dfs to draw a nice looking graph. This is
 * inspired by the Stock example from the first book.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class GraphDrawerCanvas<E extends Comparable, V> extends GCanvas
		implements ComponentListener {

	private static final int NODE_SEPARATION_INITIAL = 40;
	public static final boolean HORIZONTAL = true; // means levels go left
	public static final boolean VERTICAL = false; // means levels go down
	public static final int SHAPE_RECT = 0;
	public static final int SHAPE_ROUNDRECT = 1;
	public static final int SHAPE_OVAL = 2;
	public static final boolean GRID_ON = true;
	public static final boolean GRID_OFF = false;
	public static final int EDGE_STYLE_DIRECT = 0;
	public static final int EDGE_STYLE_AVOID_OVERLAP = 1;
	// public static final int EDGE_STYLE_SQUARE = 1;
	// public static final int EDGE_STYLE_SQUARE2 = 2;
	// public static final int EDGE_STYLE_QUAD = 3;
	public static final boolean NODES_SHOW = true;
	public static final boolean NODES_HIDE = false;
	public static final int ALIGN_LEFT = 0;
	public static final boolean EDGE_LABELS_SHOW = true;
	public static final boolean EDGE_LABELS_HIDE = false;
	public static final int ALIGN_CENTER = 1;
	public static final int ALIGN_RIGHT = 2;

	private boolean isGridOn = GRID_OFF;
	protected boolean orientation = VERTICAL;
	protected int alignment = ALIGN_CENTER;
	protected boolean showVertices = NODES_SHOW;
	protected boolean showEdgeLabels = EDGE_LABELS_HIDE;
	protected int nodeSeparationX = NODE_SEPARATION_INITIAL;
	protected int nodeSeparationY = NODE_SEPARATION_INITIAL;
	protected int edgeStyle = EDGE_STYLE_DIRECT;
	private int shapeNode = SHAPE_ROUNDRECT;
	private int marginNodeX = 7;
	private int marginNodeY = 2;
	protected Color colorVertices = Color.BLACK;
	protected Color colorVerticesFill = Color.WHITE;
	protected Color colorEdges = Color.BLACK;
	private Color colorGrid = Color.LIGHT_GRAY;
	private String font = "Courier new-bold-18"; // "Times New Roman-bold-18",
													// "Arial-bold-18"

	protected AbstractGraphInterface<E, V> graph;
	protected GraphDrawingAlgorithm<E, V> algorithm;
	private boolean executeWasCalled = false;

	public GraphDrawerCanvas(AbstractGraphInterface<E, V> graph) {
		super();
		this.graph = graph;
		addComponentListener(this);
	}

	public boolean isGridOn() {
		return isGridOn;
	}

	public void setGridOn(boolean isGridOn) {
		this.isGridOn = isGridOn;
	}

	public boolean isOrientation() {
		return orientation;
	}

	public void setOrientation(boolean orientation) {
		this.orientation = orientation;
	}

	public int getAlignment() {
		return alignment;
	}

	public void setAlignment(int alignment) {
		this.alignment = alignment;
	}

	public boolean isShowVertices() {
		return showVertices;
	}

	public void setShowVertices(boolean showInternalNodes) {
		this.showVertices = showInternalNodes;
	}

	public boolean isShowEdgeLabels() {
		return showEdgeLabels;
	}

	public void setShowEdgeLabels(boolean showEdgeLabels) {
		this.showEdgeLabels = showEdgeLabels;
	}

	public int getNodeSeparationX() {
		return nodeSeparationX;
	}

	public void setNodeSeparationX(int nodeSeparationX) {
		this.nodeSeparationX = nodeSeparationX;
	}

	public int getNodeSeparationY() {
		return nodeSeparationY;
	}

	public void setNodeSeparationY(int nodeSeparationY) {
		this.nodeSeparationY = nodeSeparationY;
	}

	public int getEdgeStyle() {
		return edgeStyle;
	}

	public void setEdgeStyle(int edgeStyle) {
		this.edgeStyle = edgeStyle;
	}

	public int getShapeNode() {
		return shapeNode;
	}

	public void setShapeNode(int shapeNode) {
		this.shapeNode = shapeNode;
	}

	public int getMarginNodeX() {
		return marginNodeX;
	}

	public void setMarginNodeX(int marginNodeX) {
		this.marginNodeX = marginNodeX;
	}

	public int getMarginNodeY() {
		return marginNodeY;
	}

	public void setMarginNodeY(int marginNodeY) {
		this.marginNodeY = marginNodeY;
	}

	public Color getColorVertices() {
		return colorVertices;
	}

	public void setColorVertices(Color colorVertices) {
		this.colorVertices = colorVertices;
	}

	public Color getColorVerticesFill() {
		return colorVerticesFill;
	}

	public void setColorVerticesFill(Color colorVerticesFill) {
		this.colorVerticesFill = colorVerticesFill;
	}

	public Color getColorEdges() {
		return colorEdges;
	}

	public void setColorEdges(Color colorEdges) {
		this.colorEdges = colorEdges;
	}

	public Color getColorGrid() {
		return colorGrid;
	}

	public void setColorGrid(Color colorGrid) {
		this.colorGrid = colorGrid;
	}

	public void setFont(String font) {
		this.font = font;
	}

	@Override
	public Dimension preferredSize() {
		int width = (algorithm.getHorPosMax() + 1) * nodeSeparationX;
		int height = (algorithm.getVertPosMax() + 1) * nodeSeparationY;
		return new Dimension(width, height);
	}

	public void componentHidden(ComponentEvent arg0) {
	}

	public void componentMoved(ComponentEvent arg0) {
	}

	public void componentResized(ComponentEvent arg0) {
		update();
	}

	public void componentShown(ComponentEvent arg0) {
	}

	private void update() {
		if (executeWasCalled) {
			removeAll();

			drawNodes();
			drawEdges();
			if (isGridOn) {
				drawGrid();
			}
			
		} else {
			System.out.println("You must call the execute() method before update() can be called!");			
		}
	}

	public void setTraversalType(int traversalType) {
		switch (traversalType) {
		case GraphDrawingAlgorithm.TRAVERSAL_SPRING_FORCE:
			this.algorithm = new GraphDrawingAlgorithmSpringForce<E, V>(graph);
			break;
		case GraphDrawingAlgorithm.TRAVERSAL_BFS:
			this.algorithm = new GraphDrawingAlgorithmBFS<E, V>(graph);
			break;
		case GraphDrawingAlgorithm.TRAVERSAL_DFS:
			this.algorithm = new GraphDrawingAlgorithmDFS<E, V>(graph);
			break;
		case GraphDrawingAlgorithm.TRAVERSAL_LINEAR:
			this.algorithm = new GraphDrawingAlgorithmLinear<E, V>(graph);
			break;
		case GraphDrawingAlgorithm.TRAVERSAL_BFS_MOST_IMPORTANT:
			this.algorithm = new GraphDrawingAlgorithmBFSMostImportant<E, V>(
					graph);
			break;
		case GraphDrawingAlgorithm.TRAVERSAL_DFS_MOST_IMPORTANT:
			this.algorithm = new GraphDrawingAlgorithmDFSMostImportant<E, V>(
					graph);
			break;
		case GraphDrawingAlgorithm.TRAVERSAL_BFS_RADIAL:
			this.algorithm = new GraphDrawingAlgorithmBFSRadialMostImportant<E, V>(
					graph, this);
			break;
		default:
			break;
		}
	}

	public void execute() {
		algorithm.execute();
		executeWasCalled = true;
	}

	/**
	 * Draw the nodes (vertices).
	 */
	protected void drawNodes() {
		for (Vertex<V> vertex : ((AbstractGraphInterface<E, V>) graph)
				.vertices()) {
			HashMap<Vertex<V>, Point> positions = algorithm.getPositions();
			Point pt = positions.get(vertex);
			String tmp = vertex.getElement().toString();
			if (showVertices) {
				drawElement(tmp, pt.x, pt.y, colorVertices, colorVerticesFill);
			}
		}
	}

	protected void drawEdges() {
		Collection<AbstractEdge<E>> edges = graph.edges();
		for (AbstractEdge<E> edge : edges) {
			Vertex<V>[] vtcs = (Vertex<V>[]) edge.getVertices();
			HashMap<Vertex<V>, Point> positions = algorithm.getPositions();
			drawEdge(positions.get(vtcs[0]), positions.get(vtcs[1]),
					edge.getElement());
		}
	}

	private void drawGrid() {
		// int vertPos = graph.size();

		for (int i = 1; i <= algorithm.getHorPosMax() + 1; i++) {
			GLine line = new GLine(nodeSeparationX * i, nodeSeparationY * 0,
					nodeSeparationX * i, nodeSeparationY
							* (algorithm.getVertPosMax() + 1));
			line.setColor(colorGrid);
			add(line);
			line.sendToBack();
		}
		for (int i = 1; i <= algorithm.getVertPosMax() + 1; i++) {
			GLine line = new GLine(nodeSeparationX * 0, nodeSeparationY * i,
					nodeSeparationX * (algorithm.getHorPosMax() + 1),
					nodeSeparationY * i);
			line.setColor(colorGrid);
			add(line);
			line.sendToBack();
		}
	}

	/**
	 * this draws a line between a parent and its child. We assume that the
	 * parameters have the nodes x and y position attached to the name of the
	 * node, e.g., "Wings attached?,0,0"
	 * 
	 * @param p1
	 * @param p2
	 */
	protected void drawEdge(Point p1, Point p2, E element) {
		if (!p1.equals(p2)) {
			if (orientation) {
				drawEdgeLeft(nodeSeparationX * p1.x, nodeSeparationY * p1.y,
						nodeSeparationX * p2.x, nodeSeparationY * p2.y);
			} else {
				drawEdgeDown(nodeSeparationX * p1.x, nodeSeparationY * p1.y,
						nodeSeparationX * p2.x, nodeSeparationY * p2.y, element);
			}
		} else {
			// System.out.println("p1 == p2");
			drawSelfLoop(nodeSeparationX * p1.x, nodeSeparationY * p1.y,
					element);
		}
	}

	private void drawSelfLoop(int x0, int y0, E element) {
		int oneOrTwo = (int) (Math.random() * 2) + 1;
		int twoOrOne = 3 - oneOrTwo;
		int width = nodeSeparationX / 2 / oneOrTwo;
		int height = nodeSeparationY / 2 / twoOrOne;
		GObject edge = null;
		if (oneOrTwo == 2) {
			edge = new GOval(x0 - width / 2, y0, width, height);
			if (showEdgeLabels) {
				drawEdgeLabel(x0, y0 + height, element);
			}
		} else {
			edge = new GOval(x0, y0 - height / 2, width, height);
			if (showEdgeLabels) {
				drawEdgeLabel(x0 + width, y0, element);
			}
		}
		edge.setColor(colorEdges);
		add(edge);
		edge.sendToBack();
	}

	protected void drawEdgeLabel(int x0, int y0, E element) {
		GLabel nodeLbl = new GLabel(element.toString().trim());
		nodeLbl.setFont(font);
		double lblW = nodeLbl.getWidth();
		double lblH = nodeLbl.getHeight();
		double lblA = nodeLbl.getAscent();

		GRoundRect backgroundRect = new GRoundRect(lblW, lblH);
		backgroundRect.setColor(Color.WHITE);
		backgroundRect.setFilled(true);
		backgroundRect.setFillColor(Color.WHITE);

		add(backgroundRect, x0 - lblW / 2, y0 - lblH / 2 + 1);
		add(nodeLbl, x0 - lblW / 2, y0 + lblA / 2 - 2);
	}

	protected void drawEdgeDown(int x0, int y0, int x1, int y1, E element) {
		// GObject edge = null;
		if (edgeStyle == EDGE_STYLE_DIRECT) {
			GObject edge = new GLine(x0, y0, x1, y1);
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
					//if (y1 <= y0) {
						drawEdgeLabel(x0 - (width + height) / 2, y0 + width
								+ height, element);
//					} else {
//						drawEdgeLabel(x0 + (width + height) / 2, y0 + width
//								+ height, element);
//					}
				}
			}
			// } else if (edgeStyle == EDGE_STYLE_SQUARE) {
			// GLine line2 = new GLine(x0, y0, x1, y0);
			// line2.setColor(colorEdges);
			// add(line2);
			// line2.sendToBack();
			// edge = new GLine(x1, y0, x1, y1);
			// } else if (edgeStyle == EDGE_STYLE_SQUARE2) {
			// GLine line2 = new GLine(x0, y0, x0, y1);
			// line2.setColor(colorEdges);
			// add(line2);
			// line2.sendToBack();
			// edge = new GLine(x0, y1, x1, y1);
			// } else { // EDGE_STYLE_QUAD
			// edge = new GQuadLine(x0, y0, x0, y1, x1, y1);
		}
	}

	protected boolean noCollision(int x0, int y0, int x1, int y1) {
		// nodeSeparationX / 10 -1
		// final int SKIP_STEPS = 8;
		final int SKIP_STEPS = nodeSeparationX / 10 - 1;
		double dx = (x1 - x0);
		double dy = (y1 - y0);
		double len = Math.sqrt(dx * dx + dy * dy);

		double nrOfSteps = len / 5; // every fifth pixel
		if (nrOfSteps < 2 * SKIP_STEPS) {
			System.out.println("PROBLEM");
		}
		dx = dx / nrOfSteps;
		dy = dy / nrOfSteps;
		double x = x0 + SKIP_STEPS * dx;
		double y = y0 + SKIP_STEPS * dy;
		for (int i = 0; i < nrOfSteps - 2 * SKIP_STEPS; i++) {
			GObject obj = getElementAt(x, y);
			if (obj != null) {
				if (!(obj instanceof GQuadLine)) {
					// System.out.println(obj.getClass());
					return false;
				}
			}
			x += dx;
			y += dy;
		}
		return true;
	}

	private boolean noCollisionOrig(int x0, int y0, int x1, int y1) {
		double dx = (x1 - x0);
		double dy = (y1 - y0);
		double len = Math.sqrt(dx * dx + dy * dy);

		double nrOfSteps = len / 5; // every fifth pixel
		dx = dx / nrOfSteps;
		dy = dy / nrOfSteps;
		double x = x0 + 4 * dx;
		double y = y0 + 4 * dy;
		for (int i = 0; i < nrOfSteps - 8; i++) {
			GObject obj = getElementAt(x, y);
			if (obj != null) {
				if (!(obj instanceof GQuadLine)) {
					// System.out.println(obj.getClass());
					return false;
				}
			}
			x += dx;
			y += dy;
		}
		return true;
	}

	private void drawEdgeLeft(int x0, int y0, int x1, int y1) {
		GObject edge = null;
		if (edgeStyle == EDGE_STYLE_DIRECT) {
			edge = new GLine(x0, y0, x1, y1);
			// } else if (edgeStyle == EDGE_STYLE_SQUARE) {
			// GLine line2 = new GLine(x0, y0, x0, y1);
			// line2.setColor(colorEdges);
			// add(line2);
			// line2.sendToBack();
			// edge = new GLine(x0, y1, x1, y1);
			// } else if (edgeStyle == EDGE_STYLE_SQUARE2) {
			// GLine line2 = new GLine(x0, y0, x1, y0);
			// line2.setColor(colorEdges);
			// add(line2);
			// line2.sendToBack();
			// edge = new GLine(x1, y0, x1, y1);
			// } else { // EDGE_STYLE_QUAD
			// edge = new GQuadLine(x0, y0, x1, y0, x1, y1);
		}
		edge.setColor(colorEdges);
		add(edge);
		edge.sendToBack();
	}

	/**
	 * draws a node with a label
	 * 
	 * @param element
	 * @param x
	 * @param y
	 */
	protected void drawElement(String element, int x, int y, Color colorEdge,
			Color colorFill) {
		GLabel nodeLbl = new GLabel(element.trim());
		nodeLbl.setFont(font);
		double lblW = nodeLbl.getWidth();
		double lblH = nodeLbl.getHeight();
		double lblA = nodeLbl.getAscent();

		int dx = ((int) lblW + 2 * marginNodeX);
		int dy = ((int) lblH + 2 * marginNodeY);

		GObject node;
		if (shapeNode == SHAPE_OVAL) {
			node = createShape(new GOval(dx, dy), colorFill);
		} else if (shapeNode == SHAPE_RECT) {
			node = createShape(new GRect(dx, dy), colorFill);
		} else { // SHAPE_ROUNDRECT
			node = createShape(new GRoundRect(dx, dy), colorFill);
		}
		node.setColor(colorEdge);

		if (alignment == ALIGN_CENTER) {
			add(node, nodeSeparationX * x - dx / 2 - 1, nodeSeparationY * y
					- dy / 2);
			add(nodeLbl, nodeSeparationX * x - lblW / 2, nodeSeparationY * y
					+ lblA / 2 - 1);
		} else if (alignment == ALIGN_LEFT) {
			add(node, nodeSeparationX * x - marginNodeX - 1, nodeSeparationY
					* y - dy / 2);
			add(nodeLbl, nodeSeparationX * x, nodeSeparationY * y + lblA / 2
					- 1);
		} else {
			add(node, nodeSeparationX * x - dx + marginNodeX - 1,
					nodeSeparationY * y - dy / 2);
			add(nodeLbl, nodeSeparationX * x - lblW, nodeSeparationY * y + lblA
					/ 2 - 1);
		}
	}

	private GObject createShape(GFillable nod, Color colorFill) {
		if (colorFill != null) {
			nod.setFilled(true);
			nod.setFillColor(colorFill);
		}
		return (GObject) nod;
	}

}
