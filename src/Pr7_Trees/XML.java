import java.util.StringTokenizer;

import tree.OrderedNode;
import tree.OrderedTree;
import tree.TreePrinter;
import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * XML
 * 
 * Representing html/xml as a tree.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class XML extends ConsoleProgram {
	private static final int WIDTH = 400;
	private static final int HEIGHT = 500;

	public void run() {
		setSize(WIDTH, HEIGHT);
		setFont("Times New Roman-bold-18");

		String xml = "<html>" + "  <head>" + "  </head>" + "  <body>"
				+ "    My web page." + "  </body>" + "</html>";

		OrderedTree<String> tree = new OrderedTree<String>(parseXML(xml));
		
		new TreePrinter().prettyPrintSimpleVertical(tree);
	}

	private OrderedNode<String> parseXML(String xml) {
		OrderedNode<String> root = null;
		OrderedNode<String> currentNode = null;
		StringTokenizer st = new StringTokenizer(xml, "<>/", true);
		String state = "normal";
		while (st.hasMoreTokens()) {
			String token = st.nextToken().trim();
			if (token.length() > 0) {
				switch (token) {
				case "<":
					state = "open";
					break;
				case ">":
					state = "normal";
					break;
				case "/":
					if (state.equals("open")) {
						state = "close";
					} else {
						System.out
								.println("we should never get here: there was a '/' without the '<'");
					}
					break;
				default:
					switch (state) {
					case "open":
						if (root == null) {
							root = new OrderedNode<String>(token);
							currentNode = root;
						} else {
							OrderedNode<String> node = new OrderedNode<String>(
									token);
							currentNode.addChild(node);
							currentNode = node;
						}
						break;
					case "close":
						currentNode = (OrderedNode<String>) currentNode
								.getParent();
						break;
					default:
						OrderedNode<String> node = new OrderedNode<String>(
								token);
						currentNode.addChild(node);
						break;
					}

					break;
				}
			}
		}
		return root;
	}
}
