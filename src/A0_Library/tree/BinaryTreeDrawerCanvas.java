package tree;

import java.awt.Point;
import java.util.HashMap;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * BinaryTreeDrawerCanvas
 * 
 * Use GRects or GOvals and in-order traversal to draw a nice looking tree. 
 * This is inspired by the Stock example from the first book.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class BinaryTreeDrawerCanvas extends AbstractTreeDrawerCanvas {

	protected HashMap<BinaryNode<?>, Point> positions;
	
	public BinaryTreeDrawerCanvas(BinaryTree<?> tree) {
		super(tree);
	}

	/**
	 * edges can only be drawn after the positions of the nodes are known. we
	 * start from the root node, descending recursively through the whole tree.
	 * 
	 * @param n1
	 */
	protected void drawEdges(AbstractNode<?> n1) {
		
		// left
		if (((BinaryNode<?>) n1).hasLeft()) {
			BinaryNode<?> n2 = ((BinaryNode<?>) n1).getLeft();
			drawEdge(positions.get(n1), positions.get(n2));
			drawEdges(n2);
		}
		// right
		if (((BinaryNode<?>) n1).hasRight()) {
			BinaryNode<?> n2 = ((BinaryNode<?>) n1).getRight();
			drawEdge(positions.get(n1), positions.get(n2));
			drawEdges(n2);
		}
	}
	
	/**
	 * recursively draw the nodes (vertices) using in-order traversal.
	 */
	protected void drawNodes() {
		positions = new HashMap<BinaryNode<?>, Point>();
		if (orientation) {
			inOrderHorizontal((BinaryNode<?>) tree.root());
		} else {
			inOrderVertical((BinaryNode<?>) tree.root());
		}
	}

	/**
	 * simple in order traversal.
	 * 
	 * @param p
	 */
	private void inOrderVertical(BinaryNode<?> p) {
		// left
		if (p.hasLeft()) {
			vertPos++;
			if (vertPos + 1 > vertPosMax) {
				vertPosMax = vertPos + 1;
			}
			inOrderVertical(p.getLeft());
			vertPos--;
		}
		
		// visit, i.e., paint
		visit(p);
		horPos++;
		if (horPos > horPosMax) {
			horPosMax = horPos;
		}
		
		// right
		if (p.hasRight()) {
			vertPos++;
			if (vertPos + 1 > vertPosMax) {
				vertPosMax = vertPos + 1;
			}
			inOrderVertical(p.getRight());
			vertPos--;
		}
	}

	private void inOrderHorizontal(BinaryNode<?> p) {
		// left
		if (p.hasLeft()) {
			horPos++;
			if (horPos + 1 > horPosMax) {
				horPosMax = horPos + 1;
			}
			inOrderHorizontal(p.getLeft());
			horPos--;
		}
		
		// visit, i.e., paint
		visit(p);
		vertPos++;
		if (vertPos > vertPosMax) {
			vertPosMax = vertPos;
		}
		
		// right
		if (p.hasRight()) {
			horPos++;
			if (horPos + 1 > horPosMax) {
				horPosMax = horPos + 1;
			}
			inOrderHorizontal(p.getRight());
			horPos--;
		}
	}

	private void visit(BinaryNode<?> p) {
		String tmp = p.getElement().toString();
		if (p.isInternal() && showInternalNodes) {
			drawElement(tmp, horPos + 1, vertPos + 1, colorInternalNodes,
					colorInternalNodesFill);
		} else if (p.isExternal() && showExternalNodes) {
			drawElement(tmp, horPos + 1, vertPos + 1, colorExternalNodes,
					colorExternalNodesFill);
		}
		// remember position, needed for drawing edges later
		positions.put(p, new Point(horPos+1 , vertPos+1));
	}
}
