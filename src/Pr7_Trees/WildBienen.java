import acm.program.ConsoleProgram;
import tree.BinaryNode;
import tree.BinaryTree;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * WildBienen
 * 
 * Based on handout about wild bees.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class WildBienen extends ConsoleProgram {

	private BinaryTree<String> decisions;

	public WildBienen() {
		setSize(400, 200);
		setFont("Times New Roman-bold-18");

		// create decision tree, left is yes:
		BinaryNode<String> root = new BinaryNode<String>(
				"Dichte Haarbüschel (\"Sammelbürsten\") am 3. Beinpaar oder auf der Unterseite des Hinterleibes?");

		// your code goes here...
		// add all nodes to the tree...

		decisions = new BinaryTree<String>(root);

		// just to check, it should have 15 elements:
		println("size: " + decisions.size());

		// print the tree, to see if it looks good (maybe difficult, because
		// text is to long)
		printDecisionTree();

		// walk through a bee checklist:
		walkThrough();
	}

	private void walkThrough() {
		println("Willkommen zur Wildbienenbestimmung.");
		println("Beantworten Sie die Fragen mit ja oder nein.");
		// start with the root:
		BinaryNode<String> currentNode = (BinaryNode<String>) decisions.root();
		while (currentNode.isInternal()) {

			String answer = readLine(currentNode.getElement() + "(yes/no)");
			if (answer.equals("ja")) {
				currentNode = currentNode.getLeft();
			} else {
				currentNode = currentNode.getRight();
			}
		}
		println(currentNode.getElement());
	}

	/**
	 * Prints binary tree in a nice form, note yes answers go to the left, no
	 * answers to the right.
	 * 
	 * @param p
	 */
	private void printDecisionTree() {
		String tmp[][] = new tree.TreePrinter().prettyPrintSimple(decisions);
		printTreeVertical(tmp);
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
