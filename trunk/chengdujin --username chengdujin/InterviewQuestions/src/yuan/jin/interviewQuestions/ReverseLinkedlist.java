package yuan.jin.interviewQuestions;

import java.util.ArrayList;
import java.util.List;

/**
 * http://crackinterviewtoday.wordpress.com/2010/03/24/reverse-a-single-linked-
 * list-iterative-procedure/
 * 
 * @author Yuan
 * 
 */
public class ReverseLinkedlist {

	// iterative
	static BNode reverseListIterative(BNode head) {
		BNode prevNode = null;
		BNode nextNode = null;
		while (head != null) {
			nextNode = head.next;
			head.next = prevNode;
			prevNode = head;
			head = nextNode;
		}
		return prevNode;
	}

	// recursive
	static BNode reverseListRecursive(BNode head) {
		if (head == null)
			return null;
		if (head.next == null)
			return head;
		BNode nextNode = head.next;
		head.next = null;
		BNode temp = reverseListRecursive(nextNode);
		nextNode.next = head;
		return temp;
	}

	// Given a linked list, we need to write a function that reverses the nodes
	// of a linked list ‘k’ at a time and returns modified linked list.
	// http://crackinterviewtoday.wordpress.com/2010/03/28/k-reverse-linked-list/
	static BNode reverseKBNodes(BNode head, int k) {
		BNode nextNode = head;
		BNode startNode = null;
		BNode endNode = null;
		head = null;
		while (nextNode != null) {
			startNode = nextNode;
			endNode = nextNode;
			for (int i = 1; i < k; i++) {
				endNode = endNode.next;
				if (endNode == null)
					break;
			}
			if (endNode != null) {
				nextNode = endNode.next;
				endNode.next = null;
				startNode = reverseListIterative(startNode);
			} else
				nextNode = null;
			if (head == null)
				head = startNode;
			////////// NOT FULLY FUNCTIONAL
			else
				head.next = startNode;
		}

		return head;
	}

	static void print(BNode head) {
		while (head != null) {
			System.out.print(head.data + " ");
			head = head.next;
		}
	}

	static List<BNode> list = new ArrayList<BNode>();

	public static void main(String[] args) {
		// Constructing Single Linked List: 1 -> 2 ->3 -> 4 ->5
		BNode head = new BNode(1);
		list.add(head);
		head.next = new BNode(2);
		list.add(head.next);
		head.next.next = new BNode(3);
		list.add(head.next.next);
		head.next.next.next = new BNode(4);
		list.add(head.next.next.next);
		head.next.next.next.next = new BNode(5);
		list.add(head.next.next.next.next);
		// BNode tail = reverseListRecursive(head);
		BNode tail = reverseKBNodes(head, 2);
		print(tail);
	}

}
