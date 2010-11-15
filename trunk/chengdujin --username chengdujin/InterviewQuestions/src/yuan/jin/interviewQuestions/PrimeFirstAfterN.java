package yuan.jin.interviewQuestions;

/**
 * Find first prime number greater than number given in input
 * 
 * @author Yuan
 * 
 */
public class PrimeFirstAfterN {

	static void firstPrime(int n) {
		int m = n + 1;
		for (int i = 2; i <= Math.sqrt(m); i++)
			if (m % i == 0) {
				m = m + 1;
				i = 1;
			}
		System.out.println(m);
	}

	public static void main(String[] args) {
		firstPrime(41);
	}

}
