/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * HomemadeLinkedList
 * 
 * Shows how to write your own LinkedList class.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class HomemadeLinkedList {
	class Node {
		public Node next;
		public Object obj;
	}

	private Node first;
	private int size = 0;

	public HomemadeLinkedList() {
		// nothing to do
	}

	public int size() {
		return size;
	}

	public void add(Object obj) {
		if (first == null) {
			first = new Node();
			first.obj = obj;
			size++;
		} else {
			// find last node
			Node current = first;
			while (current.next != null) {
				current = current.next;
			}
			// we are at the end, add new one
			current.next = new Node();
			current.next.obj = obj;
			size++;
		}
	}

	public Object get(int i) {
		Node nde = getNode(i);
		if (nde != null) {
			return nde.obj;
		}
		return null;
	}

	public void set(int i, Object obj) {
		Node nde = getNode(i);
		if (nde != null) {
			nde.obj = obj;
		}
	}

	private Node getNode(int i) {
		int cnt = 0;
		Node current = first;
		while (current != null) {
			if (cnt == i) {
				return current;
			}
			current = current.next;
			cnt++;
		}
		return null;
	}

	public static void main(String[] args) {
		HomemadeLinkedList cities = new HomemadeLinkedList();
		// add cities
		cities.add("Nuremberg");
		cities.add("Munich");
		cities.add("Hamburg");
		cities.add("Berlin");
		cities.add("Frankfurt");

		// get size:
		System.out.println("Size: " + cities.size());

		// get the third city
		System.out.println("Third city: " + cities.get(2));

		// remove the third city
		//cities.remove(2);

		// replace the second city by another city
		cities.set(1, "Stuttgart");

		// search for Stuttgart
		// println("Stuttgart is at position: " + cities.indexOf("Stuttgart"));

		// list all remaining cities
		System.out.println("All remaining cities:");
		for (int i = 0; i < cities.size(); i++) {
			Object city = cities.get(i);
			System.out.print(city + ", ");
		}
	}
}
