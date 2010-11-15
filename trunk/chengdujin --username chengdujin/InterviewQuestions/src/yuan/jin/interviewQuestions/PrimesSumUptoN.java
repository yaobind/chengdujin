package yuan.jin.interviewQuestions;

/**
 * Find the sum of all prime numbers up to a given positive integer This code
 * takes a positive integer as input from the user and outputs the sum of all
 * prime numbers less than or equal to the integer entered.
 * 
 * http://www.dreamincode.net/code/snippet4510.htm
 * 
 * @author Yuan
 * 
 */
public class PrimesSumUptoN {

	public static boolean isPrime(int n) {
		boolean flag = true;
		if (n == 2)
			return true;
		else {
			for (int i = 2; i < n; i++)
				if (n % i == 0)
					flag = false;
			return flag;
		}
	}

	public static int sumPrimes(int n) {
		int sum = 0;
		if (n == 1)
			return sum;
		else
			for (int p = 2; p <= n; p++)
				if (isPrime(p))
					sum = sum + p;
		return sum;
	}

	public static void main(String[] args) {
		System.out.println(sumPrimes(10));
	}

}
