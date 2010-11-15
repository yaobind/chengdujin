package yuan.jin.interviewQuestions;

/**
 * The implementations of the Levenshtein algorithm on this page are
 * illustrative only. Applications will, in most cases, use implementations
 * which use heap allocations sparingly, in particular when large lists of words
 * are compared to each other. The following remarks indicate some of the
 * variations on this and related topics: Most implementations use one- or
 * two-dimensional arrays to store the distances of prefixes of the words
 * compared. In most applications the size of these structures is previously
 * known. This is the case, when, for instance the distance is relevant only if
 * it is below a certain maximally allowed distance (this happens when words are
 * selected from a dictionary to approximately match a given word). In this case
 * the arrays can be preallocated and reused over the various runs of the
 * algorithm over successive words. Using a maximum allowed distance puts an
 * upper bound on the search time. The search can be stopped as soon as the
 * minimum Levenshtein distance between prefixes of the strings exceeds the
 * maximum allowed distance. Deletion, insertion, and replacement of characters
 * can be assigned different weights. The usual choice is to set all three
 * weights to 1. Different values for these weights allows for more flexible
 * search strategies in lists of words.
 * 
 * http://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/
 * Levenshtein_distance
 * 
 * For more explanations: http://www.allisons.org/ll/AlgDS/Dynamic/Edit/
 * 
 * @author Yuan
 * 
 */
public class LevenshteinDistance {
	private static int minimum(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}

	public static int computeLevenshteinDistance(CharSequence str1,
			CharSequence str2) {
		int[][] distance = new int[str1.length() + 1][str2.length() + 1];

		for (int i = 0; i <= str1.length(); i++)
			distance[i][0] = i;
		for (int j = 0; j <= str2.length(); j++)
			distance[0][j] = j;

		for (int i = 1; i <= str1.length(); i++)
			for (int j = 1; j <= str2.length(); j++)
				distance[i][j] = minimum(
						distance[i - 1][j] + 1,
						distance[i][j - 1] + 1,
						distance[i - 1][j - 1]
								+ ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0
										: 1));

		return distance[str1.length()][str2.length()];
	}

	public static void main(String[] args) {
		System.out.println(computeLevenshteinDistance("like", "dimes"));
	}
}
