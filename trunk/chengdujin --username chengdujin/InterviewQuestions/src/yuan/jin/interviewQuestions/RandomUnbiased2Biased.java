package yuan.jin.interviewQuestions;

import java.util.Random;

/**
 * Given a function which generates a pure, unbiased random integer between two
 * given integers. Using this function write a function which will return 1 or 2
 * randomly with a probability of 60% and 40% respectively.
 * 
 * http://www.rawkam.com/?p=1362
 * 
 * @author Yuan
 * 
 */
public class RandomUnbiased2Biased {

	public static void main(String[] args) {
		int[] seq = { 1, 1, 1, 2, 2 };
		Random gen = new Random();
		int c1 = 0, c2= 0;
		int i = 100000;
		while (--i >= 0) {
			int temp = seq[gen.nextInt(4)];
			if (temp == 1)
				c1++;
			else
				c2++;
		}
		System.out.println(c1 + ":" + c2);
	}

}
