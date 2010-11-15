package yuan.jin.interviewQuestions;

import java.util.LinkedList;

/**
 * Finds Longest Consecutive Character's in a String
 * 
 * http://www.dreamincode.net/code/snippet5574.htm
 * 
 * @author Yuan
 * 
 */
public class LongestConsecutiveCharsOfString {

	static void LCC(String string1) {
		LinkedList<Character> Characters = new LinkedList<Character>();
		LinkedList<Integer> Maxlengths = new LinkedList<Integer>();
		int len = string1.length();
		if (len >= 2) {
			int i = 1;
			int max = 1;
			int LLCL = 0;
			for (; i < len; i++) {
				if (string1.charAt(i) == string1.charAt(i - 1))
					max++;
				else {
					if (max >= LLCL) {
						if ((Characters.indexOf(string1.charAt(i - 1)) == -1)) {
							LLCL = max;
							Characters.add(string1.charAt(i - 1));
							Maxlengths.add(LLCL);
						} else
							Maxlengths
									.set((Characters.indexOf(string1
											.charAt(i - 1))), LLCL);
					}
					max = 1;
				}
			}

			if (max >= LLCL) {
				if ((Characters.indexOf(string1.charAt(i - 1)) == -1)) {
					Characters.add(string1.charAt(i - 1));
					Maxlengths.add(LLCL);
				} else
					Maxlengths.set((Characters.indexOf(string1.charAt(i - 1))),
							LLCL);
			}

			i = 0;
			len = Characters.size();
			for (; i < len; i++)
				if (LLCL == Maxlengths.get(i))
					System.out.println(Characters.get(i) + " Length = " + LLCL);
		} else
			System.out.println(string1.charAt(0) + " Length = " + 1);
	}

	public static void main(String[] args) {
		LCC("hello world");
		System.out.println();
		LCC("h");
		System.out.println();
		LCC("AAABcdEEEghhhhgE-998EEEkkeEEEEkkEEEE");
	}
}
