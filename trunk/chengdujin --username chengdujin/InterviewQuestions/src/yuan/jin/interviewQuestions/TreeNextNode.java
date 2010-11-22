package yuan.jin.interviewQuestions;

import java.util.Stack;

public class TreeNextNode {
	// find the next node in pre-order traversal
	static TNode preorderNext(TNode root, int node_val) {
		Stack<TNode> stk = new Stack<TNode>();
		TNode cur = root;

		// Find match node
		while (cur != null) {
			stk.push(cur);
			if (cur.data == node_val)
				break;
			else if (cur.data < node_val)
				cur = cur.right;
			else if (cur.data > node_val)
				cur = cur.left;
		}

		if (cur == null) {
			System.out.print("Cannot find node with data: " + node_val);
			return null;
		} else {
			if (cur.left != null)
				return cur.left;
			else if (cur.right != null)
				return cur.right;
			else {
				stk.pop();
				TNode parent;
				if (!stk.empty()) {
					parent = stk.peek();
					stk.pop();
				} else
					return null;

				while (!(cur == parent.left && parent.right != null)) {
					cur = parent;
					if (!stk.empty()) {
						parent = stk.peek();
						stk.pop();
					} else
						return null;
				}
				return parent.right;
			}
		}
	}

	// find next node in in-order traversal
	static TNode inorderNext(TNode root) {
		if (root == null)
			return null;
		TNode p;
		if (root.parent == null || root.right != null)
			p = leftMostChild(root);
		else {
			while (root.parent.left != root)
				root = root.parent;
			p = root.parent;
		}
		return p;
	}

	public static TNode leftMostChild(TNode root) {
		if (root == null)
			return null;
		while (root != null)
			root = root.left;
		return root;
	}

	public static void main(String[] args) {
		TNode node = new TNode(6);
		node.parent = null;
		node.left = new TNode(2);
		node.left.parent = node;
		node.right = new TNode(7);
		node.right.parent = node;
		node.left.left = new TNode(1);
		node.left.left.parent = node.left;
		node.left.right = new TNode(4);
		node.left.right.parent = node.left;
		node.right.right = new TNode(9);
		node.right.right.parent = node.right;
		node.left.right.left = new TNode(3);
		node.left.right.left.parent = node.left.right;
		node.left.right.right = new TNode(5);
		node.left.right.right.parent = node.left.right;
		node.right.right.left = new TNode(8);
		node.right.right.left.parent = node.right.right;
		
		System.out.println(inorderNext(node.left.left).data);
		System.out.println(preorderNext(node, node.left.left.data).data);
	}
}

class TNode {
	int data;
	TNode left;
	TNode right;
	TNode parent;

	public TNode(int value) {
		this.data = value;
	}
}
