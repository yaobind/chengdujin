package yuan.jin.interviewQuestions;

import java.util.Stack;

/**
 * 
 * Given an array of size n, design an algorithm to find for each entry of the
 * array the first number to the right which is less than it. That is, for each
 * entry A. This has to be done in O(n) time.
 * 
 * http://www.rawkam.com/?p=797
 * 
 * @author Yuan
 * 
 */
public class NextLess {
	
	static int[] input = { 3, 6, 0, 45, 30, 21, 12, 13, 19 };
	static int[] record = new int[input.length];

	public static PNode insert(PNode node, int data) {
		if (node == null) {
			node = new PNode(data);
		} else {
			if (data < node.data)
				node.left = insert(node.left, data);
			else
				node.right = insert(node.right, data);
		}
		return node;
	}
	
	public static void inOrder(PNode root) {
		if (root == null)
			return;
		Stack<PNode> s = new Stack<PNode>();
		while (true) {
			if (root != null) {
				s.push(root);
				root = root.left;
			} else {
				if (s.isEmpty())
					break;
				else {
					root = (PNode) s.pop();
					System.out.print(root.data);
					if (root.left != null)
						System.out.println(": " + root.left.data);
					else
						System.out.println();
					root = root.right;
				}
			}
		}
	}

	public static void main(String[] args) {
		PNode root = null;
		for (int i = 0; i < input.length; i++)
			root = insert(root, input[i]);
		inOrder(root);
	}

}
