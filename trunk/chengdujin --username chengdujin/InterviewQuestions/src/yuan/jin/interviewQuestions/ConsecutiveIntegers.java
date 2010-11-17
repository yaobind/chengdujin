package yuan.jin.interviewQuestions;

import java.util.ArrayList;

/**
 * Find all consecutive number sets which add up to a given number
 * 
 * http://www.hiremath.in/2009/10/03/
 * 
 * http://www.mitbbssg.com/bbsann2/life.faq/JobHunting/17/D12842543542i0/M
 * .1284782598_2.w0/%BC%B8%B5%C0%C3%E6%CA%D4%CC%E2
 * 
 * It is very much like the coin change problem.
 * 
 * @author Yuan
 * 
 */
public class ConsecutiveIntegers {

	static int[] bases;
	static ArrayList<Integer> results = new ArrayList<Integer>();

	static void find(int index, int sum) {
		if (sum == 0) {
			int compare = 1 + results.get(results.size() - 1) - results.get(0);
			if (compare == results.size())
				System.out.println(results);
			return;
		}
		for (int i = index; i < bases.length; i++) {
			if (sum >= bases[i]) {
				results.add(bases[i]);
				find(i + 1, sum - bases[i]);
				results.remove(results.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		int key = 78;
		bases = new int[(int) (key + 1) / 2];
		for (int i = 1; i <= (int) (key + 1) / 2; i++)
			bases[i - 1] = i;
		find(0, key);
	}

}
