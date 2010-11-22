package yuan.jin.interviewQuestions;

/**
 * If you are given a number as a parameter, write a function that would put
 * commas after every third digit from the right. e.g. 1000000 -> 1,000,000
 * 
 * @author Yuan
 * 
 */
public class CommaInsertion {
	
	static String insertComma(String s) {
		char[] c = s.toCharArray();
		int length = c.length;
		int commas = (length - 1) / 3;
		char[] d = new char[c.length + commas];
		int j = d.length - 1;
		for (int i = c.length - 1; i >= 0;) {
			if (i + 1 <= 3) {
				while (i >= 0)
					d[j--] = c[i--];
			} else {
				d[j--] = c[i--];
				d[j--] = c[i--];
				d[j--] = c[i--];
				d[j--] = ',';
			}
		}
		return new String(d);
	}

	public static void main(String[] args) {
		String input = "1234";
		System.out.println(insertComma(input));
	}

}
