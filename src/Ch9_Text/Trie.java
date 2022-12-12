import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Trie
 * 
 * A very basic implementation of the Trie data structure. Only works with the
 * lower case letters [a-z]. No optimization and no real testing whatsoever has
 * been done.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Trie<E> {
	private static final int _26 = 26;

	private class Node<E> {
		private Node<E>[] arr;
		private Set<E> storage;

		public Node() {
			this.arr = new Node[_26];
		}
	}

	private Node<E> root;

	public Trie() {
		root = new Node<E>();
	}
	
	public void add(String word, E store) {
		Node<E> p = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int index = c - 'a';
			if (index >= 0 && index < _26) {
				if (p.arr[index] == null) {
					Node<E> temp = new Node<E>();
					p.arr[index] = temp;
					p = temp;
				} else {
					p = p.arr[index];
				}
			} else {
				System.out.print(c);
			}
		}
		//p.isString = true;
		if ( p.storage == null ) {
			p.storage = new TreeSet<E>();
		}
		p.storage.add(store);
	}

	public boolean contains(String key) {
		Node<E> p = getNode(key);
		if (p == null) {
			return false;
		} else {
			if (p.storage != null){
				return true;
			}
		}

		return false;
	}

	// Returns if there is any word in the trie
	// that starts with the given prefix.
	public boolean startsWith(String prefix) {
		Node<E> p = getNode(prefix);
		if (p == null) {
			return false;
		} else {
			return true;
		}
	}

	public Set<E> get(String s) {
		Node<E> n = getNode(s);
		if (n != null) {
			return n.storage;
		}
		return null;
	}
	
	private Node<E> getNode(String s) {
		Node<E> p = root;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int index = c - 'a';
			if (p.arr[index] != null) {
				p = p.arr[index];
			} else {
				return null;
			}
		}

		if (p == root)
			return null;

		return p;
	}

	public Set<String> nodesThatMatch(String pattern) {
		Set<String> nodes = new HashSet<String>();
		Set<String> prefixes = prefixesThatMatch(pattern);
		for (String s : prefixes) {
			Set<String> tmpSet = nodesWithPrefix(s);
			for (String s2 : tmpSet) {
				// System.out.println(s2);
				nodes.add(s2);
			}
		}
		return nodes;
	}

	// "h.l"
	private Set<String> prefixesThatMatch(String s) {
		Set<String> set = new HashSet<String>();
		prefixesThatMatchRecurse(root, set, s, "");
		return set;
	}

	private void prefixesThatMatchRecurse(Node<E> p, Set<String> set, String s,
			String sofar) {
		char c = s.charAt(0);
		if (c == '.') {
			for (int index = 0; index < _26; index++) {
				char cc = (char) (index + 'a');
				if (p.arr[index] != null) {
					if (s.length() > 1) {
						prefixesThatMatchRecurse(p.arr[index], set,
								s.substring(1), sofar + cc);
					} else {
						set.add(sofar + cc);
					}
				}
			}

		} else {
			int index = c - 'a';
			if (index >= 0 && index < _26) {
				if (p.arr[index] != null) {
					if (s.length() > 1) {
						prefixesThatMatchRecurse(p.arr[index], set,
								s.substring(1), sofar + c);
						// difference between 's..n' and 's..n..'
						// if (p.arr[index].isEnd) {
						// set.add(sofar + c);
						// }
					} else {
						set.add(sofar + c);
					}
				}
			} else {
				System.out.print(c);
			}
		}
	}

	public Set<String> nodesWithPrefix(String s) {
		Set<String> set = new HashSet<String>();
		Node<E> p;
		if (s.length() == 0) {
			p = root;
		} else {
			p = getNode(s);
			if (p != null && (p.storage != null)) {
				set.add(s);
			}
		}
		if (p != null) {
			collectNodes(p, s, set);
		}
		return set;
	}

	private void collectNodes(Node<E> p, String s, Set<String> set) {
		for (int i = 0; i < p.arr.length; i++) {
			String s2 = s;
			if (p.arr[i] != null) {
				char c = (char) (i + 'a');
				s2 += c;
				if (p.arr[i].storage != null) {
					set.add(s2);
				}
				collectNodes(p.arr[i], s2, set);
			}
		}
	}

	public static void main(String[] args) {
		// test1();
		test2();
	}

	private static void test2() {
		String[] words = { "ark", "card", "dad", "day", "dear", "down", "east",
				"erase", "ever", "father", "itsy", "man", "mesh", "near",
				"nerf", "send", "stinks", "stun", "sync", "yard", };

		Trie<String> t = new Trie<String>();
		for (int i = 0; i < words.length; i++) {
			t.add(words[i],""+i);
			
		}

		System.out.println(t.startsWith("ne"));
		System.out.println(t.contains("sync"));
		System.out.println(t.get("card"));

		System.out.println("All strings that start with 'ne':");
		Set<String> set = t.nodesWithPrefix("ne");
		for (String s : set) {
			System.out.println(s);
		}

		System.out.println("All strings that start with 'nn':");
		Set<String> set6 = t.nodesWithPrefix("nn");
		for (String s : set6) {
			System.out.println(s);
		}

		System.out.println("All strings that start with 'stun':");
		Set<String> set4 = t.nodesWithPrefix("stun");
		for (String s : set4) {
			System.out.println(s);
		}

		System.out.println("All prefixes that match 's..n':");
		Set<String> set2 = t.prefixesThatMatch("s..n");
		for (String s : set2) {
			System.out.println(s);
		}

		System.out.println("All prefixes that match 's..n..':");
		Set<String> set3 = t.prefixesThatMatch("s..n..");
		for (String s : set3) {
			System.out.println(s);
		}

		System.out.println("All strings that start with 's..n..':");
		Set<String> set5 = t.nodesThatMatch("s..n..");
		for (String s : set5) {
			System.out.println(s);
		}
	}

	private static void test1() {
		Trie<String> t = new Trie<String>();
		t.add("hello", "1");
		t.add("hallo", "2");
		t.add("hell", "3");
		t.add("teg", "4");
		t.add("tag", "5");

		System.out.println(t.startsWith("he"));
		System.out.println(t.contains("hello"));
		// TrieNode tn = t.searchNode("he");

		System.out.println("All strings that start with 'he':");
		Set<String> set = t.nodesWithPrefix("he");
		for (String s : set) {
			System.out.println(s);
		}

		System.out.println("All prefixes that match 'h.l':");
		Set<String> set2 = t.prefixesThatMatch("h.l");
		for (String s : set2) {
			System.out.println(s);
		}

		System.out.println("All strings that start with 'h.l':");
		Set<String> set5 = t.nodesThatMatch("h.l");
		for (String s : set5) {
			System.out.println(s);
		}
	}
}
