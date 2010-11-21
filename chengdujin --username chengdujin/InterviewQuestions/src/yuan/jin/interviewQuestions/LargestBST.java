package yuan.jin.interviewQuestions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find the largest subtree which is a Binary Search Tree
 * (BST), where largest means subtree with largest number of nodes in it.
 * 
 * http://www.rawkam.com/?p=822
 * 
 * http://www.ihas1337code.com/2010/11/largest-binary-search-tree-bst-in.html
 * 
 * @author Yuan
 * 
 */
public class LargestBST {

	static LNode findLargestBST(LNode p) {
		if (p == null) {
			p = new LNode(-1);
			p.totalNodes = 0;
			return p;
		}
		int currMin, currMax;
		boolean isBST = true;

		LNode leftNodes = findLargestBST(p.left);
		currMin = (leftNodes.totalNodes == 0) ? p.data : leftNodes.min;
		System.out.println(p.data + "-" + leftNodes + ":" + currMin);
		if (leftNodes.totalNodes != 0)
			if (leftNodes.totalNodes == -1
					|| (leftNodes.totalNodes != 0 && p.data <= leftNodes.max))
				isBST = false;

		LNode rightNodes = findLargestBST(p.right);
		currMax = (rightNodes.totalNodes == 0) ? p.data : rightNodes.max;
		System.out.println("#" + p.data + "-" + rightNodes + ":" + currMax);
		if (rightNodes.totalNodes != 0) {
			if (rightNodes.totalNodes == -1
					|| (rightNodes.totalNodes != 0 && p.data >= rightNodes.min))
				isBST = false;
		}

		if (isBST) {
			p.min = currMin;
			p.max = currMax;
			p.totalNodes = 1;
			if (leftNodes != null && rightNodes != null)
				p.totalNodes = leftNodes.totalNodes + rightNodes.totalNodes + 1;
			if (p.totalNodes > p.maxNodes) {
				System.out.println("oops " + p.totalNodes + ":" + p.maxNodes);
				p.maxNodes = p.totalNodes;
				p.largestBST = p;
			}
			return p;
		} else {
			p.totalNodes = -1;
			return p; // This subtree is not a BST
		}
	}

	static LNode find(LNode root) {
		LNode largestBST = null;
		int min = 0, max = 0;
		int maxNodes = Integer.MIN_VALUE;
		largestBST = findLargestBST(root);
		if (largestBST.largestBST == null)
			if (largestBST.left.largestBST != null)
				return largestBST.left.largestBST;
			else
				return largestBST.right.largestBST;
		return largestBST;
	}

	static void printBSF(LNode head) {
		Queue<LNode> q1 = new LinkedList<LNode>();
		Queue<LNode> q2 = new LinkedList<LNode>();
		q1.add(head);
		while (!q1.isEmpty()) {
			LNode node = (LNode) q1.poll();
			if (node != null) {
				System.out.print(node.toString() + " ");
				q2.add(node.left);
				q2.add(node.right);
			}
			if (q1.isEmpty()) {
				q1.addAll(q2);
				q2.clear();
				System.out.println();
			}
		}
	}

	// method 2: much simpler
	// http://www.rawkam.com/?p=822
	static int maxSortedSequence(int[] arr) {
		int[] aux = new int[arr.length];
		int i;
		for (i = arr.length - 2; i >= 0; i--)
			if (arr[i] < arr[i + 1])
				aux[i] = aux[i + 1] + 1;
		int max = aux[0];
		int startIndex = 0;
		for (i = 1; i < arr.length; i++) {
			if (aux[i] > max) {
				max = aux[i];
				startIndex = i;
			}
		}
		for (i = startIndex; i < startIndex + max + 1; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
		return max + 1;
	}

	public static void main(String[] args) {
		LNode root = new LNode(10);
		root.left = new LNode(5);
		root.right = new LNode(15);
		root.left.left = new LNode(1);
		root.left.right = new LNode(8);
		root.right.right = new LNode(7);
//		printBSF(root);
//		System.out.println(find(root));
		int[] seq = { 1, 5, 8, 10, 15, 7};
		System.out.println(maxSortedSequence(seq));
	}

}

class LNode {
	int data;
	LNode left;
	LNode right;
	LNode largestBST;
	int totalNodes;
	int maxNodes;
	int min;
	int max;

	public LNode(int data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "" + this.data;
	}
}