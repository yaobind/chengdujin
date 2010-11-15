package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * Divide a list of numbers into group of consecutive numbers but their original
 * order should be preserved? e.g. 8,2,4,7,1,0,3,6 2,4,1,0,3 and 8,7,6 obviously
 * in shortest time and space.
 * 
 * http://www.careercup.com/question?id=65732
 * 
 * @author Yuan
 * 
 */
public class IndexSorting {

	static void quickSort(int[] a, int b[], int low, int high) {
		int i = low, j = high;
		int pivot = a[low + (high - low) / 2];
		while (i <= j) {
			while (a[i] < pivot)
				i++;
			while (a[j] > pivot)
				j--;
			if (i <= j) {
				int temp = a[i];
				int temp2 = b[i];
				a[i] = a[j];
				b[i] = b[j];
				a[j] = temp;
				b[j] = temp2;
				i++;
				j--;
			}
		}
		if (low < j)
			quickSort(a, b, low, j);
		if (i < high)
			quickSort(a, b, i, high);
	}

	static void indexSort(int[] a) {
		int[] b = new int[a.length];
		for (int i = 0; i < b.length; i++)
			b[i] = i;

		quickSort(a, b, 0, a.length - 1);

		int start = 0;
		int prev = a[0];
		for (int i = 1; i < a.length; i++) {
			if (a[i] != (prev + 1)) {
				quickSort(b, a, start, i - 1);
				start = i;
			}
			prev = a[i];
		}
		quickSort(b, a, start, a.length-1);
		System.out.println(Arrays.toString(a));
	}

	public static void main(String[] args) {
		int[] input = { 8, 2, 4, 7, 1, 0, 3, 6 };
		indexSort(input);
	}

}
