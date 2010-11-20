package yuan.jin.interviewQuestions;

/**
 * http://stackoverflow.com/questions/2070356/find-common-prefix-of-strings
 * 
 * @author Yuan
 * 
 */
public class LongestCommonPrefix {
	// method 1
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
	// method 2
	static String commonPrefix2(String[] set) {
		String s = set[0];
		int len = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			len = i;
			for (int j = 1; j < set.length; j++)
				if (set[j].length() < (len + 1) || set[j].charAt(i) != c)
					break;
		}
		return s.substring(0, len + 1);
	}

	public static void main(String[] args) {
		// String[] input = { "h:/a/b/c", "h:/a/b/d", "h:/a/b/e", "h:/a/c" };
		String[] input = { "test", "tester", "testertest", "testing",
				"testingtester" };
		System.out.println(commonPrefix2(input));
	}

}
