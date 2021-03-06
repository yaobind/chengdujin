package yuan.jin.interviewQuestions;

/**
 * Given a binary matrix, find out the maximum size square sub-matrix with all
 * 1s.
 * 
 * @author Yuan
 * 
 */
public class MaxSquare1 {

	static int[][] input = { 
		{ 0, 1, 1, 0, 1 }, 
		{ 1, 1, 0, 1, 0 },
		{ 0, 1, 1, 1, 0 }, 
		{ 1, 1, 1, 1, 0 }, 
		{ 1, 1, 1, 1, 1 },
		{ 0, 0, 0, 0, 0 } };

	static int[][] result = new int[input.length][input[0].length];

	public static void main(String[] args) {
		for (int i = 0; i < input[0].length; i++)
			result[0][i] = input[0][i];
		for (int i = 0; i < input.length; i++)
			result[i][0] = input[i][0];

		int max = 0, may = 0;
		for (int i = 1; i < input.length; i++) {
			for (int j = 1; j < input[0].length; j++) {
				if (input[i][j] == 1) {
					int temp = result[i][j - 1] < result[i - 1][j] ? result[i][j - 1]
							: result[i - 1][j];
					result[i][j] = 1 + (temp < result[i - 1][j - 1] ? temp
							: result[i - 1][j - 1]);
					if (result[i][j] > result[max][may]) {
						max = i;
						may = j;
					}
				} else
					result[i][j] = 0;
			}
		}

		// print out
		for (int i = 0; i < result[max][may]; i++) {
			for (int j = 0; j < result[max][may]; j++)
				System.out.print(input[max - i][may - j]);
			System.out.println();
		}
	}
}
