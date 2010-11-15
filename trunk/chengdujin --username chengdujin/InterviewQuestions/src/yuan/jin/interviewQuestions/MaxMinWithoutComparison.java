package yuan.jin.interviewQuestions;

public class MaxMinWithoutComparison {

	static int max(int a, int b) {
		int c = a - b;
		// k == 1 (k < 0) or k == 0 (k > 0)
		int k = (c >> 31) & 1;
		return a - k * c;
	}

	static int min(int a, int b) {
		return a + b - max(a, b);
	}

	public static void main(String[] args) {
		System.out.println("max = " + max(10, 5) + " and min = " + min(10, 5));
	}

}
