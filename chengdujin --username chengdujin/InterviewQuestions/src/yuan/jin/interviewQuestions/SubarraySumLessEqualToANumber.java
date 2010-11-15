package yuan.jin.interviewQuestions;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given an array, find the longest subarray which the sum of the subarray less
 * or equal than the given MaxSum. int[] FindMaxSumArray(int[] array, int
 * maxsum) for example, given array: {1, -2, 4, 5, -2, 6, 7} maxsum=7 the result
 * would be: {1,-2, 4, -2, 6}
 * 
 * @author Yuan
 * 
 */
public class SubarraySumLessEqualToANumber {

	private static int findMax(Object[] integers) {
		int maxind = 0;

		for (int i = 0; i < integers.length; i++) {
			if (((Integer) integers[i]) > ((Integer) integers[maxind]))
				maxind = i;
		}

		return maxind;
	}

	private static Object[] findSum(int[] input, int num) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int sum = 0;

		for (int i = 0; i < input.length; i++) {
			if ((sum + input[i]) <= num) {
				list.add(input[i]);
				sum += input[i];
			} else {
				int maxind = findMax(list.toArray());
				if ((Integer) list.get(maxind) > input[i])
					list.set(maxind, input[i]);
			}
		}

		return list.toArray();
	}

	public static void main(String[] args) {
		int[] seq = { 1, -2, 4, 5, -2, 6, 7 };

		System.out.println(Arrays.toString(findSum(seq, 7)));
	}

}
