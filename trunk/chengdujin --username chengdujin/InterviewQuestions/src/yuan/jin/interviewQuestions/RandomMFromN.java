package yuan.jin.interviewQuestions;

import java.util.Arrays;
import java.util.Random;

/**
 * CareerCup Top 150 Questions 17.5
 * @author Yuan
 *
 */
public class RandomMFromN {
	private static int[] pickMRandomly(int[] array, int m) {
		Random gen = new Random();
		int[] subset = new int[m];
		for (int j = 0; j < m; ++j) {
			int index = j + gen.nextInt(array.length - j);
			subset[j] = array[index];
			array[index] = array[j];
		}
		return subset;
	}

	public static void main(String[] args) {
		int[] seq = { 11, 17, 5, 8, 6, 4, 7, 12, 3 };
		int[] val = pickMRandomly(seq, 4);
		System.out.println(Arrays.toString(val));
	}

}
