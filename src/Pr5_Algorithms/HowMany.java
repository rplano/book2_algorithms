import java.text.DecimalFormat;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * HowMany
 * 
 * Calculate values for the following mathematical functions <br/>
 * - log(n) <br/>
 * - n <br/>
 * - n * log(n) <br/>
 * - n² <br/>
 * - 2^n <br/>
 * - n! <br/>
 * 
 * for the values n = 2, 4, 8, 16, 32, 64, 128, 256, 512 and 1024.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class HowMany extends ConsoleProgram {

	public void run() {
		setSize(400, 300);
		setFont("monospaced-12");
		println("  n  |log(n)|  n  |nlog(n)|   n^2 |   2^n  |    n!  |");
		println("-----+------+-----+-------+-------+--------+--------+");
		for (int i = 1; i < 11; i++) {

			// n
			int n = (int) Math.pow(2, i);
			print(padWithSpaces("" + n, 4) + " |");

			// log(n)
			int log2_n = (int) log_2(n);
			print(padWithSpaces("" + log2_n, 5) + " |");

			// n
			print(padWithSpaces("" + n, 4) + " |");

			// n * log(n)
			int nlog2_n = (int) (n * log_2(n));
			print(padWithSpaces("" + nlog2_n, 6) + " |");

			// n^2
			int n2 = n * n;
			print(padWithSpaces("" + n2, 7) + "|");

			// 2^n
			double twoToTheN = Math.pow(2, n);
			DecimalFormat formatter = new DecimalFormat("0.00E00");
			String tmp = formatter.format(twoToTheN);
			print(padWithSpaces("" + tmp, 8) + "|");

			// n! 
			double nFactorial = stirling(n);
			tmp = formatter.format(nFactorial);
			print(padWithSpaces("" + tmp, 8) + "|");
			
			println();
		}
	}

	private String padWithSpaces(String what, int nr) {
		String tmp = what;
		while (tmp.length() < nr) {
			tmp = " " + tmp;
		}
		return tmp;
	}

	private double log_2(double x) {
		return Math.log(x) / Math.log(2);
	}
	
	public double stirling(int n) {
		double factorial = Math.sqrt(2.0 * Math.PI * n)
				* Math.pow(n / Math.E, n);
		return factorial;
	}
}
