import java.util.HashSet;
import java.util.Set;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Node<E>
 * 
 * A simple implementation of a Node class, needed for a simple Tree.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Node<E> {
	private Node<E> parent;
	private Set<Node<E>> children;
	private E element;

	public Node(E element) {
		this.parent = null;
		this.element = element;
		this.children = new HashSet<Node<E>>();
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public Node<E> getParent() {
		return parent;
	}

	public void setParent(Node<E> parent) {
		this.parent = parent;
	}

	public Set<Node<E>> getChildren() {
		return children;
	}

	public void addChild(Node<E> node) {
		node.setParent(this);
		children.add(node);
	}

	public void removeChild(Node<E> node) {
		node.setParent(null);
		children.remove(node);
	}

	public boolean isRoot() {
		return (parent == null);
	}

	public boolean isInternal() {
		return !isExternal();
	}

	public boolean isExternal() {
		return (children.size() == 0);
	}

	@Override
	public String toString() {
		return "Node [element=" + element + "]";
	}

	/**
	 * Shows how to use Node class
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

		// test
		System.out.println(adam);
		System.out.println(adam.isRoot());
		System.out.println(adam.isInternal());
		System.out.println(adam.isExternal());
		System.out.println(adam.getChildren());
		System.out.println(abel.getParent());

		// Abel got killed
		adam.removeChild(abel);
		System.out.println(adam.getChildren());
		System.out.println(abel.getParent());
	}
}
