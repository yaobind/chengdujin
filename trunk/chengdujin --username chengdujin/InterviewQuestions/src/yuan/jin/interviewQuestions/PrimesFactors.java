package yuan.jin.interviewQuestions;

/**
 * Find all the prime factors of a number entered
 * 
 * @author Yuan
 * 
 */
public class PrimesFactors {
	static void primeFactors(int N) {
		for (int i = 2; i <= Math.sqrt(N); )
			if (N % i == 0) {
				System.out.println(i);
				N /= i;
			} else
				i++;
		System.out.println(N);
	}

	public static void main(String[] args) {
		primeFactors(156);
	}

}
