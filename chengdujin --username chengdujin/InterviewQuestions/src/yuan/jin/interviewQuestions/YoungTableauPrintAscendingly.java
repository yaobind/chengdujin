package yuan.jin.interviewQuestions;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix of integers where every row is sorted and every column is
 * sorted. Print all elements in sorted order. Cannot use merging of arrays.
 * Solution should be better than O(n2logn). Thanks for the enlightenment from
 * lyle.smu.edu/~saad/courses/cse3358/ps5/problemset5sol.pdf
 * 
 * @author Yuan
 * 
 */
public class YoungTableauPrintAscendingly {
	static int[][] test = { { 2, 4, 9, 11 }, { 3, 8, 16, 17 },
			{ 5, 14, 20, 25 }, { 12, 15, 23, 30 } };

	static int[][] test2 = { { 5, 14, 20, 25 }, { 2, 4, 9, 11 },
			{ 12, 15, 23, 30 }, { 3, 8, 16, 17 } };

	static int m = test[0].length;
	static int n = test.length;

	static void youngify(int[][] matrix, int i, int j) {
		int smalli = i;
		int smallj = j;

		if ((i + 1) < m && matrix[i][j] > matrix[i + 1][j]) {
			smalli = i + 1;
			smallj = j;
		}
		if ((j + 1) < n && matrix[smalli][smallj] > matrix[i][j + 1]) {
			smalli = i;
			smallj = j + 1;
		}
		if (smalli != i || smallj != j) {
			int temp = matrix[i][j];
			matrix[i][j] = matrix[smalli][smallj];
			matrix[smalli][smallj] = temp;
			youngify(matrix, smalli, smallj);
		}
	}

	static int extractMin(int[][] matrix) {
		int result = matrix[0][0];
		matrix[0][0] = Integer.MAX_VALUE;
		youngify(matrix, 0, 0);
		return result;
	}

	public static void main(String[] args) {
		List<Integer> sorted = new ArrayList<Integer>();
		while (sorted.size() < m * n)
			sorted.add(extractMin(test));
		System.out.println(sorted.toString());
	}

}
