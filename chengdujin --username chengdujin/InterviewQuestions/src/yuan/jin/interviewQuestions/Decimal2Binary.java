package yuan.jin.interviewQuestions;

import java.util.Stack;

/**
 * Write a program to convert a decimal number into a binary number
 * @author Yuan
 *
 */
public class Decimal2Binary {

	static void decimal2binary(int n) {
		int m;
		if (n > 0) {
			m = n % 2;
			decimal2binary(n >>= 1);
			System.out.print(m);
		}
	}
	
	static void d2b(int n) {
	    Stack s = new Stack();
	    while(n!=0) {
	        s.push(n%2);
	        n /= 2;
	    }
	    System.out.println(s.toString());
	}

	public static void main(String[] args) {
		decimal2binary(24);
		System.out.println();
		d2b(24);
	}

}
