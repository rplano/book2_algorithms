import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * ISBN
 * 
 * This algorithm checks if a given ISBN is valid.
 * 
 * Test these: 0306406152, 1530651840, 1537765469, 111111112X
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class ISBN extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-24");

		String creditNumber = readLine("Enter ISBN number (1530651840): ");
		boolean correct = checkISBNNumber(creditNumber);
		if (correct) {
			println("Number is correct.");
		} else {
			println("Number is wrong.");
		}
	}

	private boolean checkISBNNumber(String isbnNumber) {
		int s = 0;
		int t = 0;

		char[] arr = isbnNumber.toCharArray();
		for (int i = 0; i < 9; i++) {
			t = arr[i] - '0';
			s += t * (i + 1);
		}

		// last digit could be a 10, i.e. an 'X':
		if (arr[9] == 'X') {
			s += 10 * 10;
		} else {
			t = arr[9] - '0';
			s += t * 10;
		}

		return (s % 11 == 0);
	}
}
