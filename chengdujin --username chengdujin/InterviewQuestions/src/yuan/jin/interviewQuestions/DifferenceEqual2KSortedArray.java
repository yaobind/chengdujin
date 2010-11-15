package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * Given sorted array of numbers, find two numbers in array with difference
 * equal to k [ in-place]. Time O(n)
 * 
 * http://discuss.joelonsoftware.com/default.asp?interview.11.574886
 * 
 * @author Yuan
 * 
 */
public class DifferenceEqual2KSortedArray {
	// left and right don't have to be decremented, so it's O(n)
	static void findK(int[] arr, int key) {
		int left, right, diff;
		for (left = 0, right = 1; right < arr.length; right++) {
			while (true) {
				diff = arr[right] - arr[left];
				if (diff == key)
					System.out
							.println("(" + arr[left] + "," + arr[right] + ")");
				if (diff < key)
					break;
				left++;
			}
		}
	}

	public static void main(String[] args) {
		int[] numbers = { 1, 9, 13, 25, 28, 39, 40, 46, 47, 50 };
		Arrays.sort(numbers);
		System.out.println(Arrays.toString(numbers));
		findK(numbers, 11);
	}
}
