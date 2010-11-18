package yuan.jin.interviewQuestions;

/**
 * Given an NxN matrix of positive and negative integers, write code to find the
 * sub-matrix with the largest possible sum.
 * 
 * @author Yuan
 * 
 */
public class MaxSumMatrix {
	static int[][] input = { 
		{ 2, -3, 4, 1 }, 
		{ -1, 5, 0, 2 }, 
		{ -6, 2, 7, -4 }, 
		{ 4, -3, 5, 6 } };

	static int[][] procest = new int[4][4];

	static void preprocess(int[][] original) {
		for (int i = 0; i < original.length; i++)
			for (int j = 0; j < original[0].length; j++) {
				if (i == 0 && j == 0)
					procest[i][j] = original[i][j];
				else if (i == 0)
					procest[i][j] = procest[i][j - 1] + original[i][j];
				else if (j == 0)
					procest[i][j] = procest[i - 1][j] + original[i][j];
				else
					procest[i][j] = original[i][j] + procest[i - 1][j]
							+ procest[i][j - 1] - procest[i - 1][j - 1];
			}
	}
	
	static int compute(int row1, int col1, int row2, int col2) {
		if (row1 == 0 && col1 == 0)
			return procest[row2][col2];
		else if (row1 == 0)
			return procest[row2][col2] - procest[row2][col1 - 1];
		else if (col1 == 0)
			return procest[row2][col2] - procest[row1 - 1][col2];
		else
			return procest[row2][col2] - procest[row2][col1 - 1]
					- procest[row1 - 1][col2] + procest[row1 - 1][col1 - 1];
	}
	// dummy code
	public static int method1(int[][] original) {
		int maxArea = Integer.MIN_VALUE;
		int rowCount = original.length; 
		int columnCount = original[0].length;
		for (int row1 = 0; row1 < rowCount; row1++) {
			for (int row2 = row1; row2 < rowCount; row2++) { 
				for (int col1 = 0; col1 < columnCount; col1++) {
					for (int col2 = col1; col2 < columnCount; col2++) {
						maxArea = Math.max(maxArea, compute(row1, row2, col1, col2));
					}
				}
			}
		}
		return maxArea;
	}
	// more efficient
	static int method2(int[][] A, int n, int m) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++) {
				int current = compute(i, m-1, j, m-1);
				int currMax = current;
				for (int k = m - 2; k >= 0; k--) {
					if (current < 0)
						current = 0;
					current += compute(i, k, j, k);
					if (current > currMax)
						currMax = current;
				}
				if (currMax > max) {
					System.out.println("" + i + ":" + j + "-" + m);
					max = currMax;
				}
			}
		return max;
	}

	public static void main(String[] args) {
		preprocess(input);
		System.out.println(method1(input));
		System.out.println(method2(input, 4, 4));
	}

}
