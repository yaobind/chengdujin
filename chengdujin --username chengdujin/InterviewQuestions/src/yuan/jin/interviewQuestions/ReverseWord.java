package yuan.jin.interviewQuestions;

/**
 * Write a function that reverses the order of the words in a string. For
 * example, your function should transform the string “Do or do not, there is no
 * try.” to “try. no is there not, do or Do”. Assume that all words are space
 * delimited and treat punctua- tion the same as letters.
 * 
 * @author Yuan
 * 
 */
public class ReverseWord {

	static void reverse(char[] str, int low, int high) {
		int i = low;
		int j = high;
		while (i < j) {
			char c = str[j];
			str[j] = str[i];
			str[i] = c;
			i++;
			j--;
		}
	}

	// without storage
	static void reverse2(char[] x, int low, int high) {
		int i = low;
		int j = high;
		while (i < j) {
			x[j] ^= x[i];
			x[i] ^= x[j];
			x[j] ^= x[i];
			i++;
			j--;
		}
	}

	static String reverseWord(String string) {
		char[] str = string.toCharArray();
		reverse2(str, 0, str.length - 1);
		int start = 0;
		for (int i = 0; i < str.length; i++)
			if (str[i] == ' ') {
				reverse2(str, start, i - 1);
				start = i + 1;
			}
		reverse2(str, start, str.length - 1);
		return new String(str);
	}

	public static void main(String[] args) {
		System.out.println(reverseWord("Find number of 1s up to N."));
	}

}
