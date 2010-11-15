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
		int length = s.length();

		if (length <= 1)
			return false;

		int halfWay = length / 2;
		char[] text = s.toCharArray();

		for (int i = 0; i < halfWay; i++)
			if (text[i] != text[length - 1 - i])
				return false;

		return true;
	}

}
