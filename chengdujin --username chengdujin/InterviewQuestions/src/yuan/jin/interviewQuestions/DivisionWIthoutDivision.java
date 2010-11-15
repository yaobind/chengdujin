package yuan.jin.interviewQuestions;

public class DivisionWIthoutDivision {
	static int divide(int a, int b) {
		int counter = 0;
		while ((a-b) >= 0) {
			a = a - b;
			counter++;
		}
		return counter;
	}

	public static void main(String[] args) {
		System.out.println(divide(20, 3));
	}

}
