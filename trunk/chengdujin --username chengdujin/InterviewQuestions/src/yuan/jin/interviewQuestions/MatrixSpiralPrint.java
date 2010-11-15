package yuan.jin.interviewQuestions;

/**
 * One simple solution is also available at
 * http://www.thecareerplus.com/?page=resources&cat=10&subCat=90&qNo=15
 * 
 * @author Yuan
 * 
 */
public class MatrixSpiralPrint {

	static int n = 3;
	static int[][] test1 = new int[n][n];
	static int[][] test2 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
			{ 13, 14, 15, 16 } };
	static int[][] test3 = { { 0, 1, 2, 3, 4 }, { 5, 6, 7, 8, 9 }, { 10, 11, 12, 13, 14 },
		{ 15, 16, 17, 18, 19 } };

	public static void main(String[] args) {
		m1(test3);
	}

	private static void m1(int[][] input) {
		int i, w, h, k;
		int height = input.length;
		int width = input[0].length;
		
		for (i = 0, w = width - 1, h = height - 1; w > 0; i++, w--, h--) {
			for (k = i; k < w; k++)
				System.out.println(input[i][k]);
			for (k = i; k < h; k++)
				System.out.println(input[k][w]);
			for (k = w; k > i; k--)
				System.out.println(input[h][k]);
			for (k = h; k > i; k--)
				System.out.println(input[k][i]);
		}
	}

	private static void m2(int[][] input) {
		int x = 0, y = 0;

		for (int i = 1; i <= n * n; i++) {
			input[x][y] = i;
			if (isAvailable(x + 1, y, input) && !isAvailable(x, y - 1, input)) {
				x = x + 1;
			} else if (isAvailable(x, y + 1, input)
					&& !isAvailable(x + 1, y, input)) {
				y = y + 1;
			} else if (isAvailable(x - 1, y, input)
					&& !isAvailable(x, y + 1, input)) {
				x = x - 1;
			} else if (isAvailable(x, y - 1, input)
					&& !isAvailable(x - 1, y, input)) {
				y = y - 1;
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(input[j][i] + " ");
			}
			System.out.println("");
		}
	}

	private static boolean isAvailable(int x, int y, int[][] input) {
		if (x < 0 || y < 0 || x >= n || y >= n)
			return false;
		if (input[x][y] != 0)
			return false;
		return true;
	}

}
