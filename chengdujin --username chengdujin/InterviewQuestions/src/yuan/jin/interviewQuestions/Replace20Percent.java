package yuan.jin.interviewQuestions;

import java.util.Arrays;

public class Replace20Percent {
	
	private static char[] replaceFun(char[] str, int length) {
		int spaceCount = 0;
		int newlength = 0;
		int i = 0;
		
		for (i = 0; i < length; i ++) {
			if (str[i] == ' ')
				spaceCount++;
		}
		
		newlength = length + spaceCount*2;
		char[] output = new char[newlength];
		
		for(i = length - 1; i >= 0; i --) {
			if (str[i] == ' ') {
				output[newlength - 1] = '0';
				output[newlength - 2] = '2';
				output[newlength - 3] = '%';
				newlength = newlength -3;
			} else {
				output[newlength - 1] = str[i];
				newlength -= 1;
			}
		}	
		
		return output;
	}

	public static void main(String[] args) {
		String seq = "tes tOn ly";
		char[] input = seq.toCharArray();
		
		System.out.println(Arrays.toString(replaceFun(input, 10)));
	}

}
