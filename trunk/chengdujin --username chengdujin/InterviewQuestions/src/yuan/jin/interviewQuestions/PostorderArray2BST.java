package yuan.jin.interviewQuestions;

class PNode {
	int data;
	PNode left;
	PNode right;

	public PNode(int data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "" + this.data;
	}
	
}

/**
 * 输入一个整数数组，判断该数组是不是某二元查找树的后序遍历的结果。如果是返回true，否则返回false。
 * 例如输入5、7、6、9、11、10、8，由于这一整数序列是如下树的后序遍历结果：
 * 
 *        8
 *      /  \
 *     6    10
 *   / \    / \
 *  5   7   9  11
 * 
 * 因此返回true。
 * 
 * 如果输入7、4、6、5，没有哪棵树的后序遍历的结果是这个序列，因此返回false。
 * 
 * http://zhedahht.blog.163.com/blog/static/25411174200725319627/
 * 
 * @author Yuan
 * 
 */
public class PostorderArray2BST {
	
	static PNode reconstruct (int[] arr, int low, int high) {
		if (low > high)
			return null;
		PNode node = new PNode(arr[high]);
		int i = 0;
		for (i = low; i < high; i++)
			if (arr[i] > arr[high])
				break;
		node.left = reconstruct(arr, low, i-1);
		node.right = reconstruct(arr, i, high-1);
		return node;
	}
	
	public static void printTree(PNode root) {
		if (root == null)
			return;
		printTree(root.left);
		System.out.print(Integer.toString(root.data) + " ");
		printTree(root.right);
	}

	public static void main(String[] args) {
		int[] input = { 5, 7, 6, 9, 11, 10, 8 };
		PNode root = reconstruct(input, 0, input.length-1);
		printTree(root);
	}

}
