package yuan.jin.interviewQuestions;

import java.util.*;

/**
 * Creates a coded message based on the number of shifts (input)
 * 
 * http://www.dreamincode.net/code/snippet5751.htm
 * 
 * @author Yuan
 * 
 */
public class CaesarCypher {

	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter message to be coded:");
		String input = s.nextLine().toUpperCase(), result = "";
		System.out.println("Enter number of shifts:");
		try {
			int shift = s.nextInt();
			if (shift >= 0) {
				// The coding process
				for (int i = 0; i < input.length(); i++) {
					if (Character.isLetter(input.charAt(i))) {
						int num1 = (int) input.charAt(i);
						if (shift >= 27)
							shift = shift % 26;
						if (num1 + shift > 90)
							num1 += shift - 26;
						else
							num1 += shift;
						result += (char) num1;
					} else
						result += input.charAt(i);
				}
				System.out.println("Coded message: " + result);
			} else
				System.out.println("Only positive numbers up to "
						+ Integer.MAX_VALUE);
		} catch (InputMismatchException ex) {
			System.out.println("Invalid Input!");
		}
	}
}
