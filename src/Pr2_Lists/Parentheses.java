import java.util.Stack;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Parentheses
 * 
 * Write a program that reads parentheses from the console, that returns true if
 * the parantheses match, and false if they do not match.
 * 
 * Try with the following three examples: (()) , (() and ).
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Parentheses extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("monospaced-bold-20");

		println("(()): " + doParenthesesMatch("(())"));
		println("((): " + doParenthesesMatch("(()"));
		println("): " + doParenthesesMatch(")"));
		println("(()(())): " + doParenthesesMatch("(()(()))"));
		println("{([]{()})({})}: " + doParenthesesMatch("{([]{()})({})}"));
		println("[{]}: " + doParenthesesMatch("[{]}"));
	}

	private boolean doParenthesesMatch(String text) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			switch (c) {
			case '(':
				stack.push(c);
				break;
			case ')':
				if (!stack.isEmpty()) {
					stack.pop();
				} else {
					return false;
				}
				break;
			default:
				System.out.println("we should never get here!");
				break;
			}
		}
		// if parenthesis matched, the stack should be empty now:
		if (stack.isEmpty()) {
			return true;
		}
		return false;
	}

	private boolean doParenthesesMatch2(String text) {
		Stack<Character> st = new Stack<Character>();
		try {
			for (int i = 0; i < text.length(); i++) {
				char c = text.charAt(i);
				if ("{[(".indexOf(c) > -1) {
					st.push(c);
				} else {
					char d = st.pop();
					// look at ASCII table:
					if (d == '(') {
						d = (char) (d - 1);
					}
					if (d + 2 != c) {
						return false;
					}
				}
			}
			if (st.isEmpty()) {
				return true;
			}

		} catch (Exception e) {
			return false;
		}
		return false;
	}

}
