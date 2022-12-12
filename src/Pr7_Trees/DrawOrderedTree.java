import java.awt.Color;
import java.io.File;

import javax.swing.JScrollPane;

import tree.OrderedTree;
import tree.OrderedTreeDrawerCanvas;
import tree.OrderedTreeParser;
import acm.program.Program;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * DrawOrderedTree
 * 
 * This is a simple demo on how to use OrderedTreeParser and the
 * OrderedTreeDrawerCanvas to draw nice looking binary trees.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class DrawOrderedTree extends Program {

	public void init() {
		setSize(400, 400);

		// drawJava();

		drawCombinations();
		// drawCamels();
		// drawAbraham();
		// drawUniversity();
		// drawBook();
	}

	private void drawCombinations() {
		OrderedTree<String> tree = new OrderedTreeParser().parseTree(new File(
				"combinations.txt"));

		OrderedTreeDrawerCanvas canvas = new OrderedTreeDrawerCanvas(tree);
		canvas.setShapeNode(OrderedTreeDrawerCanvas.SHAPE_ROUNDRECT);
		canvas.setOrientation(OrderedTreeDrawerCanvas.VERTICAL);
		canvas.setEdgeStyle(OrderedTreeDrawerCanvas.EDGE_STYLE_DIRECT);
		canvas.setColorExternalNodes(Color.BLUE);
		canvas.setColorExternalNodesFill(new Color(230, 230, 230));
		canvas.setNodeSeparationX(90);
		canvas.setNodeSeparationY(35);
		canvas.setFont("Courier new-bold-18");
		add(new JScrollPane(canvas), CENTER);
	}

	private void drawJava() {
		OrderedTree<String> tree = new OrderedTreeParser().parseTree(new File(
				"java.txt"));

		OrderedTreeDrawerCanvas canvas = new OrderedTreeDrawerCanvas(tree);
		canvas.setShapeNode(OrderedTreeDrawerCanvas.SHAPE_ROUNDRECT);
		canvas.setOrientation(OrderedTreeDrawerCanvas.HORIZONTAL);
		canvas.setInOrder(false);
		canvas.setAlignment(OrderedTreeDrawerCanvas.ALIGN_LEFT);
		canvas.setEdgeStyle(OrderedTreeDrawerCanvas.EDGE_STYLE_SQUARE);
		canvas.setNodeSeparationX(60);
		canvas.setNodeSeparationY(39);
		canvas.setFont("Courier new-bold-18");
		add(new JScrollPane(canvas), CENTER);
	}

	private void drawCamels() {
		OrderedTree<String> tree = new OrderedTreeParser().parseTree(new File(
				"camels_and_whales.txt"));

		OrderedTreeDrawerCanvas canvas = new OrderedTreeDrawerCanvas(tree);
		canvas.setShapeNode(OrderedTreeDrawerCanvas.SHAPE_RECT);
		canvas.setOrientation(OrderedTreeDrawerCanvas.HORIZONTAL);
		canvas.setAlignment(OrderedTreeDrawerCanvas.ALIGN_LEFT);
		canvas.setEdgeStyle(OrderedTreeDrawerCanvas.EDGE_STYLE_SQUARE);
		canvas.setShowInternalNodes(false);
		canvas.setColorExternalNodes(Color.WHITE);
		canvas.setNodeSeparationX(35);
		canvas.setNodeSeparationY(20);
		canvas.setFont("Courier new-bold-18");
		add(new JScrollPane(canvas), CENTER);
	}

	private void drawAbraham() {
		OrderedTree<String> tree = new OrderedTreeParser().parseTree(new File(
				"abraham.txt"));

		OrderedTreeDrawerCanvas canvas = new OrderedTreeDrawerCanvas(tree);
		canvas.setShapeNode(OrderedTreeDrawerCanvas.SHAPE_ROUNDRECT);
		canvas.setOrientation(OrderedTreeDrawerCanvas.HORIZONTAL);
		canvas.setAlignment(OrderedTreeDrawerCanvas.ALIGN_LEFT);
		canvas.setEdgeStyle(OrderedTreeDrawerCanvas.EDGE_STYLE_QUAD);
		canvas.setNodeSeparationX(76);
		canvas.setNodeSeparationY(14);
		canvas.setMarginNodeX(4);
		canvas.setMarginNodeY(0);
		canvas.setFont("Times New Roman-bold-12");
		add(new JScrollPane(canvas), CENTER);
	}

	private void drawUniversity() {
		OrderedTree<String> tree = new OrderedTreeParser().parseTree(new File(
				"university.txt"));

		OrderedTreeDrawerCanvas canvas = new OrderedTreeDrawerCanvas(tree);
		canvas.setShapeNode(OrderedTreeDrawerCanvas.SHAPE_ROUNDRECT);
		canvas.setOrientation(OrderedTreeDrawerCanvas.HORIZONTAL);
		canvas.setAlignment(OrderedTreeDrawerCanvas.ALIGN_CENTER);
		canvas.setEdgeStyle(OrderedTreeDrawerCanvas.EDGE_STYLE_SQUARE);
		canvas.setNodeSeparationX(76);
		canvas.setNodeSeparationY(34);
		canvas.setFont("Courier new-bold-18");
		add(new JScrollPane(canvas), CENTER);
	}

	private void drawBook() {
		OrderedTree<String> tree = new OrderedTreeParser().parseTree(new File(
				"book.txt"));

		OrderedTreeDrawerCanvas canvas = new OrderedTreeDrawerCanvas(tree);
		canvas.setShapeNode(OrderedTreeDrawerCanvas.SHAPE_ROUNDRECT);
		canvas.setOrientation(OrderedTreeDrawerCanvas.VERTICAL);
		canvas.setEdgeStyle(OrderedTreeDrawerCanvas.EDGE_STYLE_QUAD);
		canvas.setNodeSeparationX(62);
		canvas.setNodeSeparationY(35);
		canvas.setFont("Courier new-bold-18");
		add(new JScrollPane(canvas), CENTER);
	}

}
