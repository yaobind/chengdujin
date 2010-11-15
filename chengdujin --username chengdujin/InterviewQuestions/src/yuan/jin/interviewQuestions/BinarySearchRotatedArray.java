package yuan.jin.interviewQuestions;

/**
 * CareerCup Top 150 Questions 19.3
 * 
 * Also find explanations via
 * http://www.ihas1337code.com/2010/04/searching-element-in-rotated-array.html
 * 
 * @author Yuan
 * 
 */
public class BinarySearchRotatedArray {

	static int rotatedBinarySearch(int key, int a[], int left, int rite) {
		if (left > rite)
			return -1;
		// Avoid overflow, same as M=(L+R)/2
		int m = left + ((rite - left) / 2);
		if (a[m] == key)
			return m;
		if (a[m] >= a[left]) {
			if (key < a[m] && key >= a[left])
				rite = m - 1;
			else
				left = m + 1;
		} else {
			if (key > a[m] && key <= a[rite])
				left = m + 1;
			else
				rite = m - 1;
		}
		return rotatedBinarySearch(key, a, left, rite);
	}

	public static void main(String[] args) {
		int[] sequence = { 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14 };
		int key = 14;
		int val = rotatedBinarySearch(key, sequence, 0, sequence.length - 1);
		System.out.println("[" + val + "] = " + key);
	}

}
