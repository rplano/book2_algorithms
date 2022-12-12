import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * CountingMoney2
 * 
 * Counting Money with a Greedy algorithm using a TreeMap.
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
public class CountingMoney2 extends ConsoleProgram {
	private final int COST_OF_COFFEE = 115;
	private Map<Integer, Integer> coins;

	public void run() {
		setSize(400, 300);
		setFont("Times New Roman-bold-18");

		initMap();

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
		for (int coin : coins.keySet()) {
			int nrOfCoins = coins.get(coin);
			while ((nrOfCoins > 0) && ((amount - coin) >= 0)) {
				amount = amount - coin;
				nrOfCoins--;
				print(coin + "¢, ");
			}
			coins.put(coin, nrOfCoins);
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
	 * initialize the coins map.
	 */
	private void initMap() {
		// this is a dirty trick, to reverse the order the treemap is traversed:
		coins = new TreeMap<Integer, Integer>(Collections.reverseOrder());
		coins.put(5, 4); // four 5-cent coins
		coins.put(10, 15); // fifteen 10-cent coins,
		coins.put(20, 2); // two 20-cent coins,
		coins.put(50, 1); // one 50-cent coin,
		coins.put(100, 2); // two 1-euro coins,
		coins.put(200, 3); // three 2-euro coins
	}

	/**
	 * this is for testing only, to see that the map has changed as expeceted.
	 */
	private void printMap() {
		println("Available coins:");
		for (int coin : coins.keySet()) {
			System.out.println(coin + " coins: " + coins.get(coin));
			print(coin + "¢: " + coins.get(coin) + "; ");
		}
		println("\n");
	}
}
