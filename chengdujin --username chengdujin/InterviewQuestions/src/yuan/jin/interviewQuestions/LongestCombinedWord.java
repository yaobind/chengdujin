package yuan.jin.interviewQuestions;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Write a program to find the longest word made of other words
 * 
 * CareerCup 4.20.7
 * 
 * @author Yuan
 * 
 */
public class LongestCombinedWord {
	
	static String findLongest(String[] arr) {
		String longest = "";
		for (int i = 0; i < arr.length; i++) {
			for (int j = 1; j < arr[i].length(); j++) {
				String str1 = arr[i].substring(0, j);
				boolean found = false;
				for (int k = i; k < arr.length; k++) {
					if (str1.equals(arr[k]))
						found = true;
				}
				if (found) {
					String str2 = arr[i].substring(j);
					for (int k = i; k < arr.length; k++)
						if (str2.equals(arr[k]) && arr[i].length() > longest.length())
							longest = arr[i];
				} else
					continue;
			}
		}
		
		return longest;
	}

	public static void main(String[] args) {
		String[] input = { "test", "tester", "testertest", "testing",
				"testingtester" };
		Arrays.sort(input, new Comparator<String>() {
			@Override
			public int compare(String arg0, String arg1) {
				return arg1.length() - arg0.length();
			}
		});
		System.out.println(findLongest(input));
	}

}
