package yuan.jin.interviewQuestions;

/**
 * Array, elements are +/-, find longest sub-array sum of which is less or equal
 * to K / How will you find the subarray whose sum is k in an array of negative
 * and positive numbers
 * 
 * Modified from http://www.doctorinterview.com/A/2A/2A44.html
 * 
 * Compare with ConsecutiveIntegers
 * 
 * @author Yuan
 * 
 */
public class LongestArrayEqual2K {
	// O(n^2)
	static int find(int[] a, int k) {
		int sum = 0;
		int current = 0;
		int maxLen = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			current = sum;
			for (int j = 0; j < i; j++) {
				current = current - a[j];
				if (current <= k) {
					if (maxLen < (i - j)) {
						maxLen = i - j;
						System.out.print("* ");
						System.out.println("Elements from " + (j + 1) + "("
								+ a[j + 1] + ") to " + i + "(" + a[i] + ")");
					}
					break;
				}
			}
		}
		return maxLen;
	}

	public static void main(String[] args) {
		int[] input = { 4, 14, 3, 2, -1, -5, 3, -2, 5, -7, 19, -12 };
		System.out.println(find(input, 5));
	}

}
