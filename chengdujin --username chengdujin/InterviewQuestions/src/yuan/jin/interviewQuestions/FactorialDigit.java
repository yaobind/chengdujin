package yuan.jin.interviewQuestions;

/**
 * Find number of digits in a factorial of number Without calculating factorial
 * of a number is there a way to number how many digits will be there in the
 * result.
 * 
 * The logarithm of the factorial can be used to calculate the number of digits
 * that the factorial number will take:
 * 
 * log (n!) = log(n * (n-1) * (n -2 ) *......1)
 * 
 * = log(n) + log (n-1) + ...... log(1)
 * 
 * = SUM (log k) ( 1<= k <=n)
 * 
 * http://inder-gnu.blogspot.com/2009/08/find-number-of-digits-in-factorial-of.
 * html
 * 
 * http://stackoverflow.com/questions/1113167/can-one-know-how-large-a-factorial
 * -would-be-before-calculating-it
 * 
 * @author Yuan
 * 
 */
public class FactorialDigit {

	static int factorialDigits(int n) {
		double result = 0;
		for (int i = 1; i <= n; i++)
			result += Math.log10(i);
		return (int) Math.floor(result) + 1;
	}

	public static void main(String[] args) {
		System.out.println(factorialDigits(4));
	}

}
