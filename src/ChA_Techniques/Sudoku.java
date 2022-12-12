import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import tree.GThickLine;
import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Sudoku
 * 
 * Sudoku is a well known number puzzle with a simple rule: there can be no
 * repeats of the numbers 1 through 9 in a row, a column, or a block. This can
 * be solved by recursive backtracking.
 * 
 * Will not work for 12x12 Sudokus.
 * 
 * @see https://see.stanford.edu/materials/icspacs106b/Lecture11.pdf
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Sudoku extends GraphicsProgram {

	private final int SIZE_OF_SUDOKU = 4; // 4 or 9
	private final String SUDOKU_FILE_NAME = "sudoku4x4.txt"; // "sudoku9x9.txt";
	// private final int SIZE_OF_SUDOKU = 9;
	// private final String SUDOKU_FILE_NAME = "sudoku9x9.txt";

	private final int WIDTH = 400;
	private final int SIZE = WIDTH / SIZE_OF_SUDOKU;

	private int[][] grid;

	public void run() {
		setSize(WIDTH, WIDTH + 44);

		grid = readGridFromFile(SUDOKU_FILE_NAME);
		displayGrid();

		waitForClick();

		if (solveSudoku()) {
			displayGrid();
		} else {
			println("There is no unique solution!");
		}
	}

	private void displayGrid() {
		drawLabels();
		drawGrid();
	}

	private void drawGrid() {
		int nrOfLines = (int) Math.sqrt(SIZE_OF_SUDOKU);
		for (int i = 0; i < nrOfLines; i++) {
			int x0 = (i + 1) * nrOfLines * SIZE;
			GThickLine verticalLine = new GThickLine(x0, 0, x0, WIDTH, 3);
			add(verticalLine);
			int y0 = (i + 1) * nrOfLines * SIZE;
			GThickLine horizontalLine = new GThickLine(0, y0, WIDTH, y0, 3);
			add(horizontalLine);
		}
	}

	private void drawLabels() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				String lbl = "";
				if (grid[i][j] > 0) {
					lbl = " " + grid[i][j];
				}
				GRectWithLabel rect = new GRectWithLabel(lbl, SIZE);
				add(rect, i * SIZE, j * SIZE);
			}
		}
	}

	private void printGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				print(grid[i][j]);
			}
			println();
		}
	}

	private boolean solveSudoku() {
		Point p = new Point(0,0);

		if (!findUnassignedLocation(grid, p)) {
			return true;
		}

		for (int num = 1; num <= SIZE_OF_SUDOKU; num++) {
			if (noConflicts(grid, num, p.y, p.x)) {
				grid[p.y][p.x] = num;
				if (solveSudoku()) {
					return true;
				}
				grid[p.y][p.x] = 0;
			}
		}
		return false;
	}

	private boolean noConflicts(int[][] grid, int num, int row, int col) {
		// first check rows
		for (int i = 0; i < grid.length; i++) {
			if (grid[row][i] == num) {
				return false;
			}
		}
		// next check cols
		for (int i = 0; i < grid.length; i++) {
			if (grid[i][col] == num) {
				return false;
			}
		}
		// finally check blocks
		// first find relevant block:
		int blockLength = (int) Math.sqrt(grid.length);
		int j0 = col / blockLength;
		int i0 = row / blockLength;
		for (int i = 0; i < blockLength; i++) {
			for (int j = 0; j < blockLength; j++) {
				if (grid[i0 * blockLength + i][j0 * blockLength + j] == num) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean findUnassignedLocation(int[][] grid, Point p) {
		for (int i = 0; (i < grid.length); i++) {
			for (int j = 0; (j < grid.length); j++) {
				if (grid[i][j] == 0) {
					p.y = i;
					p.x = j;
					return true;
				}
			}
		}
		return false;
	}

	private int[][] readGridFromFile(String fileName) {
		int[][] arrSudoku = new int[SIZE_OF_SUDOKU][SIZE_OF_SUDOKU];
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line;
			int lineCount = 0;
			line = br.readLine();
			while (line != null) {
				// System.out.println( line );
				for (int i = 0; i < arrSudoku.length; i++) {
					arrSudoku[lineCount][i] = line.charAt(i) - 48;
					if (arrSudoku[lineCount][i] == -16) {
						arrSudoku[lineCount][i] = 0;
					}
				}
				line = br.readLine();
				lineCount++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return arrSudoku;
	}

	private class GRectWithLabel extends GCompound {
		private GLabel lbl;
		private int size;

		public GRectWithLabel(String label, int size) {
			super();
			this.size = size;

			// setBackground(Color.LIGHT_GRAY);

			add(new GRect(size, size));

			lbl = new GLabel(label);
			lbl.setFont("Serif-Bold-" + size / 2);
			lbl.setLocation(getX() + size / 8, getY() + 5 * size / 8);
			add(lbl);
		}
	}
}
