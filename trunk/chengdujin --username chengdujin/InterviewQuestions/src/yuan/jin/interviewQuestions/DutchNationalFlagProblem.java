package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * Given an array A[] consisting 0s, 1s and 2s, write a function that sorts A[].
 * The functions should put all 0s first, then all 1s and all 2s in last.
 * 
 * Time Complexity: O(n)
 * 
 * http://www.csse.monash.edu.au/~lloyd/tildeAlgDS/Sort/Flag/
 * 
 * @author Yuan
 * 
 */
public class DutchNationalFlagProblem {

	// http://geeksforgeeks.org/?p=5234
	void segregate01(int arr[], int size) {
		int left = 0, right = size - 1;
		while (left < right) {
			while (arr[left] == 0 && left < right)
				left++;
			while (arr[right] == 1 && left < right)
				right--;
			if (left < right) {
				arr[left] = 0;
				arr[right] = 1;
				left++;
				right--;
			}
		}
	}

	// http://geeksforgeeks.org/?p=8133
	static void segregate012(int[] a) {
		int lo = 0;
		int hi = a.length - 1;
		int mid = 0;
		while (mid <= hi) {
			switch (a[mid]) {
			case 0:
				int c = lo++;
				int d = mid++;
				a[c] = a[c] ^ a[d];
				a[d] = a[c] ^ a[d];
				a[c] = a[c] ^ a[d];
				break;
			case 1:
				mid++;
				break;
			case 2:
				int e = hi--;
				a[mid] = a[mid] ^ a[e];
				a[e] = a[mid] ^ a[e];
				a[mid] = a[mid] ^ a[e];
				break;
			}
		}
	}

	public static void main(String[] args) {
		int a01[] = { 0, 1, 0, 1, 1, 1 };
		segregate012(a01);
		System.out.println(Arrays.toString(a01));

		int a012[] = { 0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1 };
		segregate012(a012);
		System.out.println(Arrays.toString(a012));
	}

}
