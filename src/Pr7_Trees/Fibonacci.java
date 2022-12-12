import tree.BinaryNode;
import tree.BinaryTree;
import tree.BinaryTreeParser;
import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Fibonacci
 * 
 * Visualize the Fibonacci numbers as tree.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Fibonacci extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Courier New-bold-18");

		int n = readInt("Enter n: ");
		BinaryNode<Integer> root = new BinaryNode<Integer>(n);
		fibo(root);

		String tmp[][] = new tree.TreePrinter()
				.prettyPrintSimple(new BinaryTree<Integer>(root));
		printTreeVertical(tmp);

		// System.out.println( new BinaryTreeParser().createStringFromTree(root,
		// false));
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

	private void printTreeVertical(String[][] tmp) {
		for (int i = 0; i < tmp.length; i++) { // 4
			for (int j = 0; j < tmp[0].length; j++) { // 7
				System.out.println();
				if (tmp[i][j] != null) {
					print(tmp[i][j] + " ");
				} else {
					print("  ");
				}
			}
			println();
		}
	}
}
