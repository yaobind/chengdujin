package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * Find pythagorean triplets in an array in O(N)
 * 
 * Compare with PairSum and TripletsSum2K
 * 
 * @author Jeroen Steenbeeke
 */
public class PythagoreanTriple {

	static void compute(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			// System.out.print(arr[i]);
			int j = i + 1;
			int k = n - 1;
			while (j < k) {
				// System.out.print(" " + arr[j] + "-" + arr[k]);
				int sum = arr[i] + arr[j];
				if (sum < arr[k])
					k--;
				else if (sum > arr[k])
					j++;
				else {
					System.out.println(arr[i] + "+" + arr[j] + "=" + arr[k]);
					j++;
					k--;
				}
			}
			// System.out.println();
		}
	}

	public static void main(String[] args) {
		int[] input = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		for (int i = 0; i < input.length; i++)
			input[i] = (int) Math.pow(input[i], 2);
		System.out.println(Arrays.toString(input));
		compute(input);
	}
}
