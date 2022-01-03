package week1.linkedLists;

import java.util.LinkedList;
import java.util.Objects;

/*
 * SD2x Homework #1
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class LinkedListUtils {
	
	public static void insertSorted(LinkedList<Integer> list, int value) {
		// There is no list
		if (list == null) return;

		// If the list is empty, just add the value
		if (list.isEmpty()) {
			list.add(value);
			return;
		}

		int tmp = 0;

		// Search for the right place to add the value
		for (Integer listValue: list) {
			if (value < listValue) {
				list.add(tmp, value);
				return;
			}
			tmp++;
		}

		// Adding at the end
		list.addLast(value);
	}

	public static void removeMaximumValues(LinkedList<String> list, int N) {
		// There is no list
		if (list == null) return;

		// N is negative
		if (N < 0) return;

		// Doing the operation N times
		for (int i = 0; i < N; i++) {
			// List is empty
			if (list.isEmpty()) return;

			// Copy of the list so we can modify it
			LinkedList<String> tmp = new LinkedList<>(list);

			// Getting the first value
			String val1 = list.getFirst();
			for (String val2 : tmp) {
				if (val1.compareTo(val2) < 0) {
					val1 = val2;
				}
			}

			// Removing each occurrence of the value
			int index = 0;
			for (String val2 : tmp) {
				if (val1.compareTo(val2) == 0) {
					list.remove(index);
					index--;
				}
				index++;
			}
		}
	}
	
	public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) {
		// There is no list
		if (one == null || two == null) return false;

		// List is empty
		if (one.isEmpty() || two.isEmpty()) return false;

		// Get the length of one
		int lenOne = 0;
		for (Integer element: one) {
			lenOne++;
		}

		// Get the length of two
		int indexTwo = 0;
		int lenTwo = 0;
		for (Integer element: two) {
			lenTwo++;
		}

		// Loop inside of one
		for (int indexOne = 0; indexOne < lenOne; indexOne++) {
			// If the value of one is equal to the value of two
			if (Objects.equals(one.get(indexOne), two.get(indexTwo))) {
				// We now search the next value of two
				indexTwo++;
				// If it was the last value of two, success!
				if (indexTwo == lenTwo) {
					return true;
				}
			} else if (indexTwo != 0) {
				// If the value of one isn't the same as the value of two after finding at least the first value
				return false;
			}
		}
		// End of one, then there isn't two in one
		return false;
	}
}
