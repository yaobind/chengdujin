package yuan.jin.interviewQuestions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * N个数的数组,找出最大的和第二大的数,只用N+logN-2的比较次数 ,不需要额外空间。这个是典型的问题本身就是答案提示的题目--基于比较又有LogN,
 * 很显然思路涉及二分法,继续下去,剩下的问题就仅仅是找一个符合要求的 Implementation了。
 * 
 * http://www.cis.ysu.edu/~kramer/CSCI5870/Adversary/SecondLargest.pdf
 * 
 * @author Yuan
 * 
 */
public class SecondLargest {

	static Queue q;

	public static void main(String[] args) {
		q = new LinkedList();

		int[] input = { 12, 13, 8, 5, 6, 3, 10, 15, 1, 2, 9, 4, 11, 0, 7, 14 };

		for (int i = 0; i < input.length; i++) {
			Competitor c = new Competitor(input[i]);
			q.add(c);
		}

		while (q.size() > 1) {
			Competitor a = (Competitor) q.poll();
			Competitor b = (Competitor) q.poll();

			if (a.value < b.value) {
				b.list.add(a.value);
				q.add(b);
			} else {
				a.list.add(b.value);
				q.add(a);
			}
		}

		Competitor d = ((Competitor) q.poll());
		System.out.println(d.value);
		System.out.println(d.list);
	}

}

class Competitor {
	int value;
	List list;

	public Competitor(int value) {
		this.value = value;
		this.list = new ArrayList();
	}
}
