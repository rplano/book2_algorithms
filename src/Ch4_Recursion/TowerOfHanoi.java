import java.util.*;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * A console application demonstrating the tower of Hanoi 
 * example for different number of disks.
 * 
 * @author Berit Kreibich
 */
public class TowerOfHanoi {

	private int totalNrOfMoves = 0;
	private int nrOfDisks;
	private Stack<Integer>[] tower = new Stack[3];

	public TowerOfHanoi(int nrOfDisks) {
		this.nrOfDisks = nrOfDisks;
		
		tower[0] = new Stack<Integer>();
		tower[1] = new Stack<Integer>();
		tower[2] = new Stack<Integer>();

		startTower();

		System.out.println("It took " + totalNrOfMoves
				+ " moves to move the Tower of Hanoi");
	}

	private void startTower() {
		for (int i = nrOfDisks; i > 0; i--) {
			tower[0].push(i);
		}
		displayTower();
		moveTower(nrOfDisks, 0, 1, 2);
	}

	private void moveTower(int n, int source, int destination, int temp) {
		if (n > 0) {
			moveTower(n - 1, source, temp, destination);
			moveOneDisk(source, temp);
			displayTower();
			moveTower(n - 1, destination, source, temp);

			totalNrOfMoves++;
		}
	}

	private void moveOneDisk(int s, int t) {
		int x = tower[s].pop();
		tower[t].push(x);
	}

	private void displayTower() {

		for (int i = (nrOfDisks - 1); i >= 0; i--) {
			String tower1 = " ";
			String tower2 = " ";
			String tower3 = " ";
			try {
				tower1 = ""+ tower[0].get(i);
			} catch (Exception e) {
			}

			try {
				tower2 = ""+ tower[1].get(i);
			} catch (Exception e) {
			}

			try {
				tower3 = ""+ tower[2].get(i);
			} catch (Exception e) {
			}

			System.out.println("    " + tower1 + "    |    " + tower2
					+ "    |    " + tower3);

		}
		System.out.println("-----------------------------");
		System.out.println(" Tower 1 | Tower 2 | Tower 3 ");
		System.out.println("\n");
	}

	public static void main(String[] args) {
		System.out.println("Number of disks?");
		Scanner scan = new Scanner(System.in);
		int nrOfDisks = scan.nextInt();
		TowerOfHanoi toh = new TowerOfHanoi( nrOfDisks );
	}
}