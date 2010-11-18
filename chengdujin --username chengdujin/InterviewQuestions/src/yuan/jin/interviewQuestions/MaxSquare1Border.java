package yuan.jin.interviewQuestions;

/**
 * Imagine you have a square matrix, where each cell is filled with either black
 * or white. Design and algorithm to find the maximum sub-square such that all
 * four borders are filled with black pixels.
 * 
 * @author Yuan
 * 
 */
public class MaxSquare1Border {
	static int[][] input = { 
		{ 0, 1, 1, 1, 1 }, 
		{ 1, 1, 0, 0, 1 },
		{ 0, 1, 1, 0, 1 }, 
		{ 1, 1, 1, 1, 1 }, 
		{ 1, 0, 1, 1, 1 },
		{ 1, 1, 1, 0, 0 } };

	static boolean isSquare(int row, int col, int size) {
		for (int i = 0; i < size; i++) {
			if (input[row][col + i] == 0)
				return false;
			if (input[row + size - 1][col + i] == 0)
				return false;
		}
		for (int i = 0; i < size; i++) {
			if (input[row + i][col] == 0)
				return false;
			if (input[row + i][col + size - 1] == 0)
				return false;
		}
		return true;
	}

	static Subsquare findSquare() {
		int currentMax = 0;
		Subsquare square = null;
		for (int col = 0; input[0].length - col > currentMax; col++) {
			for (int row = 0; row < input.length; row++) {
				int srow = input.length - row;
				int scol = input[0].length - col;
				int size = Math.min(srow, scol);
				while (size > currentMax) {
					if (isSquare(row, col, size)) {
						currentMax = size;
						square = new Subsquare(row, col, size);
						break;
					}
					size--;
				}
			}
		}
		return square;
	}

	public static void main(String[] args) {
		Subsquare sub = findSquare();
		System.out.println(sub.toString());
	}
}

class Subsquare {
	public int row, column, size;

	public Subsquare(int r, int c, int sz) {
		this.row = r;
		this.column = c;
		this.size = sz;
	}

	@Override
	public String toString() {
		return "" + row + ":" + column + " with size: " + size;
	}
}
