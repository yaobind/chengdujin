package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * Given an array a[], create another array ar_low[] such that ar_low[i] =
 * number of elements less than or equal to ar[i] in ar[i+1:n-1]
 * 
 * http://www.careercup.com/question?id=3320663
 * 
 * Scan from right to left of the array
 * 
 * Now as you create the tree if the new element is to the right of the node
 * then add the freq of that node until that element find place in the tree The
 * total freq = no of elements small or equal to it.
 * 
 * If element is already their in the tree then increase its freq.
 * 
 * >>>>>>>>>>>>>> This is the same question for: >>>>>>>>>>>>>>
 * 
 * Given an array a[1:n], we say a[i], a[j] forms a reverse if a[i] > a[j], i <
 * j. Count the number of reverse.
 * 
 * http://stackoverflow.com/questions/3836767/interview-question-reverse-pairs
 * 
 * Time complexity: O(nlogn)
 * 
 * @author Yuan
 * 
 */
public class NextLessArray {

	static Tree insert(int[] a, Tree node, int index, int value, int count) {
		if (node == null) {
			node = new Tree();
			node.data = value;
			node.size++;
			node.left = null;
			node.right = null;
			a[index] = count;
		} else {
			if (value == node.data) {
				a[index] = count + node.size;
				node.size++;
			} else if (value < node.data) {
				node.size++;
				node.left = insert(a, node.left, index, value, count);
			} else
				node.right = insert(a, node.right, index, value,
						(count + node.size));
		}
		return node;
	}

	public static void main(String[] args) {
		// int[] input = { 34, 49, 42, 10, 30, 18, 31, 14, 1, 35 };
		int[] input = { 3, 4, 1, 6, 7, 3 };
		// int[] input = { 1, 3, 2, 4, 5, 4, 2 };
		int[] counter = new int[input.length];
		Tree root = null;
		counter[input.length - 1] = 0;
		for (int i = input.length - 1; i >= 0; i--)
			root = insert(counter, root, i, input[i], 0);
		System.out.println(Arrays.toString(input));
		System.out.println(Arrays.toString(counter));
	}
}

class Tree {
	int data;
	int size;
	Tree left;
	Tree right;
}
