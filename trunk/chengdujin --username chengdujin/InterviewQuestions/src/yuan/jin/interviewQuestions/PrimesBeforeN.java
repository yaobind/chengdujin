package yuan.jin.interviewQuestions;

import java.util.Arrays;


/**
 * Sieve of Erastosthenes
 * http://en.wikibooks.org/wiki/Algorithm_Implementation/Mathematics
 * /Prime_number_generation
 * 
 * @author Yuan
 * 
 */
public class PrimesBeforeN {
	static boolean[] generatePrimes(int a) {
		boolean[] b = new boolean[a];
		for (int i = 0; i < b.length; i++)
			b[i] = true;
		for (int i = 2; i < Math.sqrt(a); i++)
			if (b[i])
				for (int j = i; j * i < a; j++)
					b[j * i] = false;
		return b;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(generatePrimes(20)));
	}

}
