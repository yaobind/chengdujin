package yuan.jin.interviewQuestions;

import java.util.Arrays;
import java.util.Random;

/**
 * You have a random n function, generate random [m, n]
 * 
 * @author Yuan
 * 
 */
public class RandomM2NFromN {
	
	static int random30To50() {
		Random gen = new Random();
		int random30To50 = 30 + gen.nextInt(50-30+1);
		return random30To50;
	}

	public static void main(String[] args) {
		int[] randomHouse = new int[20];
		for (int i = 0; i < randomHouse.length; i++)
			randomHouse[i] = random30To50();
		Arrays.sort(randomHouse);
		System.out.println(Arrays.toString(randomHouse));
	}

}
