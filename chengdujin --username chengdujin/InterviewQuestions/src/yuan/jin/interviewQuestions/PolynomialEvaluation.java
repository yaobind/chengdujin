package yuan.jin.interviewQuestions;

/**
 * In a computer program it is sometimes necessary to evaluate polynomial
 * functions. The basic method to evaluate the polynomial function is to
 * "plug in" the value of x into the polynomial. Horner's method however results
 * in fewer multiplications and additions and is faster and more precise when
 * using float variables.
 * 
 * Horner's method is commonly used to find the roots of a polynomial function.
 * However it can also be used to evaluate the polynomial function for a given
 * value of x.
 * 
 * The main goal of the Horner scheme is to reduce the number of multiplications
 * needed by isolating the variable (in this case x).
 * 
 * f(x) = a4*x^4 + a3*x^3 + a2*x^2 + a1*x + a0 can be converted to: f(x) =
 * (((a4*x + a3)*x +a2)*x + a1)*x + a0
 * 
 * http://knol.google.com/k/using-horner-to-evaluate-polynomial-functions#
 * 
 * @author Yuan
 * 
 */

public class PolynomialEvaluation {
	static int[] a = {2, 3, 4, -7, 5};
	
	static int evaluate(int x) {
		int result = 0;
		for (int i = a.length - 1; i >= 0; --i)
			result = result * x + a[i];
		return result;
	}

	public static void main(String[] args) {
		// 2 + 3*x + 4*x^2 - 7*x^3 + 5*x^4
		int result = evaluate(4);
		System.out.println(result);
	}

}
