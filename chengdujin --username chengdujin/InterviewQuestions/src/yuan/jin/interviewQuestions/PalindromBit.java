package yuan.jin.interviewQuestions;

public class PalindromBit {

	static int reverseBits(int b) {
		int v = b;
		int r = 0;
		for (; v != 0; v >>= 1) {
			r <<= 1;
			r |= v & 1;
		}
		return r;
	}
	
	static boolean isPalindrome(int b) {
		int v = b;
		int r = reverseBits(v);
		return v == r;
	}
	
	public static void main(String[] args) {
		int n = 27;
		System.out.print(Integer.toBinaryString(n));
		System.out.println(" -> " + Integer.toBinaryString(reverseBits(n)));
		System.out.println(isPalindrome(n));
	}

}
