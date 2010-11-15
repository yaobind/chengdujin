package yuan.jin.interviewQuestions;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Cracking the Coding Interview 8.7 Given an infinite number of quarters (25
 * cents), dimes (10 cents), nickels (5 cents) and pennies (1 cent), write code
 * to calculate the number of ways of representing n cents
 * 
 * @author Yuan
 * 
 */
public class CoinProblem {

	static int[] changes = { 1, 10, 25, 50 };
	static ArrayList<Integer> results = new ArrayList<Integer>();

	static void makeChange(int index, int sum) {
		if (sum == 0) {
			System.out.println(results);
			return;
		}
		for (int i = index; i < changes.length; i++) {
			if (sum >= changes[i]) {
				results.add(changes[i]);
				// System.out.println(": " + results.toString() + " - " + (sum -
				// a[i]));
				makeChange(i, sum - changes[i]);
				// System.out.println(sum + " B: " + results.toString());
				results.remove(results.size() - 1);
				// System.out.println(sum + " A: " + results.toString());
			}
		}
	}

	static int[] change, d;

	// http://oucsace.cs.ohiou.edu/~razvan/courses/cs404/lecture19.pdf
	// O(sum*denom.length)
	// other resources:
	// <http://www.algorithmist.com/index.php/Coin_Change> (Frobenous numbers)
	// <http://en.wikipedia.org/wiki/Coin_problem#Frobenius_numbers_for_small_n>
	// <http://placementsindia.blogspot.com/2007/12/solutions-to-few-google-top-interview.html>
	static void coinChange(int sum, int[] denom) {
		change[0] = 0;
		for (int i = 1; i < sum; i++) {
			change[i] = Integer.MAX_VALUE / 2;
			for (int j = 0; j < denom.length; j++) {
				if (i >= denom[j] && (1 + change[i - denom[j]] < change[i])) {
					change[i] = 1 + change[i - denom[j]];
					d[i] = denom[j];
				}
			}
		}
	}

	static void print(int[] denom, int j) {
		if (j > 0) {
			System.out.print(denom[j] + " ");
			print(denom, j - denom[j]);
		}
	}

	public static void main(String[] args) {
		int sum = 31;
		change = new int[sum];
		d = new int[sum];
		coinChange(sum, changes);
		System.out.println(Arrays.toString(change));
		print(d, 30);
		// makeChange(0, 30);
	}

}
