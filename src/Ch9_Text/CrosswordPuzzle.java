import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * CrosswordPuzzle
 * 
 * Creates crossword puzzles from a given set of initial words. It starts by
 * filling random words horizontally and then uses a trie data structure to find
 * words that fit vertically. This is also a randomized algorithm.
 * 
 * Crossword directories are available here: <br/>
 * http://www.onlinecrosswords.net/crossworddirectory.php
 * https://crosswordlabs.com/ http://www.dowedo.net/compiler/
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class CrosswordPuzzle extends ConsoleProgram {
	private final int PUZZLE_SIZE = 9;

	private final String[] words = { "ark", "card", "dad", "day", "dear",
			"down", "east", "erase", "ever", "father", "itsy", "man", "mesh",
			"near", "nerf", "send", "stinks", "stun", "sync", "yard" };

	private SimpleTrie trie = new SimpleTrie();
	private char[][] puzzle = new char[PUZZLE_SIZE][PUZZLE_SIZE];
	private Set<String> finds = new HashSet<String>();

	public void run() {
		setSize(400, 350);
		setFont("monospaced-bold-24");

		initArrayWithSpaces();

		// loadLexiconFromFile("dictionary_en_de.txt");
		loadWordsIntoTrie();

		initPuzzleWithWords();
		char[][] puzzleOrig = makeCopyOf(puzzle);
		// initPuzzleWithRandomWords();

		// printPuzzle();

		findVerticals(6);
		findVerticals(5);
		findVerticals(4);
		findVerticals(3);
		// printPuzzle();

		printPuzzleSideBySide(puzzleOrig, puzzle);
	}

	/**
	 * we add verticals in all-caps, we should still check for duplicates.
	 */
	private void findVerticals(int length) {
		// pick 100 random x,y values and see what happens
		for (int i = 0; i < 100; i++) {
			int x = (int) (Math.random() * PUZZLE_SIZE);
			int y = (int) (Math.random() * (PUZZLE_SIZE - length + 1));
			String key = "";// "d..n";
			for (int j = 0; j < length; j++) {
				key += puzzle[x][y + j];
			}

			for (String s : trie.nodesThatMatch(key.toLowerCase())) {
				if (s.length() <= length) {
					finds.add(s + ",x=" + x + ",y=" + y);
					// add to puzzle in all-caps
					for (int j = 0; j < s.length(); j++) {
						puzzle[x][y + j] = s.toUpperCase().charAt(j);
					}
				}
			}
		}

	}

	private void loadWordsIntoTrie() {
		for (int i = 0; i < words.length; i++) {
			trie.add(words[i]);
		}
	}

	private void initPuzzleWithRandomWords() {
		String[] initWords = { "dad", "send", "east", "itsy", "nerf", "ark",
				"sync", "mesh", "ever", "near" };
		Set<String> duplicates = new HashSet<String>();
		for (int i = 0; i < initWords.length * 2; i++) {
			String randWord = initWords[(int) (Math.random() * initWords.length)];
			if (!duplicates.contains(randWord)) {
				int x = (int) (Math.random() * (puzzle[0].length
						- randWord.length() + 1));
				int y = (int) (Math.random() * (puzzle.length));
				if (addWordToPuzzleIfPossible(randWord, x, y, true)) {
					duplicates.add(randWord);
				}
			}
		}
	}

	private void initPuzzleWithWords() {
		addWordToPuzzle("dad", 0, 0, true);
		addWordToPuzzle("send", 5, 0, true);
		addWordToPuzzle("east", 2, 1, true);
		addWordToPuzzle("itsy", 5, 2, true);
		addWordToPuzzle("nerf", 0, 3, true);
		addWordToPuzzle("ark", 3, 4, true);
		addWordToPuzzle("sync", 5, 5, true);
		addWordToPuzzle("mesh", 0, 6, true);
		addWordToPuzzle("ever", 3, 7, true);
		addWordToPuzzle("near", 0, 8, true);
	}

	private void initArrayWithSpaces() {
		for (int i = 0; i < puzzle.length; i++) {
			Arrays.fill(puzzle[i], '.');
		}
	}

	/**
	 * 
	 * @param string
	 * @param i
	 * @param b
	 *            if true horizontal, else vertical
	 */
	private void addWordToPuzzle(String word, int x, int y, boolean horizontal) {
		if (horizontal) {
			for (int i = 0; i < word.length(); i++) {
				puzzle[x + i][y] = word.charAt(i);
			}
		} else {
			for (int i = 0; i < word.length(); i++) {
				puzzle[x][y + i] = word.charAt(i);
			}
		}

	}

	private boolean addWordToPuzzleIfPossible(String randWord, int x, int y,
			boolean horizontal) {
		if (horizontal) {
			// check if at the desired position space is available:
			boolean isAvailable = true;
			for (int i = 0; i < randWord.length(); i++) {
				if (puzzle[x + i][y] != '.') {
					isAvailable = false;
				}
			}
			if (isAvailable) {
				addWordToPuzzle(randWord, x, y, true);
				return true;
			}
		} else {
			System.out.println("Not yet implemented");
		}
		return false;
	}

	private void printPuzzle() {
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle[0].length; j++) {
				print(puzzle[j][i]);
			}
			println();
		}
	}

	private void printPuzzleSideBySide(char[][] puzzle1, char[][] puzzle2) {
		for (int i = 0; i < puzzle1.length; i++) {
			for (int j = 0; j < puzzle1[0].length; j++) {
				print(puzzle1[j][i]);
			}
			print("     ");
			for (int j = 0; j < puzzle2[0].length; j++) {
				print(puzzle2[j][i]);
			}
			println();
		}
	}

	private char[][] makeCopyOf(char[][] puzzle) {
		char[][] puzzleCopy = new char[PUZZLE_SIZE][PUZZLE_SIZE];
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle[0].length; j++) {
				puzzleCopy[j][i] = puzzle[j][i];
			}
		}
		return puzzleCopy;
	}

	private void loadLexiconFromFile(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while (true) {
				String words = br.readLine();
				if (words == null)
					break;
				StringTokenizer st = new StringTokenizer(words, "=");
				String en = st.nextToken();
				String de = st.nextToken();
				// maybe some of your code goes here
				trie.add(en);
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done loading file");
	}
}
