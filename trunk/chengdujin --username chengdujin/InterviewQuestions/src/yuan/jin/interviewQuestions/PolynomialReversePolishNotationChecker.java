package yuan.jin.interviewQuestions;

import java.util.*;

/**
 * This little code will calculate mathematic expression written with RPN
 * (Reverse Polish Notation)
 * 
 * http://www.dreamincode.net/code/snippet3489.htm
 * 
 * @author Yuan
 * 
 */
public class PolynomialReversePolishNotationChecker {

	public static void main(String[] args) {
		String test = "2 3 + 5 * 30 -";
		System.out.println(execute(test));
	}

	public static double execute(String expr) {
		Stack<Double> stack = new Stack<Double>();
		Scanner scan = new Scanner(expr);
		String part;
		while (scan.hasNext()) {
			if (scan.hasNextDouble()) {
				stack.push(scan.nextDouble());
			} else {
				part = scan.next();
				double a = 0, b = 0;
				System.out.println(part.length());
				for (int i = 0; i < part.length(); i++) {
					switch (part.charAt(i)) {
					case '+':
						a = stack.pop();
						b = stack.pop();
						stack.push(b + a);
						break;
					case '-':
						a = stack.pop();
						b = stack.pop();
						stack.push(b - a);
						break;
					case '*':
						a = stack.pop();
						b = stack.pop();
						stack.push(b * a);
						break;
					case '/':
						a = stack.pop();
						b = stack.pop();
						stack.push(b / a);
						break;
					}
				}
			}
		}
		return stack.pop();
	}
}
