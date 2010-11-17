package yuan.jin.interviewQuestions;

/**
 * how to find out whether a number N is perfect square or not
 * 
 * @author Yuan
 * 
 */
public class SquareOrNot {
	static int binarySearch(int n) {
		int l = 1;
		int u = n;
		while (l < u) {
			int m = (l + u) / 2;
			if (n == m * m)
				return m;
			else if (n > m * m)
				l += 1;
			else
				u -= 1;
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(binarySearch(141));
	}

}
