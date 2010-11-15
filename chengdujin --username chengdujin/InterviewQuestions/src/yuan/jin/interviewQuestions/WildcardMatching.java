package yuan.jin.interviewQuestions;

/**
 * http://www.careercup.com/question?id=4079975
 * 
 * Input a string and a pattern having . and * Output: whether the string fully
 * matches the pattern . match any char, * means matching 0 or more times.
 * Example: input "a", "." => match input "abc", ".*" => match input "abcd",
 * "a.*d" => match
 * 
 * www.ideone.com/0WWcw
 * 
 * @author Yuan
 * 
 */
public class WildcardMatching {

	// Hold size of the input string
	static int tsize = 0;
	// Hold the size of the pattern
	static int psize = 0;

	static boolean matchHelper(char[] text, int tindex, char[] pattern,
			int pindex) {
		// If we reached the end of string and pattern return true
		if (tindex == tsize && pindex == psize)
			return true;
		// If we reached the end of the string, but not the end of pattern
		else if (tindex == tsize) {
			// If only '*'s are remaining in the pattern then its a match
			while (pindex < psize) {
				if (pattern[pindex] != '*')
					return false;
				pindex++;
			}
			return true;
		}
		// If we reached the end of pattern, but not the end of the string, its
		// not a match
		else if (pindex == psize)
			return false;

		// Recursive calls
		// If chars match, just move ahead in the string and pattern
		if (text[tindex] == pattern[pindex])
			return matchHelper(text, tindex + 1, pattern, pindex + 1);

		// If we find a '.' just move ahead in string and pattern
		else if (pattern[pindex] == '.')
			return matchHelper(text, tindex + 1, pattern, pindex + 1);

		// If a '*' is observed, recursively check every suffix of the string
		// beginning at tindex for a match
		// If at least one match occurs return true
		else if (pattern[pindex] == '*') {
			boolean b = false;
			for (int i = tindex; i <= tsize; i++)
				b = b | matchHelper(text, i, pattern, pindex + 1);
			return b;
		}

		// If none of the above return false
		else
			return false;
	}

	public static void main(String[] args) {
		String input = "regular expression";
		String pattern = ".egu*pr..*o*";

		tsize = input.length();
		psize = pattern.length();

		boolean result = matchHelper(input.toCharArray(), 0,
				pattern.toCharArray(), 0);
		System.out.println(result);
	}

}
