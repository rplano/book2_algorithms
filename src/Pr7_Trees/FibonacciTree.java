import java.awt.Color;

import tree.AbstractNode;
import tree.BinaryNode;
import tree.GThickLine;
import acm.graphics.GLine;
import acm.io.IODialog;
import acm.program.GraphicsProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * FibonacciTree
 * 
 * Draws a nice FibonacciTree.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class FibonacciTree extends GraphicsProgram {
	private static final int SIZE = 400;

	private double len = 80;
	private double leftAngle = -40;
	private double rightAngle = 20;
	private double scaleFactor = 0.8;
	private double thickness = 10;

	public void run() {
		setSize(SIZE, SIZE);

		IODialog dialog = getDialog();
		int n = dialog.readInt("Enter n: ");

		BinaryNode<Integer> root = new BinaryNode<Integer>(n);
		fibo(root);

		// preOrderSimple(root, SIZE/2-40, SIZE-44, -Math.toRadians(90));
		preOrderPretty(root, SIZE / 2, SIZE - 44, -Math.toRadians(90));
	}

	private void preOrderSimple(BinaryNode<Integer> node, int x0, int y0,
			double alpha) {

		visitSimple(node, x0, y0, alpha);
		x0 += len * Math.cos(alpha);
		y0 += len * Math.sin(alpha);

		if (node.hasLeft()) {
			preOrderSimple(node.getLeft(), x0, y0,
					alpha + Math.toRadians(leftAngle));
		}
		if (node.hasRight()) {
			preOrderSimple(node.getRight(), x0, y0,
					alpha + Math.toRadians(rightAngle));
		}
	}

	private void visitSimple(AbstractNode<Integer> node, int x1, int y1,
			double alpha) {
		GLine line = new GLine(x1, y1, x1 + len * Math.cos(alpha), y1 + len
				* Math.sin(alpha));
		add(line);
	}

	private void preOrderPretty(BinaryNode<Integer> node, int x0, int y0,
			double alpha) {
		Color col = new Color(101, 67, 33); // Color.BLACK;
		if (node.isExternal()) {
			col = Color.GREEN;
		}
		visit(node, x0, y0, alpha, col);
		x0 += len * Math.cos(alpha);
		y0 += len * Math.sin(alpha);

		len *= scaleFactor;
		thickness *= scaleFactor;
		if (node.hasLeft()) {
			preOrderPretty(node.getLeft(), x0, y0,
					alpha + Math.toRadians(leftAngle));
		}
		if (node.hasRight()) {
			preOrderPretty(node.getRight(), x0, y0,
					alpha + Math.toRadians(rightAngle));
		}
		len /= scaleFactor;
		thickness /= scaleFactor;
	}

	private void visit(AbstractNode<Integer> node, int x1, int y1,
			double alpha, Color col) {
		GThickLine line = new GThickLine(x1, y1, x1 + len * Math.cos(alpha), y1
				+ len * Math.sin(alpha), (int) thickness);
		line.setColor(col);
		add(line);
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
