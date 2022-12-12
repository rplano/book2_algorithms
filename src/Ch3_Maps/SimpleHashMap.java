/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * SimpleHashMap
 * 
 * Shows how to write List class using a HashMap as underlying data structure.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class SimpleHashMap {

	private String[] data;
	private int capacity = 10;

	public SimpleHashMap() {
		data = new String[capacity];
	}

	private int hashCode(int key) {
		return key % capacity;
	}

	private void put(int key, String value) {
		data[hashCode(key)] = value;
	}

	private String get(int key) {
		return data[hashCode(key)];
	}

	public static void main(String[] args) {
		SimpleHashMap reversePhoneBook = new SimpleHashMap();
		reversePhoneBook.put(12345, "Lisa");
		reversePhoneBook.put(22222, "Mike");
		reversePhoneBook.put(43434, "Don");

		System.out.println("22222: " + reversePhoneBook.get(22222));
	}

}
