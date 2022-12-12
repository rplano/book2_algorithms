import java.util.Stack;
import java.util.StringTokenizer;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * ArithmeticExpression
 * 
 * Converts an infix expression to a postfix expression which it then evaluates.
 * 
 * ( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) ) = 212
 * 
 * ( ( 1 + ( 2 + 3 ) ) * ( ( 4 + 3 ) / 2 ) ) = 18
 * 
 * ( 2 * ( 2 - 3 ) ) = -2
 * 
 * ( 1 + ((3 * 5) / 2 )) = 8
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class ArithmeticExpression extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("monospaced-bold-20");

		String infix = readLine("Enter expression, like (2*(2-3)): ");
		String postfix = infixToPostfix(infix);
		println("Postfix: " + postfix);
		int result = evaluatePostfix(postfix);
		println("Result: " + result);
	}

	/**
	 * 1. read one token at a time; 2. if it is an integer, push it on the
	 * stack; 3. if it is an operator, pop the two topmost elements from the
	 * stack and apply the opertator to them, put the result back on the stack;
	 * 4. at the end, only one element should remain on the stack, the result.
	 */
	public int evaluatePostfix(String postfix) {
		Stack<Integer> stack = new Stack<Integer>();
		StringTokenizer st = new StringTokenizer(postfix, "+-*/ ", true);
		while (st.hasMoreTokens()) {
			String token = st.nextToken().trim();
			if (token.length() == 0) {
				// do nothing
			} else if ("+-*/".contains(token)) {
				int y = stack.pop();
				int x = stack.pop();
				if (token.equalsIgnoreCase("+")) {
					stack.push(x + y);
				} else if (token.equalsIgnoreCase("-")) {
					stack.push(x - y);
				} else if (token.equalsIgnoreCase("*")) {
					stack.push(x * y);
				} else if (token.equalsIgnoreCase("/")) {
					stack.push(x / y);
				}
			} else {
				stack.push(Integer.parseInt(token));
			}
		}
		return stack.pop();
	}

	/**
	 * 1. read one token at a time; 2. if it is an operator push it on the
	 * stack; 3. if it is an integer, add it to the out string; 4. if it is a
	 * right parenthesis, pop the top most element from the stack and add it to
	 * the out string; 5. if it is a left parenthesis, do nothing.
	 */
	public String infixToPostfix(String infix) {
		String out = "";
		Stack<String> stack = new Stack<String>();
		StringTokenizer st = new StringTokenizer(infix, "()+-*/ ", true);
		while (st.hasMoreTokens()) {
			String token = st.nextToken().trim();
			if (token.length() == 0) {
				// do nothing
			} else if ("+-*/".contains(token)) {
				stack.push(token);
			} else if (")".contains(token)) {
				out += stack.pop() + " ";
			} else if ("(".contains(token)) {
				// do nothing
			} else {
				out += token + " ";
			}
		}
		return out;
	}

}
