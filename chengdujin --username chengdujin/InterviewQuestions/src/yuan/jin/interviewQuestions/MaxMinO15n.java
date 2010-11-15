package yuan.jin.interviewQuestions;

/**
 * Beauty of Programming 2.10: Find the max and min of an array at the cost of
 * O(1.5n) time complexity
 * 
 * @author Yuan
 * 
 */
public class MaxMinO15n {
	static void maxMin(int[] input) {
		int temp = input[0] + input[1];
		int max = input[0] > input[1] ? input[0] : input[1];
		int min = temp - max;
		int currMax = 0;
		int count = 1;

		for (int i = 0; i < input.length; i += 2) {
			temp = input[i] + input[i + 1];
			currMax = input[i] > input[i + 1] ? input[i] : input[i + 1];
			count++;
			temp = temp - currMax;
			max = max > currMax ? max : currMax;
			count++;
			min = min < temp ? min : temp;
			count++;
		}
		System.out.println(max + ":" + min + " " + input.length + "@" + count);
	}

	public static void main(String[] args) {
		int[] input = { 5, 9, 8, 3, 7, 6, 1, 4, 10, 2 };
		maxMin(input);
	}

}
