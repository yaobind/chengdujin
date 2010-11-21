package yuan.jin.interviewQuestions;

/**
 * Find if a tree is a mirror of itself. You can consider the tree to be a
 * binary tree
 * 
 * http://inder-gnu.blogspot.com/2010/11/find-if-tree-is-mirror-of-itself.html
 * 
 * @author Yuan
 * 
 */
public class TreeMirrorChecker {

	static boolean isSymmetricTree(PNode a, PNode b) {
		if (a == null && b == null)
			return true;
		if (a == null || b == null)
			return false;
		if (a.data == b.data)
			return isSymmetricTree(a.left, b.right)
					&& isSymmetricTree(a.right, b.left);
		else
			return false;
	}

	public static void main(String[] args) {
		PNode node = new PNode(6);
		node.left = new PNode(2);
		node.right = new PNode(7);
		node.left.left = new PNode(1);
		node.left.right = new PNode(4);
		node.right.left = new PNode(4);
		node.right.right = new PNode(1);
		System.out.println(isSymmetricTree(node.left, node.right));
	}

}
