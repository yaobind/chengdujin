package yuan.jin.interviewQuestions;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Dynamically input numbers, return the median Eg: 2,4,1,5,6 return 4. Enter
 * another two numbers 7,8 return 5. The interviewer is looking for a (1) look
 * up solution using BST.
 * 
 * http://discuss.joelonsoftware.com/default.asp?interview.11.736715
 * 
 * @author Yuan
 * 
 */
public class MedianBSTDynamicGeneration {
	class TreeNode {
		int data;
		int size;
		TreeNode left;
		TreeNode right;

		public TreeNode(int value) {
			this.data = value;
			this.size = 1;
			this.left = null;
			this.right = null;
		}
		
		@Override
		public String toString() {
			return "" + this.data;
		}
	}

	TreeNode root;

	void insert(int value) {
		if (root == null) {
			root = new TreeNode(value);
			return;
		}

		TreeNode node = root;
		while (node != null) {
			node.size++;
			if (node.data > value) {
				if (node.left != null)
					node = node.left;
				else {
					node.left = new TreeNode(value);
					break;
				}
			} else {
				if (node.right != null)
					node = node.right;
				else {
					node.right = new TreeNode(value);
					break;
				}
			}
		}
	}

	int median() {
		TreeNode node = root;
		int m = (node.size - 1) / 2;
		int size = 0;
		// originally the condition is "true"
		while (node != null) {
			size = node.left != null ? node.left.size : 0;
			if (size == m)
				return node.data;
			else if (size > m)
				node = node.left;
			else {
				node = node.right;
				m -= size + 1;
			}
		}
		// originally no return statement here
		return size;
	}

	void print(TreeNode root) {
		if (root == null)
			return;
		Queue<TreeNode> s1 = new LinkedList<TreeNode>();
		Queue<TreeNode> s2 = new LinkedList<TreeNode>();
		s1.add(root);
		while (!s1.isEmpty()) {
			TreeNode n = (TreeNode) s1.poll();
			if (n != null) {
				System.out.println(n.toString() + " :" + n.left + "-" + n.right);
				s2.add(n.left);
				s2.add(n.right);
			}
			if (s1.isEmpty()) {
				s1.addAll(s2);
				s2.clear();
				System.out.println();
			}
		}

	}

	public static void main(String[] args) {
//		int[] seq1 = { 12, 9, 1, 5, 7, 14, 8, 2, 3, 6, 4, 10, 11, 15 };
//		int[] seq2 = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
		int[] seq3 = { 2, 4, 1, 5, 6, 7, 8 };
		MedianBSTDynamicGeneration test = new MedianBSTDynamicGeneration();
		for (int i = 0; i < seq3.length; i++) {
			test.insert(seq3[i]);
			System.out.print(test.median() + " ");
		}
	}
}
