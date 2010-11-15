package yuan.jin.interviewQuestions;

/**
 * Remove all duplicates from the input string.
 * 
 * http://geeksforgeeks.org/?p=21
 * 
 * @author Yuan
 * 
 */
public class DuplicateRemovalString {
	
	// if the length of string is more than 32 characters, we have to sacrifice
	// some space for performance
	static String dedup2(String str) {
		StringBuilder sb = new StringBuilder(str);
		boolean[] characters = new boolean[256];
		for (int i = 0; i < sb.length();) {
			if (!characters[sb.charAt(i)]) {
				characters[sb.charAt(i)] = true;
				i++;
			} else
				sb.replace(i, i + 1, "");
		}
		return sb.toString();
	}

	// if the length of string is less than 32 characters, by using this method,
	// it would save more space
	static String dedup(String str) {
		StringBuilder sb = new StringBuilder(str);
		int i = 0;
		int checker = 0;
		while (i < sb.length()) {
			int val = sb.charAt(i);
			if ((checker & (1 << val)) > 0) {
				sb.replace(i, i + 1, "");
				continue;
			}
			checker |= (1 << val);
			i++;
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String input = "geeksforgeeks";
		System.out.println(dedup2(input));
	}

}
