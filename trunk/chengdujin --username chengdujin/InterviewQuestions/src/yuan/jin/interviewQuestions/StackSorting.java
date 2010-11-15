package yuan.jin.interviewQuestions;

import java.util.Random;
import java.util.Stack;

public class StackSorting {
	static Stack s1 = new Stack();
	static Stack s2 = new Stack();

	static void sort() {
		while (!s1.isEmpty()) {
			int vs1 = (Integer) s1.pop();
			while (!s2.isEmpty() && (Integer) s2.peek() > vs1)
				s1.push(s2.pop());
			s2.push(vs1);
		}
	}

	public static void main(String[] args) {
		Random gen = new Random();
		while (s1.size() <= 10)
			s1.add(gen.nextInt(20) + 1);
		System.out.println(s1.toString());
		sort();
		System.out.println(s2.toString());
	}

}
