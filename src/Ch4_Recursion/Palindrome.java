import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Palindrome
 * 
 * Checks if a given string is a palindrome.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Palindrome extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-24");

		String tmp = readLine("Enter a word: ");
		if (isPalindrome(tmp)) {
			println(tmp + " is a palindrome");			
		} else {
			println(tmp + " is not a palindrome");
		}
	}

	private boolean isPalindrome(String s) {
		// base case
		if (s.length() <= 1) {
			return true;

			// recursive case
		} else {
			if (s.charAt(0) == s.charAt(s.length() - 1)) {
				return isPalindrome(s.substring(1, s.length() - 1));

			} else {
				return false;

			}
		}
	}
}
