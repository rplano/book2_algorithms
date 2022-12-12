import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Benford
 * 
 * Loads stock prices from SP500_HistoricalStockDataMonthly.csv and checks if
 * they follow Benford's law.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Benford extends ConsoleProgram {
	
	private Map<String, StockEntry> stockDB = new HashMap<String, StockEntry>();

	public void run() {
		setSize(400, 300);
		setFont("MonoSpaced-bold-20");
		
		loadStockPrices("SP500_HistoricalStockDataMonthly.csv");
		
		double[] percentages = analyze();
		
		for (int i = 1; i < percentages.length; i++) {
			println(i + ": " + String.format("%02.1f", percentages[i]));
		}
	}

	public double[] analyze() {
		double total = 0;
		double[] counts = new double[10];
		for (String stock : stockDB.keySet()) {
			StockEntry ent = stockDB.get(stock);
			List<Double> prices = ent.getPrices();
			for (int i = 0; i < prices.size(); i++) {
				double price = prices.get(i);
				char c = String.valueOf(price).charAt(0);
				if (Character.isDigit(c)) {
					counts[c - '0']++;
					total++;
				}
			}
		}
		for (int i = 0; i < counts.length; i++) {
			counts[i] = counts[i] * 100 / total;
			System.out.println(i + ": " + String.format("%02.3f", counts[i]));
		}
		return counts;
	}

	public void loadStockPrices(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));

			// first line contains dates:
			String line = br.readLine();

			// other lines contain data:
			readStockPrices(br);

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readStockPrices(BufferedReader br) throws IOException {
		String line;
		while (true) {
			line = br.readLine();
			if (line == null)
				break;
			StockEntry entry = new StockEntry(line);
			stockDB.put(entry.getSymbol(), entry);
		}
	}

	class StockEntry {
		private String symbol;
		private List<Double> prices;

		public StockEntry(String line) {
			String[] sVals = line.split(",");
			symbol = sVals[0];
			prices = new ArrayList<Double>();
			for (int i = 1; i < sVals.length; i++) {
				if (sVals[i].equals("null")) {
					prices.add(-1.0);
				} else {
					prices.add(Double.parseDouble(sVals[i]));
				}
			}
		}

		public String getSymbol() {
			return symbol;
		}

		public List<Double> getPrices() {
			return prices;
		}

		public String toString() {
			String s = "StockEntry [symbol=" + symbol + ", prices=";
			for (int i = 0; i < 10; i++) {
				s += prices.get(i) + ",";
			}
			s += "...]";
			return s;
		}
	}
}
