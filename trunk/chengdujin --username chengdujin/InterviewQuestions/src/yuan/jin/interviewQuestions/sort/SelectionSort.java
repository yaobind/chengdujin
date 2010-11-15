package yuan.jin.interviewQuestions.sort;

import java.util.Arrays;

public class SelectionSort {

	private static int[] selectionSort(int[] input) {

		for (int i = 0; i < input.length-1; i++) {
			for (int j = i + 1; j < input.length; j++) {
				if (input[i] > input[j]) {
					int temp = input[i];
					input[i] = input[j];
					input[j] = temp;
				}
			}
		}
		
		return input;
	}

	public static void main(String args[]) {
		int[] seq = { 1, 0, 5, 3, 6, 2 };

		System.out.println(Arrays.toString(selectionSort(seq)));
	}

}
