/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Dog
 * 
 * Shows how to implement the Comparable interface, which allows easy sorting,
 * see the SortDogs class for an example how this is done.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Dog implements Comparable<Dog> {

	private int age;
	private String name;

	public Dog(int age, String name) {
		this.age = age;
		this.name = name;
	}

	/**
	 * Compares this object with the specified object for order. Returns a
	 * negative integer, zero, or a positive integer as this object is less
	 * than, equal to, or greater than the specified object.
	 */
	@Override
	public int compareTo(Dog dog0) {
		// sort by age:
		int ageDifference = this.age - dog0.age;
		return ageDifference;
	}

	@Override
	public String toString() {
		return this.name + "," + this.age;
	}

}
