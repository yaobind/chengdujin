package yuan.jin.interviewQuestions;

/**
 * CareerCup Top 150 Questions 1.1
 * 
 * @author Yuan
 * 
 */
public class Fibonacci {
	// Beauty of Programming 2.9
	// O(1)
	// http://www.dreamincode.net/code/snippet2114.htm
	static long fib(int n) {
		double termA = Math.pow((1 + Math.sqrt(5)) / 2, n);
		double termB = Math.pow((1 - Math.sqrt(5)) / 2, n);
		double factor = 1 / Math.sqrt(5);
		return Math.round(factor * (termA - termB));
	}
	
	// O(2^n)
	static int recursiveFibon(int input) {
		if (input == 0)
			return 0;
		else if (input == 1)
			return 1;
		else if (input > 1)
			return recursiveFibon(input - 1) + recursiveFibon(input - 2);
		else
			return -1;
	}

	// O(n)
	static int iterativeFibon(int input) {
		if (input == 0)
			return 0;
		else if (input == 1)
			return 1;
		else {
			int v1 = 1;
			int v2 = 1;
			for (int i = 3; i <= input; i++) {
				int v3 = v1 + v2;
				v1 = v2;
				v2 = v3;
			}
			return v2;
		}
	}

	public static void main(String[] args) {
		// int output = recursiveFibon(3);
		// int output = iterativeFibon(2);
		int output = (int) fib(8);
		if (output > -1)
			System.out.println(output);
		else
			System.out.println("Error!");
	}

}
