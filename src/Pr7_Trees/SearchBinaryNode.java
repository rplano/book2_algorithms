/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * SearchBinaryNode<E>
 * 
 * The simplest implementation of a Node class, needed for a simple binary
 * search example.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class SearchBinaryNode<E> {
	private SearchBinaryNode<E> leftChild;
	private SearchBinaryNode<E> rightChild;
	private E element;

	public SearchBinaryNode(E element) {
		this.element = element;
	}

	public E getElement() {
		return element;
	}

	public boolean hasLeft() {
		return (leftChild != null);
	}

	public boolean hasRight() {
		return (rightChild != null);
	}

	public SearchBinaryNode<E> getLeft() {
		return leftChild;
	}

	public SearchBinaryNode<E> getRight() {
		return rightChild;
	}

	public void setLeft(SearchBinaryNode<E> child) {
		leftChild = child;
	}

	public void setRight(SearchBinaryNode<E> child) {
		rightChild = child;
	}

	@Override
	public String toString() {
		return "BinarySearchNode [element=" + element + "]";
	}

}
