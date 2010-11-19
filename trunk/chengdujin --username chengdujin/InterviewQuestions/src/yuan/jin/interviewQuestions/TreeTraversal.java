package yuan.jin.interviewQuestions;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Pre-order, In-order and Post-order tree traversal's iterative solution
 * 
 * Pre-order: 6, 2, 1, 4, 3, 5, 7, 9, 8
 * 
 * In-order: 1, 2, 3, 4, 5, 6, 7, 8, 9
 * 
 * Post-order: 1, 3, 5, 4, 2, 8, 9, 7, 6
 * 
 * http://en.wikipedia.org/wiki/Tree_traversal
 * 
 * @author Yuan
 * 
 */
public class TreeTraversal {

	public static void preOrder(PNode root) {
		if (root == null)
			return;
		Stack<PNode> s = new Stack<PNode>();
		s.push(root);
		while (!s.isEmpty()) {
			PNode n = (PNode) s.pop();
			System.out.print(n + " ");
			if (n.right != null)
				s.push(n.right);
			if (n.left != null)
				s.push(n.left);
		}
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
					System.out.print(root.data + " ");
					root = root.right;
				}
			}
		}
	}

	public static void postOrder(PNode root) {
		if (root == null)
			return;
		Stack<PNode> s1 = new Stack<PNode>();
		Stack<PNode> s2 = new Stack<PNode>();
		s1.push(root);
		while (!s1.empty()) {
			PNode curr = (PNode) s1.pop();
			s2.push(curr);
			if (curr.left != null)
				s1.push(curr.left);
			if (curr.right != null)
				s1.push(curr.right);
		}
		while (!s2.empty())
			System.out.print(s2.pop().data + " ");
	}

	/**
	 * In-order Tree Traversal without recursion and without stack!
	 * 
	 * Morris Traversal
	 * 
	 * http://geeksforgeeks.org/?p=6358
	 * 
	 * @param root
	 */
	public static void morrisTraversal(PNode root) {
		if (root == null)
			return;
		PNode prev;
		while (root != null) {
			if (root.left != null) {
				prev = root.left;
				while (prev.right != null && prev.right != root)
					prev = prev.right;
				if (prev.right == null) {
					prev.right = root;
					root = root.left;
				} else {
					prev.right = null;
					System.out.print(root.data + " ");
					root = root.right;
				}
			} else {
				System.out.print(root.data + " ");
				root = root.right;
			}
		}
	}
	
	// Print the binary tree by level (BFS)
	static void levelOrder(PNode root) {
	    if (root == null)
	        return;
	    Queue<PNode> q1 = new LinkedList<PNode>();
	    Queue<PNode> q2 = new LinkedList<PNode>();
	    q1.add(root);
	    while (!q1.isEmpty()) {
	        PNode n = (PNode) q1.poll();
	        if (n != null) {
	        	System.out.print(n + " ");
	        	q2.add(n.left);
	        	q2.add(n.right);
	        }
	        if (q1.isEmpty()) {
	            q1.addAll(q2);
	            q2.clear();
	            System.out.println();
	        }
	    }
	}
	
	// // Print the binary tree by level (Non BFS)
	static void levelOrderNonBFS(PNode root) {
	    if (root == null)
	        return;
	    int hite = height(root);
	    for (int i = 1; i <= hite; i++) {
	        printLevel(root, i);
	        System.out.println();
	    }
	}

	static void printLevel(PNode root, int level) {
	    if (root == null)
	        return;
	    if (level == 1)
	        System.out.print(root.data + " ");
	    printLevel(root.left, level - 1);
	    printLevel(root.right, level - 1);
	}

	// Print the binary tree by level (BFS) in reverse order.
	// 8, 5, 3, 9, 4, 1, 7, 2, 6
	static void levelOrderReverse(PNode root) {
		if (root == null)
			return;
		Queue<PNode> q1 = new LinkedList<PNode>();
		Queue<PNode> q2 = new LinkedList<PNode>();
		Stack<PNode> s = new Stack<PNode>();
		q1.add(root);
		s.push(root);
		while (!q1.isEmpty()) {
			PNode n = (PNode) q1.poll();
			if (n != null) {
				q2.add(n.left);
				if (n.left != null)
				s.push(n.left);
				q2.add(n.right);
				if (n.right != null)
				s.push(n.right);
			}
			if (q1.isEmpty()) {
				q1.addAll(q2);
				q2.clear();
			}
		}
		System.out.println(s.toString());
	}

	// Print the binary tree by level (BFS) in reverse order - Non BFS version
	static void levelOrderReverseNonBFS(PNode root) {
		if (root == null)
			return;
		int hite = height(root);
		for (int i = hite; i >= 1; i--) {
			printLevel(root, i);
			System.out.println();
		}
	}

	static void printLevelReverse(PNode root, int level) {
		if (root == null)
			return;
		if (level == 1)
			System.out.print(root.data + " ");
		printLevelReverse(root.left, level - 1);
		printLevelReverse(root.right, level - 1);
	}

	static int height(PNode root) {
		if (root == null)
			return 0;
		int hleft = height(root.left);
		int hright = height(root.right);
		return 1 + Math.max(hleft, hright);
	}
	
	// Given a binary tree, print out the tree in zig zag level order
	static void levelOrderZigZag(PNode root) {
	    if (root == null)
	        return;
	    Stack<PNode> s = new Stack<PNode>();
	    Stack<PNode> t = new Stack<PNode>();
	    s.push(root);
	    boolean left2rite = true;
	    while (!s.isEmpty()) {
	        PNode n = (PNode) s.pop();
	        if (n != null) {
	        	System.out.print(n + " ");
	        	if (left2rite) {
	            	t.push(n.left);
	            	t.push(n.right);
	        	} else {
	            	t.push(n.right);
	            	t.push(n.left);
	        	}
	        }
	        if (s.isEmpty()) {
	            left2rite = !left2rite;
	            s.addAll(t);
	            t.clear();
	            System.out.println();
	        }
	    }
	}

	public static void main(String[] args) {
		// example from http://en.wikipedia.org/wiki/Tree_traversal
		PNode node = new PNode(6);
		node.left = new PNode(2);
		node.right = new PNode(7);
		node.left.left = new PNode(1);
		node.left.right = new PNode(4);
		node.right.right = new PNode(9);
		node.left.right.left = new PNode(3);
		node.left.right.right = new PNode(5);
		node.right.right.left = new PNode(8);
		preOrder(node);
		System.out.println();
		inOrder(node);
		System.out.println();
		morrisTraversal(node);
		System.out.println();
		postOrder(node);
		System.out.println();
		levelOrder(node);
		levelOrderZigZag(node);
	}

}
