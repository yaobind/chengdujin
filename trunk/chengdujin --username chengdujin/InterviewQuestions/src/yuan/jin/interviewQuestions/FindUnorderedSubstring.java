package yuan.jin.interviewQuestions;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given two arrays, say A: {4, 1, 6, 2, 8, 9, 5, 3, 2, 9, 8, 4, 6} B:
 * {6, 1, 2, 9, 8}, where B contains elements which are in A in consecutive
 * locations but may be in any order. Find their starting and ending indexes in
 * A. (Be careful of duplicate numbers).
 * 
 * @author Yuan
 * 
 */
public class FindUnorderedSubstring {
	static List<Integer> list = new ArrayList<Integer>();

	static int findStartIndex(int[] a, int[] b) {
		for (int i = 0; i < a.length; i++) {
			if ((a.length - i) > b.length) {
				int hash = a[i];
				for (int j = 1; j < b.length; j++)
					hash ^= a[i + j];
				list.add(hash);
			}
		}
		int bhash = b[0];
		for (int i = 1; i < b.length; i++)
			bhash ^= b[i];

		for (int i = 0; i < list.size(); i++)
			if (list.get(i).equals(bhash))
				return i;

		return -1;
	}

	public static void main(String[] args) {
		int[] a1 = { 4, 7, 1, 6, 2, 8, 9, 5, 3, 2, 9, 8, 4, 6 };
		int[] a2 = { 6, 1, 2, 9, 8 };
		System.out.println(findStartIndex(a1, a2));
	}

}
