import java.awt.Color;
import java.util.Stack;

import acm.graphics.*;
import acm.io.IODialog;
import acm.program.*;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * TowerOfHanoiGraphic
 * 
 * A graphics application demonstrating the Tower of Hanoi example for different
 * number of disks.
 *      
 * @see http://www.VariationenZumThema.de/
 * @author Berit Kreibich and Ralph P. Lano
 */
public class TowerOfHanoiGraphic extends GraphicsProgram {
	public final int WIDTH = 400;
	public final int HEIGHT = 200;

	public final int DELAY = 1000;
	public final int MIN_DISK_WIDTH = 10;
	public final int MAX_DISK_WIDTH = 100;
	public final int HOLDER_THICKNESS = 10;
	public final int HOLDER_HEIGHT = 70;
	public final Color[] DISK_COLORS = { Color.GREEN, Color.CYAN, Color.BLUE,
			Color.MAGENTA, Color.RED, Color.ORANGE, Color.YELLOW, Color.WHITE,
			Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK };

	private int diskHeight;
	private int yAllTowers;
	private int xLeftTower;
	private int xDistanceTowers;

	private int totalNrOfMoves = 0;
	private int nrOfDisks = 3; // max is 10
	private Stack<Integer>[] tower = new Stack[3];

	public void init() {
		setSize(WIDTH, HEIGHT);

		IODialog dialog = new IODialog();
		while (true) {
			nrOfDisks = dialog.readInt("Enter the number of disks: ");
			if ((nrOfDisks > 2) && (nrOfDisks < 11))
				break;
		}

		yAllTowers = (getHeight() - getHeight() / 3);
		xDistanceTowers = getWidth() / 4;
		xLeftTower = getWidth() / 2 - xDistanceTowers;

		tower[0] = new Stack<Integer>();
		tower[1] = new Stack<Integer>();
		tower[2] = new Stack<Integer>();
	}

	public void run() {
		diskHeight = HOLDER_HEIGHT / (nrOfDisks) - 2;
		drawScaffoldForTowers();
		displayStartTower();

		recursivelyMoveDisks(nrOfDisks, 0, 1, 2);
	}

	private void recursivelyMoveDisks(int n, int s, int d, int t) {
		if (n > 0) {
			recursivelyMoveDisks(n - 1, s, t, d);
			int x = tower[s].pop();
			tower[t].push(x);
			displayTowers();
			recursivelyMoveDisks(n - 1, d, s, t);
			totalNrOfMoves++;
		}
	}

	private void displayTowers() {
		pause(DELAY);
		removeAll();
		drawScaffoldForTowers();
		for (int i = 0; i < 3; i++) {
			drawOneTower(i);
		}
	}

	private void displayStartTower() {
		for (int i = nrOfDisks; i > 0; i--) {
			tower[0].push(i);
		}
		drawOneTower(0);
	}

	private void drawOneTower(int i) {
		int diskSize = MAX_DISK_WIDTH / nrOfDisks;
		for (int j = 0; j < tower[i].size(); j++) {
			int diskNr = tower[i].get(j);
			drawOneDisc(xLeftTower + i * xDistanceTowers, yAllTowers - (j + 1)
					* diskHeight - 2 * j, diskNr * diskSize,
					DISK_COLORS[diskNr]);
		}
	}

	private void drawOneDisc(int x, int y, int width, Color col) {
		GRect disc = new GRect(x - width / 2, y, width, diskHeight);
		disc.setFilled(true);
		disc.setFillColor(col);
		add(disc);
	}

	private void drawScaffoldForTowers() {
		GRect baseLine = new GRect(xLeftTower - MAX_DISK_WIDTH / 2, yAllTowers,
				2 * xDistanceTowers + MAX_DISK_WIDTH, 2);
		baseLine.setColor(Color.BLACK);
		add(baseLine);

		drawHoldersForTowers(xLeftTower, yAllTowers);
		drawHoldersForTowers(xLeftTower + xDistanceTowers, yAllTowers);
		drawHoldersForTowers(xLeftTower + 2 * xDistanceTowers, yAllTowers);
	}

	private void drawHoldersForTowers(int x, int y) {
		GRect holder = new GRect(x - HOLDER_THICKNESS / 2, y - HOLDER_HEIGHT,
				HOLDER_THICKNESS, HOLDER_HEIGHT);
		holder.setColor(Color.BLACK);
		holder.setFilled(true);
		add(holder);
	}
}