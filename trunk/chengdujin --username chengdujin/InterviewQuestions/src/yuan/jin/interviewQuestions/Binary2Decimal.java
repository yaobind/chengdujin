package yuan.jin.interviewQuestions;

public class Binary2Decimal {

	private static int str2Int(String input) {
		char[] cinput = input.toCharArray();
		int output = 0;

		for (int i = 0; i < cinput.length; i++) {
			output *= 10;
			output += (int) cinput[i] - '0';
		}

		return output;
	}

	private static int binary2Decimal(int input) {
		int output = 0;
		int acc = -1;
		while (input != 0) {
			if ((input % 10) != 0)
				output += Math.pow(2, acc + 1);
			++acc;
			input /= 10;
		}
		return output;
	}

	public static void main(String[] args) {
		int bin = str2Int(Integer.toBinaryString(12));
		System.out.println("Our binary is: " + bin);
		System.out.println("Its decimal is: " + binary2Decimal(bin));
	}

}
