/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * HomemadeArrayList
 * 
 * Shows how to write your own ArrayList class.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class HomemadeArrayList {
	
	private Object[] arr;
	private int capacity = 10;
	private int position = -1;

	public HomemadeArrayList() {
		arr = new Object[capacity];
	}

	public int size() {
		return position + 1;
	}

	public void add(Object obj) {
		if (position < capacity-1) {
			position++;
			arr[position] = obj;
		} else {
			// we need to increase size of underlying array
		}
	}

	public Object get(int i) {
		if (i >= 0 && i <= position) {
			return arr[i];
		}
		return null;
	}

	public void set(int i, Object obj) {
		if (i >= 0 && i <= position) {
			arr[i] = obj;
		}
	}
	
	public static void main(String[] args) {
		HomemadeArrayList cities = new HomemadeArrayList();
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
		//println("Stuttgart is at position: " + cities.indexOf("Stuttgart"));

		// list all remaining cities
		System.out.println("All remaining cities:");
		for (int i=0; i< cities.size(); i++) {
			Object city = cities.get(i);
			System.out.print(city + ", ");
		}
	}
}
