package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * http://blog.csdn.net/v_JULY_v/archive/2011/01/01/6111565.aspx
 * 
 * @author Yuan
 * 
 */
public class KMP {

	/**
	 * 对子串加以预处理，从而找到匹配失败时子串回退的位置 找到匹配失败时的最合适的回退位置，而不是回退到子串的第一个字符，即可提高查找的效率
	 * 因此为了找到这个合适的位置，先对子串预处理，从而得到一个回退位置的数组
	 * 
	 */
	public static int[] preProcess(char[] B) {
		int[] P = new int[B.length];
		P[0] = 0;
		int j = 0;

		for (int i = 1; i < B.length; i++) {
			while (j > 0 && B[j] != B[i])
				j = P[j];
			if (B[j] == B[i])
				j++;
			P[i] = j;
		}
		System.out.println(Arrays.toString(P));
		return P;
	}

	public static void kmp(String str, String patn) {
		char[] A = str.toCharArray();
		char[] B = patn.toCharArray();
		int[] P = preProcess(B);
		int j = 0;
		int k = 0;

		for (int i = 0; i < str.length(); i++) {
			while (j > 0 && B[j] != A[i])
				j = P[j - 1];
			if (B[j] == A[i])
				j++;
			if (j == patn.length()) {
				j = P[j - 1];
				k++;
				System.out.printf("Find subString '%s' at %d\n", patn,
						i - patn.length() + 1);
			}
		}

		System.out.printf("Totally found %d times for '%s'.\n\n", k, patn);
	}

	public static void main(String[] args) {
		kmp("annbcdanacadsannannacanna", "annacanna");
	}
}
