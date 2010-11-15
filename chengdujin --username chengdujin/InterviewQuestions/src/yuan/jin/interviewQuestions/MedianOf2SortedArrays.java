package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * Time complexity O(logn)
 * 
 * http://projecteureka.org/solution/show/1168
 * 
 * 1) Calculate the medians m1 and m2 of the input arrays ar1[] and ar2[]
 * respectively.
 * 
 * 2) If m1 and m2 both are equal then we are done. return m1 (or m2)
 * 
 * 3) If m1 is greater than m2, then median is present in one of the below two
 * subarrays.     
 * 
 * 3.a) From first element of ar1 to m1 (ar1[0...|_n/2_|])    
 * 
 * 3.b) From m2 to last element of ar2  (ar2[|_n/2_|...n-1])
 * 
 * 4) If m2 is greater than m1, then median is present in one of the below two
 * subarrays.   
 * 
 * 4.a) From m1 to last element of ar1  (ar1[|_n/2_|...n-1])   
 * 
 * 4.b) From first element of ar2 to m2 (ar2[0...|_n/2_|])
 * 
 * 5) Repeat the above process until size of both the subarrays becomes 2.
 * 
 * 6) If size of the two arrays is 2 then use below formula to get the median.
 * 
 * Median = (max(ar1[0], ar2[0]) + min(ar1[1], ar2[1]))/2
 * 
 * @author Yuan
 * 
 */
public class MedianOf2SortedArrays {
	
	static int findMedian(int[] arr1, int low1, int high1, int[] arr2, int low2, int high2) {
		if ((high1 - low1) == 1 && (high2 - low2) == 1)
			return (Math.max(arr1[low1], arr2[low2]) + Math.min(arr1[high1], arr2[high2]))/2;
		
		int med1 = (high1 - low1)/2 + low1;
		int med2 = (high2 - low2)/2 + low2;
		
		if (arr1[med1] == arr2[med2])
			return arr1[med1];
		else if (arr1[med1] > arr2[med2])
			return findMedian(arr1, low1, med1, arr2, med2, high2);
		else
			return findMedian(arr1, med1, high1, arr2, low2, med2);
	}

	public static void main(String[] args) {
		int[] input1 = { 1, 9, 3, 8, 2, 11, 4, 5, 6 };
		int[] input2 = { 1, 6, 3, 5, 4 };
//		int[] input1 = { 1, 12, 15, 26, 38 };
//		int[] input2 = { 2, 13, 17, 30, 45 };
		Arrays.sort(input1);
		Arrays.sort(input2);
//		System.out.println(Arrays.toString(input1));
//		System.out.println(Arrays.toString(input2));
		System.out.println(findMedian(input1, 0, input1.length-1, input2, 0, input2.length-1));
	}

}
