package yuan.jin.interviewQuestions;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class StackByQueues {
	static Queue q1 = new LinkedList();
	static Queue q2 = new LinkedList();
	static StackByQueues stack = new StackByQueues();
	
	void push(int value) {
		q1.add(value);
	}
	
	int pop() {
		while (q1.size() > 1)
			q2.add(q1.poll());
		int result = (Integer) q1.poll();
		q1.addAll(q2);
		q2.clear();
		return result;
	}
	
	boolean isEmpty() {
		return q1.isEmpty();
	}
	
	public void init(StackByQueues s) {
		Random gen = new Random();
		int counter = 0;
		while (counter < 50) {
			int value = gen.nextInt(100) + 1;
			System.out.print(value + " ");
			s.push(value);
			counter ++;
		}
		System.out.println();
		
		while(!s.isEmpty())
			System.out.print(s.pop() + " ");
	}
	
	public static void main(String[] args) {
		StackByQueues s = new StackByQueues();
		s.init(stack);
	}

}
