package yuan.jin.interviewQuestions;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * [2.17.3] Numbers are randomly generated and stored in an array. Write a
 * program to find and maintain the median value as new values are generated.
 * 
 * @author Yuan
 * 
 */
public class MedianDynamicPriorityQueue {

	private static Comparator<Integer> maxHeapComparator, minHeapComparator;
	private static PriorityQueue<Integer> maxHeap, minHeap;

	public static void addNewNumber(int randomNumber) {
		if (maxHeap.size() == minHeap.size()) {
			if ((minHeap.peek() != null) && randomNumber > minHeap.peek()) {
				maxHeap.offer(minHeap.poll());
				minHeap.offer(randomNumber);
			} else {
				maxHeap.offer(randomNumber);
			}
		} else {
//			System.out.println(maxHeap.size() + " - " + minHeap.size());
			if (randomNumber < maxHeap.peek()) {
				minHeap.offer(maxHeap.poll());
				maxHeap.offer(randomNumber);
			} else {
				minHeap.offer(randomNumber);
			}
		}
	}

	public static double getMedian() {
		if (maxHeap.isEmpty())
			return minHeap.peek();
		else if (minHeap.isEmpty())
			return maxHeap.peek();

		if (maxHeap.size() == minHeap.size())
			return (minHeap.peek() + maxHeap.peek()) / 2;
		else if (maxHeap.size() > minHeap.size())
			return maxHeap.peek();
		else
			return minHeap.peek();
	}

	public static void main(String[] args) {
//		int[] seq = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
		int[] seq = { 2, 4, 1, 5, 6, 7, 8 };

		maxHeap = new PriorityQueue<Integer>();
		minHeap = new PriorityQueue<Integer>();

		for (int i = 0; i < seq.length; i++) {
			addNewNumber(seq[i]);
			System.out.print(getMedian() + " ");
		}
	}

}
