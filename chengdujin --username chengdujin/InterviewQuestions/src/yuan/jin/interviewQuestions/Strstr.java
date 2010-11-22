package yuan.jin.interviewQuestions;

/**
 * Write C code to implement the strstr (Search for a substring) function. Do
 * not use any system library such as strlen.
 * 
 * http://www.ihas1337code.com/2010/10/implement-strstr-to-find-substring-in.
 * html
 * 
 * @author Yuan
 * 
 */
public class Strstr {

	static boolean originalMethod(String str1, String str2) {
		char[] c1 = str1.toCharArray();
		char[] c2 = str2.toCharArray();
		int i1 = 0;
		while (i1 <= c1.length) {
			int i1c = i1;
			int i2 = 0;
			while (i1 < c1.length && i2 < c2.length && c1[i1] == c2[i2]) {
				i1++;
				i2++;
			}
			if (i2 == c2.length)
				return true;
			i1 = i1c + 1;
		}
		return false;
	}
	// my modified version
	static boolean strstr(String str1, String str2) {
		char[] c1 = str1.toCharArray();
		char[] c2 = str2.toCharArray();
		int i1 = 0;
		int i1c = i1;
		int i2 = 0;
		while (i1 < c1.length) {
			if (c1[i1] == c2[i2]) {
				i1++;
				i2++;
				if (i2 == c2.length)
					return true;
			} else {
				i1 = i1c + 1;
				i1c = i1;
				i2 = 0;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		String str1 = "abcdefghijk";
		String str2 = "fgh";
		System.out.println(strstr(str1, str2));
	}

}
