package yuan.jin.interviewQuestions.sort;

import java.util.Arrays;

public class QuickSort {

	private static void quickSort(int[] a, int low, int high) {
	    int i = low;
	    int h = high;
	    int m = a[(h-i)/2 + i];
	    while (i <= h) {
	        while (a[i] < m)
	            i++;
	        while (a[h] > m)
	            h--;
	        if (i <= h) {
	            int temp = a[h];
	            a[h] = a[i];
	            a[i] = temp;
	            i++;
	            h--;
	        }
	    }
	    if (low < h)
	        quickSort(a, low, h);
	    if (high > i)
	        quickSort(a, i, high);
	}

	public static void main(String[] args) {
		int[] seq = { 11, 5, 7, 4, 9, 0, 2, 3, 12, 1, 10, 6, 8 };
//		int[] seq = {1, 12, 5, 26, 7, 14, 3, 7, 2};
		quickSort(seq, 0, seq.length-1);
		System.out.println(Arrays.toString(seq));
	}

}
