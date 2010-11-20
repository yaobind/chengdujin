package yuan.jin.interviewQuestions;

/**
 * A majority element in an array A[] of size n is an element that appears more
 * than n/2 times (and hence there is at most one such element).
 * 
 * Write a function which takes an array and emits the majority element (if it
 * exists), otherwise prints NONE.
 * 
 * http://geeksforgeeks.org/?p=503
 * 
 * Moore’s Voting Algorithm
 * 
 * @author Yuan
 * 
 */
public class MajorityElement {

	static void printMajority(int a[], int size) {
		int cand = findCandidate(a, size);
		if (isMajority(a, size, cand))
			System.out.println(cand);
		else
			System.out.println("NO Majority Element");
	}

	static int findCandidate(int a[], int size) {
		int maj = 0, count = 1;
		int i;
		for (i = 1; i < size; i++) {
			if (a[maj] == a[i])
				count++;
			else
				count--;
			if (count == 0) {
				maj = i;
				count = 1;
			}
		}
		return a[maj];
	}

	static boolean isMajority(int a[], int size, int cand) {
		int i, count = 0;
		for (i = 0; i < size; i++)
			if (a[i] == cand)
				count++;
		if (count > size / 2)
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
//		int a[] = {1, 3, 3, 1, 2};
		int a[] = { 2, 2, 3, 5, 2, 2, 6 };
		printMajority(a, 7);
	}

}
