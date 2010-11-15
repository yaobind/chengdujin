package yuan.jin.interviewQuestions;

public class Test {

	public static void main(String[] args) {
		int x = 30;
		int y = x & (-x);
		int z = x & ~(x-1);
		System.out.println(Integer.toBinaryString(x));
		System.out.println(Integer.toBinaryString(y));
		System.out.println(Integer.toBinaryString(z));
		
		String s1 = "test";
		String s2 = "estt";
		
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		int j = 0;
		for (int i = 0; i < c1.length; i++)
			j ^= (c1[i] ^ c2[i]);
		System.out.println(j);
	}

}
