package yuan.jin.interviewQuestions;

import java.util.Arrays;
import java.util.Random;

public class Random7From5 {

	static int random5() {
		Random gen = new Random();
		int random5 = 1 + gen.nextInt(5);

		return random5;
	}

	static int random7() {
		int random7 = 0;

		do {
			random7 = random5() ^ random5();
		} while (random7 == 0);

		return random7;
	}

	/**
	 * standard method:
	 * 
	 * http://www.ihas1337code.com/2010/11/rejection-sampling.html
	 * 
	 * the idea is to have a 5*5 matrix, filling 1-7 in the matrix like:
	 * 
	 * **1 2 3 4 5
	 * 
	 * 1 1 2 3 4 5
	 * 
	 * 2 6 7 1 2 3
	 * 
	 * 3 4 5 6 7 1
	 * 
	 * 4 2 3 4 5 6
	 * 
	 * 5 7 0 0 0 0
	 * 
	 * To get 3:6 which should be 14, we need to have col + (row - 1) * 5. And 1
	 * + (idx - 1) % 7 is to avoid 7 % 7 -> 0
	 * 
	 * 
	 * @return
	 */
	static int random72() {
		int row, col, idx;
		do {
			row = random5();
			col = random5();
			idx = col + (row - 1) * 5;
		} while (idx > 21);
		return 1 + (idx - 1) % 7;
	}

	public static void main(String[] args) {
		int[] randomHouse = new int[100];
		for (int i = 0; i < randomHouse.length; i++)
			randomHouse[i] = random72();
		System.out.println(Arrays.toString(randomHouse));
	}

}
