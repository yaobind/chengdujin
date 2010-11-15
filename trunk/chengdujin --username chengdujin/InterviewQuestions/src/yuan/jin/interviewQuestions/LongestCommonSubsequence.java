package yuan.jin.interviewQuestions;

/**
 * Dynamic Programming
 * 
 * http://en.wikipedia.org/wiki/Longest_common_subsequence_problem lcs(xi, yj) =
 * 1. 0, if(i == 0 || j ==0) 2. lcs((lcs(xi-1, yj-1)), xi), if(xi == yj) 3.
 * longest(lcs(xi, yj-1), lcs(xi-1, yj)), if (xi != yj)
 * 
 * @author Yuan
 * 
 */
public class LongestCommonSubsequence {

	public static String lcs(String a, String b) {
		int[][] lengths = new int[a.length() + 1][b.length() + 1];

		// row 0 and column 0 are initialized to 0 already
		for (int i = 0; i < a.length(); i++)
			for (int j = 0; j < b.length(); j++)
				if (a.charAt(i) == b.charAt(j))
					lengths[i + 1][j + 1] = lengths[i][j] + 1;
				else
					lengths[i + 1][j + 1] = Math.max(lengths[i + 1][j],
							lengths[i][j + 1]);

		// read the substring out from the matrix
		StringBuffer sb = new StringBuffer();
		for (int x = a.length(), y = b.length(); x != 0 && y != 0;) {
			if (lengths[x][y] == lengths[x - 1][y])
				x--;
			else if (lengths[x][y] == lengths[x][y - 1])
				y--;
			else {
				System.out.println(x + "-" + y);
				sb.append(a.charAt(x - 1));
				x--;
				y--;
			}
		}

		return sb.reverse().toString();
	}
	
	public static void main(String[] args) {
		String x = "this is a test string";
		String y = "tihs";

		System.out.println(lcs(x, y));
	}
}
