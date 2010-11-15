package yuan.jin.interviewQuestions;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of n integers, such that each number in the array appears
 * exactly twice, except for three numbers (say a, b and c) which appear exactly
 * once.
 * 
 * In O(n) time and O(1) space find a,b and c.
 * http://discuss.techinterview.org/default.asp?interview.11.694323.19
 * 
 * @author Yuan
 * 
 */
public class ThreeOddNumbers {

	/**
	 * lowbit is to find the digit of the first 1.
	 * 
	 */
	private static int lowbit(int input) {
		return input & ~(input - 1);
	}

	private static List<Integer> findNumbers(int[] input) {
		List<Integer> results = new ArrayList<Integer>();

		int s = 0;
		for (int x = 0; x < input.length; x++) {
			s ^= input[x];
		}

		int flips = 0;
		for (int x = 0; x < input.length; x++) {
			flips ^= lowbit(input[x] ^ s);
		}
		// the distinguished 1 that differs a, b & c
		flips = lowbit(flips);

		int a = 0;
		for (int x = 0; x < input.length; x++) {
			if (lowbit(input[x] ^ s) == flips)
				a ^= input[x];
		}
		// so far, one of three is found.
		results.add(a);

		// find the other two numbers.
		s ^= a;
		int flips2 = lowbit(s);
		int b = 0;
		int c = 0;

		for (int x = 0; x < input.length; x++) {
			if (input[x] == a)
				continue;

			if ((input[x] & flips2) == 0)
				b ^= input[x];
			else
				c ^= input[x];
		}

		results.add(b);
		results.add(c);

		return results;
	}

	public static void main(String[] args) {
		int[] seq = { 1, 4, 1, 2, 2, 7, 5, 5, 8, 8, 0, 3, 0 };

		System.out.println(findNumbers(seq));
	}

}
