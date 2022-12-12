import tree.AbstractNode;
import tree.OrderedNode;
import tree.OrderedTree;
import tree.VisitorInterface;
import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * University
 * 
 * Shows how to build a tree respresenting the hierarchies at a univerity.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class University extends ConsoleProgram {
	private static final int WIDTH = 400;
	private static final int HEIGHT = 500;

	public void run() {
		setSize(WIDTH, HEIGHT);
		setFont("Times New Roman-bold-18");

		// first, create nodes
		OrderedNode<String> president = new OrderedNode<String>("president");
		OrderedNode<String> dean_A = new OrderedNode<String>("dean_A");
		OrderedNode<String> dean_B = new OrderedNode<String>("dean_B");
		OrderedNode<String> prof_A = new OrderedNode<String>("prof_A");
		OrderedNode<String> prof_B = new OrderedNode<String>("prof_B");
		OrderedNode<String> prof_C = new OrderedNode<String>("prof_C");
		OrderedNode<String> tutor_A = new OrderedNode<String>("tutor_A");
		OrderedNode<String> tutor_B = new OrderedNode<String>("tutor_B");
		OrderedNode<String> tutor_C = new OrderedNode<String>("tutor_C");

		// then connect them
		president.addChild(dean_A);
		president.addChild(dean_B);
		dean_A.addChild(prof_A);
		dean_B.addChild(prof_B);
		dean_B.addChild(prof_C);
		prof_A.addChild(tutor_A);
		prof_B.addChild(tutor_B);
		prof_B.addChild(tutor_C);

		// finally wrap it in a tree
		OrderedTree<String> university = new OrderedTree<String>(president);

		// how many people work here
		println("Number of employees: " + university.size());

		// number of hierarchy levels
		println("Number of hierarchy levels: " + university.height());

		// list all employees
		println("List of all employees: " + university.elements());

		// boss of prof_B
		println("Boss of prof_B: " + prof_B.getParent().getElement());

		// employees of dean_B
		println("Employees of dean_B: " + dean_B.getChildren());

		// president is big boss
		println("Big boss is president: " + president.isRoot());

		// print hierarchy levels
		println("Hierarchy: ");
		university.levelOrder(new VisitorInterface<String>() {
			public void visit(AbstractNode<?> node) {
				print(node.getElement() + ", ");
			}
		});
	}

}
