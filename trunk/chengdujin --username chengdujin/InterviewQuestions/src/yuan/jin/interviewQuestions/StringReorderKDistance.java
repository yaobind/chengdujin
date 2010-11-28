package yuan.jin.interviewQuestions;

/**
 * Given a string of lowercase characters, reorder them such that the same
 * characters are at least distance d from each other.
 * 
 * Input: { a, b, b }, distance = 2
 * 
 * Output: { b, a, b }
 * 
 * The solution below involves a greedy strategy, that is: The character that
 * has the most duplicates has the highest priority of being chosen to put in
 * the new list. If that character cannot be chosen (due to the distance
 * constraint), we go for the character that has the next highest priority. We
 * also use some tables to improve the efficiency. (i.e., keeping track of # of
 * duplicates of each character.)
 * 
 * http://www.ihas1337code.com/2010/05/here-is-another-google-phone-interview.
 * html
 * 
 * @author Yuan
 * 
 */
public class StringReorderKDistance {

	static char max(int[] freq, boolean[] excep) {
		char maxi = (char) -1;
		int max = -1;
		for (char c = 'a'; c <= 'z'; c++)
			if (!excep[c] && freq[c] > 0 && freq[c] > max) {
				max = freq[c];
				maxi = c;
			}
		return maxi;
	}

	static String create(char[] a, int distance, char[] b) {
		int[] freq = new int[256];
		for (int i = 0; i < a.length; i++)
			freq[a[i]]++;
		int[] used = new int[256];
		for (int i = 0; i < a.length; i++) {
			boolean[] excep = new boolean[256];
			boolean done = false;
			while (!done) {
				char j = max(freq, excep);
				if (j == -1) {
					System.out.print("Error!\n");
					return null;
				}
				excep[j] = true;
				if (used[j] <= 0) {
					b[i] = j;
					freq[j]--;
					used[j] = distance;
					done = true;
				}
			}
			for (int k = 0; k < 256; k++)
				used[k]--;
		}
		return new String(b);
	}

	public static void main(String[] args) {
		String input = "asdfadatg";
		char[] cinput = input.toCharArray();
		char[] result = new char[cinput.length];
		System.out.println(create(cinput, 3, result));
	}

}
