package yuan.jin.interviewQuestions;

/**
 * Given a read only array of size n each of which is a number between 1 to n-1
 * inclusive, find any element which is present more than once in the array in
 * linear time and constant space. E.g. 2 1 2 3 5 3. Output: 2 or 3
 * 
 * @author Yuan
 * 
 */
public class DuplicateDetection {
	// elegant solution
	static void findDupBitwise(int[] input) {
		int checker = 0; 
		for (int i = 0; i < input.length; ++i) {
			int val = input[i]; 
			if ((checker & (1 << val)) > 0) {
				System.out.print(input[i] + " ");
				continue;
			}
			checker |= (1 << val);
		}
	}

	public static void main(String[] args) {
		int[] seq = { 7, 3, 1, 5, 2, 4, 1, 8, 6, 5, 3 };
		findDupBitwise(seq);
	}

}
