package yuan.jin.interviewQuestions.sort;

import java.util.Arrays;

/**
 * http://en.wikibooks.org/wiki/Algorithm_implementation/Sorting/Counting_sort
 * 
 * @author Yuan
 * 
 */
public class CountingSort {

	static void countingSort(int[] a, int low, int high) {
		int[] counts = new int[high - low + 1];
		for (int x : a)
			counts[x - low]++;

		int current = 0;
		for (int i = 0; i < counts.length; i++) {
			Arrays.fill(a, current, current + counts[i], i + low);
			current += counts[i];
		}
	}
	
	public static void countingSort2(int[] a, int maxVal) {
		int[] bucket = new int[maxVal + 1];

		for (int i = 0; i < bucket.length; i++)
			bucket[i] = 0;
		for (int i = 0; i < a.length; i++)
			bucket[a[i]]++;

		int outPos = 0;
		for (int i = 0; i < bucket.length; i++)
			for (int j = 0; j < bucket[i]; j++)
				a[outPos++] = i;
	}

	public static void main(String[] args) {
		int a[] = { 1, 2, 4, 6, 7, 7, 0, 6, 3, 5 };
		countingSort(a, 0, a.length - 1);
//		countingSort2(a, 3);
		System.out.println(Arrays.toString(a));
	}

}
