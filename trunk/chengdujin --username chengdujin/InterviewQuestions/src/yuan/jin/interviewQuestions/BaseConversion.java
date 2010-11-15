package yuan.jin.interviewQuestions;

public class BaseConversion {

	public static String value = "17";
	public static String quotient = null;
	public static int M = 8;
	public static int N = 10;

	public static void main(String[] args) {
		String nValue = "";
		quotient = value;
		while (quotient.length() > 0)
			nValue = qiuyu(quotient) + nValue;
		System.out.println(nValue);
	}

	/**
	 * 功能：对给定的M进制字符串对n求余。
	 */
	static String qiuyu(String n) {
		quotient = "";
		int temp = 0;
		while (n.length() > 0) {
			int t = atoi(n.substring(0, 1));
			n = n.substring(1);
			temp = temp * M + t;
			quotient += itoa(temp / N);
			System.out.println(quotient);
			temp = temp % N;
		}
		System.out.println();
		while (quotient.length() > 0 && quotient.charAt(0) == '0')
			quotient = quotient.substring(1);
		System.out.println("@" + quotient + "-" + temp);
		return itoa(temp);
	}

	static int atoi(String str) {
		return str.charAt(0) <= '9' && str.charAt(0) >= '0' ? str.charAt(0) - '0'
				: str.charAt(0) - 'a' + 10;
	}

	static String itoa(int value) {
		String result = null;
		if (value >= 0 && value <= 9)
			result = String.valueOf((char) ('0' + value));
		else if (value > 9 && value < 36)
			result = String.valueOf((char) ('a' + value - 10));
		else
			result = "-1";
		return result;
	}

}
