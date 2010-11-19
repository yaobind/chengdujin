package yuan.jin.interviewQuestions;

/**
 * Given a puzzle of letters/ characters e.g.
 * 
 * a e r o p s
 * 
 * b h a r l s
 * 
 * w r i s l o
 * 
 * a s n k t q
 * 
 * Write a function to which this puzzle and a word will be passed to test
 * whether that word exists in the puzzle or not. e.g. rain and slow will return
 * true. rain is present in the second column and slow in the third row wrapped
 * around.
 * 
 * http://www.careercup.com/question?id=60349
 * 
 * @author Yuan
 * 
 */
public class BogglePuzzle {

	enum Direction {
		up, down, left, right, upLeft, upRight, downLeft, downRight
	};

	public static void main(String args[]) {
		char[][] ar = new char[][] { 
				{ 'a', 'e', 'r', 'o', 'p', 's' },
				{ 'b', 'h', 'a', 'r', 'l', 's' },
				{ 'w', 'r', 'i', 's', 'l', 'o' },
				{ 'a', 's', 'n', 'k', 't', 'q' } };
		String str = "ktqas";
		BogglePuzzle sf = new BogglePuzzle();
		System.out.print(sf.stringFinder(ar, str));
	}

	boolean stringFinder(char[][] ar, String str) {
		char[] charArray = str.toCharArray();
		for (int i = 0; i < ar.length; i++)
			for (int j = 0; j < ar[0].length; j++) {
				if (ar[i][j] == charArray[0]) {
					boolean checker;
					// check up, down, left and right
					// System.out.println("*" + i + ":" + j);
					checker = isMatch(ar, charArray, i,
							Math.abs((j + 1) % ar[0].length), 1,
							Direction.right)
							|| isMatch(ar, charArray,
									Math.abs((i + 1) % ar.length), j, 1,
									Direction.down)
							|| isMatch(ar, charArray, (i - 1 < 0) ? ar.length
									+ i - 1 : i - 1, j, 1, Direction.up)
							|| isMatch(ar, charArray, i,
									(j - 1 < 0) ? ar[0].length + j - 1 : j - 1,
									1, Direction.left);
					// check diagonals, deep water, be careful
					// ......
					if (checker)
						return true;
				}
			}
		return false;
	}

	boolean isMatch(char[][] ar, char[] charArray, int i, int j, int k,
			Direction dir) {
		if (k == charArray.length - 1) {
			if (ar[i][j] == charArray[k])
				return true;
			else
				return false;
		}
		// System.out.println(i + "-" + j + ":" + dir);
		if (ar[i][j] == charArray[k]) {
			if (dir == Direction.down)
				i = Math.abs((i + 1) % ar.length);
			else if (dir == Direction.right)
				j = Math.abs((j + 1) % ar[0].length);
			else if (dir == Direction.left) {
				j = j - 1;
				if (j < 0)
					j = ar[0].length + j;
			} else if (dir == Direction.up) {
				i = i - 1;
				if (i < 0)
					i = ar.length + i;
			}
			return isMatch(ar, charArray, i, j, k + 1, dir);
		}
		return false;
	}
}
