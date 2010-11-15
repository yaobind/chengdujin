package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * Write code to remove duplicates in a sorted array, in place (you can not
 * create a new array).
 * 
 * http://discuss.fogcreek.com/techInterview/default.asp?cmd=show&ixPost=1070&
 * ixReplies=5
 * 
 * @author Yuan
 * 
 */
public class DuplicateRemovalSortedArray {

	static int[] dedup(int[] a) {
		int i, k = 0;
		for (i = 1; i < a.length; i++)
			if (a[k] != a[i]) {
				a[k + 1] = a[i];
				k++;
			}
		Arrays.fill(a, k + 1, a.length, '\0');
		return a;
	}

	public static void main(String[] args) {
		int[] dups = { 0, 0, 1, 2, 2, 2, 3, 4, 5, 8, 8, 9, 9, 9, 9, 10 };
		System.out.println(Arrays.toString(dedup(dups)));
	}

}
