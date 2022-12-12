import java.util.HashMap;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * HashList
 * 
 * Implement a List interface, but use a HashMap as underlying data structure.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class HashList {

	private HashMap<Integer, Object> map;
	private int position = -1;

	public HashList() {
		map = new HashMap<Integer, Object>();
	}

	public int size() {
		return map.size();
	}

	public void add(Object obj) {
		position++;
		map.put(position, obj);
	}

	public Object get(int i) {
		return map.get(i);
	}

	public void set(int i, Object obj) {
		if (i >= 0 && i <= position) {
			map.put(i, obj);
		}
	}

	public static void main(String[] args) {
		HashList cities = new HashList();
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
		// cities.remove(2);

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
