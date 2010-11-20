package yuan.jin.interviewQuestions;

public class PalindromesAllFound {

	public static void main(String[] args) {
		String line = "abccbab";

		boolean isPal = isPalindrome(line);
		System.out.println(line + " is " + (isPal ? "" : "not ")
				+ "a palindrome");

		String subStr;
		StringBuffer sb = new StringBuffer(line);
		int length = sb.length();
		for (int i = 0; i < length; i++) {
			for (int j = i + 2; j <= length; j++) {
				subStr = sb.substring(i, j);
				if (isPalindrome(subStr))
					System.out.println(subStr);
			}
		}
	}

	public static boolean isPalindrome(String s) {
	    int i = 0;
	    int j = s.length()-1;
	    while (i < j) {
	        if (s.charAt(i) != s.charAt(j))
	            return false;
	        i++;
	        j--;
	    }
	    return true;
	}

}
