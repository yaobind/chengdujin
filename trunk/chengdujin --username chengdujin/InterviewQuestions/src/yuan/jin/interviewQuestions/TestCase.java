package yuan.jin.interviewQuestions;

import java.util.BitSet;
import java.util.Random;

public class TestCase {

	public static void main(String[] args) {
		BitSet bits = new BitSet();
		Random gen = new Random();
		int i = 0;
		while (i++ < 20) {
			int t = gen.nextInt(50);
			bits.set(t, true);
		}
		System.out.println(bits.toString());
	}

}
