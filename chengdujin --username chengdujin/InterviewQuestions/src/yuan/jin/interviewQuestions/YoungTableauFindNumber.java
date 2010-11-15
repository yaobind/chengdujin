package yuan.jin.interviewQuestions;

/**
 * Given a matrix in which each row and each column is sorted, write a method to
 * find an element in it.
 * 
 * CareerCup 1.14.3
 * 
 * @author Yuan
 * 
 */
public class YoungTableauFindNumber {

	static boolean findElement(int[][] mat, int elem) {
		int row = mat.length - 1, col = 0;
		while (row >= 0 && col < mat[0].length) {
			if (mat[row][col] == elem) {
				System.out.println(elem + " is found @row " + (row + 1)
						+ " column " + (col + 1));
				return true;
			} else if (mat[row][col] > elem) {
				row--;
			} else {
				col++;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] test = { { 2, 4, 9, 11 }, { 3, 8, 16, 17 }, { 5, 14, 20, 25 },
				{ 12, 15, 23, 30 } };
		findElement(test, 2);
	}

}
