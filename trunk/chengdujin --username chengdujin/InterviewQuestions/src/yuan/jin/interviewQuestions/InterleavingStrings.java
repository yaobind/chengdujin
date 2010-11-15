package yuan.jin.interviewQuestions;

import java.util.ArrayList;

/**
 * Design an algorithm to find whether a given string is formed by the
 * interleaving of two given strings or not. e.g. s1= aabccabc s2= dbbabc s3=
 * aabdbbccababcc Given s1,s2,s3 design an efficient algorithm to find whether
 * s3 is formed from the interleaving of s1 and s2.
 * 
 * http://www.careercup.com/question?id=2571
 * 
 * @author Yuan
 * 
 */
public class InterleavingStrings {
	static int[][] m;
	static ArrayList<BoardPoint> path = new ArrayList<BoardPoint>();
	static String str1 = "aabccabc";
	static String str2 = "dbbabc";
	static String str3 = "aabdbbccababcc";

	/** Method1 May trigger serious problems */
	static boolean checkInterleaving(String str1, String str2, String str3) {
		char[] c1 = str1.toCharArray();
		char[] c2 = str2.toCharArray();
		char[] c3 = str3.toCharArray();

		int i1 = 0, i2 = 0, i3 = 0;

		while (i3 <= c3.length) {
			if (i1 < c1.length && c3[i3] == c1[i1]) {
				i1++;
				i3++;
			} else if (i2 < c2.length && c3[i3] == c2[i2]) {
				i2++;
				i3++;
			}
			if (i3 == c3.length)
				return true;
		}
		return false;
	}

	/** Method2 */
	static boolean isValid(String str, int x, int y) {
		return (str.charAt(x) == str3.charAt(y)) ? true : false;
	}

	static boolean isFree(int x, int y) {
		return (m[x][y] == 0) ? true : false;
	}

	static boolean findPath(int x, int y) {
		boolean success = false;
		BoardPoint p = new BoardPoint(x, y);
		path.add(p);
		m[x][y] = 3;
		if (x == str1.length() && y == str2.length())
			return true;
		if (x < str1.length() && isFree(x + 1, y)
				&& isValid(str1, x, path.size() - 1))
			success = findPath(x + 1, y);
		if (!success && y < str2.length() && isFree(x, y + 1)
				&& isValid(str2, y, path.size() - 1))
			success = findPath(x, y + 1);
		if (!success) {
			path.remove(p);
			m[x][y] = 2;
		}
		return success;
	}

	public static void main(String[] args) {
		m = new int[str1.length() + 1][str2.length() + 1];
		System.out.println("Is str3 interleaved by str1 & str2? "
				+ findPath(0, 0) + "\n");
		for (int i = 0; i < m.length; i++) {
			System.out.print("\t");
			for (int j = 0; j < m[0].length; j++)
				System.out.print(m[i][j]);
			System.out.println();
		}
	}
}

class BoardPoint {
	int x;
	int y;

	public BoardPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
