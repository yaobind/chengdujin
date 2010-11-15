package yuan.jin.interviewQuestions.sort;

/**
 * For any element, i, in a list of size n, the following is true:
 *    -Parent element index = i/2 (floor(i))
 *    -Left child element index = 2i
 *    -Right child element index = 2i + 1
 */

import java.util.ArrayList;

/**
 * A Java implementation of the heap sorting algorithm. This version uses an
 * array to represent the heap. Uses a binary representation of the number of
 * elements currently in the heap to find the pathway to the next
 * insertion/removal site.
 * 
 * http://www.dreamincode.net/code/snippet2444.htm
 * 
 * @author Yuan
 * 
 */
class HeapSort {
	private ArrayList<Integer> heap; // Array containing the heap
	private int length; // Current length of the heap;

	/**
	 * Calculates the index of the left child of the passed parent
	 * 
	 * @param parent
	 *            parent node to find left child for
	 * @return index of the left child of the passed parent
	 */
	private int getLeftIndex(int parent) {
		return (2 * parent);
	}

	/**
	 * Calculates the index of the right child of the passed parent
	 * 
	 * @param parent
	 *            parent node to find the right child for
	 * @return index of the right child of the passed parent
	 */
	private int getRightIndex(int parent) {
		return ((2 * parent) + 1);
	}

	/**
	 * Calculates the index of the parent node for the passed node
	 * 
	 * @param node
	 *            node to find the parent of
	 * @return index of the parent node of the passed node
	 */
	private int getParentIndex(int node) {
		return (node / 2);
	}

	/**
	 * Returns the value stored at the passed index
	 * 
	 * @param index
	 *            index to look for a value
	 * @return the value stored at the passed index
	 */
	private Integer getValueAtIndex(int index) {
		return heap.get(index - 1);
	}

	/**
	 * Swaps the values stored at the two passed indecies
	 * 
	 * @param index_a
	 *            first index to be used in the swap
	 * @param index_b
	 *            second index to be used in the swap
	 */
	private void swap(int index_a, int index_b) {
		// Convert indecies to an n-1 scheme
		index_a--;
		index_b--;

		Integer tmp = heap.get(index_a);
		heap.set(index_a, heap.get(index_b));
		heap.set(index_b, tmp);
	}

	/**
	 * Inserts a the passed value into the list, and then percolates that number
	 * upwards accordingly
	 * 
	 * @param value
	 *            value to be inserted into the heap
	 */
	private void insert(Integer value) {
		heap.add(value);

		int current = ++length; // Set current to length + 1
		int parent = getParentIndex(current); // Get index of the parent element

		// While the current index is not the head, and the parent is greater
		// than the child
		while ((current > 1)
				&& (getValueAtIndex(current) <= getValueAtIndex(parent))) {
			swap(current, parent); // Swap the values
			current = parent; // Move current index to parent index
			parent = getParentIndex(current); // Re-calculate parent index from
												// current
		}
	}

	/**
	 * Pops the head of the heap, and then moves the last value addded to the
	 * top of the heap. Finally, that value is percolated downwards accordingly
	 * 
	 * @return head of the heap
	 */
	private Integer pop() {
		Integer ret = getValueAtIndex(1); // Value to be returned

		int current = 1; // Index to begin percolating
		heap.set(0, getValueAtIndex(length)); // Move last value added to the
												// head
		heap.remove(--length); // Remove the last value added

		// While a left child exists from the current
		while (getLeftIndex(current) <= length) {
			int left = getLeftIndex(current); // Calculate the left child's
												// index
			int right = getRightIndex(current); // Calculate the right child's
												// index
			int move = left; // Index where the percolating number will move
								// next

			// If the left child is larger than the right child
			if ((right <= length)
					&& (getValueAtIndex(left) > getValueAtIndex(right))) {
				move = right; // Set the next move to the right child
			}

			// If the parent is greater than the lesser of the two children
			if (getValueAtIndex(current) >= getValueAtIndex(move)) {
				swap(current, move); // Swap the parent with the lesser of the
										// two children
			}

			current = move; // Set current index to the position moved to
		}

		return ret; // Return top of the heap
	}

	/**
	 * Will traverse the passed list element by element, adding each element it
	 * encounters to the heap. The, until the heap is empty, it will pull the
	 * top, or minimum, value off of the heap and place it into a sorted list.
	 * 
	 * @param unsorted
	 *            unsorted set of integers to be sorted
	 * @return the sorted version of the passed unsorted integer set
	 */
	public Integer[] Sort(Integer[] unsorted) {
		Integer[] sorted = new Integer[unsorted.length];

		// Insert each number in the list into the heap
		for (int i = 0; i < unsorted.length; i++) {
			insert(unsorted[i]);
		}

		// Remove the smallest value from the heap until the heap is empty
		for (int i = 0; i < unsorted.length; i++) {
			sorted[i] = pop();
		}

		return sorted;
	}

	/**
	 * Default constructor for this class
	 */
	public HeapSort() {
		length = 0;
		heap = new ArrayList();
	}
}
