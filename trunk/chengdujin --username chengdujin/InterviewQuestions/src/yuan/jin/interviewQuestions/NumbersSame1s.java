package yuan.jin.interviewQuestions;

/**
 * Given a bit-pattern, start from right, find successive zeroes (xxxx011110000)
 * Followed by zeroes find successive 1’s (xxxx011110000) Stop on hitting a zero
 * (xxxx011110000) Interchange that zero with a 1 from successive 1’s
 * (xxxx101110000) Now move the remaining 1’s to extreme right, filling the gap
 * with zeroes (xxxx100000111)
 * 
 * Check CareerCup books
 * 
 * @author Yuan
 * 
 */
public class NumbersSame1s {
	static boolean getBit(int n, int index) {
		return (n & (1<< index)) > 0;
	}
	
	static int setBit(int n, int index, boolean on) {
		if (on)
			return n | (1 << index);
		else
			return n & ~(1 << index);
	}
	
	static int getNext(int n) {
		int index = 0;
		// find first 1
		while (!getBit(n, index)) 
			index++;
		int count = 0;
		// count number of consecutive 1s
		while (getBit(n, index)) {
			count++;
			index++;
		}
		// set the 0 after consecutive 1s to 1
		n = setBit(n, index, true);
		// set the previous 1 to 0
		n = setBit(n, --index, false);
		// count is number of consecutive 1s
		count--;
		// set all count 1s to 0
		for (int i = index - 1; i >= count; i--)
			n = setBit(n, i, false);
		// rebuild 1s from the beginning
		for (int i = count - 1; i >= 0; i--)
			n = setBit(n, i, true);
		return n;
	}
	
	static int getPrevious(int n) {
		int index = 0;
		while (getBit(n, index))
			index++;
		int count = 0;
		while (!getBit(n, index)) {
			count++;
			index++;
		}
		n = setBit(n, index, false);
		n = setBit(n, --index, true);
		count--;
		for (int i = index - 1; i >= count; i--)
			n = setBit(n, i, true);
		for (int i = count - 1; i >= 0; i--)
			n = setBit(n, i, false);
		return n;
	}

	public static void main(String[] args) {
		int x = 145;
		System.out.println(Integer.toBinaryString(x));
		System.out.println("Next: " + Integer.toBinaryString(getNext(x)));
		System.out.println("Prev: " + Integer.toBinaryString(getPrevious(x)));
	}

}
