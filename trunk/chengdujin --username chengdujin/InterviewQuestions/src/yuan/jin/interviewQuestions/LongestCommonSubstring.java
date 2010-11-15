package yuan.jin.interviewQuestions;

import java.util.ArrayList;
import java.util.List;

public class LongestCommonSubstring {

	public static List longestSubstr(String a, String b) {
		if (a.isEmpty() || b.isEmpty())
			return null;

		int[][] num = new int[a.length()][b.length()];
		int maxlen = 0;
		int max = 0;
		int may = 0;

		List maxs = new ArrayList();
		List results = new ArrayList();

		for (int i = 0; i < a.length(); i++) {
			for (int j = 0; j < b.length(); j++) {
				if (a.charAt(i) != b.charAt(j))
					num[i][j] = 0;
				else {
					if ((i == 0) || (j == 0))
						num[i][j] = 1;
					else
						num[i][j] = num[i - 1][j - 1] + 1;

					if (num[i][j] >= maxlen) {
						if (num[i][j] > maxlen)
							maxs.clear();

						maxlen = num[i][j];
						maxs.add(i);
					}
				}
			}
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < maxs.size(); i++) {
			sb.delete(0, sb.length());
			int len = maxlen;
			int t = (Integer) maxs.get(i);

			while (len > 0) {
				sb.append(a.charAt(t));
				t--;
				len--;
			}

			results.add(sb.toString());
		}

		return results;
	}

	public static void main(String[] args) {
		String a = "ABAB";
		String b = "BABA";

		System.out.println(longestSubstr(a, b));
	}

}
