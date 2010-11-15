package yuan.jin.interviewQuestions;

/**
 * http://stackoverflow.com/questions/2070356/find-common-prefix-of-strings
 * 
 * @author Yuan
 * 
 */
public class LongestCommonPrefix {

	static String commonPrefix(String[] ss) {
		if (ss.length < 1)
			return null;
		if (ss.length == 1)
			return ss[0];
		int prefixLength = 0;
		for (char c : ss[0].toCharArray()) {
			for (int i = 1; i < ss.length; i++)
				if (ss[i].length() <= prefixLength
						|| ss[i].charAt(prefixLength) != c)
					return ss[0].substring(0, prefixLength);
			prefixLength++;
		}
		return ss[0];
	}

	public static void main(String[] args) {
//		String[] input = { "h:/a/b/c", "h:/a/b/d", "h:/a/b/e", "h:/a/c" };
		String[] input = { "test", "tester", "testertest", "testing", "testingtester" };
		System.out.println(commonPrefix(input));
	}

}
