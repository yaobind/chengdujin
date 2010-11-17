package yuan.jin.interviewQuestions;

/**
 * Write a method to implement *, - , / operations You should use only the +
 * operator
 * 
 * @author Yuan
 * 
 */
public class ArithmeticWithPlus {
	// helper method
	static int negate(int i) {
		int result = 0;
		int n = i > 0 ? -1 : 1;
		while (i != 0) {
			result += n;
			i--;
		}
		return result;
	}
	// helper method
	static int abs(int i) {
		if (i < 0)
			i = negate(i);
		return i;
	}
	// helper method
	static boolean diffSigns(int a, int b) {
		return ((a < 0 && b > 0) || (a > 0 && b < 0)) ? true : false;
	}

	static int minus(int a, int b) {
		int nb = negate(b);
		return a + nb;
	}

	static int multiply(int a, int b) {
		int product = 0;
		for (int i = 0; i < abs(b); i++)
			product += a;
		if (b < 0)
			product = negate(product);
		return product;
	}

	static int divide(int a, int b) {
		if (b == 0) {
			System.out.println("Error!");
			return Integer.MIN_VALUE;
		}
		int nb = negate(abs(b));
		int quotient = 0;
		while (abs(a) >= abs(b)) {
			quotient++;
			a += nb;
		}
		if (diffSigns(a, b))
			quotient = negate(quotient);
		return quotient;
	}

	public static void main(String[] args) {
		System.out.println("!4=" + negate(4));
		System.out.println("2-4=" + minus(2, 4));
		System.out.println("4*2=" + multiply(4, 2));
		System.out.println("4/2=" + divide(4, 2));
	}

}
