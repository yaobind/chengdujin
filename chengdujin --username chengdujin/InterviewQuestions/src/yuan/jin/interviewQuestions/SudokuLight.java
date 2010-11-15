package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * Given a 4 x 4 square, fill in each cell an integer selected from {1, 2, 3,
 * 4}. Make sure that each row, each column, and each 2 x 2 sub-square
 * (excluding the central one) do not contain duplicate numbers.
 * 
 * This question resembles very much like the 8 queens question in a more
 * difficult fashion.
 * 
 * Compare with EightQueens
 * 
 * @author Yuan
 * 
 */
public class SudokuLight {
	static int[] spaces = new int[4];

	static boolean checkHorizontal(int row) {
		for (int i = 0; i < row; i++) {
			int diff = Math.abs(spaces[i] - spaces[row]);
			if (diff == 0)
				return false;
		}
		return true;
	}

	static boolean checkDiagonal(int row, int i) {
		if (i >= 0 && i < 4) {
			int diff = Math.abs(spaces[i] - spaces[row]);
			if (diff == 1)
				if (!(row == 1 && spaces[row] == 1 && i == 2 && spaces[i] == 2)
						&& !(row == 2 && spaces[row] == 1 && i == 1 && spaces[i] == 2)
						&& !(row == 2 && spaces[row] == 2 && i == 1 && spaces[i] == 1)
						&& !(row == 1 && spaces[row] == 2 && i == 2 && spaces[i] == 1))
					return false;
		}
		return true;
	}

	static void placePosition(int row) {
		if (row == 4) {
			System.out.println(Arrays.toString(spaces));
			return;
		}

		for (int i = 0; i < 4; i++) {
			spaces[row] = i;
			if (checkHorizontal(row) && checkDiagonal(row, row - 1)
					&& checkDiagonal(row, row + 1)) {
				placePosition(row + 1);
			} else
				spaces[row] = -100;
		}
	}

	static void place() {
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 4; i++)
				spaces[i] = -100;
			spaces[0] = j;
			placePosition(1);
		}
	}

	public static void main(String[] args) {
		// place finds acceptable ways to place a number.
		place();
	}

}
