package yuan.jin.interviewQuestions;

/**
 * You have a matrix with 0 & 1 with rows being in sorted order. Find the row
 * with minimum number of 1's
 * 
 * 0 0 0 1 1
 * 
 * 0 0 1 1 1
 * 
 * 0 1 1 1 1
 * 
 * 0 0 1 1 1
 * 
 * http://inder-gnu.blogspot.com/2010/03/find-row-with-minumum-0s.html
 * 
 * @author Yuan
 * 
 */
public class RowWithMin0s {

	static void findMin(int[][] a) {
		int min = a[0].length;
		int index = 0;
		int j = a[0].length - 1;
		for (int i = 0; i < a.length; i++) {
			for (; j >= 0; j--) {
				if (a[i][j] == 0) {
					if ((j + 1) < min) {
						min = j + 1;
						index = i;
					}
					break;
				}
			}
		}
		System.out.println("min:" + min + " line:" + index);
	}

	public static void main(String[] args) {
		int[][] input = { 
				{ 0, 0, 0, 1, 1 }, 
				{ 0, 0, 1, 1, 1 },
				{ 0, 0, 0, 1, 1 }, 
				{ 0, 1, 1, 1, 1 }, 
				{ 0, 0, 1, 1, 1 } };
		findMin(input);
	}

}
