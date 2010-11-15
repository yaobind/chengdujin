package yuan.jin.interviewQuestions.sort;

import java.util.Arrays;

/**
 * Best O(n) Worst depends on gap sequence. Best known: O(nlog2n)
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

	public static void main(String[] args) {
		int[] seq = { 3, 5, 0, 2, 1, 6 };

		shellSort(seq);
		System.out.println(Arrays.toString(seq));
	}

}
