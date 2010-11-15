package yuan.jin.interviewQuestions;

import java.util.PriorityQueue;

public class KthLargest {

	/**
	 * find the Middle element in the Array without sorting it. This function
	 * uses a modified version of QuickSort, where we only consider the one half
	 * of the array. This is a recursive function where we look at some section
	 * of the array (Concerned Array) at a time.
	 * 
	 */
	static int findElementAtRank(int[] arr, int low, int high, int rank) {
		int pivot = low;
		int l = low;
		int h = high;

		if (l <= h) {
			while (l < h) {
				while (arr[l] <= arr[pivot])
					l++;
				while (arr[h] > arr[pivot])
					h--;

				if (l < h) {
					// Swapping the left and right
					int temp = arr[l];
					arr[l] = arr[h];
					arr[h] = temp;
				}
			}
			// Swapping the pivot with the high pointer
			int temp = arr[pivot];
			arr[pivot] = arr[h];
			arr[h] = temp;
		}

		if (rank < h) {
			return findElementAtRank(arr, low, h - 1, rank);
		} else if (rank > h) {
			return findElementAtRank(arr, h + 1, high, rank);
		} else {
			return arr[h];
		}
	}
	
	static PriorityQueue<Integer> maxHeap;
	
	static void largestK(int[] arr, int n) {
		maxHeap = new PriorityQueue<Integer>(n);
		for (int i = 0; i < n; i++)
			maxHeap.offer(arr[i]);
		for (int i = n+1; i < arr.length; i++) {
			if (maxHeap.peek() < arr[i]) {
				maxHeap.remove(maxHeap.peek());
				maxHeap.add(arr[i]);
			}
		}
		for (int i = n; i > 0; i--) 
			System.out.print(maxHeap.poll() + " ");
	}

	public static void main(String[] args) {
//		int[] seq = { 1, 9, 3, 8, 11, 4, 5, 6 };
//		int[] seq = { 1, 6, 3, 5, 4 };
		int[] seq = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
		int key = 5;
		System.out.println(findElementAtRank(seq, 0, seq.length - 1, (seq.length-key)));
		largestK(seq, key);
	}

}
