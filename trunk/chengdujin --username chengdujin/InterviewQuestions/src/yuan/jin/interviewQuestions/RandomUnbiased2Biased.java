package yuan.jin.interviewQuestions;

import java.util.Random;

/**
 * Suppose that you want to output 0 with probability 1/2 and 1 with probability
 * 1/2. At your disposal is a procedure BIASED-RANDOM, that outputs either 0 or
 * 1. It outputs 1 with some probability p and 0 with probability 1 – p, where 0
 * < p < 1, but you do not know what p is. Give an algorithm that uses
 * BIASED-RANDOM as a subroutine, and returns an unbiased answer, returning 0
 * with probability 1/2 and 1 with probability 1/2. What is the expected running
 * time of your algorithm as a function of p?
 * 
 * SOLUTION:
 * 
 * If we run BIASED-RANDOM twice, we might get of of following result: 00, 01,
 * 10, 11. And the probabilities for getting each of them is: (1-p) square,
 * (1-p)*p, p*(1-p), p square respectively. We can see that the probabilities
 * for getting 01 equals 10. So, if we can constraint the output to either 01 or
 * 10, we have a UNBIASED-RANDOM that can return 01 or 10 with probability of
 * 1/2 each.
 * 
 * And we can simply replace 01, 10 with 0 and 1 to get desired function. How
 * can we constraint the result in 01 and 10? We can use the similar idea used
 * in Ex5.1-2, that’s abandoning any result that doesn’t belong to 01 and 10
 * till we get one of them. And there’s a risk in this algorithm. If p is very
 * close to 1 or 0, we may need to try a looooot of times to get either 01 or 10
 * which makes a very poor performance. To get around this, we can invoke
 * BIASED-RANDOM more times. As we know, the probability of getting a full 0 or
 * 1 permutation is p power the number of times invoking BIASED-RANDOM. And
 * because p is between 0 and 1, the more we invoke BIASED-RANDOM, the less will
 * the probability be, consequently the quicker we don’t get full 0 or 1.
 * 
 * http://rxwen.wordpress.com/2009/12/04/ex5-1-3-of-introduction-to-algorithms/
 * 
 * @author Yuan
 * 
 */
public class RandomUnbiased2Biased {

	static int biased() {
		int result = 0;
		Random gen = new Random();
		if (gen.nextInt() % 100 >= 60)
			result = 1;
		else
			result = 0;
		return result;
	}

	static int unbiased() {
		int result = 0;
		int temp = 0;
		while (true) {
			temp = 0;
			temp = biased() * 10 + biased();
			if (temp == 10 || temp == 1)
				break;
		}
		if (temp == 10)
			result = 1;
		else
			result = 0;
		return result;
	}

	public static void main(String[] args) {
		// biased
		int c0 = 0, c1 = 0;
		for(int i = 0; i < 10000; i++) {
			int temp = biased();
			if (temp == 0)
				c0++;
			else if (temp == 1)
				c1++;
			else {
				System.out.println("Error!");
				break;
			}
		}
		System.out.println("[biased]   " + c0 + ":" + c1);
		
		// unbiased
		c0 = 0;
		c1 = 0;
		for(int i = 0; i < 10000; i++) {
			int temp = unbiased();
			if (temp == 0)
				c0++;
			else if (temp == 1)
				c1++;
			else {
				System.out.println("Error!");
				break;
			}
		}
		System.out.println("[unbiased] " + c0 + ":" + c1);
	}

}
