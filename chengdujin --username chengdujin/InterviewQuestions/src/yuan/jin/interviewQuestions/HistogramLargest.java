package yuan.jin.interviewQuestions;

import java.util.Arrays;
import java.util.Random;

/**
 * Given: An integer array represents a histogram. Goal: Find the largest
 * rectangle under the histogram. Complexity O(N) where N is the size of the
 * given array.
 * 
 * 输入为一个整数数组h[i]. 对于图中的某个面积最大的矩形， 必然有一个最低的高度h[k]， 即矩形的高等于 h[k]， 以第k块矩形的高度，
 * 最左边可以到达这个矩形的左边， 最右边可以到达这个矩形的右边。 所以， 可以以每块矩形进行扩展， 求出最左边和最右边（即两边的高度都大于等于这块的高度
 * ）， 得出面积s[i]， 这样就可求出最大的s[i]了。
 * 
 * http://mach.debagua.com/archives/2010/0416_001117.html
 * 
 * @author Yuan
 * 
 */
public class HistogramLargest {
	static int height[];
	// left[i] = j表示第i个矩形以它的高度到达最左边的下标
	static int left[];
	static int right[];

	static void findHistogram() {
		int i, temp, max;
		left[0] = 0;
		right[right.length - 1] = right.length - 1;
		for (i = 1; i <= height.length - 1; i++) {
			left[i] = i;
			int j = i;
			while (j > 0 && height[j - 1] >= height[i]) {
				left[i] = j - 1;
				j--;
			}
		}
		for (i = height.length - 2; i >= 0; i--) {
			right[i] = i;
			int j = i;
			while (j < 9 && height[j + 1] >= height[i]) {
				right[i] = j + 1;
				j++;
			}
		}
		max = 0;
		for (i = 0; i <= height.length - 1; i++) {
			temp = height[i] * (right[i] - left[i] + 1);
			if (temp > max) {
				System.out.println(height[i] + " left:" + left[i] + " right:"
						+ right[i]);
				max = temp;
			}
		}
		System.out.println(max);
	}

	public static void main(String[] args) {
		int n = 10;
		height = new int[n];
		left = new int[n];
		right = new int[n];
		Random gen = new Random();
		int i = 0;
		while (i < height.length)
			height[i++] = 1 + gen.nextInt(height.length * 2);
		System.out.println(Arrays.toString(height));
		findHistogram();
	}

}
