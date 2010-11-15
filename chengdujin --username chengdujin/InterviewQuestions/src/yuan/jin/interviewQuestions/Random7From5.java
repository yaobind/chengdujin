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

	public static void main(String[] args) {
		int[] randomHouse = new int[100];
		for (int i = 0; i < randomHouse.length; i++)
			randomHouse[i] = random7();
		
		System.out.println(Arrays.toString(randomHouse));
	}

}
