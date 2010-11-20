package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * Replace all spaces in a string with ‘%20’
 * 
 * @author Yuan
 * 
 */
public class Replace20Percent {

	static char[] replace(char[] str, int length) {
		int count = 0;
		for (int i = 0; i < str.length; i++)
			if (str[i] == ' ')
				count++;
		char[] c = new char[str.length + 2 * count];
		int j = c.length - 1;
		for (int i = str.length - 1; i >= 0; i--) {
			if (str[i] != ' ') {
				c[j] = str[i];
				j--;
			} else {
				c[j--] = '0';
				c[j--] = '2';
				c[j--] = '%';
			}
		}
		return c;
	}

	public static void main(String[] args) {
		String seq = "tes tOn ly";
		char[] input = seq.toCharArray();
		System.out.println(Arrays.toString(replace(input, 10)));
	}

}
