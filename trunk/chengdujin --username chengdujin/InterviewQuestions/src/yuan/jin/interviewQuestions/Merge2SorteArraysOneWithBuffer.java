package yuan.jin.interviewQuestions;

/**
 * You are given two sorted arrays, A and B, and A has a large enough buffer at
 * the end to hold B. Write a method to merge B into A in sorted order.
 * 
 * @author Yuan
 * 
 */
public class Merge2SorteArraysOneWithBuffer {

	static void merge(int[] a, int[] b, int n, int m) {
		int asize = n - 1;
		int bsize = m - 1;
		int nsize = n + m - 1;
		while (asize >= 0 && bsize >= 0) {
			if (a[asize] > b[bsize]) {
				a[nsize] = a[asize];
				asize--;
				nsize--;
			} else {
				a[nsize] = b[bsize];
				bsize--;
				nsize--;
			}
		}
		while (bsize >= 0) {
			a[nsize] = b[bsize];
			nsize--;
			bsize--;
		}
	}

	public static void main(String[] args) {

	}

}
