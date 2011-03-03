package yuan.jin.interviewQuestions.sort;

import java.util.Arrays;

public class InsertionSort {

	// increasing
	static int[] insertionSort(int[] input) {
		int j = 0, key = 0;
		for (int i = 1; i < input.length; i++) {
			key = input[i];
			j = i - 1;
			while (j >= 0 && input[j] > key) {
				input[j + 1] = input[j];
				j--;
			}
			input[j + 1] = key;
		}
		return input;
	}
	
	// decreasing
	static int[] insertionSortDesc(int[] input) {
		int j = 0, key = 0;
		for (int i = 1; i < input.length; i++) {
			key = input[i];
			j = i - 1;
			while (j >= 0 && input[j] < key) {
				input[j + 1] = input[j];
				j--;
			}
			input[j + 1] = key;
		}
		return input;
	}

	public static void main(String[] args) {
		int[] seq = { 1, 0, 5, 7, 4, 2 };
		System.out.println(Arrays.toString(insertionSortDesc(seq)));
	}

}
