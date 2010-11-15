package yuan.jin.interviewQuestions;

import java.util.ArrayList;

public class SubsetsAll {

	private static ArrayList<ArrayList<Integer>> getSubsets2(
			ArrayList<Integer> set) {
		ArrayList<ArrayList<Integer>> allsubsets = new ArrayList<ArrayList<Integer>>();
		int max = 1 << set.size();

		for (int i = 0; i < max; i++) {
			ArrayList<Integer> subset = new ArrayList<Integer>();
			int k = i;
			int index = 0;
			while (k > 0) {
				if ((k & 1) > 0)
					subset.add(set.get(index));
				k >>= 1;
				index++;
			}
			allsubsets.add(subset);
		}
		
		return allsubsets;
	}

	public static void main(String[] args) {
		ArrayList<Integer> test = new ArrayList<Integer>();
		test.add(1);
		test.add(2);
		test.add(3);

		System.out.println(getSubsets2(test));

	}

}
