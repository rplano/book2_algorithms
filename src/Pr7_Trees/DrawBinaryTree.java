import java.awt.Color;
import java.io.File;

import javax.swing.JScrollPane;

import tree.BinaryTree;
import tree.BinaryTreeParser;
import tree.BinaryTreeDrawerCanvas;
import acm.program.Program;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * DrawBinaryTree
 * 
 * This is a simple demo on how to use BinaryTreeParser and the
 * BinaryTreeDrawerCanvas to draw nice looking binary trees.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class DrawBinaryTree extends Program {

	public void init() {
		setSize(400, 200);

		BinaryTree<String> tree = new BinaryTreeParser().parseTree(new File(
				"combinations.txt"));
		// "pilot.txt"));
		// "sort_algorithm.txt"));
		// "eat.txt", "math.txt", "pilot.txt", "sort_algorithm.txt",
		// "type_face.txt", "visualization_method.txt", "fibonacci.txt"

		// String tree =
		// "Wings attached?{Wheels attached?{Fly!,Water plane?{Fly!,Don't Fly!}},Don't Fly!}";
		// String tree = "D{E{F,},G{H{I,},J{K,L}}}";
		// String tree = "+{*{2,-{a,1}},*{3,b}}";
		// String tree =
		// "5 { 3 { 1, 2 { 0, 1 } }, 4 { 2 { 0, 1 }, 3 { 1, 2 { 0, 1 } } } }";

		// BinaryTree<String> decisions = new
		// BinaryTreeParser().parseTree(tree);

		BinaryTreeDrawerCanvas canvas = new BinaryTreeDrawerCanvas(tree);
		canvas.setShapeNode(BinaryTreeDrawerCanvas.SHAPE_ROUNDRECT);
		// canvas.setColorExternalNodes(Color.BLUE);
		// canvas.setColorExternalNodesFill(Color.LIGHT_GRAY);
		// canvas.setColorInternalNodes(Color.RED);
		// canvas.setGridOn(true);
		// canvas.setOrientation(BinaryTreeDrawerCanvas.VERTICAL);
		// canvas.setEdgeStyle(BinaryTreeDrawerCanvas.EDGE_STYLE_DIRECT);
		canvas.setNodeSeparationX(90);
		canvas.setNodeSeparationY(30);
		// canvas.setFont("Courier new-bold-18");
		add(new JScrollPane(canvas), CENTER);
	}

}
