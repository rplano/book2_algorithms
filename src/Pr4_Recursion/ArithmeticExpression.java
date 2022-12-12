import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * ArithmeticExpression
 * 
 * Evaluate arithmetic expressions recursively.
 *      
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class ArithmeticExpression extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-24");

		String expression = readLine("Enter an expression, e.g., 1 + 3 * 5 / 2:");
		println(evaluate(expression));

		//try 1 + 3 * 5 / 2 = 8
		//try 100 / 10 / 10 = 1
	}

	private int evaluate(String expression) {
		// base case
		if (!expression.contains("+") && !expression.contains("-") && !expression.contains("*")
				&& !expression.contains("/")) {
			return Integer.parseInt(expression.trim());
		}

		// recursive case
		int i = findPlusAndMinus(expression);
		if (i < 0) {
			i = findTimesAndDivideBy(expression);
		}

		String o1 = expression.substring(0, i);
		String o2 = expression.substring(i + 1, expression.length());

		int result = 0;

		switch (expression.charAt(i)) {
		case '+':
			result = evaluate(o1) + evaluate(o2);
			break;
		case '-':
			result = evaluate(o1) - evaluate(o2);
			break;
		case '*':
			result = evaluate(o1) * evaluate(o2);
			break;
		case '/':
			int right = evaluate(o2);
			if (right == 0) { // if denominator is zero
				throw new ArithmeticException("divide by zero");
			} else {
				result = evaluate(o1) / right;
			}
			break;
		}
		return result;
	}

	private int findTimesAndDivideBy(String expression) {
		int i;
		for (i = expression.length() - 1; i >= 0; i--) {
			if (expression.charAt(i) == '*' || expression.charAt(i) == '/') {
				break;
			}
		}
		return i;
	}
	
	private int findPlusAndMinus(String expression) {
		int i;
		for (i = expression.length() - 1; i >= 0; i--) {
			if (expression.charAt(i) == '+' || expression.charAt(i) == '-') {
				break;
			}
		}
		return i;
	}
}
