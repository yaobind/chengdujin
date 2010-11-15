package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * Compare with SudokuLight
 * 
 * @author Yuan
 * 
 */
public class EightQueens {

	static int[] spaces = new int[8];

	static boolean check(int row) {
		for (int i = 0; i < row; i++) {
			int diff = Math.abs(spaces[i] - spaces[row]);
			if (diff == 0 || diff == row - i)
				return false;
		}
		return true;
	}

	static void place(int row) {
		if (row == 8) {
			System.out.println(Arrays.toString(spaces));
			return;
		}

		for (int i = 0; i < 8; i++) {
			spaces[row] = i;
			if (check(row)) {
				place(row + 1);
			}
		}
	}

	public static void main(String[] args) {
		place(0);
	}

}
