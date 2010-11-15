package yuan.jin.interviewQuestions;

/**
 * The method takes two int value matrices, mat1 and mat2. Then returns a new
 * matrix, newMat, in which each cell's value is the result of the
 * multiplication of mat1 and mat2 value in that exact cell. The method throws
 * an IllegalArgumentException if mat1 and mat2 are not at the same length.
 * 
 * http://www.dreamincode.net/code/snippet4866.htm
 * 
 * @author Yuan
 * 
 */
public class MatrixMultiplication {

	static int[][] matricesMultiplication(int[][] mat1, int[][] mat2)
			throws IllegalArgumentException {

		/*
		 * check if mat1 and mat2 are of the same lengthif not, throw an
		 * IllegalArgumentException
		 */
		if (mat1.length != mat2.length) {
			throw new IllegalArgumentException();
		}

		else {

			/* initiate the new matrix by the length of mat1 */
			int[][] newMat = new int[mat1.length][mat1[0].length];

			for (int i = 0; i < mat1.length; i++) {

				/*
				 * check if mat1 and mat2 contain the same number of arguments
				 * for each given rowif not, throw an IllegalArgumentException
				 */
				if (mat1[i].length != mat2[i].length) {
					throw new IllegalArgumentException();
				}
				/* multiple aruments */
				else {
					for (int j = 0; j < mat1[0].length; j++) {
						newMat[i][j] += mat1[i][j] * mat2[i][j];
					}
				}
			}
			/* return the new matrix */
			return newMat;
		}
	}

	public static void main(String[] args) {

	}
}
