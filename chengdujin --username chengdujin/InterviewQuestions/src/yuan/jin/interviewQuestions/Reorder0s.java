package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * Given an array of integers, {1,0,2,0,3,0,0,4,5,6,7,0,0,0}, Move all 0s at the
 * end like (1,2,3,4,5,6,7,0,0,0,0,0,0,0}
 * 
 * @author Yuan
 * 
 */
public class Reorder0s {

	static void reorder(int[] a) {
		int k = 0;
		for (int i = 0; i < a.length; i++)
			if (a[i] != 0)
				a[k++] = a[i];
		Arrays.fill(a, k, a.length, 0);
	}

	public static void main(String[] args) {
		int[] input = { 1, 0, 2, 0, 3, 0, 0, 4, 5, 6, 7, 0, 0, 0 };
		reorder(input);
		System.out.println(Arrays.toString(input));
	}

}
