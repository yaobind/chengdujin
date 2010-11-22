package yuan.jin.interviewQuestions;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个排好序的数组的一个旋转，输出旋转数组的最小元素。例如数组{3, 4, 5, 1,
 * 2}为{1, 2, 3, 4, 5}的一个旋转，该数组的最小值为1。
 * 
 * http://zhedahht.blog.163.com/blog/static/25411174200952765120546/
 * 
 * @author Yuan
 * 
 */
public class MinRotatedArray {
	// O(logN)
	static int min(int[] numbers) {
		int low = 0;
		int middle = 0;
		int high = numbers.length - 1;
		while (numbers[low] >= numbers[high]) {
			if (high - low == 1) {
				middle = high;
				break;
			}
			middle = (low + high) / 2;
			if (numbers[middle] >= numbers[low])
				low = middle;
			else if (numbers[middle] <= numbers[high])
				high = middle;
		}
		return numbers[middle];
	}

	public static void main(String[] args) {
		int[] sequence = { 15, 16, 19, 20, 25, 2, 3, 4, 5, 7, 9, 10, 11, 12, 14 };
		// int[] sequence = { 7, 8, 9, 1, 2, 3, 4, 5, 6 };
		// int[] sequence = { 6, 7, 8, 1, 2, 3, 4, 5 };
		int val = min(sequence);
		System.out.println("The minimum is " + val);
	}

}
