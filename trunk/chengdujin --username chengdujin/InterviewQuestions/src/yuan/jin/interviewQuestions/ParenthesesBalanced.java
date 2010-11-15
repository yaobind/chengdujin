package yuan.jin.interviewQuestions;

import java.util.Scanner;
import java.util.Stack;

/**
 * Your input is a string which is composed from bracket characters. The allowed
 * characters are: ()[]{} <>. Your mission is to determine whether the brackets
 * structure is legal or not. Example of a legal expression: ([](< {} >)).
 * Example of an illegal expression: ({<) >}. Provide the most efficient,
 * elegant and simple solution for that problem.
 * 
 * This is a code that checks if parentheses have pairs (open '(' and close ')'
 * parentheses). The parentheses with pairs will then be replaced by brackets
 * with pairs (open '[' and close ']' brackets). And then outputs the result.
 * 
 * http://www.dreamincode.net/code/snippet5768.htm
 * 
 * @author Yuan
 * 
 */
public class ParenthesesBalanced {
	public static void main(String[] args) {
		System.out.println("Enter parentheses:");
		// ())()()
		Scanner s = new Scanner(System.in);
		String input = s.nextLine();
		if (input.length() != 0 && check(input)) {
			checkBalance(input);
		} else
			System.out.println("Invalid Input!");
	}

	public static boolean check(String a) {
		boolean isReturn = true;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != '(' && a.charAt(i) != ')') {
				isReturn = false;
				break;
			}
		}
		return isReturn;
	}

	public static void checkBalance(String input) {
		Stack<Character> st = new Stack<Character>();
		int index = 0;
		int[] arr = new int[input.length()];
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '(') {
				st.push(input.charAt(i));
				arr[index] = i;
				index++;
			} else {
				if (input.charAt(i) == ')' && !st.empty()
						&& st.peek().equals('(')) {
					st.pop();
					index--;
					input = input.substring(0, arr[index]) + "["
							+ input.substring((arr[index] + 1), i) + "]"
							+ input.substring(i + 1, input.length());
				} else
					st.push(input.charAt(i));
			}
		}
		System.out.println("Result: " + input);
		if (st.empty())
			System.out.println("They are balanced!");
		else
			System.out.println("They're not balanced!");
	}
}
