package yuan.jin.interviewQuestions;

import java.util.ArrayList;

/**
 * Pascal's triangle is an interesting math concept. Its first few rows look
 * like this:
 * 
 * 1
 * 
 * 1 1
 * 
 * 1 2 1
 * 
 * 1 3 3 1
 * 
 * where each element of each row is either 1 or the sum of the two elements
 * right above it. For example, the next row would be 1 (since the first element
 * of each row doesn't have two elements above it), 4 (1 + 3), 6 (3 + 3), 4 (3 +
 * 1), and 1 (since the last element of each row doesn't have two elements above
 * it). Each row n (starting with row 0 at the top) shows the coefficients of
 * the binomial expansion of (x + y)n.
 * 
 * Write a function that prints out the first n rows of the triangle (with f(1)
 * yielding the row consisting of only the element 1). This can be done either
 * by summing elements from the previous rows or using a binary coefficient or
 * combination function. Behavior for n <= 0 does not need to be uniform, but
 * should be noted.
 * 
 * @author Yuan
 * 
 */
public class PascalTriangle {

	public static void pascal(int rows) {
		if (rows < 0)
			return;
		ArrayList<Integer> last = new ArrayList<Integer>();
		last.add(1);
		System.out.println(last);
		for (int i = 1; i < rows; ++i) {
			ArrayList<Integer> current = new ArrayList<Integer>();
			current.add(last.get(0));
			for (int j = 1; j < i; ++j)
				current.add(last.get(j-1) + last.get(j));
			current.add(last.get(0));
			last = current;
			System.out.println(current);
		}
	}

	public static void main(String[] args) {
		pascal(5);
	}

}
