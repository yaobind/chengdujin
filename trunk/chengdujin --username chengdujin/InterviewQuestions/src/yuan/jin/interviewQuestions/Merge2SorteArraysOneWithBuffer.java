package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * You are given two sorted arrays, A and B, and A has a large enough buffer at
 * the end to hold B. Write a method to merge B into A in sorted order.
 * 
 * @author Yuan
 * 
 */
public class Merge2SorteArraysOneWithBuffer {

	static void merge(int[] a, int[] b, int asize, int bsize) {
		int i = asize - 1;
		int j = bsize - 1;
		int k = asize + bsize - 1;
		while (i >= 0 && j >= 0) {
			if (a[i] >= b[j]) {
				a[k] = a[i];
				i--;
				k--;
			} else {
				a[k] = b[j];
				j--;
				k--;
			}
		}
		while (j >= 0) {
			a[k] = b[j];
			k--;
			j--;
		}
	}

	public static void main(String[] args) {
		int[] input1 = { 1, 3, 9, 16, 17, 17, 0, 0, 0, 0, 0, 0 };
		int[] input2 = { 3, 3, 4, 5, 11, 13 };
		merge(input1, input2, 6, 6);
		System.out.println(Arrays.toString(input1));
	}

}
