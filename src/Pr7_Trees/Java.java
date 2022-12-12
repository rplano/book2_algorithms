import java.util.StringTokenizer;

import tree.AbstractNode;
import tree.OrderedNode;
import tree.OrderedTree;
import tree.TreePrinter;
import tree.VisitorInterface;
import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Java
 * 
 * Turn a simple Java program into a tree.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Java extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-18");

		String code = "" + "class Karel {  " + "  run() {" + "    move();"
				+ "    move();" + "    turnAround();" + "  }"
				+ "  turnAround() {" + "    turnLeft();" + "    turnLeft();"
				+ "  }" + "}";

		OrderedNode<String> nodes = parseJava(code);
		OrderedTree<String> tree = new OrderedTree<>(nodes);

		// new TreePrinter().prettyPrintSimpleVertical(tree);
		// new TreePrinter().prettyPrintSimpleHorizontal(tree);
		try {
			new TreePrinter().prettyPrint(tree);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(createJava(tree));
	}

	private String java;

	private String createJava(OrderedTree<String> tree) {
		java = "";
		levelOrder(tree.root(), 2);
		return java;
	}

	private void levelOrder(AbstractNode<String> node, int level) {
		if (node == null) {
			return;
		}

		if (level == 2) { // class level
			java += node.getElement() + " {\n";
			for (AbstractNode<String> child : node.getChildren()) {
				levelOrder(child, level - 1);
			}
			java += "}\n";

		} else if (level == 1) { // method level
			java += " " + node.getElement() + " {\n";
			for (AbstractNode<String> child : node.getChildren()) {
				levelOrder(child, level - 1);
			}
			java += " }\n";

		} else if (level == 0) { // statement level
			java += "  " + node.getElement() + ";\n";
		}
	}

	private OrderedNode<String> parseJava(String code) {
		StringTokenizer st = new StringTokenizer(code, "{};", true);

		// first token is class name:
		String token = st.nextToken().trim();
		OrderedNode<String> root = new OrderedNode<String>(token);
		OrderedNode<String> currentNode = root;

		boolean classLevel = false;
		while (st.hasMoreTokens()) {
			token = st.nextToken().trim();
			if (token.length() > 0) {
				// System.out.println(token);
				switch (token) {
				case "{":
					classLevel = !classLevel;
					break;
				case "}":
					currentNode = (OrderedNode<String>) currentNode.getParent();
					classLevel = !classLevel;
					break;
				case ";":
					break;
				default:
					// System.out.println(token);
					if (classLevel) {
						OrderedNode<String> tmp = new OrderedNode<String>(token);
						currentNode.addChild(tmp);
						currentNode = tmp;
						// classLevel= !classLevel;
					} else {
						// System.out.println(token);
						currentNode.addChild(new OrderedNode<String>(token));
					}
					break;
				}
			}
		}
		return root;
	}

}
