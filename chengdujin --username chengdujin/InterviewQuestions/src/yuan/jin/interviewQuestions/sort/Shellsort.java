package yuan.jin.interviewQuestions.sort;

import java.util.Arrays;

/**
 * Best O(n) Worst depends on gap sequence. Best known: O(nlog2n)
 * 
 * http://rosettacode.org/wiki/Sorting_algorithms/Shell_sort#Java
 * 
 * @author Yuan
 * 
 */
public class Shellsort {

	/** Shell sort using Shell's (original) gap sequence: n/2, n/4, ..., 1. */
	public static void shellSort(int[] array) {
		// loop over the gaps
		for (int gap = array.length / 2; gap > 0; gap /= 2) {
			// do the insertion sort
			for (int i = gap; i < array.length; i++) {
				int val = array[i];
				int j;
				for (j = i; j >= gap && (array[j - gap] > val); j -= gap)
					array[j] = array[j - gap];
				array[j] = val;
			}
		}
	}
	
	/** Implementation from Algorithms 4e by Robert Sedgewick*/
	public static void shellSort2(int[] array) {
		int N = array.length;
		int h = 1;
		while (h < N/3) h = 3*h + 1;
		while (h >= 1) {
			for (int i = h; i < N; i++)
				for (int j = i; j >= h && array[j] < array[j-h]; j -= h) {
					int t = array[j];
					array[j] = array[j-h];
					array[j-h] = t;
				}
			h = h/3;
		}
	}

	public static void main(String[] args) {
		int[] seq = { 3, 5, 0, 2, 1, 6 };

		shellSort2(seq);
		System.out.println(Arrays.toString(seq));
	}

}
