import java.util.HashSet;
import java.util.Set;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Tree<E>
 * 
 * A simple implementation of an unordered, generic Tree class, basically a
 * wrapper for the Node class. Note: nothing in here checks if this is really a
 * tree, it might actually be a graph!
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Tree<E> {
	private Node<E> root;

	public Tree(Node<E> root) {
		this.root = root;
	}

	public Node<E> root() {
		return root;
	}

	public int size() {
		return elements().size();
	}

	public Set<Node<E>> elements() {
		Set<Node<E>> elements = getDescendants(root);
		elements.add(root);
		return elements;
	}

	private Set<Node<E>> getDescendants(Node<E> ancestor) {
		if (ancestor != null) {
			Set<Node<E>> children = ancestor.getChildren();
			if ((children != null) && (children.size() > 0)) {
				Set<Node<E>> decendants = new HashSet<Node<E>>();
				for (Node<E> child : children) {
					decendants.add(child);
					Set<Node<E>> temp = getDescendants(child);
					if (temp != null) {
						decendants.addAll(temp);
					}
				}
				return decendants;
			}
		}
		return null;
	}

	public void preOrder() {
		preOrder(root);
	}

	private void preOrder(Node<E> p) {
		visit(p);
		if (p.getChildren() != null) {
			for (Node<E> child : p.getChildren()) {
				preOrder(child);
			}
		}
	}

	public void postOrder() {
		postOrder(root);
	}

	private void postOrder(Node<E> p) {
		if (p.getChildren() != null) {
			for (Node<E> child : p.getChildren()) {
				postOrder(child);
			}
		}
		visit(p);
	}

	private void visit(Node<E> p) {
		System.out.println(p);
	}

	public String toString() {
		return "Tree [root=" + root + "]";
	}

	/**
	 * Shows how to use Tree class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// init
		Node<String> adam = new Node<String>("Adam");
		adam.addChild(new Node<String>("Seth"));
		adam.addChild(new Node<String>("Awan"));
		adam.addChild(new Node<String>("Azura"));

		Node<String> abel = new Node<String>("Abel");
		adam.addChild(abel);

		Node<String> cain = new Node<String>("Cain");
		cain.addChild(new Node<String>("Enoch"));
		adam.addChild(cain);

		// create a tree
		Tree<String> humans = new Tree<String>(adam);

		// test
		System.out.println(humans);
		System.out.println(humans.size());
		System.out.println(humans.elements());
		System.out.println();
		humans.preOrder();
		System.out.println();
		humans.postOrder();
	}
}
