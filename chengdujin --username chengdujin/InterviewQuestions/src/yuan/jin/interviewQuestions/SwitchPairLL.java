package yuan.jin.interviewQuestions;

import java.util.Random;

/**
 * Switch pairs of nodes in a linked list
 * 
 * @author Yuan
 * 
 */
public class SwitchPairLL {

	static BNode switcher(BNode head) {
		BNode copy = head;
		while (head != null && head.next != null) {
			int temp = head.data;
			head.data = head.next.data;
			head.next.data = temp;
			head = head.next.next;
		}
		return copy;
	}

	static void print(BNode head) {
		while (head != null) {
			System.out.print(head.data + "->");
			head = head.next;
		}
		System.out.println("null");
	}

	public static void main(String[] args) {
		BNode tail = null;
		BNode head = null;
		int i = 0;
		Random gen = new Random();
		while (i++ <= 6) {
			BNode node = new BNode(gen.nextInt(50));
			if (tail == null) {
				tail = node;
				head = tail;
			} else {
				tail.next = node;
				tail = node;
			}
			tail.next = null;
		}
		print(head);
		print(switcher(head));
	}

}
