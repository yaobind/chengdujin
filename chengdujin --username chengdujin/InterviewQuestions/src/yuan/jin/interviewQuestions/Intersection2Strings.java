package yuan.jin.interviewQuestions;

/**
 * Intersection of 2 strings (in the order of 1st string) O(n)
 * 
 * @author Yuan
 * 
 */
public class Intersection2Strings {

	static String intersect(String s1, String s2) {
		boolean[] chars = new boolean[256];
		for (int i = 0; i < s2.length(); i++)
			chars[s2.charAt(i)] = true;
		for (int i = 0; i < s1.length(); i++)
			if (!chars[s1.charAt(i)])
				s1 = s1.replace(s1.charAt(i), '\0');
		return s1;
	}

	public static void main(String[] args) {
		String s1 = "Hello everyone!";
		String s2 = "Nobody knows hello world";
		System.out.println(intersect(s1, s2));
	}

}
