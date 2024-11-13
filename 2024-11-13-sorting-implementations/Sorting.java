// Sorting Algorithm Notes

import java.util.*;

class Sorting {
	public static ArrayList<Integer> bubbleSort(ArrayList<Integer> a) {
		boolean noSwaps;
		while (true) {
			noSwaps = true;
			for (int i = 0; i < a.size() - 1; i++) {
				if (a.get(i) > a.get(i + 1)) {
					noSwaps = false;
					int z = a.get(i); // placeholder, swapping a[i] and a[i + 1]
					a.set(i, a.get(i + 1));
					a.set(i + 1, z);
				}
			}
			if (noSwaps) {
				break;
			}
		}     
		return a;       
	}

	public static void main(String[] args) {
		// Create random integer ArrayList
		final int length = 2000;
		ArrayList<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < length; i++) {
			list.add((int) Math.floor(Math.random() * length));
		}

		System.out.println("UNSORTED:");
		System.out.println(list);
		System.out.println("");

		long start = System.nanoTime();

		final ArrayList<Integer> sortedList = Sorting.bubbleSort(list);

		long end = System.nanoTime();
		long total = end - start;

		System.out.println("SORTED:");
		System.out.println(sortedList + " (" + total + " ns)");
	}	
}