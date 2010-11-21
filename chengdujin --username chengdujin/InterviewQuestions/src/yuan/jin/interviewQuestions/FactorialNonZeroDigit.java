package yuan.jin.interviewQuestions;

public class FactorialNonZeroDigit {
	private static int last_digit_factorial(int N) {
		int i, j, ans = 1, a2 = 0, a5 = 0, a;
		for (i = 1; i <= N; i++) {
			j = i;
			// divide i by 2 and 5
			while (j % 2 == 0) {
				j /= 2;
				a2++;
			}

			while (j % 5 == 0) {
				j /= 5;
				a5++;
			}
			// 1.number without 2 and 5
			// 2.last digit after multiplying with this number
			// System.out.println("" + (j%10) + "-" + (ans * (j % 10)) + "-" +
			// ((ans * (j % 10)) % 10));
			// ans = (ans * (j % 10)) % 10;
			ans = (ans * j) % 10;
		}
		a = a2 - a5;
		for (i = 1; i <= a; i++)
			ans = (ans * 2) % 10;
		return ans;
	}

	public static void main(String[] args) {
		System.out.println("last digit is: " + last_digit_factorial(14));
	}

}
