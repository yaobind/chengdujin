package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * Given an int array of positive and negative numbers, rearrange it in O(1)
 * extra space such that all positive numbers are on the left and all negative
 * numbers are on the right, and the relative order of positive numbers, and the
 * relative order of the negative numbers are the same as in the input. Can it
 * be done in O(n)?
 * 
 * http://www.careercup.com/question?id=4099194
 * 
 * @author Yuan
 * 
 */
public class NegativePositiveRearrange {

	static int[] reorder(int[] a) {
		int [] b = new int [a.length];
		int i = 0, j =0;
		for(; i < a.length; i++)
			if(a[i] < 0)
				b[j++] = a[i];
		for(i =0; i < a.length; i++)
			if(a[i] >= 0)
				b[j++] = a[i];
		return b;
	}

	public static void main(String[] args) {
		int[] input = { -5, -7, 3, 4, -2, 9, -1, 7 };
//		 int[] input = { 0, -4, 37, 4, 66, 5, -2, -3, 65, -6, 60, 39, 50, -8,
//		 42, 33, 20, 29, 53, 36, 0, -20 };
		System.out.println(Arrays.toString(input));
		System.out.println(Arrays.toString(reorder(input)));
	}

}
