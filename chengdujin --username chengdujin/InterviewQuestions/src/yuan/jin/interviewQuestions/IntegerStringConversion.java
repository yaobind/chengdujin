package yuan.jin.interviewQuestions;

/**
 * Programming Interviews Exposed: Array and String Problems.Integer/String
 * Conversions
 * 
 * @author Yuan
 * 
 */
public class IntegerStringConversion {

	private static String int2Str(int input) {
		char[] cinput = new char[10];
		StringBuffer output = new StringBuffer();

		int digits = 0;
		do {
			cinput[digits++] = (char) (input % 10 + '0');
			input /= 10;
		} while (input != 0);

		while (digits > 0)
			output.append(cinput[--digits]);

		return output.toString();
	}

	private static int str2Int(String input) {
		char[] cinput = input.toCharArray();
		int output = 0;

		for (int i = 0; i < cinput.length; i++) {
			output *= 10;
			output += (int) cinput[i] - '0';
		}

		return output;
	}

	public static void main(String[] args) {
		// integer to string
		int in1 = 123;
		String out1 = int2Str(in1);
		System.out.println("Integer to String: " + out1);

		// string to integer
		String in2 = "456";
		int out2 = str2Int(in2);
		System.out.println("String to Integer: " + out2);
	}

}
