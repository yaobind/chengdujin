package yuan.jin.interviewQuestions;

import java.util.Random;
import java.util.Stack;

public class QueueByStacks {
	static Stack<Integer> s1 = new Stack<Integer>();
	static Stack<Integer> s2 = new Stack<Integer>();
	static QueueByStacks queue = new QueueByStacks();

	void push(int value) {
	    s1.push(value);
	}

	int pop() {
	    while (!s1.isEmpty())
	        s2.push(s1.pop());
	    int result = s2.pop();
	    while(!s2.isEmpty())
	        s1.push(s2.pop());
	    return result;
	}
	
	boolean isEmpty() {
		return s1.isEmpty();
	}
	
	public void init(QueueByStacks q) {
		Random gen = new Random();
		int counter = 0;
		while (counter < 50) {
			int value = gen.nextInt(100) + 1;
			System.out.print(value + " ");
			q.push(value);
			counter ++;
		}
		System.out.println();
		
		while(!q.isEmpty())
			System.out.print(q.pop() + " ");
	}
	
	public static void main(String[] args) {
		QueueByStacks q = new QueueByStacks();
		q.init(queue);
	}

}
