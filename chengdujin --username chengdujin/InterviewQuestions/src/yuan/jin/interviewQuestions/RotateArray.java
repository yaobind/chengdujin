package yuan.jin.interviewQuestions;

import java.util.Arrays;

public class RotateArray {
	
	static void reverse(char[] c, int start, int end) {
		if (start > end)
			return;
		for (; start < end; start++, end--) {
			char temp = c[start];
			c[start] = c[end];
			c[end] = temp;
		}
	}
	
	public static void main(String[] args) {
		String input = "abcd1234";
		char[] cs = input.toCharArray();
		int k = 4 % cs.length;
		reverse(cs, 0, k-1);
		reverse(cs, k, cs.length-1);
		reverse(cs, 0, cs.length-1);
		System.out.println(Arrays.toString(cs));
	}

}
