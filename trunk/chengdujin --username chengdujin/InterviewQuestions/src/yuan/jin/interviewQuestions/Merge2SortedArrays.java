package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * http://www.algolist.net/Algorithms/Merge/Sorted_arrays
 * 
 * @author Yuan
 * 
 */
public class Merge2SortedArrays {

	static int[] merge(int[] a, int[] b) {
		int[] c = new int[a.length + b.length];
		int i, j, k, m, n;
		i = 0;
		j = 0;
		k = 0;
		m = a.length;
		n = b.length;
		while (i < m && j < n) {
			if (a[i] <= b[j]) {
				c[k] = a[i];
				i++;
			} else {
				c[k] = b[j];
				j++;
			}
			k++;
		}
		if (i < m)
			for (int p = i; p < m; p++) {
				c[k] = a[p];
				k++;
			}
		else
			for (int p = j; p < n; p++) {
				c[k] = b[p];
				k++;
			}
		return c;
	}

	public static void main(String[] args) {
		int[] arr1 = { 0, 1, 2, 3, 5, 6 };
		int[] arr2 = { 0, 1, 2, 4, 3, 5, 6, 7, 8, 9, 10, 11, 12 };
		System.out.println(Arrays.toString(merge(arr1, arr2)));
	}

}
