package tree;

import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * OrderedTreeDrawerCanvas
 * 
 * Use GRects or GOvals and in-order traversal to draw a nice looking tree.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class OrderedTreeDrawerCanvas extends AbstractTreeDrawerCanvas {

	public static final boolean TRAVERSAL_IN_ORDER = true; 
	public static final boolean TRAVERSAL_PRE_ORDER = false; 
	
	protected HashMap<OrderedNode<?>, Point> positions;
	private boolean isInOrder = TRAVERSAL_IN_ORDER;
	
	
	public OrderedTreeDrawerCanvas(OrderedTree<?> tree) {
		super(tree);
	}

	public boolean isInOrder() {
		return isInOrder;
	}

	public void setInOrder(boolean isInOrder) {
		this.isInOrder = isInOrder;
	}

	/**
	 * edges can only be drawn after the positions of the nodes are known. we
	 * start from the root node, descending recursively through the whole tree.
	 * 
	 * @param p1
	 */
	protected void drawEdges(AbstractNode<?> p1) {
		if (p1.getChildren() != null && p1.getChildren().size() > 0) {
			for (AbstractNode<?> p2 : p1.getChildren()) {
				drawEdge(positions.get(p1), positions.get(p2));
				drawEdges(p2);
			}
		}
	}
	
	/**
	 * recursively draw the nodes (vertices) using in-order or pre-order traversal.
	 */
	protected void drawNodes() {
		positions = new HashMap<OrderedNode<?>, Point>();
		if (isInOrder) {
			if (orientation) {
				inOrderHorizontal((OrderedNode<?>) tree.root());
			} else {
				inOrderVertical((OrderedNode<?>) tree.root());
			}
		} else {
			if (orientation) {
				preOrderHorizontal((OrderedNode<?>) tree.root());
			} else {
				preOrderVertical((OrderedNode<?>) tree.root());
			}
		}
	}

	/**
	 * in order traversal for non-binary tree.
	 * 
	 * @param p
	 */
	private void inOrderVertical(OrderedNode<?> p) {
		// left
		int size = 0;
		if (p.getChildren() != null && p.getChildren().size() > 0) {
			size = p.getChildren().size();
			Iterator<?> iter = p.getChildren().iterator();
			int i = 0;
			vertPos++;
			if (vertPos + 1 > vertPosMax) {
				vertPosMax = vertPos + 1;
			}
			while (i < size / 2) {
				OrderedNode<?> child = (OrderedNode<?>) iter.next();
				inOrderVertical(child);
				i++;
			}
			vertPos--;
		}		
		
		// visit, i.e., paint
		visit(p);
		//if (size > 0) {
		if (size%2 == 0) {
			horPos++;
		}
		if (horPos > horPosMax) {
			horPosMax = horPos;
		}
		
		// right
		if (p.getChildren() != null && p.getChildren().size() > 0) {
			size = p.getChildren().size();
			Iterator<?> iter = p.getChildren().iterator();
			// move to previous position
			for (int i = 0; i < size / 2; i++) {
				iter.next();
			}
			vertPos++;
			if (vertPos + 1 > vertPosMax) {
				vertPosMax = vertPos + 1;
			}
			while (iter.hasNext()) {
				OrderedNode<?> child = (OrderedNode<?>) iter.next();
				inOrderVertical(child);
			}
			vertPos--;
		}
	}

	/**
	 * in order traversal for non-binary tree.
	 * 
	 * @param p
	 */
	private void inOrderHorizontal(OrderedNode<?> p) {
		// left
		if (p.getChildren() != null && p.getChildren().size() > 0) {
			int size = p.getChildren().size();
			Iterator<?> iter = p.getChildren().iterator();
			// for (AbstractNode<String> child : p.getChildren()) {
			int i = 0;
			while (i < size / 2) {
				OrderedNode<?> child = (OrderedNode<?>) iter.next();
				horPos++;
				if (horPos + 1 > horPosMax) {
					horPosMax = horPos + 1;
				}
				inOrderHorizontal(child);
				horPos--;
				i++;
			}
		}
		
		// visit, i.e., paint
		visit(p);
		vertPos++;
		if (vertPos > vertPosMax) {
			vertPosMax = vertPos;
		}
		
		// right
		if (p.getChildren() != null && p.getChildren().size() > 0) {
			int size = p.getChildren().size();
			Iterator<?> iter = p.getChildren().iterator();
			// move to previous position
			for (int i = 0; i < size / 2; i++) {
				iter.next();
			}
			// for (AbstractNode<String> child : p.getChildren()) {
			while (iter.hasNext()) {
				OrderedNode<?> child = (OrderedNode<?>) iter.next();
				horPos++;
				if (horPos + 1 > horPosMax) {
					horPosMax = horPos + 1;
				}
				inOrderHorizontal(child);
				horPos--;
			}
		}
	}

	private void visit(OrderedNode<?> p) {
		String tmp = p.getElement().toString();
		if (p.isInternal() && showInternalNodes) {
			drawElement(tmp, horPos + 1, vertPos + 1, colorInternalNodes,
					colorInternalNodesFill);
		} else if (p.isExternal() && showExternalNodes) {
			drawElement(tmp, horPos + 1, vertPos + 1, colorExternalNodes,
					colorExternalNodesFill);
		}
		// remember position, needed for drawing edges later
		positions.put(p, new Point(horPos +1, vertPos+1));
	}

	/**
	 * simple pre-order traversal.
	 * 
	 * @param p
	 */
	private void preOrderVertical(OrderedNode<?> p) {
		// visit, i.e., paint
		visit(p);
		horPos++;
		if (horPos > horPosMax) {
			horPosMax = horPos;
		}

		// right = children
		if (p.getChildren() != null && p.getChildren().size() > 0) {
			vertPos++;
			for (AbstractNode<?> child : p.getChildren()) {
				preOrderVertical((OrderedNode<?>) child);
			}
			vertPos--;
		}
	}

	/**
	 * simple pre-order traversal.
	 * 
	 * @param p
	 */
	private void preOrderHorizontal(OrderedNode<?> p) {

		// visit, i.e., paint
		visit(p);
		vertPos++;
		if (vertPos > vertPosMax) {
			vertPosMax = vertPos;
		}

		// right = children
		if (p.getChildren() != null && p.getChildren().size() > 0) {
			horPos++;
			for (AbstractNode<?> child : p.getChildren()) {
				preOrderHorizontal((OrderedNode<?>) child);
			}
			horPos--;
		}
	}
}
