package yuan.jin.interviewQuestions;

/**
 * Count the number of numbers up to n which are both square and cube. e.g., for
 * 1 < n < 1000 answer is 2 (64, 729)
 * 
 * Any number that is both a square and a cube is a sixth power. How many sixth
 * powers of less than 1000? Take the sixth root of 999, drop any fraction, and
 * subtract one to eliminate 1 (which otherwise would be considered). 次数:
 * floor(999^(1/6)) - 1.
 * 
 * @author Yuan
 * 
 */
public class NumberBothSquareAndCube {

	public static void main(String[] args) {
		int i = 2;
		int n = 10000;
		while (true) {
			int j = (int) Math.pow(i++, 6);
			if (j <= n)
				System.out.println(i + ": " + j);
			else
				break;
		}
	}
}
