import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * CountingMoney
 * 
 * Counting Money with a Greedy algorithm using two arrays.
 * 
 * Try to implement an automatic coffee machine. Assume the machine has been
 * pre-filled with a certain number coins of different types, for instance, four
 * 5-cent coins, fifteen 10-cent coins, two 20-cent coins, one 50-cent coin, two
 * 1-euro coins and three 2-euro coins. Assume a cup of coffee costs 1.15 euro.
 * Have the user enter different amounts of money, and make sure you always
 * return the exact change, or give the user her money back.
 * 
 * TODO:<br/>
 * - the coins entered (i.e. by paying for the coffee) should be added to the
 * coins map. <br/>
 * - should check if the machine has enough money to give change <br/>
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class CountingMoney extends ConsoleProgram {
	private final int COST_OF_COFFEE = 115;
	// four 5-cent coins
	// fifteen 10-cent coins,
	// two 20-cent coins,
	// one 50-cent coin,
	// two 1-euro coins,
	// three 2-euro coins
	private int[] coinNumbers = { 3, 2, 1, 2, 15, 4 };
	private int[] coinValues = { 200, 100, 50, 20, 10, 5 };

	public void run() {
		setSize(400, 300);
		setFont("Times New Roman-bold-18");

		// for testing:
		printMap();

		int amount = askUserForMoney();
		if ((amount - COST_OF_COFFEE) >= 0) {
			giveChange(amount - COST_OF_COFFEE);
		} else {
			println("The amount you entered is not sufficient!");
		}
		// for testing:
		printMap();
	}

	/**
	 * Returns the coins using a greedy algorithm.
	 * 
	 * @param amount
	 */
	private void giveChange(int amount) {
		print("Your change: ");
		// go through all the coins, starting with 2 euro:
		for (int i = 0; i < coinValues.length; i++) {
			while ((coinNumbers[i] > 0) && ((amount - coinValues[i]) >= 0)) {
				amount = amount - coinValues[i];
				coinNumbers[i]--;
				print(coinValues[i] + "¢, ");
			}
		}
		println("\n");
	}

	/**
	 * Simply asks the user to enter amount for coffee. Should be a number.
	 * 
	 * @return
	 */
	private int askUserForMoney() {
		while (true) {
			String sAmount = readLine("Enter amount in cent (e.g. 200): ");
			try {
				int amount = Integer.parseInt(sAmount);
				return amount;
			} catch (Exception e) {
				println("  You must enter a number!");
			}
		}
	}

	/**
	 * this is for testing only, to see that the map has changed as expeceted.
	 */
	private void printMap() {
		println("Available coins:");
		for (int i = 0; i < coinValues.length; i++) {
			print(coinValues[i] + "¢: " + coinNumbers[i] + "; ");
		}
		println("\n");
	}
}
