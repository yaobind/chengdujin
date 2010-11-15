package yuan.jin.interviewQuestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class AnagramsFoundAll {

	static HashMap<Integer, HashSet<String>> anagrams = new HashMap<Integer, HashSet<String>>();

	static HashSet<String> find(String word) {
		char[] key = word.toCharArray();
		Arrays.sort(key);
		int hashcode = (new String(key)).hashCode();
		if (anagrams.containsKey(hashcode))
			return anagrams.get(hashcode);
		else
			return null;
	}

	static void preprocess(String[] strs) {
		for (int i = 0; i < strs.length; i++) {
			char[] key = strs[i].toCharArray();
			Arrays.sort(key);
			int hashcode = (new String(key)).hashCode();
			HashSet<String> words = null;
			if (anagrams.containsKey(hashcode)) {
				words = anagrams.get(hashcode);
				words.add(strs[i]);
			}
			else {
				words = new HashSet<String>();
				words.add(strs[i]);
				anagrams.put(hashcode, words);
			}
		}
	}

	public static void main(String[] args) {
		String[] strs = { "cat", "god", "earshot", "restful", "world", "act",
				"fluster", "inferno", "fire", "dog", "nice love",
				"i wont hear this", "violence", "within earshot" };
		preprocess(strs);
		HashSet<String> result = find("tac");
		if (result != null)
			System.out.println(result.toString());
		else
			System.out.println("Sorry!");
	}

}
