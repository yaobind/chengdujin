package yuan.jin.interviewQuestions;

import java.util.ArrayList;

/**
 * http://www.ihas1337code.com/2010/11/unique-paths.html
 * 
 * @author Yuan
 * 
 */
public class RobotsPath {

	static int[][] m = new int[11][11];
	static ArrayList<BoardPoint> path = new ArrayList<BoardPoint>();

	class BoardPoint {
		int x;
		int y;

		public BoardPoint(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "[" + x + ":" + y + "]";
		}
	}

	void init() {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++)
				m[i][j] = 0;
		}

		for (int i = 0; i < m.length; i++) {
			m[0][i] = 1;
			m[i][0] = 1;
			m[m.length - 1][i] = 1;
			m[i][m.length - 1] = 1;
		}
		m[5][3] = 1;
		m[7][9] = 1;
		m[4][1] = 1;
		m[9][6] = 1;
		m[8][5] = 1;

		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++)
				System.out.print(m[i][j]);
			System.out.println();
		}
	}

	boolean isFree(int x, int y) {
		return (m[x][y] == 0) ? true : false;
	}

	boolean findPath(int x, int y) {
		boolean success = false;
		BoardPoint p = new BoardPoint(x, y);
		path.add(p);
		m[x][y] = 3;

		if (x == 9 && y == 9)
			return true;

		if (x < 10 && isFree(x + 1, y))
			success = findPath(x + 1, y);
		if (!success && y < 10 && isFree(x, y + 1))
			success = findPath(x, y + 1);

		if (!success) {
			path.remove(p);
			m[x][y] = 2;
			return false;
		}

		return success;
	}

	/**
	 * http://www.ihas1337code.com/2010/11/unique-paths.html
	 * 
	 * How many possible unique paths are there?
	 */
	int backtrack(int r, int c, int m, int n) {
		if (r == m && c == n)
			return 1;
		if (r > m || c > n)
			return 0;
		return backtrack(r + 1, c, m, n) + backtrack(r, c + 1, m, n);
	}

	public static void main(String[] args) {
		RobotsPath r = new RobotsPath();
		r.init();
		if (r.findPath(1, 1))
			System.out.println("\n" + path.toString());
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++)
				System.out.print(m[i][j]);
			System.out.println();
		}
	}

}
