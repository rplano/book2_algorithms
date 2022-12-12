import tree.BinaryNode;
import tree.BinaryTree;
import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * PilotCheckList
 * 
 * Going through a decision tree.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class PilotCheckList extends ConsoleProgram {

	private BinaryTree<String> decisions;

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-18");

		createDecisionTree();
		pilotWalkThrough();
	}

	private void pilotWalkThrough() {
		println("Welcome to the pilot check list.");
		println("Answer the following questions with yes or no.");
		// start with the root:
		BinaryNode<String> currentNode = (BinaryNode<String>) decisions.root();
		while (currentNode.isInternal()) {

			String answer = readLine(currentNode.getElement() + "(yes/no)");
			if (answer.equals("yes")) {
				currentNode = currentNode.getLeft();
			} else {
				currentNode = currentNode.getRight();
			}
		}
		println(currentNode.getElement());
	}

	private void createDecisionTree() {
		// left is yes:
		BinaryNode<String> root = new BinaryNode<String>("Wings attached?");
		BinaryNode<String> wheels = new BinaryNode<String>("Wheels attached?");
		root.setLeft(wheels);
		root.setRight(new BinaryNode<String>("Don't Fly!"));

		BinaryNode<String> water = new BinaryNode<String>("Water plane?");
		wheels.setLeft(new BinaryNode<String>("Fly!"));
		wheels.setRight(water);

		water.setLeft(new BinaryNode<String>("Fly!"));
		water.setRight(new BinaryNode<String>("Don't Fly!"));

		decisions = new BinaryTree<String>(root);
	}
}
