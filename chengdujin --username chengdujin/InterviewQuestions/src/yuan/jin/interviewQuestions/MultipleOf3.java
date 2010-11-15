package yuan.jin.interviewQuestions;

/**
 * Write an Efficient Method to Check if a Number is Multiple of 3.
 * 
 * The very first solution that comes to our mind is the one that we learned in
 * school. If sum of digits in a number is multiple of 3 then number is multiple
 * of 3 e.g., for 612 sum of digits is 9 so it’s a multiple of 3. But this
 * solution is not efficient. You have to get all decimal digits one by one, add
 * them and then check if sum is multiple of 3.
 * 
 * There is a pattern in binary representation of the number that can be used to
 * find if number is a multiple of 3. If difference between count of odd set
 * bits (Bits set at odd positions) and even set bits is multiple of 3 then is
 * the number.
 * 
 * http://geeksforgeeks.org/?p=511
 * 
 * @author Yuan
 * 
 */
public class MultipleOf3 {

	static boolean isMultiple(int n) {
		int odds = 0;
		int evens = 0;
		if (n < 0)
			n = -n;
		if (n == 0)
			return true;
		if (n == 1)
			return false;
		while (n != 0) {
			if ((n & 1) == 1)
				odds++;
			n = n >> 1;

			if ((n & 1) == 1)
				evens++;
			n = n >> 1;
		}
		return isMultiple(Math.abs(odds - evens));
	}

	public static void main(String[] args) {
		System.out.println(isMultiple(51));
	}

}
