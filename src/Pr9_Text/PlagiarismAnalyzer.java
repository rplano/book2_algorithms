import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * PlagiarismAnalyzer
 * 
 * The idea is to load a bunch of files compare all of them using the diagonal
 * trick and displaying it in graphical form. It loads all files and removes
 * white spaces. Then it compares the files and for every 20 chars that
 * coincide, it draws a pixel. In addition, it measures the longest coincidences
 * and marks those for high likelihood of copy-paste.
 * 
 * ToDo: - to determine likelihood of plagiarism, we currently only measure the
 * largest common substring, but there could also be many smaller common
 * substrings - web app: do a multi-file upload, that gives you a stream, i.e.
 * String, hence no saving files - then just limit the maximum number of chars,
 * also limit number of files - try difference correlation, do this at parse
 * time - turn it into web service google app engine, limit size and number of
 * files, - labels in horizontal line should be printed downward
 * 
 * 0.07: - added some comments
 * 
 * 0.06: - need to do some refactoring, such that it can be used in JSP page,
 * i.e., make the component PlagiarismAnalyzer out of it
 * 
 * 0.05: - now uses BufferedImage for drawing and can save as png image - create
 * a png file dynamically and set/unset bits to set pixels, should be limited in
 * size to 1000x1000 - exportAsCSV works
 * 
 * 0.04: - do a test with real data - we need to remove white spaces - there is
 * a bug, there should be nothing on the diagonal - something is wrong in the
 * lower right corner
 * 
 * 0.03: - recursion do an abort criteria in recursion - added file read
 * libraries: @see guava-libraries https://code.google.com/p/guava-libraries/
 * 
 * 0.02: - try recursion, i.e. if you found the charAt(x) == charAt(y) then
 * check if charAt(x+1) == charAt(y+1) if true continue recursion, then only
 * draw the line, if the recursive depth is larger than some constant, also use
 * this constant to stop recursion - also added grid lines and labels for the
 * file names - can compare upper case version at parse time
 * 
 * 0.01: - got started
 * 
 * @author Ralph P. Lano
 */
public class PlagiarismAnalyzer {
	public static final int IMG_WIDTH = 800;
	public static final int IMG_HEIGHT = 800;
	public static final Color COLOR_GRID = Color.DARK_GRAY;
	public static final Color COLOR_LABELS = Color.BLUE;

	public static final int MIN_STREAK_LENGTH = 20; // how many chars must
													// coincide for a pixel to
													// be set

	private long[] lengthsOfFilesHelper; // 0, 22, 32, 52, ...
	private int[][] plagiarismLikelihood; // contains likelihoods that student i
											// has collaborated with student j
	private double scale = 1.0; // needed for fitting the data into the img

	private long[] lengthsOfFiles; // 22, 10, 20, ...
	private String[] namesOfFiles; // contains the file names that were uploaded
	private char[] allCode; // contains all the file contents in one char array

	/**
	 * Initializes the PlagiarismAnalyzer. To run the plagiarism analysis you
	 * need to call analyze().
	 * 
	 * @param lengthsOfFiles
	 *            lengths of the individual files in the allCode char array.
	 *            Note, the white spaces should have be removed already.
	 * @param namesOfFiles
	 *            names of the files, most of the time the student names.
	 * @param allCode
	 *            contents of all files in one big char array.
	 */
	public PlagiarismAnalyzer(long[] lengthsOfFiles, String[] namesOfFiles,
			char[] allCode) {
		super();

		this.lengthsOfFiles = lengthsOfFiles;
		this.namesOfFiles = namesOfFiles;
		this.allCode = allCode;

		lengthsOfFilesHelper = new long[lengthsOfFiles.length + 1];
		plagiarismLikelihood = new int[lengthsOfFiles.length][lengthsOfFiles.length];

		int counter = 0;
		for (int i = 0; i < lengthsOfFiles.length; i++) {
			lengthsOfFilesHelper[i] = counter;
			counter += lengthsOfFiles[i];
		}
		lengthsOfFilesHelper[lengthsOfFiles.length] = counter;
	}

