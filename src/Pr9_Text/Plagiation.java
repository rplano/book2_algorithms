import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Plagiation
 * 
 * This shows how to use the PlagiarismAnalyzer from a desktop graphics program.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Plagiation extends GraphicsProgram {
	public static final String ASSIGNMENT_DIRECTORY = "samples";// "testfiles"
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;

	public static final int ALGORITHM_NONE = 1;
	public static final int ALGORITHM_UPPERCASE = 2;
	// public static final int ALGORITHM_DIFFERENCE = 3;
	public static final int ALGORITHM_CHOSEN = ALGORITHM_UPPERCASE;

	private long[] lengthsOfFiles; // 22, 10, 20, ...
	private String[] namesOfFiles;
	private char[] allCode;

	public void init() {
		this.setSize(WIDTH+3, HEIGHT+47);
	}

	public void run() {
		loadAssignments();

		long startTime = System.currentTimeMillis();
		PlagiarismAnalyzer an = new PlagiarismAnalyzer(lengthsOfFiles,
				namesOfFiles, allCode);
		BufferedImage img = an.analyze();
		System.out.println("Time: " + (System.currentTimeMillis() - startTime)
				+ " ms.");

		GImage im = new GImage(img);
		add(im);

		saveImageToFile(img);
		saveCSVToFile(an.exportAsCSV());
	}

	/**
	 * Exports the mutual collaboration likelihoods to a csv file.
	 * 
	 * @param csv
	 */
	private void saveCSVToFile(String csv) {
		try {
			FileWriter fw = new FileWriter("data.csv");
			fw.write(csv);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Saves the generated image as png file.
	 */
	private void saveImageToFile(BufferedImage img) {
		try {
			ImageIO.write(img, "png", new File("image.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads all the files (assignments) in the ASSIGNMENT_DIRECTORY and stores
	 * char array allCode[], also does a conversion toUpperCase if wanted.
	 */
	private void loadAssignments() {
		File[] files = new File("" + ASSIGNMENT_DIRECTORY).listFiles();
		// first estimate the total file sizes and get the file names:
		int totalSize = 0;
		namesOfFiles = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			namesOfFiles[i] = files[i].getName();
			File sourceFile = new File(files[i].getAbsolutePath());
			if (sourceFile.exists()) {
				// System.out.println("File length: "+sourceFile.length());
				totalSize += sourceFile.length();
			} else {
				System.out.println("File " + sourceFile.getName()
						+ " does not exist.");
			}
		}
		System.out.println("Total length: " + totalSize);

		// now read all files into one char array:
		allCode = new char[totalSize];
		lengthsOfFiles = new long[files.length];
		int pointer = 0;
		for (int i = 0; i < files.length; i++) {
			System.out.print(".");
			File sourceFile = new File(files[i].getAbsolutePath());
			if (sourceFile.exists()) {
				// try {
				// read whole file at once: @see guava-libraries
				// https://code.google.com/p/guava-libraries/
				// String fileContent = Files.toString(sourceFile,
				// Charsets.UTF_8);
				String fileContent = readFromFile(sourceFile);

				// now remove all white-space characters:
				fileContent = fileContent.replaceAll("\\s+", "");

				lengthsOfFiles[i] = fileContent.length();

				// copy file content into big char array:
				fileContent.getChars(0, (int) lengthsOfFiles[i], allCode,
						pointer);
				pointer += lengthsOfFiles[i];

				// } catch (IOException e) {
				// e.printStackTrace();
				// }

			} else {
				System.out.println("File " + sourceFile.getName()
						+ " does not exist.");
			}
		}
		System.out.println("");
		System.out.println("done reading total of " + totalSize + " bytes.");

		// now manipulate char array:
		switch (ALGORITHM_CHOSEN) {
		case ALGORITHM_UPPERCASE:
			for (int i = 0; i < allCode.length; i++) {
				allCode[i] = Character.toUpperCase(allCode[i]);
			}
			break;
		/*
		 * case ALGORITHM_DIFFERENCE: int[] allCodeInt = new
		 * int[allCode.length]; for (int i = 0; i < allCode.length-1; i++) {
		 * allCodeInt[i] = allCode[i+1]-allCode[i]; } break;
		 */
		}
	}

	private String readFromFile(File fileName) {
		String content = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				content += line;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
}
