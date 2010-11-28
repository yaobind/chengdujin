package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * A given arrangement S of non-negative numbers and an integer k. Partition S
 * into k ranges, so as to minimize the maximum sum over all the ranges.
 * 
 * http://www.cs.sunysb.edu/~algorith/video-lectures/2007/lecture18.pdf
 * 
 * http://www8.cs.umu.se/kurser/TDBA77/VT06/algorithms/BOOK/BOOK2/NODE45.HTM
 * 
 * @author Yuan
 * 
 */
public class LinearPartitionProblem {

	// O(kn^2)
	static void partition(int[] a, int k) {
		int[] p = new int[a.length];
		p[0] = a[0];
		for (int i = 1; i < p.length; i++)
			p[i] = p[i - 1] + a[i];
		int[][] m = new int[a.length][k];
		int[][] d = new int[a.length][k];
		for (int i = 0; i < a.length; i++)
			m[i][0] = p[i];
		for (int i = 0; i < k; i++)
			m[0][i] = a[0];

		for (int i = 1; i < m.length; i++)
			for (int j = 1; j < m[0].length; j++) {
				m[i][j] = Integer.MAX_VALUE;
				for (int x = 0; x < i; x++) {
					int s = Math.max(m[x][j - 1], (p[i] - p[x]));
					if (m[i][j] > s) {
						m[i][j] = s;
						d[i][j] = x + 1;
					}
				}
			}
		
		for (int i = 0; i < m.length; i++)
			System.out.println(Arrays.toString(m[i]));
		System.out.println();
		for (int i = 0; i < d.length; i++)
			System.out.println(Arrays.toString(d[i]));
	}

	public static void main(String[] args) {
		int[] input = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		partition(input, 4);
	}

}
