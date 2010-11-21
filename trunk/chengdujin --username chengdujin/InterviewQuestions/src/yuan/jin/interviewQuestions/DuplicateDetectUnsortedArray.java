package yuan.jin.interviewQuestions;

import java.util.Random;

/**
 * You are given an array of elements. Some/all of them are duplicates. Find
 * them in 0(n) time and 0(1) space. Property of inputs - Number are in the
 * range of 1..n where n is the limit of the array.
 * 
 * http://inder-gnu.blogspot.com/2010/11/find-duplicates-in-array-of-elements.
 * html
 * 
 * @author Yuan
 * 
 */
public class DuplicateDetectUnsortedArray {

	static int[] randomInput(int n) {
		int[] input = new int[n];
		Random gen = new Random();
		int i = n;
		while (--i >= 0)
			input[i] = 1 + gen.nextInt(n);
		return input;
	}

	static void detect(int[] a) {
		int i = 0;
		while (i < a.length) {
			int j = a[i]; // 6
			if (j > 0 && j <= a.length) {
				int temp = a[j - 1]; // 2
				if (temp >= j + a.length) {
					a[i] = 0;
					i++;
				} else
					a[i] = temp;
				if (a[j - 1] != j)
					a[j - 1] += j + a.length;
				else
					a[j-1] += a.length;
			} else
				i++;
		}
		// printing
		for (i = 0; i < a.length; i++) {
			int base = i + 1 + a.length;
			int fois = a[i] / base;
			if (fois > 1)
				System.out.println((i+1) + " is repeated " + fois + " times");
		}
	}

	public static void main(String[] args) {
		int[] input = { 3, 2, 6, 4, 4, 6 };
		detect(input);
	}
}
