import java.util.Stack;
import java.util.StringTokenizer;

import tree.BinaryNode;
import tree.BinaryTree;
import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * ArithmeticExpression
 * 
 * Builds an arithmetic expression tree from a string, evaluates it, and prints
 * it in a nice way. Showing how to use a stack to parse, post-order traversal
 * to evaluate, and in-order traversal to print an arithmetic expression tree.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class ArithmeticExpression extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		// setFont("Times New Roman-bold-18");
		setFont("Courier New-bold-18");

		String ariExp = readLine("Enter arithmetic expression, like 5 + (( 3 - 4 ) * 2) - (3 * 4): ");

		// parse
		BinaryTree<String> tree = new BinaryTree<String>(
				buildArithmeticExpression(ariExp));

		// evaluate
		int result = evaluate(tree);
		println("The result is: " + result);

		// print as string
		printExpression((BinaryNode<String>) tree.root());

		// print as tree
		String tmp[][] = new tree.TreePrinter().prettyPrintSimple(tree);
		printTreeVertical(tmp);
	}

	private void printExpression(BinaryNode<String> p) {
		if (p.hasLeft()) {
			print("(");
			printExpression(p.getLeft());
		}
		print(p.getElement());
		if (p.hasRight()) {
			printExpression(p.getRight());
			print(")");
		}
	}

	private int evaluate(BinaryTree<String> tree) {
		return evaluate((BinaryNode<String>) tree.root());
	}

	private int evaluate(BinaryNode<String> node) {
		if (node.isInternal()) {
			int x = evaluate(node.getLeft());
			int y = evaluate(node.getRight());
			String operator = node.getElement();
			switch (operator) {
			case "+":
				return x + y;
			case "-":
				return x - y;
			case "*":
				return x * y;
			case "/":
				return x / y;

			default:
				System.out.println("we should never get here! operator is "
						+ operator);
				break;
			}
			return -1;
		} else {
			return Integer.parseInt(node.getElement());
		}
	}

	// note this does not do operator precedence!
	private BinaryNode<String> buildArithmeticExpression(String ariExp) {
		Stack<BinaryNode<String>> stackOfTrees = new Stack<BinaryNode<String>>();

		StringTokenizer st = new StringTokenizer(ariExp, " ()+-*/", true);
		while (st.hasMoreTokens()) {
			String tok = st.nextToken();
			switch (tok) {
			case "(":
			case " ":
				// do nothing
				break;
			case ")":
				BinaryNode<String> operand1 = stackOfTrees.pop();
				BinaryNode<String> operator = stackOfTrees.pop();
				BinaryNode<String> operand2 = stackOfTrees.pop();
				operator.setLeft(operand2);
				operator.setRight(operand1);
				stackOfTrees.push(operator);
				break;
			default:
				BinaryNode<String> node = new BinaryNode<String>(tok.trim());
				stackOfTrees.push(node);
				break;
			}
		}
		// check if we need to do some reduction:
		while (stackOfTrees.size() > 1) {
			BinaryNode<String> operand1 = stackOfTrees.pop();
			BinaryNode<String> operator = stackOfTrees.pop();
			BinaryNode<String> operand2 = stackOfTrees.pop();
			operator.setLeft(operand2);
			operator.setRight(operand1);
			stackOfTrees.push(operator);
		}
		return stackOfTrees.pop();
	}

	private void printTreeVertical(String[][] tmp) {
		for (int i = 0; i < tmp.length; i++) { // 4
			for (int j = 0; j < tmp[0].length; j++) { // 7
				// System.out.println();
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
