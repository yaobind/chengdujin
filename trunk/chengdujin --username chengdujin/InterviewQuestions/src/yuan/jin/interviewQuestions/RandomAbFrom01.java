package yuan.jin.interviewQuestions;

import java.util.Random;

/**
 * Describe an implementation of the procedure RANDOM(a, b) that only makes
 * calls to RANDOM(0, 1). What is the expected running time of your procedure,
 * as a function of a and b? ( RANDOM(0,1) returns either 0 or 1 with the same
 * probability, and RANDOM(a,b) is supposed to return any number between [a,b]
 * with the same probability.)
 * 
 * SOLUTION:
 * 
 * The question is similar to flip coins. Each time we flip a coin, we have the
 * same probability to get either 0 or 1. If we flip the coin for several times,
 * the probability of getting each permutation of 0 and 1 is the same. So, if we
 * can use each permutation to represent a distinct number between a and b, we
 * shall get the desired RANDOM function.
 * 
 * http://rxwen.wordpress.com/2009/12/02/ex5-1-2-of-introduction-to-algorithms/
 * 
 * @author Yuan
 * 
 */
public class RandomAbFrom01 {

	static int ran(int a, int b) {
		int result = 0;
		int i = 0;
		// compute log2(b-a) via log10(b-a)/log10(2)
		double t = Math.ceil(Math.log10((float) b - a) / Math.log10((float) 2)
				+ 1);
		int one = 1, zero = ~1;
		Random gen = new Random();
		while (i < t) {
			result = result << 1;
			if (gen.nextInt(2) == 1)
				result |= one;
			else
				result &= zero;
			if (i == (t - 1) && result > (b - a)) {
				result = 0;
				i = 0;
			}
			++i;
		}
		return result + a;
	}

	public static void main(String[] args) {
		System.out.println(ran(2, 8));
	}

}
