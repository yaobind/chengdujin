package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * The method takes two int value matrices, mat1 and mat2. Then returns a new
 * matrix, newMat, in which each cell's value is the result of the
 * multiplication of mat1 and mat2 value in that exact cell. The method throws
 * an IllegalArgumentException if mat1 and mat2 are not at the same length.
 * 
 * @author Yuan
 * 
 */
public class MatrixMultiplication {

	static int dotProduct(int[][] a, int apos, int[][] b, int bpos) {
		int result = 0;
		for (int i = 0; i < a[0].length; i++)
			result += a[apos][i] * b[i][bpos];
		return result;
	}

	static int[][] multiply(int[][] mat1, int[][] mat2) {
		int[][] newMat = new int[mat1.length][mat2[0].length];
		for (int i = 0; i < mat1.length; i++)
			for (int j = 0; j < mat2[0].length; j++)
				newMat[i][j] = dotProduct(mat1, i, mat2, j);
		return newMat;
	}

	public static void main(String[] args) {
		// example from
		// http://zh.wikipedia.org/zh-cn/%E7%9F%A9%E9%99%A3%E4%B9%98%E6%B3%95
		int[][] input1 = { { 1, 0, 2 }, { -1, 3, 1 } };
		int[][] input2 = { { 3, 1 }, { 2, 1 }, { 1, 0 } };
		int[][] result = multiply(input1, input2);
		for (int i = 0; i < result.length; i++)
			System.out.println(Arrays.toString(result[i]));
	}
}
