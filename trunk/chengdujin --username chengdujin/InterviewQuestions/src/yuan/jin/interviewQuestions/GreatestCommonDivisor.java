package yuan.jin.interviewQuestions;

public class GreatestCommonDivisor {

	public static int gcd(int n1, int n2) {
		if (n2 == 0)
			return n1;
		else
			return gcd(n2, n1 % n2);
	}

	public static void main(String[] args) {
		System.out.println(gcd(20, 18));
	}

}
