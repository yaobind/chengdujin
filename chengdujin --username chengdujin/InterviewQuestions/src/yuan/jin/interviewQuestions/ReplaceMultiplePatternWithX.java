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

	static void replace(char str[], char[] pan) {
		if (str == null || pan == null)
			return;
		char[] pSlow = str;
		char[] pFast = str;
		int iSlow = 0;
		int iFast = 0;
		int pLen = pan.length;
		while (iFast < str.length) {
			if (isMatch(pFast, iFast, pan)) {
				iFast += pLen;
				if (iSlow - 1 > 0 && pSlow[iSlow - 1] == 'X')
					continue;
				pSlow[iSlow++] = 'X';
			} else if (iFast < str.length)
				pSlow[iSlow++] = pFast[iFast++];
		}
		Arrays.fill(pSlow, iSlow, pSlow.length, '\0');
		System.out.println("After replacement: " + new String(pSlow));
	}

	public static void main(String[] args) {
		String text = "abcdeffdfegabcabc";
		String pattern = "abc";
		System.out.println("Text: " + text);
		System.out.println("Pattern: " + pattern);
		replace(text.toCharArray(), pattern.toCharArray());
	}

}
