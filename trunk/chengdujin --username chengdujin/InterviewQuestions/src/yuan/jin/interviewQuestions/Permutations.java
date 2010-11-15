package yuan.jin.interviewQuestions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * with duplicates http://n1b-algo.blogspot.com/2009/01/string-permutations.html
 * 
 * @author Yuan
 * 
 */
public class Permutations {
	static String input = "abc";
	static char[] cquence = input.toCharArray();
	static StringBuffer result = new StringBuffer();
	static boolean[] used = new boolean[input.length()];
	static int counter = 0;

	public static void main(String args[]) throws IOException {
		// Arrays.sort(cquence);
		rekrPermute1();
		// rekrPermute2(0);
		System.out.println();
		printByIndex(3, 4);
	}

	public static void rekrPermute1() {
		if (result.length() == input.length()) {
			System.out.println((counter++) + "." + result.toString());
			return;
		}
		char prev = '\0';
		for (int i = 0; i < input.length(); i++) {
			if (used[i])
				continue;
			if (prev == cquence[i])
				continue;
			else
				prev = cquence[i];
			result.append(cquence[i]);
			used[i] = true;
			rekrPermute1();
			used[i] = false;
			result.setLength(result.length() - 1);
		}
	}

	public static void rekrPermute2(int d) {
		if (d == cquence.length)
			System.out.println(Arrays.toString(cquence));
		else {
			char prev = '\0';
			for (int i = d; i < cquence.length; i++) {
				if (prev == cquence[i])
					continue;
				else
					prev = cquence[i];
				char temp = cquence[d];
				cquence[d] = cquence[i];
				cquence[i] = temp;
				rekrPermute2(d + 1);
				temp = cquence[i];
				cquence[i] = cquence[d];
				cquence[d] = temp;
			}
		}
	}

	/**
	 * offline (独酌客)
	 * 
	 * 基本思路： 1. 知道n，k和数字集的情况下，可以直接计算最高位。 2. 计算过后更新n，k和数字集
	 * 
	 * http://www.mitbbssg.com/bbsann2/life.faq/JobHunting/17/D12842543542i0/M
	 * .1284782138
	 * _2.w0/%CC%F9%BC%B8%B5%C0%C4%B3%B4%F3%B9%AB%CB%BE%B5%C4%C3%E6%CA%D4%CC%E2
	 * 
	 * @param n
	 * @param k
	 */
	static void printByIndex(int n, int k) {
		StringBuilder result = new StringBuilder();
		List<Character> candidat = new ArrayList<Character>();
		for (int i = 0; i < n; i++) {
			char c = (char)(i + 'a');
			candidat.add(c);
		}
		while (candidat.size() > 1) {
			result = result.append(candidat.get(k / (n - 1)));
			candidat.remove(candidat.get(k / (n - 1)));
			k = k % (n - 1);
			n = n - 1;
		}
		result = result.append(candidat.get(0));
		System.out.println("The " + k + "th permutation is " + result);
	}
}
