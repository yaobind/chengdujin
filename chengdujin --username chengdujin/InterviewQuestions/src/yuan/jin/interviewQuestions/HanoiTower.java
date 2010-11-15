package yuan.jin.interviewQuestions;

import java.util.Stack;

public class HanoiTower {

	private Stack<Integer> disks;
	private int index;

	public HanoiTower(int i) {
		disks = new Stack<Integer>();
		index = i;
	}

	public int index() {
		return index;
	}

	public void add(int d) {
		if (!disks.isEmpty() && disks.peek() <= d) {
			System.out.println("Error");
		} else
			disks.push(d);
	}

	public void moveTopTo(HanoiTower t) {
		int top = disks.pop();
		t.add(top);
		System.out.println("Move disk " + (top + 1) + " from " + (index() + 1)
				+ " to " + (t.index + 1));
	}

	public void print() {
		System.out.println("Contents of Tower " + index());
		for (int i = disks.size() - 1; i >= 0; i--)
			System.out.println(" " + disks.get(i));
	}

	public void moveDisks(int n, HanoiTower destination, HanoiTower buffer) {
		if (n > 0) {
			moveDisks(n - 1, buffer, destination);
			moveTopTo(destination);
			buffer.moveDisks(n - 1, destination, this);
		}
	}

	public static void main(String[] args) {
		int n = 5;
		HanoiTower[] towers = new HanoiTower[n];
		for (int i = 0; i < 3; i++)
			towers[i] = new HanoiTower(i);
		for (int i = n - 1; i >= 0; i--)
			towers[0].add(i);
		towers[0].moveDisks(n, towers[2], towers[1]);
	}

}
