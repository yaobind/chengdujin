package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * Suppose we have an array a1, a2, ..., an, b1, b2, ..., bn. Implement an
 * algorithm to change this array to a1, b1, a2, b2, ..., an, bn.
 * 
 * @author Yuan
 * 
 */
public class ArraySectionSwap {

	private static void rightRotate(int[] seq, int start, int window) {
		int temp = seq[start + window];
		for (int i = (start + window - 1); i > (start - 1); i--)
			seq[i + 1] = seq[i];
		seq[start] = temp;
	}

	public static void main(String[] args) {
		int[] input = { 1, 2, 3, 4, 5, 10, 20, 30, 40, 50 };
		for (int i = 1, j = (input.length / 2 - 1); j > 0; i += 2, j--)
			rightRotate(input, i, j);
		System.out.println(Arrays.toString(input));
	}

}
