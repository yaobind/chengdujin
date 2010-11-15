package yuan.jin.interviewQuestions;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Given two arrays A [1..n] and B[1..m], find the smallest window in A that co
 * ntains all elements of B. That is, find a pair <l,k> such that A[l..k] conta
 * ins B[1..m]
 * 
 * For example, given A = 3,1,5,7,3,5,2 and B = 5,3 then the smallest window is
 * [3,5].
 * 
 * Other reference:
 * http://www.mitbbs.com/article_t1/JobHunting/31696043_0_1.html
 * 
 * @author Yuan
 * 
 */
public class ExcerptShortest {

	static int[] text = { 1, 4, 15, 2, 5, 3, 15, 4, 5, 2, 1, 14, 3 };
	static int[] input = { 1, 2, 3, 4, 1 };

	static boolean isAllIncluded(int begin, int end) {
		if (end < text.length) {
			HashSet<Integer> numbers = new HashSet<Integer>();
			for (int i = begin; i < end + 1; i++)
				numbers.add(text[i]);
			for (int i = 0; i < input.length; i++)
				if (!numbers.contains(input[i]))
					return false;
			return true;
		} else
			return false;
	}

	static void findExcerpt(int N) {
		int nTargetLen = N;
		int pBegin = 0;
		int pEnd = 0;
		int nLen = N;
		int nAbstractBegin = 0;
		int nAbstractEnd = 0;
		while (true) {
			while (pEnd < nLen && !isAllIncluded(pBegin, pEnd))
				pEnd++;
			while (isAllIncluded(pBegin, pEnd)) {
				if (pEnd - pBegin < nTargetLen) {
					nTargetLen = pEnd - pBegin;
					nAbstractBegin = pBegin;
					nAbstractEnd = pEnd;
				}
				pBegin++;
			}
			if (pEnd >= N)
				break;
		}
		for (int i = nAbstractBegin; i <= nAbstractEnd; i++)
			System.out.print(text[i] + " ");
	}

	// http://www.mitbbs.com/article_t1/JobHunting/31696043_0_1.html
	static void method2() {
		boolean[] chars = new boolean[256];
		for (int i = 0; i < input.length; i++)
			if (!chars[input[i]])
				chars[input[i]] = true;
		ArrayList records = new ArrayList();
		for (int i = 0; i < text.length; i++)
			if (chars[text[i]])
				records.add(i);
	}

	public static void main(String[] args) {
		findExcerpt(text.length);
	}

}
