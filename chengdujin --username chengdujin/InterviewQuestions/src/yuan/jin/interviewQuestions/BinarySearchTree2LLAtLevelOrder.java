package yuan.jin.interviewQuestions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary search tree, design an algorithm which creates a linked list
 * of all the nodes at each depth (e.g., if you have a tree with depth D, you’ll
 * have D linked lists).
 * 
 * @author Yuan
 * 
 */
public class BinarySearchTree2LLAtLevelOrder {

	static void convert(PNode root) {
		int level = 0;
		Queue<PNode> q = new LinkedList<PNode>();
		q.add(root);
		ArrayList<Queue<PNode>> list = new ArrayList<Queue<PNode>>();
		list.add(level, q);
		while (true) {
			q = new LinkedList<PNode>();
			for (int i = 0; i < ((LinkedList<PNode>) list.get(level)).size(); i++) {
				PNode m = (PNode) ((LinkedList<PNode>) list.get(level)).get(i);
				if (m != null) {
					if (m.left != null)
						q.add(m.left);
					if (m.right != null)
						q.add(m.right);
				}
			}
			if (q.size() > 0) {
				level++;
				list.add(level, q);
			} else
				break;
		}
		// print
		for (int i = 0; i < list.size(); i++) {
			LinkedList<PNode> ll = (LinkedList<PNode>) list.get(i);
			System.out.println(ll.toString());
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
		convert(node);
	}

}
