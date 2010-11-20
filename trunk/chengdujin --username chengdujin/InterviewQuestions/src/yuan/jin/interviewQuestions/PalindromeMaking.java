package yuan.jin.interviewQuestions;


/**
 * Given a string, figure out how many characters minimum are needed to make the
 * word a palindrome.
 * 
 * http://stackoverflow.com/questions/903176/how-do-i-figure-out-the-least-
 * number-of-characters-to-create-a-palindrome
 * 
 * You need to find the longest palindrome at the end of the string. An
 * algorithm to see if a string is a palindrome can be created by simply running
 * one pointer from the start of the string and one from the end, checking that
 * the characters they refer to are identical, until they meet in the middle.
 * Something like:
 * 
 * function isPalindrome(s): i1 = 0 i2 = s.length() - 1 while i2 > i1: if
 * s.char_at(i1) not equal to s.char_at(i2): return false increment i1 decrement
 * i2 return true
 * 
 * Try that with the full string. If that doesn't work, save the first character
 * on a stack then see if the remaining characters form a palindrome. If that
 * doesn't work, save the second character as well and check again from the
 * third character onwards.
 * 
 * Eventually you'll end up with a series of saved characters and the remaining
 * string which is a palindrome.
 * 
 * @author Yuan
 * 
 */
public class PalindromeMaking {

	static String makePalindrome(String s) {
		String sc = s;
		int i1 = 0;
		int i2 = s.length() - 1;
		StringBuilder sb = new StringBuilder();
		while (i2 > i1) {
			if (!isPalindrome(sc)) {
				sb.append(sc.charAt(i1));
				sc = sc.substring(i1 + 1);
				continue;
			}
			i1++;
			i2--;
		}
		return s + sb.reverse().toString();
	}
	
	// Figure out min characters to make the word a palindrome.
	static int count(String s) {
	    int i = 0;
	    int j = s.length() - 1;
	    int counter = 0;
	    while (i < j) {
	        if (!isPalindrome(s)) {
	            s = s.substring(i+1);
	            counter++;
	        }
	        i++;
	        j--;
	    }
	    return counter;
	}

	static boolean isPalindrome(String s) {
		int i1 = 0;
		int i2 = s.length() - 1;
		while (i2 > i1) {
			if (s.charAt(i1) != s.charAt(i2))
				return false;
			i1++;
			i2--;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(makePalindrome("FAE"));
		System.out.println(count("FAEE"));
	}

}
