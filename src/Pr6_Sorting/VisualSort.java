import java.awt.Color;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * VisualSort
 * 
 * A simple GraphicsProgram visualizing two sort algorithms.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class VisualSort extends GraphicsProgram {
	public final int WIDTH = 400;
	public final int HEIGHT = 400;

	private GRect[] lines;

	public void run() {
		setSize(WIDTH, HEIGHT + 8);

		int[] arrOfInts = { 5, 55, 2, 7, 45, 3, 1, 8, 23, 12 };
		setup(arrOfInts);
		displayArray(arrOfInts);
		// selectionSort(arrOfInts);
		insertionSort(arrOfInts);
	}

	private void setup(int[] arrOfInts) {
		lines = new GRect[arrOfInts.length];
		for (int i = 0; i < arrOfInts.length; i++) {
			lines[i] = new GRect(0, 0);
			lines[i].setFilled(true);
			lines[i].setFillColor(Color.YELLOW);
			add(lines[i]);
		}
	}

	private void displayArray(int[] arrOfInts) {
		int yStep = HEIGHT / (lines.length + 1);
		for (int i = 0; i < lines.length; i++) {
			lines[i].setBounds(0, yStep * i, 5 * arrOfInts[i], yStep);
		}
	}

	public void insertionSort(int arr[]) {
		for (int i = 1; i < arr.length; i++) {
			int cur = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > cur) {
				arr[j + 1] = arr[j];
				arr[j] = cur;
				j--;
			}

			displayArray(arr);
			pause(100);
		}
	}

	public void selectionSort(int arr[]) {
		for (int i = 0; i < arr.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			swap(arr, i, min);

			displayArray(arr);
			pause(100);
		}
	}

	private void swap(int arr[], int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
