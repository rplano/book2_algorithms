import java.util.Collections;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Knapsack
 * 
 * A master thief (Daniel "Danny" Ocean) wants to rob the Louvre. His team of
 * thieves can only carry a total of 300kg. But naturally he wants to maximize
 * his profit. He hired you as a computer expert to figure out which art works
 * to rob so that he maximizes his profit. You may want to use a greedy
 * algorithm to solve this problem.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Knapsack extends ConsoleProgram {

	private String[] piecesOfArt = { "Venus de Milo,315,20000000",
			"Mona Lisa,10,9000000", "Liberty Leading People,14,2000000",
			"Psyche Revived By the Kiss of Love,545,5000000",
			"Oedipus and the Sphinx,23,4000000",
			"The Raft of the Medusa,9,3000000", "Milon de Crotone,332,4000000",
			"Louis XIV (1638-1715),17,100000", "Death of the Virgin,32,50000",
			"Cy Twombly's Ceiling,10000,10000" };

	private Map<Double, Valuable> valuables;

	public void run() {
		setSize(400, 300);
		setFont("Times New Roman-bold-18");

		initMap();

		println("Take the following items:");
		double weight = 0;
		double value = 0;
		for (double key : valuables.keySet()) {
			Valuable val = valuables.get(key);
			if (weight + val.weight <= 300.0) {
				weight += val.weight;
				value += val.price;
				println("- " + val);
			}
		}
		println("\nTotal weight: " + weight + "kg, total value: €" + value);
	}

	/**
	 * initialize the valuables map.
	 */
	private void initMap() {
		// this is a dirty trick, to reverse the order the treemap is traversed:
		valuables = new TreeMap<Double, Valuable>(Collections.reverseOrder());
		for (int i = 0; i < piecesOfArt.length; i++) {
			StringTokenizer st = new StringTokenizer(piecesOfArt[i], ",");
			String name = st.nextToken().trim();
			double weight = Double.parseDouble(st.nextToken().trim());
			double price = Double.parseDouble(st.nextToken().trim());
			Valuable valubl = new Valuable(name, weight, price);
			valuables.put(price / weight, valubl);
			// valuables.put(price, valubl);
		}
	}

	private class Valuable {
		protected String name;
		protected double weight;
		protected double price;

		public Valuable(String name, double weight, double price) {
			super();
			this.name = name;
			this.weight = weight;
			this.price = price;
		}

		@Override
		public String toString() {
			return name + ": " + weight + "kg, €" + price + "";
		}

	}
}
