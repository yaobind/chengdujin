package yuan.jin.interviewQuestions;

/**
 * Find kth largest element in two sorted arrays.
 * 
 * http://inder-gnu.blogspot.com/2007/09/find-k-th-largest-in-two-sorted-arrays.
 * html
 * 
 * @author Yuan
 * 
 */
public class KthLargestOf2SortedArrays {

	// this method gives a time complexity of O(k)
	static int methodOk(int[] a, int[] b, int k) {
		if (k >= 2*a.length || k >= 2*b.length) {
			if (a.length > b.length)
				return a[k-b.length];
			else
				return b[k-a.length];
		}
		int i = 0, j = 0;
		while ((i + j) < k) {
			if (a[i] < b[j])
				i++;
			else
				j++;
		}
		if ((i + j) == k)
			return Math.max(a[i], b[j]);
		return -1;
	}

	// this method gives a time complexity of O(logk)
	// the idea is that we have to find the median of first k elements of A and
	// B and k = 5
	static int findKth(int[] a, int[] b, int i1, int j1, int i2, int j2, int key) {
		if (key < 1)
			return -1;
		if ((j1 == i1) && (j2 == i2)) {
			int max = Math.max(a[i1], b[i2]);
			int min = Math.min(a[i1], b[i2]);
			return key == 1 ? min : max;
		}
		int median = a[(i1 + j1) / 2];
		// System.out.print(i1 + "-" + j1 + " " + i2 + "-" + j2 + " key:" +
		// key);
		// System.out.print(" median:" + median);
		int end = i2;
		for (int i = i2; i <= j2; i++)
			if (b[i] > median) {
				end = i - 1;
				break;
			}
		int t = 0;
		t = ((i1 + j1) / 2 - i1 + 1) + (end - i2 + 1);
		// System.out.println(" t:" + t + " end:" + end);
		if (key == t)
			return median;
		else if (key > t) {
			if (end + 1 < b.length && ((i1 + j1) / 2 + 1 < a.length))
				return findKth(a, b, (i1 + j1) / 2 + 1, j1, end + 1, j2,
						(key - t));
			else {
				if (((i1 + j1) / 2 + 1) < a.length)
					return a[(i1 + j1) / 2 + (key - t)];
				else if (end + 1 < b.length)
					return b[end + key - t];
			}
		} else if (key < t)
			return findKth(a, b, i1, (i1 + j1) / 2, i2, end, key);
		return -1;
	}

	public static void main(String[] args) {
//		int[] a = { 2, 3, 7, 12, 27, 81, 91 };
//		int[] b = { 1, 25, 32, 74, 89 };
		int[] b = { 1, 2, 3, 4, 5, 6, 8, 9, 11 };
		int[] a = { 1, 3, 4, 5, 6 };
		System.out.println(methodOk(a, b, 13));
//		System.out.println(methodOlogk(a, a.length-5, a.length-1, b, b.length-5, b.length-1));
	}

}
