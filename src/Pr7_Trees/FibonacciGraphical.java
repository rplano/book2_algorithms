import tree.BinaryNode;
import tree.BinaryTree;
import tree.BinaryTreeDrawerCanvas;
import acm.io.IODialog;
import acm.program.Program;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * FibonacciGraphical
 * 
 * Visualize the Fibonacci numbers as tree in a graphical way.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class FibonacciGraphical extends Program {

	private BinaryTreeDrawerCanvas canvas;

	public void init() {
		setSize(400, 250);

		IODialog dialog = getDialog();
		int n = dialog.readInt("Enter n: ");

		BinaryNode<Integer> root = new BinaryNode<Integer>(n);
		fibo(root);

		BinaryTree<Integer> tree = new BinaryTree<Integer>(root);
		canvas = new BinaryTreeDrawerCanvas(tree);
		canvas.setShapeNode(BinaryTreeDrawerCanvas.SHAPE_ROUNDRECT);
		canvas.setOrientation(BinaryTreeDrawerCanvas.VERTICAL);
		canvas.setEdgeStyle(BinaryTreeDrawerCanvas.EDGE_STYLE_DIRECT);
		canvas.setNodeSeparationX(25);
		canvas.setNodeSeparationY(33);
		canvas.setFont("Courier new-bold-18");
		add(canvas, CENTER);
	}

	private int fibo(BinaryNode<Integer> node) {
		int n = node.getElement();
		switch (n) {
		case 0:
			return 0;
		case 1:
			return 1;
		default:
			BinaryNode<Integer> left = new BinaryNode<Integer>(n - 2);
			node.setLeft(left);
			BinaryNode<Integer> right = new BinaryNode<Integer>(n - 1);
			node.setRight(right);
			return fibo(right) + fibo(left);
		}
	}
}
