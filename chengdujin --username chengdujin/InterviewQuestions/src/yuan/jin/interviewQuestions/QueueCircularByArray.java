package yuan.jin.interviewQuestions;

import java.util.Random;

/**
 * Implement a circular queue using an array, enqueue & dequeue.
 * 
 * Code modified from
 * 
 * http://www.scribd.com/doc/14768610/Array-Circular-Queue
 * 
 * @author Yuan
 * 
 */
public class QueueCircularByArray {
	int size = 4;
	int[] a = new int[size];
	int front, rear;

	void enqueue(int value) {
		a[rear++ % size] = value;
	}

	int dequeue() {
		int prev = front;
		int k = a[front++ % size];
		a[prev % size] = 0;
		return k;
	}

	boolean isEmpty() {
		return front == rear;
	}

	boolean isFull() {
		return (rear + 1) % size == front;
	}

	void print() {
		for (int i = 0; i < size; i++)
			System.out.print(a[i] + "   ");
		System.out.println();
	}

	public static void main(String[] args) {
		QueueCircularByArray q = new QueueCircularByArray();
		Random gen = new Random();
		int i = 0;
		while (i < q.size * 2) {
			q.enqueue(1 + gen.nextInt(50));
			q.print();
			i++;
		}
		i = 0;
		while (i < q.size) {
			System.out.println(q.dequeue());
			i++;
		}
	}

}
