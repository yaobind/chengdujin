package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * Replace all occurrence of the given pattern to 'X'. For example, given that
 * the pattern="abc", replace "abcdeffdfegabcabc" with "XdeffdfegX". Note that
 * multiple occurrences of abc's that are contiguous will be replaced with only
 * one 'X'.
 * 
 * http://www.ihas1337code.com/2010/11/microsoft-string-replacement-problem.html
 * 
 * @author Yuan
 * 
 */
public class ReplaceMultiplePatternWithX {

	static boolean isMatch(char[] str, int start, char[] pattern) {
		int i = start;
		int j = 0;
		while (j < pattern.length)
			if (str[i++] != pattern[j++])
				return false;
		return true;
	}

	static void replace(char str[], char[] pattern) {
		if (str == null || pattern == null)
			return;
		char[] pSlow = str;
		char[] pFast = str;
		int iSlow = 0;
		int iFast = 0;
		int pLen = pattern.length;
		while (iFast < str.length) {
			System.out.println(iFast);
			if (isMatch(pFast, iFast, pattern)) {
				iFast += pLen;
				pSlow[iSlow++] = 'X';
				continue;
			}
			if (iFast < str.length)
				pSlow[iSlow++] = pFast[iFast++];
		}
		Arrays.fill(pSlow, iSlow, pSlow.length, '\0');
		System.out.println(Arrays.toString(pSlow));
	}

	public static void main(String[] args) {
		String text = "abcdeffdfegabcabc";
		String pattern = "abc";
		replace(text.toCharArray(), pattern.toCharArray());
	}

}
