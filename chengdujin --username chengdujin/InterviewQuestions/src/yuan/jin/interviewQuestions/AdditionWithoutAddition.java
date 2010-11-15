package yuan.jin.interviewQuestions;

public class AdditionWithoutAddition {
	
	static int add(int a, int b) {
		if (b == 0) 
			return a;
		int sum = (a ^ b);
		int carry = (a & b) << 1;
		return add(sum, carry);
	}
	
	static int addOne(int x) {
		  int m = 1;
		  /* Flip all the set bits until we find a 0 */
		  while( (x & m) > 0) {
		    x = x^m;
		    m <<= 1;
		  }
		  /* flip the rightmost 0 bit */
		  return x^m;
	}

	public static void main(String[] args) {
		System.out.println(add(4, 5));
	}

}
