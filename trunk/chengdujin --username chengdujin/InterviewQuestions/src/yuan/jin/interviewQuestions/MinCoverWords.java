package yuan.jin.interviewQuestions;

/**
 * You have a large text file containing words Given any two words, find the
 * shortest distance (in terms of number of words) between them in the file Can
 * you make the searching operation in O(1) time? What about the space
 * complexity for your solution?
 * 
 * CareerCup 4.20.5
 * 
 * @author Yuan
 * 
 */
public class MinCoverWords {

	static int shortest(String[] words, String w1, String w2) {
		int min = Integer.MAX_VALUE / 2;
		int pos1 = -min;
		int pos2 = -min;
		for (int i = 0; i < words.length; i++) {
			String current = words[i];
			if (current.equals(w1)) {
				pos1 = i;
				int distance = pos1 - pos2;
				if (min > distance)
					min = distance;
			} else if (current.equals(w2)) {
				pos2 = i;
				int distance = pos2 - pos1;
				if (min > distance)
					min = distance;
			}
		}
		return min;
	}

	public static void main(String[] args) {
		String input = "We will assume for this question that the word order does not matter "
				+ "This is a question you should ask your interviewer If the word order does matter "
				+ "we can make the small modification shown in the code below To solve this problem "
				+ "simply traverse the file and for every occurrence of word1 and word2 compare difference "
				+ "of positions and update the current minimum";
		String[] arinput = input.split("([ \t]+)");
		System.out.println(shortest(arinput, "for", "current"));
	}

}
