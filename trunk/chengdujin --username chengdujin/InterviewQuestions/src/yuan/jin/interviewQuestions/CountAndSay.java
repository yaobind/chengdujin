package yuan.jin.interviewQuestions;

/**
 * "Count and Say problem"
 * 
 * n String to print
 * 
 * 0 1
 * 
 * 1 1 1
 * 
 * 2 2 1
 * 
 * 3 1 2 1 1
 * 
 * ...
 * 
 * Base case: n = 0 print "1" for n = 1, look at previous string and write
 * number of times a digit is seen and the digit itself. In this case, digit 1
 * is seen 1 time in a row... so print "1 1" for n = 2, digit 1 is seen two
 * times in a row, so print "2 1" for n = 3, digit 2 is seen 1 time and then
 * digit 1 is seen 1 so print "1 2 1 1" for n = 4 you will print "1 1 1 2 2 1"
 * Consider the numbers as integers for simplicity. e.g. if previous string is
 * "10 1" then the next will be "1 10 1 1" and the next one will be
 * "1 1 1 10 2 1"
 * 
 * http://www.careercup.com/question?id=4425679
 * 
 * @author Yuan
 * 
 */
public class CountAndSay {
	
	static String countAndSay(int n) {
		StringBuilder prev = new StringBuilder("1");
		StringBuilder curr = new StringBuilder();
		while (n-- > 0) {
			curr.delete(0, curr.length());
			int count = 0;
			char lastSeen = prev.charAt(0);
			for (int i = 0; i < prev.length(); i++) {
				char c = prev.charAt(i);
				if (lastSeen == c)
					count++;
				else {
					curr.append(count);
					curr.append(lastSeen);
					lastSeen = c;
					count = 1;
				}
				if (i == prev.length()-1){
					curr.append(count);
					curr.append(lastSeen);
				}
			}
			StringBuilder t = prev;
			prev = curr;
			curr = t;
		}
		return prev.toString();
	}

	public static void main(String[] args) {
		System.out.println(countAndSay(5));
	}
}
