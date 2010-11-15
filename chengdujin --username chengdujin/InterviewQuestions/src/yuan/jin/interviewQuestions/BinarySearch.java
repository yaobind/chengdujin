package yuan.jin.interviewQuestions;

public class BinarySearch {
	
	// recursive
	static int binarySearch1(int[] input, int low, int high, int key) {
		if (key < 0)
			return -1;
		int middle = (low + high)/2;
		if (key == input[middle])
			return middle;
		else if (key < input[middle])
			return binarySearch1(input, low, middle-1, key);
		else
			return binarySearch1(input, middle+1, high, key);
	}
	
	// iterative
	static int binarySearch2(int[] input, int key) {
		if (key < 0)
			return -1;
		int i = 0;
		int j = input.length;
		while (true) {
			if (i == j && input[i] != key)
				return -1;
			int middle = (i + j)/2;
			if (key == input[middle])
				return middle;
			else if (key < input[middle]) {
				j = middle - 1;
			} else
				i = middle + 1;
		}
	}

	public static void main(String[] args) {
		int[] arr = { 0, 1, 2, 5, 6, 7, 8, 9, 10, 11, 12 };
//		System.out.println(binarySearch1(arr, 0, arr.length, 11));
		System.out.println(binarySearch2(arr, 11));
	}

}
