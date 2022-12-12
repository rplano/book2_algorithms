package tree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.HashMap;

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
 * AbstractTreeDrawerCanvas
 * 
 * Use GRects or GOvals and in-order traversal to draw a nice looking tree. 
 * This is inspired by the Stock example from the first book.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public abstract class AbstractTreeDrawerCanvas extends GCanvas implements
		ComponentListener {

	private static final int NODE_SEPARATION_INITIAL = 40;

	public static final boolean HORIZONTAL = true; // means levels go left
	public static final boolean VERTICAL = false; // means levels go down
	public static final int SHAPE_RECT = 0;
	public static final int SHAPE_ROUNDRECT = 1;
	public static final int SHAPE_OVAL = 2;
	public static final boolean GRID_ON = true;
	public static final boolean GRID_OFF = false;
	public static final int EDGE_STYLE_DIRECT = 0;
	public static final int EDGE_STYLE_SQUARE = 1;
	public static final int EDGE_STYLE_SQUARE2 = 2;
	public static final int EDGE_STYLE_QUAD = 3;
	public static final boolean NODES_SHOW = true;
	public static final boolean NODES_HIDE = false;
	public static final int ALIGN_LEFT = 0;
	public static final int ALIGN_CENTER = 1;
	public static final int ALIGN_RIGHT = 2;

	private boolean isGridOn = GRID_OFF;
	protected boolean orientation = VERTICAL;
	protected int alignment = ALIGN_CENTER;
	protected boolean showInternalNodes = NODES_SHOW;
	protected boolean showExternalNodes = NODES_SHOW;
	private int nodeSeparationX = NODE_SEPARATION_INITIAL;
	private int nodeSeparationY = NODE_SEPARATION_INITIAL;
	private int edgeStyle = EDGE_STYLE_DIRECT;
	private int shapeNode = SHAPE_ROUNDRECT;
	private int marginNodeX = 7;
	private int marginNodeY = 2;
	protected Color colorInternalNodes = Color.BLACK;
	protected Color colorInternalNodesFill = Color.WHITE;
	protected Color colorExternalNodes = Color.BLACK;
	protected Color colorExternalNodesFill = Color.WHITE;
	private Color colorEdges = Color.BLACK;
	private Color colorGrid = Color.LIGHT_GRAY;
	private String font = "Courier new-bold-18"; // "Times New Roman-bold-18",
													// "Arial-bold-18"
	
	protected AbstractTree<?> tree;
	protected int horPos = 0;
	protected int vertPos = 0;
	protected int horPosMax = 0;
	protected int vertPosMax = 0;
	
	
	public AbstractTreeDrawerCanvas(AbstractTree<?> tree) {
		super();
		this.tree = tree;
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

	public boolean isShowInternalNodes() {
		return showInternalNodes;
	}

	public void setShowInternalNodes(boolean showInternalNodes) {
		this.showInternalNodes = showInternalNodes;
	}

	public boolean isShowExternalNodes() {
		return showExternalNodes;
	}

	public void setShowExternalNodes(boolean showExternalNodes) {
		this.showExternalNodes = showExternalNodes;
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

	public Color getColorInternalNodes() {
		return colorInternalNodes;
	}

	public void setColorInternalNodes(Color colorInternalNodes) {
		this.colorInternalNodes = colorInternalNodes;
	}

	public Color getColorInternalNodesFill() {
		return colorInternalNodesFill;
	}

	public void setColorInternalNodesFill(Color colorInternalNodesFill) {
		this.colorInternalNodesFill = colorInternalNodesFill;
	}

	public Color getColorExternalNodes() {
		return colorExternalNodes;
	}

	public void setColorExternalNodes(Color colorExternalNodes) {
		this.colorExternalNodes = colorExternalNodes;
	}


	public Color getColorExternalNodesFill() {
		return colorExternalNodesFill;
	}

	public void setColorExternalNodesFill(Color colorExternalNodesFill) {
		this.colorExternalNodesFill = colorExternalNodesFill;
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
		return new Dimension((horPosMax + 1) * nodeSeparationX,
				(vertPosMax + 1) * nodeSeparationY);
	}
	
	private void update() {
		removeAll();
		horPos = 0;
		vertPos = 0;
		horPosMax = 0;
		vertPosMax = 0;
		drawNodes();
		drawEdges((AbstractNode<?>) tree.root());
		if (isGridOn) {
			drawGrid();
		}
	}
	
	protected abstract void drawNodes();

	protected abstract void drawEdges(AbstractNode<?> n1);

	/**
	 * this draws a line between a parent and its child. We assume that the
	 * parameters have the nodes x and y position attached to the name of the
	 * node, e.g., "Wings attached?,0,0"
	 * 
	 * @param p1
	 * @param p2
	 */
	protected void drawEdge(Point p1, Point p2) {
		if (orientation) {
			drawEdgeLeft(nodeSeparationX * p1.x, nodeSeparationY * p1.y ,
					nodeSeparationX * p2.x, nodeSeparationY * p2.y);
		} else {
			drawEdgeDown(nodeSeparationX * p1.x, nodeSeparationY * p1.y ,
					nodeSeparationX * p2.x, nodeSeparationY * p2.y);
		}
	}

	private void drawEdgeDown(int x0, int y0, int x1, int y1) {
		GObject edge = null;
		if (edgeStyle == EDGE_STYLE_DIRECT) {
			edge = new GLine(x0, y0, x1, y1);
		} else if (edgeStyle == EDGE_STYLE_SQUARE) {
			GLine line2 = new GLine(x0, y0, x1, y0);
			line2.setColor(colorEdges);
			add(line2);
			line2.sendToBack();
			edge = new GLine(x1, y0, x1, y1);
		} else if (edgeStyle == EDGE_STYLE_SQUARE2) {
			GLine line2 = new GLine(x0, y0, x0, y1);
			line2.setColor(colorEdges);
			add(line2);
			line2.sendToBack();
			edge = new GLine(x0, y1, x1, y1);
		} else { // EDGE_STYLE_QUAD
			edge = new GQuadLine(x0, y0, x0, y1, x1, y1);
		}
		edge.setColor(colorEdges);
		add(edge);
		edge.sendToBack();
	}

	private void drawEdgeLeft(int x0, int y0, int x1, int y1) {
		GObject edge = null;
		if (edgeStyle == EDGE_STYLE_DIRECT) {
			edge = new GLine(x0, y0, x1, y1);
		} else if (edgeStyle == EDGE_STYLE_SQUARE) {
			GLine line2 = new GLine(x0, y0, x0, y1);
			line2.setColor(colorEdges);
			add(line2);
			line2.sendToBack();
			edge = new GLine(x0, y1, x1, y1);
		} else if (edgeStyle == EDGE_STYLE_SQUARE2) {
			GLine line2 = new GLine(x0, y0, x1, y0);
			line2.setColor(colorEdges);
			add(line2);
			line2.sendToBack();
			edge = new GLine(x1, y0, x1, y1);
		} else { // EDGE_STYLE_QUAD
			edge = new GQuadLine(x0, y0, x1, y0, x1, y1);
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
		
		int dx = ((int) lblW + 2*marginNodeX);
		int dy = ((int) lblH + 2*marginNodeY);

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
			add(node, nodeSeparationX * x - dx / 2 -1, nodeSeparationY * y - dy / 2 );
			add(nodeLbl, nodeSeparationX * x  - lblW/2, nodeSeparationY * y +lblA/2 -1);			
		} else if (alignment == ALIGN_LEFT) {
			add(node, nodeSeparationX * x - marginNodeX  -1, nodeSeparationY * y - dy / 2 );
			add(nodeLbl, nodeSeparationX * x  , nodeSeparationY * y +lblA/2 -1);			
		} else {
			add(node, nodeSeparationX * x - dx + marginNodeX -1, nodeSeparationY * y - dy / 2 );
			add(nodeLbl, nodeSeparationX * x  - lblW, nodeSeparationY * y +lblA/2 -1);				
		}
	}

	private GObject createShape(GFillable nod, Color colorFill) {
		if (colorFill != null) {
			nod.setFilled(true);
			nod.setFillColor(colorFill);
		}
		return (GObject) nod;
	}

	private void drawGrid() {
		//int vertPos = tree.size();
		for (int i = 1; i <= horPosMax + 1; i++) {
			GLine line = new GLine(nodeSeparationX * i, nodeSeparationY * 0,
					nodeSeparationX * i, nodeSeparationY * (vertPosMax + 1));
			line.setColor(colorGrid);
			add(line);
			line.sendToBack();
		}
		for (int i = 1; i <= vertPosMax + 1; i++) {
			GLine line = new GLine(nodeSeparationX * 0, nodeSeparationY * i,
					nodeSeparationX * (horPosMax + 1), nodeSeparationY * i);
			line.setColor(colorGrid);
			add(line);
			line.sendToBack();
		}
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
}
