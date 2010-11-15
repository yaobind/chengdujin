package yuan.jin.interviewQuestions;


/**
 * covert interger number to date string, for example, 20090130 -> "01/30/2009"
 * @author Yuan
 *
 */
public class Integer2DateString {
	
	private static String int2Str(int input) {
		char[] cinput = new char[10];
		StringBuffer output = new StringBuffer();

		int digits = 0;
		do {
			cinput[digits++] = (char) (input % 10 + '0');
			input /= 10;
		} while (input != 0);

		while (digits > 0)
			output.append(cinput[--digits]);

		return output.toString();
	}
	
	private static String formToDate(String str) {
		StringBuilder result = new StringBuilder("");
		
		// be careful with the beginning/end index
		result.append(str.substring(str.length()-2, str.length()));
		result.append("/");
		
		result.append(str.substring(str.length()-4, str.length()-2));
		result.append("/");
		
		result.append(str.substring(0, str.length()-4));
		
		return result.toString();
	}

	public static void main(String[] args) {
		String date = formToDate(int2Str(20090130));
		
		System.out.println(date);
	}

}
