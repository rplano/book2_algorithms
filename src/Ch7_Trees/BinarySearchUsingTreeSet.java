import java.util.TreeSet;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * BinarySearchUsingTreeSet<E>
 * 
 * Shows how to use the TreeSet class to do a binary search.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class BinarySearchUsingTreeSet extends ConsoleProgram {

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-24");

		int[] nrs = { 44, 88, 17, 32, 97, 65, 28, 82, 29, 76, 54, 80 };

		// create tree and insert
		TreeSet<Integer> searchTree = new TreeSet<Integer>();
		for (int i = 0; i < nrs.length; i++) {
			searchTree.add(nrs[i]);
		}

		// find the entries: 76 and 25
		println("Contains 76: " + searchTree.contains(76));
		println("Contains 25: " + searchTree.contains(25));

		// insert the number 78
		searchTree.add(78);

		// remove numbers 32 and 65
		searchTree.remove(32);
		searchTree.remove(65);

		// print whole tree
		println("List content of tree: ");
		for (Integer nr : searchTree) {
			print(nr + ",");
		}

	}
}
