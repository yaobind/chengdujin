package yuan.jin.interviewQuestions;

public class KMP {

	/**
	 * 对子串加以预处理，从而找到匹配失败时子串回退的位置 找到匹配失败时的最合适的回退位置，而不是回退到子串的第一个字符，即可提高查找的效率
	 * 因此为了找到这个合适的位置，先对子串预处理，从而得到一个回退位置的数组
	 * 
	 */
	public static int[] preProcess(char[] B) {
		int size = B.length;
		int[] P = new int[size];
		P[0] = 0;
		int j = 0;

		// 每循环一次，就会找到一个回退位置
		for (int i = 1; i < size; i++) {
			// 当找到第一个匹配的字符时，即j>0时才会执行这个循环
			// 或者说p2中的j++会在p1之前执行（限于第一次执行的条件下）
			// p1
			while (j > 0 && B[j] != B[i])
				j = P[j];

			// p2，由此可以看出，只有当子串中含有重复字符时，回退的位置才会被优化
			if (B[j] == B[i])
				j++;

			// 找到一个回退位置j，把其放入P[i]中
			P[i] = j;
		}

		return P;
	}

	public static void kmp(String str, String pattern) {
		int strSize = str.length();
		int patSize = pattern.length();

		char[] A = str.toCharArray();
		char[] B = pattern.toCharArray();
		int[] P = preProcess(B);

		int j = 0;
		int k = 0;

		for (int i = 0; i < strSize; i++) {
			// 当找到第一个匹配的字符时，即j>0时才会执行这个循环
			// 或者说p2中的j++会在p1之前执行（限于第一次执行的条件下）
			// p1
			// System.out.println(i);
			while (j > 0 && B[j] != A[i])
				j = P[j - 1];

			// p2 找到一个匹配的字符
			if (B[j] == A[i])
				j++;

			// 输出匹配结果，并且让比较继续下去
			if (j == patSize) {
				j = P[j - 1];
				k++;
				System.out.printf("Find subString '%s' at %d\n", pattern, i
						- patSize + 1);
			}
		}
		System.out.printf("Totally found %d times for '%s'.\n\n", k, pattern);
	}

	public static void main(String[] args) {
		// // 回退位置数组为P[0, 0, 0, 0, 0, 0]
		kmp("abcdeg, abcdeh, abcdef!这个会匹配1次", "abcdef");
		// 回退位置数组为P[0, 0, 1, 2, 3, 4]
		// kmp("Test ititi ititit! Test ititit!这个会匹配2次", "ititit");
		// // 回退位置数组为P[0, 0, 0]
		// kmp("测试汉字的匹配，崔卫兵。这个会匹配1次", "崔卫兵");
		// // 回退位置数组为P[0, 0, 0, 1, 2, 3, 4, 5, 6]
		// kmp("这个会匹配0次", "it1it1it1");
	}
}
