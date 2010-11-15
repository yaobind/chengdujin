package yuan.jin.interviewQuestions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of numbers. Calculate a permutation when the concatenated
 * number is the smallest. For instance, [55, 31, 312, 33] is 312313355.
 * 
 * <http://effprog.blogspot.com/2010/08/given-array-of-numbers-calculate.html>
 * 
 * @author Yuan
 * 
 */
public class ConcatenatedSmallest {

	static String complete(String s1, String s2) {
		if ((s2.length() - s1.length()) <= s1.length())
			s1 = s1 + s1.substring(0, s2.length() - s1.length());
		else {
			int t = (s2.length() - s1.length()) % s1.length();
			int q = (s2.length() - s1.length()) / s1.length();
			String copy = s1;
			for (int i = 0; i < q; i++)
				s1 += copy;
			s1 += s1.substring(0, t);
		}
		return s1;
	}

	/**
	 * The code from the web page in the annotation would return wrong if
	 * comparing 31 and 3131312.
	 */
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("55");
		list.add("31");
		list.add("312");
		list.add("33");
		list.add("31314");
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				if (s1.length() < s2.length())
					s1 = complete(s1, s2);
				else
					s2 = complete(s2, s1);
				int i1 = Integer.parseInt(s1);
				int i2 = Integer.parseInt(s2);
				if (i1 == i2)
					return 0;
				else if (i1 < i2)
					return -1;
				else
					return 1;
			}
		});
		System.out.println(list.toString());
	}

}
