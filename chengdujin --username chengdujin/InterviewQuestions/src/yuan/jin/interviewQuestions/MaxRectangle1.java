package yuan.jin.interviewQuestions;

/**
 * largest rectangular problem
 * 
 * @author Yuan
 *
 */
public class MaxRectangle1 {
	static int[][] matrix = { 
			{ 0, 1, 1, 0, 1 }, 
			{ 1, 1, 0, 1, 0 },
			{ 0, 0, 1, 1, 1 }, 
			{ 1, 1, 1, 1, 0 }, 
			{ 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1 } };
	static int[][] assistant = new int[matrix.length][matrix[0].length];
	static int[][] upper = new int[matrix.length][matrix[0].length];
	static int[][] lower = new int[matrix.length][matrix[0].length];
	
	static int findLargest() {
		int max = 0;
		int current = 0;
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[0].length; j++) {
				current = assistant[i][j] * (1 + upper[i][j] + lower[i][j]);
				if (current > max) {
					System.out.println("[" + i + "," + j + "]-" + "[" + (i + lower[i][j]) + "," + (j + assistant[i][j] - 1) + "]");
					max = current;
				}
			}
		return max;
	}
	
	static void computeLength() {
		// initialization
		for (int j = 0; j < matrix[0].length; j++) {
			upper[0][j] = 0;
			lower[matrix.length-1][j] = 0;
		}
		// set upper array
		for (int j = 0; j < matrix[0].length; j++)
			for (int i = 1; i < matrix.length; i++) {
				upper[i][j] = 0;
				if (assistant[i][j] == 0)
					continue;
				if (assistant[i-1][j] < assistant[i][j])
					continue;
				
				int t = i-1;
				while (t >= 0 && assistant[t][j] >= assistant[i][j]) {
					upper[i][j]++;
					t--;
				}
			}
		// set lower array
		for (int j = 0; j < matrix[0].length; j++)
			for (int i = matrix.length-2; i >= 0; i--) {
				lower[i][j] = 0;
				if (assistant[i][j] == 0)
					continue;
				if (assistant[i+1][j] < assistant[i][j])
					continue;
				
				int t = i+1;
				while (t < matrix.length && assistant[t][j] >= assistant[i][j]) {
					lower[i][j]++;
					t++;
				}
			}
	}
	
	static void find1s() {
		for (int i = 0; i < matrix.length; i++) {
			assistant[i][matrix[0].length-1] = matrix[i][matrix[0].length-1];
		}
		
		for (int i = 0; i < matrix.length; i++)
			for (int j = matrix[0].length - 2; j >= 0 ; j--) {
					if (matrix[i][j] == 1)
						assistant[i][j] = matrix[i][j] + assistant[i][j+1];
					else if (matrix[i][j] == 0)
						assistant[i][j] = matrix[i][j];
			}
	}
	
	public static void main(String[] args) {
		find1s();
		computeLength();
		System.out.println(findLargest());
	}

}