	/**
	 * This takes the data, analyzes it and generates an image.
	 * 
	 * @return
	 */
	public BufferedImage analyze() {
		BufferedImage img = new BufferedImage(IMG_WIDTH + 1, IMG_HEIGHT + 1,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();
		g2d.setBackground(Color.GREEN);

		scale = ((double) IMG_WIDTH)
				/ lengthsOfFilesHelper[lengthsOfFiles.length];
		displayResults(img);
		displayPlagiarismIndicator(g2d);
		displayGrid(g2d);

		return img;
	}

	/**
	 * Exports the mutual likelihoods of two students having collaborated in CSV
	 * format. Before this method can be used, the analyze() method must be
	 * called.
	 */
	public String exportAsCSV() {
		StringBuffer csvSB = new StringBuffer();

		// first put headings
		csvSB.append(" ,");
		for (int j = 0; j < lengthsOfFiles.length; j++) {
			csvSB.append(namesOfFiles[j] + ",");
		}
		csvSB.append("\n");

		// now put data
		for (int j = 0; j < lengthsOfFiles.length; j++) {
			csvSB.append(namesOfFiles[j] + ",");
			for (int i = 0; i < lengthsOfFiles.length; i++) {
				float temp = (float) plagiarismLikelihood[i][j];
				if (plagiarismLikelihood[i][j] == 0) {
					temp = (float) plagiarismLikelihood[j][i];
				}
				float plagLikely;
				if (lengthsOfFiles[i] > lengthsOfFiles[j]) {
					plagLikely = temp / lengthsOfFiles[j];
				} else {
					plagLikely = temp / lengthsOfFiles[i];
				}
				csvSB.append(plagLikely + ",");

			}
			csvSB.append("\n");
		}
		return csvSB.toString();
	}

	/**
	 * This is the central method: it finds if a series of characters match in
	 * the two different files. It also measures the length of the diagonals,
	 * i.e., how many characters in the two files coincide. However, it only
	 * looks for the maximum length. Another measure could be many small
	 * coincidences, but that is difficult to measure.
	 */
	private void displayResults(BufferedImage img) {
		for (int i = 0; i < lengthsOfFiles.length; i++) {
			for (int j = i + 1; j < lengthsOfFiles.length; j++) {
				for (int x = (int) lengthsOfFilesHelper[i]; x < (int) lengthsOfFilesHelper[i + 1]; x++) {
					for (int y = (int) lengthsOfFilesHelper[j]; y < (int) lengthsOfFilesHelper[j + 1]; y++) {
						if (allCode[x] == allCode[y]) {
							int diagonalLength = 0;
							if ((diagonalLength = recurse(x, y,
									(int) lengthsOfFilesHelper[i + 1],
									(int) lengthsOfFilesHelper[j + 1], 0)) > MIN_STREAK_LENGTH) {
								if (diagonalLength > plagiarismLikelihood[i][j]) {
									plagiarismLikelihood[i][j] = diagonalLength;
								}
								drawPoint(img, (int) (x * scale),
										(int) (y * scale));
							}
						}
					}
				}

			}
		}
	}

	/**
	 * This checks how long a diagonal line is, i.e. it measures how much the
	 * two strings are the same. To avoid unneccessary recursion, we stop if the
	 * count is larger than the STREAK_LENGTH, but then we no longer know how
	 * long a diagonal really is.
	 * 
	 * @param i
	 *            current x position in char array
	 * @param j
	 *            current y position in char array
	 * @param count
	 *            length of line
	 * @return length of line
	 */
	private int recurse(int i, int j, int maxI, int maxJ, int count) {
		// if ( count < STREAK_LENGTH+1 ) { // this avoids unneccessary
		// recursion, but we no longer know how long a diagonal is
		if ((++i < maxI) && (++j < maxJ)) {
			if (allCode[i] == allCode[j]) {
				count = recurse(i, j, maxI, maxJ, ++count);
			}
		}
		// }
		return count;
	}

	private void displayPlagiarismIndicator(Graphics2D g2d) {
		for (int i = 0; i < lengthsOfFiles.length; i++) {
			for (int j = i + 1; j < lengthsOfFiles.length; j++) {
				int x = (int) lengthsOfFilesHelper[i];
				int y = (int) lengthsOfFilesHelper[j];
				int w = (int) (lengthsOfFiles[i] * scale);
				int h = (int) (lengthsOfFiles[j] * scale);
				float plagLikely;
				if (lengthsOfFiles[i] > lengthsOfFiles[j]) {
					plagLikely = ((float) plagiarismLikelihood[i][j])
							/ lengthsOfFiles[j];
				} else {
					plagLikely = ((float) plagiarismLikelihood[i][j])
							/ lengthsOfFiles[i];
				}
				// System.out.println(i+","+j+","+plagLikely);
				// System.out.println(lengthsOfFiles[i] +","+ lengthsOfFiles[j]
				// +","+ plagiarismLikelyhood[i][j]);
				Color c = new Color(1.0f, 1.0f - plagLikely, 1.0f - plagLikely,
						0.5f);
				drawBackgroundRect(g2d, (int) (x * scale), (int) (y * scale),
						w, h, c);
			}
		}
	}

	private void displayGrid(Graphics2D g2d) {
		int x = 0;
		for (int i = 0; i < lengthsOfFiles.length; i++) {
			drawLine(g2d, (int) (x * scale), 0, (int) (x * scale), IMG_HEIGHT,
					COLOR_GRID);
			drawLabel(g2d, "" + (i + 1), (int) (x * scale) + 10, 15,
					COLOR_LABELS);
			drawLine(g2d, 0, (int) (x * scale), IMG_WIDTH, (int) (x * scale),
					COLOR_GRID);
			drawLabel(g2d, "" + (i + 1) + ". " + namesOfFiles[i], 10,
					(int) (x * scale) + 15, COLOR_LABELS);
			x += lengthsOfFiles[i];
		}
		drawLine(g2d, IMG_WIDTH, 0, IMG_WIDTH, IMG_HEIGHT, COLOR_GRID);
		drawLine(g2d, 0, IMG_HEIGHT, IMG_WIDTH, IMG_HEIGHT, COLOR_GRID);
	}

	private void drawLabel(Graphics2D g2d, String msg, int x, int y, Color c) {
		g2d.setColor(COLOR_LABELS);
		g2d.setFont(new Font("SansSerif", Font.PLAIN, 12));
		g2d.drawString(msg, x, y);
	}

	private void drawBackgroundRect(Graphics2D g2d, int x, int y, int w, int h,
			Color c) {
		g2d.setColor(c);
		g2d.fillRect(x, y, w, h);
	}

	private void drawLine(Graphics2D g2d, int x1, int y1, int x2, int y2,
			Color c) {
		g2d.setColor(c);
		BasicStroke bs = new BasicStroke(1);
		g2d.setStroke(bs);
		g2d.drawLine(x1, y1, x2, y2);
	}

	private void drawPoint(BufferedImage img, int x, int y) {
		img.setRGB(x, y, Color.BLACK.getRGB());
	}

}
