package yuan.jin.interviewQuestions;

import java.util.Arrays;

public class MatrixTurn90Degrees {

	static int[][] test2 = { 
		{ 1, 2, 3, 4 }, 
		{ 5, 6, 7, 8 }, 
		{ 9, 10, 11, 12 },
		{ 13, 14, 15, 16 } };
	static int[][] test3 = { 
		{ 0, 1, 2, 3, 4 }, 
		{ 5, 6, 7, 8, 9 },
		{ 10, 11, 12, 13, 14 }, 
		{ 15, 16, 17, 18, 19 } };

	public static void rotate(int[][] matrix) {
		for (int i = 0; i < matrix[0].length / 2; i++) {
			int first = i;
			int last = matrix.length - first - 1;
			for (int j = first; j < last; j++) {
				int offset = j - first;
				int temp = matrix[first][j];
				matrix[first][j] = matrix[last - offset][first];
				matrix[last - offset][first] = matrix[last][last - offset];
				matrix[last][last - offset] = matrix[j][last];
				matrix[j][last] = temp;
			}
		}
	}

	public static void main(String[] args) {
		rotate(test2);
		for (int i = 0; i < test2.length; i++)
			System.out.println(Arrays.toString(test2[i]));
	}

}
