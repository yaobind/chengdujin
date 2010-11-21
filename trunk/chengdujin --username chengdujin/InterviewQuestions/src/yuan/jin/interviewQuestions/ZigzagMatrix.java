package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * Produce a zig-zag array. A zig-zag array is a square arrangement of the first
 * N2 integers, where the numbers increase sequentially as you zig-zag along the
 * anti-diagonals of the array.
 * 
 * 0 1 5 6 14
 * 
 * 2 4 7 13 15
 * 
 * 3 8 12 16 21
 * 
 * 9 11 17 20 22
 * 
 * 10 18 19 23 24
 * 
 * http://rosettacode.org/wiki/Zig-zag_matrix#Java
 * 
 * @author Yuan
 * 
 */
public class ZigzagMatrix {

	static int[][] zigZag(int size) {
		int[][] data = new int[size][size];
		int i = 1;
		int j = 1;
		for (int element = 0; element < size * size; element++) {
			data[i - 1][j - 1] = element;
			if ((i + j) % 2 == 0) {
				// Even stripes
				if (j < size)
					j++;
				else
					i += 2;
				if (i > 1)
					i--;
			} else {
				// Odd stripes
				if (i < size)
					i++;
				else
					j += 2;
				if (j > 1)
					j--;
			}
		}
		return data;
	}

	public static void main(String[] args) {
		int[][] result = zigZag(5);
		for (int i = 0; i < result.length; i++)
			System.out.println(Arrays.toString(result[i]));
	}

}
