import java.util.Stack;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Palindrome
 * 
 * Reverses a string using the Stack class and checks if the given word is a
 * palindrome.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Palindrome extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-24");

		String text = readLine("Enter a word: ");
		println("The word is a palindrome: " + isPalindrome(text));
	}

	private boolean isPalindrome(String text) {
		String revers = reverseString(text);
		return revers.equals(text);
	}

	private String reverseString(String text) {
		Stack<Character> st = new Stack<Character>();
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			st.push(c);
		}

		String revers = "";
		while (!st.isEmpty()) {
			char c = st.pop();
			revers = revers + c;
		}
		return revers;
	}

}
