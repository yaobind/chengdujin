package yuan.jin.interviewQuestions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Input several pairs of numbers. The second number in the pair is larger than
 * the first one. You need to combine the intervals has overlap.
 * 
 * http://www.mitbbssg.com/bbsann2/life.faq/JobHunting/17/D12842543542i0/M
 * .1284263000
 * _2.v0/%CE%D2%BB%A8%C1%CB%D2%BB%B8%F6%D0%A1%CA%B1%B2%C5%B5%F7%CD%A8%
 * B9%FD%D5%E2%B8%F6%B3%CC%D0%F2
 * 
 * @author Yuan
 * 
 */
public class OverlapsCombined {

	static List<Pair> list = new ArrayList<Pair>();

	static List<Pair> combine() {
		int i = 0;
		int j = 1;
		while (j < list.size()) {
			Pair a = (Pair) list.get(i);
			Pair b = (Pair) list.get(j);
			if (b.x >= a.x && b.x <= a.y && a.y >= b.x && a.y <= b.y) {
				a.x = Math.min(a.x, b.x);
				a.y = Math.max(a.y, b.y);
				list.remove(i);
				list.add(i, a);
				list.remove(j);
			} else {
				i++;
				j++;
			}
		}
		return list;
	}

	public static void main(String[] args) {
		list.add(new Pair(1, 3));
		list.add(new Pair(2, 4));
		list.add(new Pair(5, 6));
		list.add(new Pair(-3, 0));
		list.add(new Pair(8, 9));
		list.add(new Pair(4, 6));
		list.add(new Pair(1, 3));
		list.add(new Pair(5, 7));
		Collections.sort(list, new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				return o1.x - o2.x;
			}
		});
		System.out.println(list.toString());
		System.out.println(combine().toString());
	}
}

class Pair {
	int x;
	int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "[" + this.x + "," + this.y + "]";
	}

}
