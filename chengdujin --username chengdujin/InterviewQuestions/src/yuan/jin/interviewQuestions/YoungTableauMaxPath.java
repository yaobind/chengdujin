package yuan.jin.interviewQuestions;

/**
 * Young Tableau, find a path with max sum from (0,0) to (n,n).
 * 
 * @author Yuan
 * 
 */
public class YoungTableauMaxPath {

	static int[][] input = { 
		{ 2, 4, 9, 11 }, 
		{ 3, 8, 16, 17 },
		{ 5, 14, 20, 25 },
		{ 12, 15, 23, 30 } };

	static int findMax(int[][] a) {
		int result = a[0][0];
		int i = 0, j = 0;
		while (i < a.length && j < a[0].length) {
			int right = 0;
			if (j + 1 < a[0].length)
				right = a[i][j + 1];
			int left = 0;
			if (i + 1 < a.length)
				left = a[i + 1][j];
			if (right > left) {
				result += right;
				j++;
			} else {
				result += left;
				i++;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(findMax(input));
	}

}
