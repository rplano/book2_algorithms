import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * AutoComplete
 * 
 * Using a Trie data structure to implement a simple version of auto-complete.
 * As you start typing more than three letters an auto-complete is started. It
 * picks the shortest word to display. The tab character is used to select the
 * suggestion, the space key is used to end suggestion. Both move to the next
 * word.
 * 
 * Hint: click in the white space with the mouse before typing. Java...
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class AutoComplete extends GraphicsProgram {
	private final int FONT_SIZE = 24;

	private SimpleTrie trie = new SimpleTrie();

	private GLabel tf;
	private String text;

	private int yPos = FONT_SIZE;

	public void init() {
		setSize(400, 200);

		loadLexiconFromFile("dictionary_en_de.txt");

		text = "";
		tf = new GLabel("");
		tf.setFont("Serif-bold-" + FONT_SIZE);
		add(tf, 0, yPos);

		addKeyListeners();
		// to receive the tab-key we must add this line:
		this.getGCanvas().setFocusTraversalKeysEnabled(false);
	}

	public void keyReleased(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == ' ') {
			tf.setLabel(text);
			yPos += FONT_SIZE;
			text = "";
			tf = new GLabel("");
			tf.setFont("Serif-bold-" + FONT_SIZE);
			add(tf, 0, yPos);

		} else if (c == '\t') {
			yPos += FONT_SIZE;
			text = "";
			tf = new GLabel("");
			tf.setFont("Serif-bold-" + FONT_SIZE);
			add(tf, 0, yPos);

		} else {
			text += c;
			if (text.length() > 2) {
				// find shortest one:
				String suggestion = "hi there i am a very long string";
				for (String s : trie.nodesWithPrefix(text)) {
					if (s.length() < suggestion.length()) {
						suggestion = s;
					}
				}
				tf.setLabel(suggestion);

			} else {
				tf.setLabel(text);
			}
		}
	}

	/**
	 * This method should load the dictionary from file and store it in a trie.
	 * 
	 * @param fileName
	 */
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
				trie.add(en.toLowerCase());
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\nDone loading file");
	}
}
