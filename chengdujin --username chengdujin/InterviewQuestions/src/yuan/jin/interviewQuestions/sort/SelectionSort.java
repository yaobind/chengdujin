package yuan.jin.interviewQuestions.sort;

import java.util.Arrays;

public class SelectionSort {

	static int[] selectionSort(int[] input) {
		for (int i = 0; i < input.length - 1; i++) {
			int smallest = i;
			for (int j = i + 1; j < input.length; j++)
				if (input[smallest] > input[j])
					smallest = j;
			int temp = input[i];
			input[i] = input[smallest];
			input[smallest] = temp;
		}
		return input;
	}

	public static void main(String args[]) {
		int[] seq = { 1, 0, 5, 3, 6, 2 };
		System.out.println(Arrays.toString(selectionSort(seq)));
	}

}
