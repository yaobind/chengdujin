package yuan.jin.interviewQuestions.sort;

import java.util.Arrays;

/**
 * Other references:
 * http://www.cs.unb.ca/~bremner/teaching/java_examples/snippet/MergeSort.java/
 * 
 * @author Yuan
 * 
 */
public class MergeSort {

	static void mergeSort(int[] input, int low, int high) {
		if (low >= high)
			return;
		int middle = low + (high - low) / 2;
		int low1 = low;
		int high1 = middle;
		int low2 = middle + 1;
		int high2 = high;
		
		mergeSort(input, low1, middle);
		mergeSort(input, middle + 1, high2);

		while ((low1 <= high1) && (low2 <= high2)) {
			if (input[low1] < input[low2])
				low1++;
			else {
				int temp = input[low2];
				for (int k = low2 - 1; k >= low1; k--)
					input[k + 1] = input[k];
				input[low1] = temp;
				low1++;
				high1++;
				low2++;
			}
		}
	}

	public static void main(String[] args) {
		int[] seq = { 3, 5, 0, 2, 1, 6 };
//		int[] seq = { 11, 5, 7, 4, 9, 0, 2, 3, 12, 1, 10, 6, 8 };
		mergeSort(seq, 0, seq.length - 1);
		System.out.println(Arrays.toString(seq));
	}

}
