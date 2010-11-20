package yuan.jin.interviewQuestions;

import java.util.ArrayList;
import java.util.List;

/**
 * Programming Interviews Exposed: Recursion.Combinations of a String
 * 
 * @author Yuan
 * 
 */
public class Combinations {

	private static List<String> combinations;

	// method1 iterative
	private static void findCombinations(String[] seq) {
		for (int i = 0; i < seq.length; i++) {
			if (!combinations.isEmpty()) {
				int len = combinations.size();
				for (int j = 0; j < len; j++) {
					combinations.add(combinations.get(j).toString() + seq[i]);
				}
			}
			combinations.add(seq[i]);
		}
	}

	// method2 recursive
	static void doCombine(String[] instr, StringBuilder outstr, int start) {
		for (int i = start; i < instr.length; i++) {
			outstr.append(instr[i]);
			System.out.println(outstr);
			if (i < instr.length - 1)
				doCombine(instr, outstr, i + 1);
			outstr.setLength(outstr.length() - 1);
		}
	}

	// Print out all combinations of k numbers out of 1...N e.g. when k = 2, n =
	// 4 Print out 12, 13, 14, 23, 24, 34
	static void combByIndex(String[] instr, StringBuilder outstr, int start, int width, int digit) {
		for (int i = start; i < instr.length; i++) {
			outstr.append(instr[i]);
			if (digit == 0)
				System.out.println(outstr);
			if (width < digit)
				combByIndex(instr, outstr, i + 1, width + 1, digit - 1);
			outstr.setLength(outstr.length() - 1);
		}
	}

	public static void main(String[] args) {
		combinations = new ArrayList<String>();
		String[] sequence = { "1", "2", "3", "4" };
		StringBuilder outstr = new StringBuilder();
//		 doCombine(sequence, outstr, 0);
		combByIndex(sequence, outstr, 0, 0, 1);
		// findCombinations(sequence);
		// System.out.println(combinations.toString());
	}

}
