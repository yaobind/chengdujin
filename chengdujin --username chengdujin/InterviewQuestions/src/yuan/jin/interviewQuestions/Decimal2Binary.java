package yuan.jin.interviewQuestions;

/**
 * Write a program to convert a decimal number into a binary number
 * @author Yuan
 *
 */
public class Decimal2Binary {

	static void decimal2binary(int n) {
		int m;
		if (n > 0) {
			m = n % 2;
			decimal2binary(n >>= 1);
			System.out.print(m);
		}
	}

	public static void main(String[] args) {
		decimal2binary(24);
	}

}
