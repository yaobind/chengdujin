package yuan.jin.interviewQuestions;

import java.util.Set;
import java.util.TreeSet;

/**
 * The Beauty of Programming 2.18
 * 
 * @author Yuan
 * 
 */
public class SplitGroup {
	static Set<Integer> split(int[] input) {
		Set<Integer> result = new TreeSet<Integer>();

		int sum = 0;
		for (int i = 0; i < input.length; i++)
			sum += input[i];

		// matrix[i][j] means if we can find i elements so that their sum is j
		boolean[][] matrix = new boolean[input.length][sum / 2 + 1];
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < (sum / 2 + 1); j++)
				matrix[i][j] = false;
		matrix[0][0] = true;

		for (int i = 1; i < input.length; i++) {
			for (int k = 1; (k <= i && k <= input.length / 2); k++) {
				for (int v = 1; v <= sum / 2; v++) {
					if ((v >= input[i]) && matrix[k - 1][v - input[i]]) {
						if (v == sum / 2)
							result.add(input[i]);
						matrix[k][v] = true;
					}
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] sequence = { 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14 };
		System.out.println(split(sequence).toString());
	}

}
