package yuan.jin.interviewQuestions;

/**
 * Write you own Power without using multiplication(*) and division(/)
 * operators.
 * 
 * http://geeksforgeeks.org/?p=7654
 * 
 * @author Yuan
 * 
 */
public class Pow {

	static int pow(int a, int b) {
		if (b != 0)
			return multiply(a, pow(a, b - 1));
		else
			return 1;
	}

	static int multiply(int x, int y) {
		if (y != 0)
			return x + multiply(x, y - 1);
		else
			return 0;
	}

	public static void main(String[] args) {
		System.out.println(pow(5, 3));
	}

}
