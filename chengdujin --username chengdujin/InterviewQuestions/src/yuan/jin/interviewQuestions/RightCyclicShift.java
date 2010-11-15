package yuan.jin.interviewQuestions;

/**
 * Takes the left bit and wraps it around to the right end, instead of shifting
 * and filling with zeros
 * 
 * http://www.dreamincode.net/code/snippet1003.htm
 * 
 * @author Yuan
 * 
 */
public class RightCyclicShift {

	public static byte cyclicRightShift(byte b, int shift) {
		for (int i = 0; i < shift; ++i)
			b = (byte) (((b & 0x01) << 7) | ((b >>> 1) & 0x7F));
		return b;
	}

	public static void main(String[] args) {
		byte n = 10;
		System.out.println(Integer.toBinaryString(n));
		System.out.println(Integer.toBinaryString(cyclicRightShift(n, 2)));
	}

}
