package yuan.jin.interviewQuestions;

import java.util.Stack;

/**
 * 用递归颠倒一个栈。例如输入栈{1, 2, 3, 4, 5}，1在栈顶。颠倒之后的栈为{5, 4, 3, 2, 1}，5处在栈顶。
 * 
 * http://zhedahht.blog.163.com/blog/static/25411174200943182411790/
 * 
 * @author Yuan
 * 
 */
public class ReverseStack {

	static Stack<Integer> s = new Stack<Integer>();

	static void add2Bottom(Stack<Integer> stack, int t) {
		if (stack.empty())
			stack.push(t);
		else {
			int top = (Integer) stack.pop();
			add2Bottom(stack, t);
			stack.push(top);
		}
	}

	static void reverse(Stack<Integer> stack) {
		if (!stack.empty()) {
			int top = (Integer) stack.pop();
			reverse(stack);
			add2Bottom(stack, top);
		}
	}

	public static void main(String[] args) {
		s.add(5);
		s.add(4);
		s.add(3);
		s.add(2);
		s.add(1);
		System.out.println(s.toString());
		reverse(s);
		System.out.println(s.toString());
	}

}
