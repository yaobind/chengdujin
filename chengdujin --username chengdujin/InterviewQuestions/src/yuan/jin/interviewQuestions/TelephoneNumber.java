package yuan.jin.interviewQuestions;

import java.util.Arrays;

public class TelephoneNumber {

	static final int PHONE_NUMBER_LENGTH = 7;

	void printTelephoneWords(int[] phoneNum) {
	    char[] result = new char[PHONE_NUMBER_LENGTH];
	    doPrintTelephoneWords(phoneNum, 0, result);
	}

	void doPrintTelephoneWords(int[] phoneNum, int curDigit, char[] result) {
	    if (curDigit == PHONE_NUMBER_LENGTH) {
	        System.out.println(Arrays.toString(result));
	        return;
	    }
	    
	    for (int i = 1; i <= 3; i++) {
	        result[curDigit] = getCharKey(phoneNum[curDigit], i);
	        doPrintTelephoneWords(phoneNum, curDigit+1, result);
	        if (phoneNum[curDigit] == 0 || phoneNum[curDigit] == 1)
	            return;
	    }
	}
	
	char getCharKey(int number, int position) {
		return 'A';
	}
	
	public static void main(String[] args) {
		
	}

}
