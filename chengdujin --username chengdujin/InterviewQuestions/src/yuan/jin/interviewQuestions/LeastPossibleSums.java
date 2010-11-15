package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * Finds the smallest sum possible from a list of integers (or any number type)
 * to satisfy a goal.
 * 
 * http://www.dreamincode.net/code/snippet5466.htm
 * 
 * @author Yuan
 * 
 */
public class LeastPossibleSums {

	static int findLeastPossibleSum(int[] num, int goal) {
		int total = 0;

		for (int a : num)
			total += a;

		if (goal > total)
			return -1;
		else {
			Arrays.sort(num);
			int sum = total;
			int n = 0;
			for (int i = num.length - 1; i >= 0; i--) {
				n += num[i];
				for (int j = num.length - 1; j >= 0; j--) {
					if (i != j)
						n += num[j];
					if (n >= goal && n < sum) {
						sum = n;
						n = 0;
					}
				}
				n = 0;
			}
			return sum;
		}
	}

	public static void main(String[] args) {
		int[] input = { -1, 3, 5, 0, 1, -2, 7, 8 };
		System.out.println(findLeastPossibleSum(input, 2));
	}

}
