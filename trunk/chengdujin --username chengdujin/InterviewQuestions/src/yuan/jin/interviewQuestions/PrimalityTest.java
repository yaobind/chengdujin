package yuan.jin.interviewQuestions;

/**
 * http://www.algolist.net/Algorithms/Number_theoretic/Primality_test_naive
 * 
 * @author Yuan
 * 
 */
public class PrimalityTest {

	static boolean isPrime(int number) {
		if (number == 1)
			return false;
		if (number == 2)
			return true;
		if (number % 2 == 0)
			return false;
		for (int d = 3; d <= (int) Math.sqrt(number); d++)
			if (number % d == 0)
				return false;
		return true;
	}

	public static void main(String[] args) {
		System.out.println(isPrime(57));
	}

}
