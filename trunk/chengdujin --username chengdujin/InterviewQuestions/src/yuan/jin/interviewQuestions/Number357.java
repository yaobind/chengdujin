package yuan.jin.interviewQuestions;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Cracking the Coding Interview 10.7
 * 
 * 我们把只包含因子 2、3和5的数称作丑数（Ugly
 * Number）。例如6、8都是丑数，但14不是，因为它包含因子7。习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第1500个丑数。
 * 
 * @author Yuan
 * 
 */
public class Number357 {

	static int getKthMagicNumber(int k) {
		if (k <= 0)
			return 0;
		int val = 1;
		Queue<Integer> Q3 = new LinkedList<Integer>();
		Queue<Integer> Q5 = new LinkedList<Integer>();
		Queue<Integer> Q7 = new LinkedList<Integer>();
		Q3.add(3);
		Q5.add(5);
		Q7.add(7);

		for (k--; k > 0; k--) {
			val = Math.min(Q3.peek(), Math.min(Q5.peek(), Q7.peek()));
			if (val == Q7.peek())
				Q7.remove();
			else {
				if (val == Q5.peek())
					Q5.remove();
				else {
					Q3.remove();
					Q3.add(val * 3);
				}
				Q5.add(val * 5);
			}
			Q7.add(val * 7);
		}
		return val;
	}

	public static void main(String[] args) {
		System.out.println(getKthMagicNumber(10));
	}

}
