package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * int[] Seq1 = { 9, 5, 2, 8, 7, 3, 1, 6, 4 }; 
 * int[] Seq2 = { 11, 17, 5, 8, 6, 4, 7, 12, 3 }; 
 * int[] Seq3 = { 1, 9, 3, 8, 11, 4, 5, 6, 4, 19, 7, 1, 7 };
 * int[] Seq4 = { 1, 3, 2, 4, 3, 5, 4, 6 };
 * int[] Seq5 = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
 * int[] Seq6 = { 1, 5, 8, 3, 6, 7 };
 * int[] Seq7 = { 1, 5, 8, 3 };
 * int[] Seq8 = { 11, 17, 5, 8, 6, 4, 7, 12, 13 };
 */

/**
 * CareerCup Top 150 Questions 1.9
 */
public class LongestIncreasingSubsequence {

	public static int binarySearchNear(int[] sequence, int end, int value) {
		int low = 0, high = end;
		if (value < sequence[0])
			return 0;
		if (value > sequence[end])
			return end + 1;
		while (low <= high) {
			if (low == high)
				return low;
			else {
				int middle = (low + high) / 2;
				if (sequence[middle] == value)
					return middle;
				if (value < sequence[middle])
					high = middle;
				else
					low = middle + 1;
			}
		}
		return -1;
	}

	public static int LISLength(int[] sequence) {
		int size = sequence.length;
		int d[] = new int[size];
		d[0] = sequence[0];
		int end = 0;
		for (int i = 1; i < size; i++) {
			int index = binarySearchNear(d, end, sequence[i]);
			if (index <= end)
				d[index] = sequence[i];
			else {
				end++;
				d[end] = sequence[i];
			}
		}
		return end + 1;
	}

	public static int[] LIS(int[] sequence) {
		int size = sequence.length;
		int end = 0;

		int mat[][] = new int[size][size];
		mat[0][0] = sequence[0];

		int d[] = new int[size];
		d[0] = sequence[0];

		for (int i = 1; i < size; i++) {
			int index = binarySearchNear(d, end, sequence[i]);

			mat[index][index] = sequence[i];
			for (int j = 0; j < index; j++)
				mat[index][j] = mat[index - 1][j];

			d[index] = sequence[i];
			for (int j = 0; j < index; j++)
				d[j] = mat[index - 1][j];

			if (index > end)
				end++;
		}

		int ans[] = new int[end + 1];
		for (int j = 0; j <= end; j++)
			ans[j] = mat[end][j];

		return ans;
	}

	public static void main(String[] args) {
		int[] Seq = { 11, 17, 5, 8, 6, 4 };
//		int[] Seq = { 9, 5, 2, 8, 7, 3, 1, 6, 4 };
//		int[] Seq = { 11, 17, 5, 8, 6, 4, 7, 12, 3 };
//		int[] Seq = { 1, 9, 3, 8, 11, 4, 5, 6, 4, 19, 7, 1, 7 };
//		int[] Seq = { 1, 3, 2, 4, 3, 5, 4, 6 };
//		int[] Seq = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
//		int[] Seq = { 1, 5, 8, 3, 6, 7 };
//		int[] Seq = { 1, 5, 8, 3 };
//		int[] Seq = { 11, 17, 5, 8, 6, 4, 7, 12, 13 };

		// int m = LISLength(seq);
		// System.out.println("m = " + m);

		int[] d = LIS(Seq);
		System.out.println(Arrays.toString(d));

	}

}
