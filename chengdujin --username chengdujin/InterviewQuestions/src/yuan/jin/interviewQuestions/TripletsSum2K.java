package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * Given a set S of n integers, are there elements a, b, c in S such that a + b
 * + c = 0? Find all unique triplets in the set which gives the sum of zero.
 * 
 * http://www.ihas1337code.com/2010/04/finding-all-unique-triplets-that-sums.
 * html
 * 
 * Given an array of n elements, find if there is a subset of 3 elements sum up
 * to value T with time complexity O(nlgn).
 * 
 * Compare with PythogoreanTriple and PairSum
 * 
 * @author Yuan
 * 
 */
public class TripletsSum2K {

	static void findTriplets(int[] arr, int key) {
		Arrays.sort(arr);
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			int j = i + 1;
			int k = n - 1;
			while (j < k) {
				int sum = arr[i] + arr[j] + arr[k];
				if (sum < key)
					j++;
				else if (sum > key)
					k--;
				else {
					System.out.println(arr[i] + "+" + arr[j] + "+" + arr[k]
							+ "=" + key);
					j++;
					k--;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] input = { -1, 2, 0, 4, 1, 2, -1, -4, 3 };
		findTriplets(input, 5);
	}

}
