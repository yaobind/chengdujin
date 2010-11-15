package yuan.jin.interviewQuestions;

/**
 * Cracking the Coding Interview: 8.5 Implement an algorithm to print all valid
 * (e g , properly opened and closed) combinations of n-pairs of parentheses
 * 
 * @author Yuan
 * 
 */
public class ParenthesesPrint {

	public static void printPar(int left, int right, char[] str, int count) {
		if (left < 0 || right < left)
			return; // invalid state
		
		if (left == 0 && right == 0) {
			System.out.println(str); // found one, so print it
		} else {
			if (left > 0) { // try a left paren, if there are some available
				str[count] = '[';
				printPar(left - 1, right, str, count + 1);
			}
			if (right > left) { // try a right paren, if there’s a matching left
				str[count] = ']';
				printPar(left, right - 1, str, count + 1);
			}
		}
	}

	public static void main(String[] args) {
		int count = 3;
		char[] str = new char[count * 2];	
		printPar(count, count, str, 0);
	}

}
