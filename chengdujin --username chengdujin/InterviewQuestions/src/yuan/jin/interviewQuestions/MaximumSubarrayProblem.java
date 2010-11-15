package yuan.jin.interviewQuestions;

/**
 * CareerCup Top 150 Questions 2.3
 * 
 * This snippet finds the maximum non-negative sum of contiguous elements in an
 * array, using Kadane's algorithm. This algorithm is an example of a greedy
 * algorithm, the running time is O(n)
 * 
 * @author Yuan
 * 
 */
public class MaximumSubarrayProblem {

	private static int maxSum(int[] seq) {
		int current = seq[0];
		int max = seq[0];

		for (int i = 1; i < seq.length; i++) {
			current += seq[i];
			if (current > 0) {
				if (current > max)
					max = current;
			} else
				current = 0;
		}

		return max;
	}

	public static void main(String[] args) {
		int[] sequence = { 2, -8, 3, -2, 4, -10 };
		int[] seq = { -2, 11, -4, 13, -5, 2 };
		int output = maxSum(seq);
		System.out.println(output);
	}

}
