package yuan.jin.interviewQuestions;

class IONode {
	int data;
	IONode left;
	IONode right;
}

/**
 * Inorder Tree Traversal without recursion and without stack!
 * 
 * Morris Traversal
 * 
 * http://geeksforgeeks.org/?p=6358
 * 
 * @author Yuan
 * 
 */
public class InorderNoRecursionNoStack {

	static void MorrisTraversal(IONode root) {
		IONode current, pre;
		if (root == null)
			return;
		current = root;
		while (current != null) {
			if (current.left == null) {
				System.out.println(current.data);
				current = current.right;
			} else {
				/* Find the inorder predecessor of current */
				pre = current.left;
				while (pre.right != null && pre.right != current)
					pre = pre.right;

				/* Make current as right child of its inorder predecessor */
				if (pre.right == null) {
					pre.right = current;
					current = current.left;
				}

				/*
				 * Revert the changes made in if part to restore the original
				 * tree i.e., fix the right child of predecssor
				 */
				else {
					pre.right = null;
					System.out.println(current.data);
					current = current.right;
				} /* End of if condition pre.right == null */
			} /* End of if condition current.left == null */
		} /* End of while */
	}

	public static IONode insert(int data) {
		IONode t = new IONode();
		t.data = data;
		t.left = null;
		t.right = null;
		return t;
	}

	public static void printTree(IONode root) {
		if (root == null)
			return;
		printTree(root.left);
		System.out.print(Integer.toString(root.data) + " ");
		printTree(root.right);
	}

	public static void main(String[] args) {
		IONode root = insert(4);
		root.left = insert(2);
		root.right = insert(5);
		root.left.left = insert(1);
		root.left.right = insert(3);
//		printTree(root);
		MorrisTraversal(root);
	}

}
