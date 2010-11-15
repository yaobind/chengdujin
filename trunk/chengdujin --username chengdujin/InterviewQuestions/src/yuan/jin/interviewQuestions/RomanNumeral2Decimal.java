package yuan.jin.interviewQuestions;

/**
 * Convert a Roman Numeral string to Decimal
 * 
 * @author Yuan
 * 
 */
public class RomanNumeral2Decimal {

	static int map(char inp) {
		switch (inp) {
		case 'i':
		case 'I':
			return 1;
		case 'v':
		case 'V':
			return 5;
		case 'x':
		case 'X':
			return 10;
		case 'l':
		case 'L':
			return 50;
		case 'c':
		case 'C':
			return 100;
		case 'd':
		case 'D':
			return 500;
		default:
			return 0;
		}
	}

	public static void main(String[] args) {
		int res = 0, prev = 501, curr = 0;
		String input = "IX";
		int len = input.length();
		char[] str = input.toCharArray();
		for (int i = 0; i < len; i++) {
			curr = map(str[i]);
			if (curr <= prev)
				res += curr;
			else {
				res += curr;
				res -= 2 * prev;
			}
			prev = curr; // Re-initialize the previous
		}
		System.out.println("The decimal equivalent of input Roman " + input
				+ " = " + res);
	}

}
