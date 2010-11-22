package yuan.jin.interviewQuestions;

/**
 * Newton-Raphson algorithm, 或者Newton's iteration, Newton's method.
 * 
 * @author Yuan
 * 
 */
public class Sqrt {

	private static double computeSquareRoot(int value) {
		double estimated = value / 2;
		do {
			estimated = (estimated + value / estimated) / 2;
		} while (estimated * estimated - value > 0.00000001);
		return estimated;
	}

	public static void main(String[] args) {
		double root = computeSquareRoot(25);
		System.out.println(root);
	}
}
