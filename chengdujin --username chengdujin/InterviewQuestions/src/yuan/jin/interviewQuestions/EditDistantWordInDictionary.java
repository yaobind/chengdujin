package yuan.jin.interviewQuestions;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class EditDistantWordInDictionary {

	static LinkedList<String> transform(String start, String end,
			Set<String> dict) {
		start = start.toUpperCase();
		end = end.toUpperCase();

		Queue<String> queue = new LinkedList<String>();
		Set<String> visited = new HashSet<String>();
		Map<String, String> backtrack = new TreeMap<String, String>();
		
		queue.add(start);
		visited.add(start);

		while (!queue.isEmpty()) {
			String w = queue.poll();
			
			for (String v : getOneEditWords(w)) {
				if (v.equals(end)) {
					LinkedList<String> result = new LinkedList<String>();
					result.add(v);
					while (w != null) {
						result.add(0, w);
						w = backtrack.get(w);
					}
					return result;
				}

//				if (dict.contains(v)) {
					if (!visited.contains(v)) {
						queue.add(v);
						visited.add(v);
						backtrack.put(v, w);
					}
//				}
			}
		}

		return null;
	}

	static Set<String> getOneEditWords(String word) {
		Set<String> words = new TreeSet<String>();

		for (int i = 0; i < word.length(); i++) {
			char[] wordArray = word.toCharArray();
			for (char c = 'A'; c < 'Z'; c++) {
				if (c != wordArray[i]) {
					wordArray[i] = c;
					words.add(new String(wordArray));
				}
			}
		}

		return words;
	}

	public static void main(String[] args) {
		Set<String> dictionary = new HashSet<String>();
		dictionary.add("DAMP");
		dictionary.add("LAMP");
		dictionary.add("LIMP");
		dictionary.add("LIME");
		dictionary.add("LIKE");
		
		System.out.println(transform("Face", "Book", dictionary));
	}

}
