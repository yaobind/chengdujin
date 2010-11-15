package yuan.jin.interviewQuestions;

import java.util.Stack;

public class StackWithConstantMinRetrieval {
	static Stack normal = new Stack();
	static Stack mins = new Stack();
	
	void push(int value) {
		normal.push(value);
		
		if (!mins.isEmpty()) {
			if ((Integer)mins.peek() < value)
				mins.push(value);
		}
	}
	
	int pop() {
		int result = -1;
		if (!normal.isEmpty()) {
			result = (Integer) normal.pop();
			if (!mins.isEmpty() && ((Integer)mins.peek() == result))
				mins.pop();
		}
		return result;
	}
	
	int min() {
		int result = -1;
		if (!mins.isEmpty())
			result = (Integer) mins.peek();
		return result;
	}

	public static void main(String[] args) {
		
	}

}
