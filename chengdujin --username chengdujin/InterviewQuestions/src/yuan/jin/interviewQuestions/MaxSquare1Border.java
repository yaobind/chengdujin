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
	static int[][] test = { 
		{ 0, 1, 1, 1, 1 }, 
		{ 1, 1, 0, 1, 0 },
		{ 0, 1, 0, 1, 0 }, 
		{ 1, 1, 1, 1, 0 }, 
		{ 1, 0, 1, 1, 1 },
		{ 1, 1, 1, 0, 0 } };

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
	
	Subsquare findSquare(int[][] matrix) {
	    int N = matrix.length;
	    int col = 0;
	    int currentMax = 0;
	    Subsquare square = null;
	    while (N-col > currentMax) {
	        for (int row = 0; row < matrix.length; row++) {
	            int size = N - Math.max(row, col);
	            while (size > currentMax) {
	                if (isSquare(matrix, row, col, size)) {
	                    currentMax = size;
	                    square = new Subsquare(row, col, size);
	                    break;
	                }
	                size--;
	            }
	        }
	        col++;
	    }
	    return square;
	}

	boolean isSquare(int[][] matrix, int row, int col, int size) {
	    for(int i = 0; i < size; i++) {
	        if (matrix[row][col + i] == 0)
	            return false;
	        if (matrix[row + size - 1][col + i] == 0)
	            return false;
	    }
	    for(int i = 0; i < size; i++) {
	        if (matrix[row + i][col] == 0)
	            return false;
	        if (matrix[row + i][col + size - 1] == 0)
	            return false;
	    }
	    return true;
	}
	
	public static void main(String[] args) {
		MaxSquare1Border square = new MaxSquare1Border();
		Subsquare sub = square.findSquare(test);
		System.out.println(sub.toString());
	}

}
