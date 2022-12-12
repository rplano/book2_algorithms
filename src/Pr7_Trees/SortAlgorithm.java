import java.io.File;

import tree.BinaryNode;
import tree.BinaryTree;
import tree.BinaryTreeParser;
import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * SortAlgorithm
 * 
 * Going through a decision tree, in this case helping you to pick the best sort
 * algorithm for your problem.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class SortAlgorithm extends ConsoleProgram {

	private BinaryTree<String> decisions;

	public void run() {
		setSize(400, 300);
		setFont("Times New Roman-bold-18");

		decisions = new BinaryTreeParser().parseTree(new File(
				"sort_algorithm.txt"));

		walkThrough();
	}

	private void walkThrough() {
		println("Welcome to the sort algorithm check list.");
		println("Answer the following questions with yes or no.");
		// start with the root:
		BinaryNode<String> currentNode = (BinaryNode<String>) decisions.root();
		while (currentNode.isInternal()) {

			String answer = readLine(currentNode.getElement() + "(yes/no) ");
			if (answer.equals("yes")) {
				currentNode = currentNode.getLeft();
			} else {
				currentNode = currentNode.getRight();
			}
		}
		println(currentNode.getElement());
	}

}
