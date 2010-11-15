package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * Design an algorithm to find all pairs of integers within an array which sum
 * to a specified value
 * 
 * CareerCup 4.19.11
 * CareerCup Top 150 Questions 1.2.4
 * 
 * Compare with PythogoreanTriple and TripletsSum2K
 * 
 * @author Yuan
 * 
 */
public class PairSum {
	
	// deprecated.
	static void findPairs(int[] seq, int val) {
		Arrays.sort(seq);

		for (int i = 0, j = (seq.length - 1); i != j;) {
			if ((seq[i] + seq[j]) == val) {
				System.out.println(seq[i] + " + " + seq[j]);
				return;
			} else if ((seq[i] + seq[j]) > val)
				j--;
			else
				i++;
		}

		System.out.println("Whoops! We can't find one pair!");
	}
	
	static void printPairSums(int[] array, int sum) {
		Arrays.sort(array);
		int first = 0;
		int last = array.length - 1;
		while (first < last) {
			int s = array[first] + array[last];
			if (s == sum) {
				System.out
						.println(array[first] + "+" + array[last] + "=" + sum);
				++first;
				--last;
			} else {
				if (s < sum)
					++first;
				else
					--last;
			}
		}
	}

	public static void main(String[] args) {
//		int[] input = { -1, 2, 0, 4, 1, 2, -1, -4, 3 };
		int[] input = { 9, 3, 6, 5, 7, -1, 13, 14, -2, 12, 0 };
//		printPairSums(input, 5);
		printPairSums(input, 13);
	}

}
