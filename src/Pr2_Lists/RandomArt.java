import java.awt.Color;
import java.util.Stack;

import acm.graphics.GLine;
import acm.program.GraphicsProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * RandomArt
 * 
 * If we compute the value at any point with −1 ≤ x, y ≤ 1, the result will also
 * be between −1 and 1. By scaling the answer to a grayscale value 0 to 255, we
 * can plot the function in this 2-by-2 square. (From three such expressions, we
 * can get red, green, and blue values for each point.) Surprisingly, every
 * sufficiently complicated expression has a decent chance of being interesting!
 * We can generate deeply-nested expressions completely randomly, see how they
 * look, and keep the good ones.
 * 
 * Based on http://nifty.stanford.edu/2009/stone-random-art/sml/index.html
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class RandomArt extends GraphicsProgram {
	private static final int SIZE = 300;
	private static final int WIDTH = SIZE;
	private static final int HEIGHT = SIZE + 44;
	private static final double pi = Math.PI;

	public void run() {
		setSize(WIDTH, HEIGHT);

		String postfix = findGoodPostfix();

		drawArt(postfix);
	}

	private String findGoodPostfix() {
		String infix = "";
		while (true) {
			infix = createMathFunction();
			if ((infix.length() > 50) && (infix.length() < 200))
				break;
		}
		String postfix = convertFromInfixToPostfix(infix);
		return postfix;
	}

	/**
	 * Go through all positions on the screen and evaluate the mathematical
	 * function (postfix expression) at each location. Make sure to scale x and
	 * y, such that they are between -1 and +1.
	 */
	private void drawArt(String postfix) {
		double step = 2.0 / SIZE;
		long startTime = System.currentTimeMillis();
		for (double x = -1.0; x < 1.0; x += step) {
			for (double y = -1.0; y < 1.0; y += step) {
				double col = evaluate(postfix, x, y);
				setPixel(x, y, col);
			}
		}
	}

	/**
	 * Evaluates a postfix expression. The rules are: <br/>
	 * 1) read the tokens one at a time; <br/>
	 * 2) if it is an 'x' or a 'y', push it on the stack; <br/>
	 * 3) if it is a 'c', 's', '*' or ',' then pop the top two elements from the
	 * stack, apply the operator to the two elements, and push the result back
	 * on the stack. <br/>
	 * At the end the last element on the stack is the results.
	 * 
	 * @param postfix
	 *            xyycy,s*yc,cx,x,cys,*yc,
	 */
	private double evaluate(String postfix, double x, double y) {
		Stack<Double> st = new Stack<Double>();
		for (int i = 0; i < postfix.length(); i++) {
			char c = postfix.charAt(i);
			switch (c) {
			case 'x':
				st.push(x);
				break;
			case 'y':
				st.push(y);
				break;
			case 'c':
				double s = st.pop();
				double r = Math.cos(pi * s);
				st.push(r);
				break;
			case 's':
				s = st.pop();
				r = Math.sin(pi * s);
				st.push(r);
				break;
			case ',':
				double s1 = st.pop();
				double s2 = st.pop();
				r = (s1 + s2) / 2.0;
				st.push(r);
				break;
			case '*':
				s1 = st.pop();
				s2 = st.pop();
				r = s1 * s2;
				st.push(r);
				break;
			default:
				println("we should not get here: " + c);
				break;
			}
		}

		return st.pop();
	}

	/**
	 * Converts am infix expression into a postfix expression. The rules are: <br/>
	 * 1) read the tokens one at a time; <br/>
	 * 2) if it is an '(' do nothing; <br/>
	 * 3) if it is a '(' pop the top-most element from the stack and add it to
	 * the out string; <br/>
	 * 4) if it is a 'c', 's', '*' or ',' push it on the stack; <br/>
	 * 5) if it is an 'x' or a 'y', add it to the out string; <br/>
	 * Return the out string, which is the postfix expression.
	 * 
	 * @param infix
	 *            ((x*(c(((c(((y*s((c(y),y))),c(y))),x),x)),s(y))),c(y))
	 * @return xyycy,s*yc,cx,x,cys,*yc,
	 */
	private String convertFromInfixToPostfix(String infix) {
		Stack<String> st = new Stack<String>();
		String out = "";
		for (int i = 0; i < infix.length(); i++) {
			char c = infix.charAt(i);
			switch (c) {
			case '(':
				break;
			case ')':
				out += "" + st.pop();
				break;
			case ',':
			case '*':
			case 's':
			case 'c':
				st.push("" + c);
				break;
			case 'x':
			case 'y':
				out += "" + c;
				break;
			default:
				println("we should not get here: " + c);
				break;
			}
		}

		return out;
	}

	/**
	 * A recursive method, that generates a mathematical expression, consisting
	 * of sin(), cos(), multiplication and average of two numbers.
	 */
	private String createMathFunction() {
		String s = "";
		int key = (int) (Math.random() * 6);
		switch (key) {
		case 0:
			s = "x";
			break;
		case 1:
			s = "y";
			break;
		case 2:
			// cosinus
			s = "c(" + createMathFunction() + ")";
			break;
		case 3:
			// sinus
			s = "s(" + createMathFunction() + ")";
			break;
		case 4:
			// multiplication
			s = "(" + createMathFunction() + "*" + createMathFunction() + ")";
			break;
		case 5:
			// average:
			s = "(" + createMathFunction() + "," + createMathFunction() + ")";
			break;
		}
		return s;
	}

	private void setPixel(double x, double y, double col) {
		int i = (int) ((x + 1.0) * SIZE) / 2;
		int j = (int) ((y + 1.0) * SIZE) / 2;
		// GRect r = new GRect(1, 1);
		GLine r = new GLine(0, 0, 0, 1);
		int color = (int) ((col + 1.0) * 255) / 2;
		r.setColor(new Color(color, color, color));
		add(r, i, j);
	}

}
