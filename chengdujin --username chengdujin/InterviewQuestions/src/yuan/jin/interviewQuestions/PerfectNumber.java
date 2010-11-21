package yuan.jin.interviewQuestions;

/**
 * A perfect number is a positive integer that is the sum of its proper positive
 * divisors excluding the number itself. Equivalently, a perfect number is a
 * number that is half the sum of all of its positive divisors (including
 * itself).
 * 
 * http://rosettacode.org/wiki/Perfect_numbers
 * 
 * @author Yuan
 * 
 */
public class PerfectNumber {

	static boolean perf(int n) {
		int sum = 0;
		for (int i = 1; i < n; i++)
			if (n % i == 0)
				sum += i;
		return sum == n;
	}

	public static void main(String[] args) {
		System.out.println("" + perf(10));
	}

}
