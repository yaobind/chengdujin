package yuan.jin.interviewQuestions;

import java.util.Arrays;


/**
 * The suffixTree class could handle queries for
 * 1. longest repeated substring
 * 2. longest common substring
 * 3. longest palindromes
 * @author Yuan
 *
 */
public class SuffixArrayApplications {
	public static String longestCommonPrefix(String s, String t) {
		int n = Math.min(s.length(), t.length());
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) != t.charAt(i))
				return s.substring(0, i);
		}
		return s.substring(0, n);
	}

	public static String longestRepeatedSubstring(String s) {
		int n = s.length();
		String[] suffixes = new String[n];
		for (int i = 0; i < n; i++) {
			suffixes[i] = s.substring(i, n);
		}

		Arrays.sort(suffixes);
		System.out.println(Arrays.toString(suffixes));

		String max = "";
		for (int i = 0; i < n - 1; i++) {
			String x = longestCommonPrefix(suffixes[i], suffixes[i + 1]);
			if (x.length() > max.length())
				max = x;
		}
		
		return max;
	}
	
	public static String reverse(String s) {
		char[] schar = s.toCharArray();
		
		for (int i = 0; i < s.length()/2; i++) {
			char temp = schar[i];
			schar[i] = schar[schar.length - i - 1];
			schar[schar.length - i - 1] = temp;
		}
		
		return new String(schar);
	}

	public static void main(String[] args) {
		// http://www.allisons.org/ll/AlgDS/Tree/Suffix/
		
		// works for longest repeated substring
		String s11 = "ABABABBA";
		String s12 = "abcabcdfabcdf";
		
		// works aussi for longest common substring
		String s21 = "ABAB$ABBA#";
		
		// works for longest palindrome
		String s3 = "mississippi";
		String s3r = reverse(s3);
		s3 = s3 + "$"+ s3r + "%";
		// longest common ancestor (LCA)
		String s22 = "google$elgoog";
		
		// substring?
		String s4 = "ChinesePeopleKnowsChinaBest$ChineseKnows#";
		
		System.out.println("'" + longestRepeatedSubstring(s22) + "'");
	}
}
