import java.awt.Color;
import java.awt.Point;

import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Queens
 * 
 * The n-queens problem comes from chess, the question is, how can you put 8
 * queens on a chess board, so no one is threatened by any of the others. This
 * is also a problem we can solve by back tracking:
 * 
 * Start in the lowest row if all queens are placed, return true for (every
 * possible choice among the columns in this row) if the queen can be placed
 * safely there, make that choice and then recursively try to place the rest of
 * the queens if recursion successful, return true if !successful, remove queen
 * and try another column in this row if all columns have been tried and nothing
 * worked, return false to trigger backtracking
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Queens extends GraphicsProgram {
	private static final int NR_OF_QUEENS = 8;
	private static final int DELAY = 100;

	private Point[] queens = new Point[NR_OF_QUEENS];

	public void run() {
		setSize(400, 400);

		placeQueen(0);

		drawBoard();
		drawQueens();
	}

	// places queen nr. n
	private boolean placeQueen(int n) {

		// our stop condition
		if (n >= NR_OF_QUEENS) {
			return true;
		}

		boolean[][] tmp = markThreatenedFields();
		// every possible choice among the columns in this row
		for (int i = 0; i < NR_OF_QUEENS; i++) {
			// check if point is safe
			if (isSafe(i, n, tmp)) {
				queens[n] = new Point(i, n);

				drawBoard();
				drawQueens();
				pause(DELAY);

				if (placeQueen(n + 1)) {
					return true;
				} else {
					queens[n] = null;
				}
			}

		}
		return false;
	}

	// check if it is safe to place the queen at i, j
	private boolean isSafe(int x, int y, boolean[][] tmp) {
		return !tmp[x][y];
	}

	private boolean[][] markThreatenedFields() {
		boolean[][] tmp = new boolean[NR_OF_QUEENS][NR_OF_QUEENS];
		for (int i = 0; i < NR_OF_QUEENS; i++) {
			if (queens[i] != null) {
				markHorizontal(queens[i], tmp);
				markVertical(queens[i], tmp);
				markDiagonal1(queens[i], tmp);
				markDiagonal2(queens[i], tmp);
				// drawTmpBoard(tmp);
			}
		}
		return tmp;
	}

	private void markDiagonal2(Point point, boolean[][] tmp) {
		int x = point.x;
		int y = point.y;
		tmp[x][y] = true;
		// go up
		while ((x > 0) && (y < NR_OF_QUEENS - 1)) {
			tmp[--x][++y] = true;
		}
		// go down
		x = point.x;
		y = point.y;
		while ((x < NR_OF_QUEENS - 1) && (y > 0)) {
			tmp[++x][--y] = true;
		}
	}

	private void markDiagonal1(Point point, boolean[][] tmp) {
		int x = point.x;
		int y = point.y;
		tmp[x][y] = true;
		// go up
		while ((x > 0) && (y > 0)) {
			tmp[--x][--y] = true;
		}
		// go down
		x = point.x;
		y = point.y;
		while ((x < NR_OF_QUEENS - 1) && (y < NR_OF_QUEENS - 1)) {
			tmp[++x][++y] = true;
		}
	}

	// returns true if there is a collision
	private void markVertical(Point queen, boolean[][] tmp) {
		for (int y = 0; y < NR_OF_QUEENS; y++) {
			tmp[queen.x][y] = true;
		}
	}

	// returns true if there is a collision
	private void markHorizontal(Point queen, boolean[][] tmp) {
		for (int x = 0; x < NR_OF_QUEENS; x++) {
			tmp[x][queen.y] = true;
		}
	}

	private void drawBoard() {
		removeAll();
		int dx = getWidth() / NR_OF_QUEENS;
		int dy = getHeight() / NR_OF_QUEENS;
		for (int i = 0; i < NR_OF_QUEENS; i++) {
			for (int j = 0; j < NR_OF_QUEENS; j++) {
				GRect rect = new GRect(i * dx, j * dy, dx, dy);
				rect.setColor(Color.BLACK);
				add(rect);
			}
		}
	}

	private void drawQueens() {
		int dx = getWidth() / NR_OF_QUEENS;
		int dy = getHeight() / NR_OF_QUEENS;
		for (int i = 0; i < NR_OF_QUEENS; i++) {
			if (queens[i] != null) {
				GOval queen = new GOval(queens[i].x * dx, queens[i].y * dy, dx,
						dy);
				queen.setColor(Color.BLACK);
				queen.setFillColor(Color.RED);
				queen.setFilled(true);
				add(queen);
			}
			// waitForClick();
		}
	}
}