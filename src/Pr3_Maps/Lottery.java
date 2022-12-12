import java.util.Set;
import java.util.TreeSet;

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Lottery
 * 
 * Generate 6 unique random numbers (no duplicates) between 1 and 49 using a Set.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Lottery extends ConsoleProgram {
	private RandomGenerator rgen = RandomGenerator.getInstance();

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-24");
		
		Set<Integer> nrs = generateLotteryNumbers();
		
		for (int i : nrs) {
			print(i + ", ");
		}
	}

	private Set<Integer> generateLotteryNumbers() {
		Set<Integer> nrs = new TreeSet<Integer>();
		
		while ( nrs.size() < 6 ) {
			int r = rgen.nextInt(1, 49);
			nrs.add(r);
		}		

		return nrs;
	}
}
