package yuan.jin.interviewQuestions;

/**
 * 数组中两两之差最小的值 有一个整数数组，请求出两两之差最小的值,记住，只要得出最小值即可，不需要求出是哪两个数
 * 
 * http://fayaa.com/tiku/view/116/
 * 
 * @author Yuan
 * 
 */
public class MinPairOfArray {

	// http://tech-queries.blogspot.com/2010/07/maximum-difference-in-array.html
	static void max(int[] a) {
		int curri = 0, currj = 1, maxDiff = 0, maxi = 0, maxj = 0;
		int currDiff = a[currj] - a[curri];
		for (int i = 2; i < a.length; i++) {
			if (a[i] < a[curri]) {
				curri = i;
				currDiff = a[maxj] - a[curri];
				if (currDiff > maxDiff) {
					maxi = curri;
					maxDiff = currDiff;
				}
			} else {
				currj = i;
				currDiff = a[currj] - a[maxi];
				if (currDiff > maxDiff) {
					maxj = currj;
					maxDiff = currDiff;
				}
			}
		}
		System.out.println("i = " + maxi + " j = " + maxj + " diff = "
				+ maxDiff);
	}

	static int findMinDiff(int[] input) {
		int[] input2 = new int[input.length - 1];
		for (int i = 0; i < input.length - 1; i++)
			input2[i] = input[i] - input[i + 1];
		return minSum(input2);
	}

	static int minSum(int[] input) {
		int current = input[0];
		int min = Math.abs(input[0]);
		for (int i = 1; i < input.length; i++) {
			current = current + input[i];
			if (current <= 0) {
				if (Math.abs(current) < min)
					min = Math.abs(current);
			} else
				current = 0;
		}
		return min;
	}

	public static void main(String[] args) {
//		 int[] seq = { 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14 };
		int[] seq = { 56, 44, 8, 59, 120, 9, 121, -6, -10, 90, -11 };
//		System.out.println(findMinDiff(seq));
		max(seq);
	}

}
