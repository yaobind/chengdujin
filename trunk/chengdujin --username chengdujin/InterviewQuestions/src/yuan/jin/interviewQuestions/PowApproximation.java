package yuan.jin.interviewQuestions;

/**
 * Compute the power of a double by approximation
 * 
 * @author Yuan
 * 
 */
public class PowApproximation {

	static double pow(double a, double b) {
		double result = 1;
		double c = 1;
		while (b > 0) {
			while (c > b)
				c /= 2;
			result *= Math.pow(a, c);
			b -= c;
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(pow(81, 0.33333333));
	}

}
