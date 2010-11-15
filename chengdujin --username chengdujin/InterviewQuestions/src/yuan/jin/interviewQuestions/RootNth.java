package yuan.jin.interviewQuestions;

/**
 * Finding the nth Root of a Number. Finds the nth root of a number with a
 * simple algorithm
 * 
 * http://www.dreamincode.net/code/snippet4611.htm
 * 
 * @author Yuan
 * 
 */
public class RootNth {

	public static double nRoot(int n, double num, double epsilon) {
		int ctr = 0;
		double root = 1;
		if (n <= 0)
			return Double.longBitsToDouble(0x7ff8000000000000L);
		// 0x7ff8000000000000L is the Java constant for NaN (Not-a-Number)
		if (num == 0) // this step is just to reduce the needed iterations
			return 0;
		while ((Math.abs(Math.pow(root, n) - num) > epsilon) && (ctr++ < 1000)) {
			root = ((1.0 / n) * (((n - 1.0) * root) + (num / Math.pow(root,
					n - 1.0))));
		}
		return root;
	}

	public static void main(String[] args) {
		double epsilon = 0.00000000001;
		System.out.println(nRoot(81, 2, epsilon));
	}

}
