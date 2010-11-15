package yuan.jin.interviewQuestions;

import java.util.HashMap;

public class DuplicateFindFirst {

	private static int findFirstDuplicate(int[] input) {
		HashMap tlist = new HashMap();
		for (int i = 0; i < input.length; i++) {
			if (tlist.containsKey(input[i]))
				return input[i];
			else
				tlist.put(input[i], 1);
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] seq = { 0, 2, 3, 1, 5, 6, 4, 3, 7, 1, 0, 9 };
		
		System.out.println(findFirstDuplicate(seq));
	}

}
