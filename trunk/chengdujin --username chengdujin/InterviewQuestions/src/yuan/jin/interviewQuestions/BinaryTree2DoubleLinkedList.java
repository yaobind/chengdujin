package yuan.jin.interviewQuestions;

import java.util.Stack;

class BNode {
	int data;
	// left, small
	BNode prev;
	// right, large
	BNode next;

	public BNode(int data) {
		this.data = data;
		prev = null;
		next = null;
	}
}

/**
 * Write a function which will accept a Binary Search Tree and convert it to a
 * sorted doubly linked List.
 * 
 * http://cslibrary.stanford.edu/109/TreeListRecursion.html
 * 
 * @author Yuan
 * 
 */
public class BinaryTree2DoubleLinkedList {

	public static void join(BNode a, BNode b) {
		a.next = b;
		b.prev = a;
	}

	public static BNode append(BNode a, BNode b) {
		if (a == null)
			return b;
		if (b == null)
			return a;
		BNode aLast = a.prev;
		BNode bLast = b.prev;
		join(aLast, b);
		join(bLast, a);
		return a;
	}

	/**
	 * recursive
	 */
	public static BNode treeToList(BNode root) {
		if (root == null)
			return (null);
		BNode aList = treeToList(root.prev);
		BNode bList = treeToList(root.next);
		root.prev = root;
		root.next = root;
		aList = append(aList, root);
		aList = append(aList, bList);
		return aList;
	}

	// http://www.mitbbs.com/article_t/JobHunting/31602199.html
	// Take note at DSW algorithm
	static void bst2LL(BNode root, BNode list) {
		if (root == null)
			return;
		bst2LL(root.next, list);
		root.next = list;
		if (list != null)
			list.prev = root;
		list = root;
		bst2LL(root.prev, list);
	}
	
	/**
	 * iterative
	 */
	static BNode inorder(BNode root, BNode head) {
		if (root == null)
			return null;
		Stack<BNode> s = new Stack<BNode>();
		while (true) {
			if (root != null) {
				s.push(root);
				root = root.prev;
			} else {
				if (s.isEmpty())
					break;
				else {
					root = (BNode) s.pop();
					System.out.print(root.data + " ");
					if (head == null) {
						head = new BNode(root.data);
						head.next = null;
						head.prev = null;
					} else {
						BNode node = new BNode(root.data);
						node.prev = head;
						node.next = null;
						head.next = node;
						head = node;
					}
					root = root.next;
				}
			}
		}
		return head;
	}

	public static void treeInsert(BNode root, int newData) {
		if (newData <= root.data) {
			if (root.prev != null)
				treeInsert(root.prev, newData);
			else
				root.prev = new BNode(newData);
		} else {
			if (root.next != null)
				treeInsert(root.next, newData);
			else
				root.next = new BNode(newData);
		}
	}

	public static void printTree(BNode root) {
		if (root == null)
			return;
		printTree(root.prev);
		System.out.print(Integer.toString(root.data) + " ");
		printTree(root.next);
	}

	public static void printList(BNode head) {
		BNode current = head;

		while (current != null) {
			System.out.print(Integer.toString(current.data) + " ");
			current = current.next;
			if (current == head)
				break;
		}

		System.out.println();
	}

	public static void main(String[] args) {
		BNode root = new BNode(4);
		treeInsert(root, 2);
		treeInsert(root, 1);
		treeInsert(root, 3);
		treeInsert(root, 5);

		System.out.println("tree:");
		printTree(root); // 1 2 3 4 5
		System.out.println();

		System.out.println("list:");
		// Node head = treeToList(root);
		BNode head = null;
		// for better efficiency
		// http://www.mitbbssg.com/bbsann2/life.faq/JobHunting/17/D12842543542i0/M.1284994103_2.g0/ms%C3%E6%CA%D4%CC%E2
		inorder(root, head);
		printList(head); // 1 2 3 4 5 yay!
	}
}
