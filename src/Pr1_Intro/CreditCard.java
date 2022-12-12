import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * CreditCard
 * 
 * The Luhn algorithm is used to see if credit card numbers contain errors.
 * 
 * Test these: 49927398716, 1234567812345670, 79927398713
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class CreditCard extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-24");

		String creditNumber = readLine("Enter credit card number (79927398713): ");
		boolean correct = checkCreditCardNumber(creditNumber);
		if (correct) {
			println("Number is correct.");
		} else {
			println("You got screwed!");
		}
	}

	private boolean checkCreditCardNumber(String creditNumber) {
		int sum = 0;
		int len = creditNumber.length();
		for (int i = 0; i < len; i++) {
			int x = creditNumber.charAt(i) - '0'; 	// turn char in to int
			int y = x * (2 - (i + len) % 2);		// multiply by two every other 
			if (y > 9) {
				y -= 9;
			}
			sum += y;
		}
		return sum % 10 == 0;
	}
}
