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
 * Time complexity: O(nlogn)
 * 
 * @author Yuan
 * 
 */
public class ArrayAccumulatingSmallers {

	static Tree addNode(Tree node, int value, int[] arr, int index, int count) {
		if (node == null) {
			node = new Tree();
			node.data = value;
			node.freq++;
			node.left = null;
			node.right = null;
			arr[index] = count;
		} else {
			if (value == node.data) {
				arr[index] = count + node.freq;
				node.freq++;
			} else if (value < node.data) {
				node.freq++;
				node.left = addNode(node.left, value, arr, index, count);
			} else
				node.right = addNode(node.right, value, arr, index,
						(count + node.freq));
		}
		return node;
	}

	public static void main(String[] args) {

		int[] input = { 34, 49, 42, 10, 30, 18, 31, 14, 1, 35 };
		// int[] input = { 1, 3, 2, 4, 5, 4, 2 };
		int[] counter = new int[input.length];
		Tree root = null;
		counter[input.length - 1] = 0;
		for (int i = input.length - 1; i >= 0; i--)
			root = addNode(root, input[i], counter, i, 0);
		System.out.println(Arrays.toString(input));
		System.out.println(Arrays.toString(counter));
	}
}

class Tree {
	int data;
	int freq;
	Tree left;
	Tree right;
}
