package yuan.jin.interviewQuestions;

/**
 * The Beauty of Programming 2.4
 * 
 * P.S. The count of 2s until n is almost the same - one just needs to add one
 * more case "case 0" after increase current cases by one; c'est a dire:
 * 
 * case 0:
 * 
 * case 1: count += numer * pow; break;
 * 
 * case 2: count += number * pow + lower + 1; break;
 * 
 * default: count += (number + 1) * pow; break;
 * 
 * 
 * @author Yuan
 * 
 */
public class Count1sNumber {

	static int count1s(int number) {
		int pow = 1;
		int digit = 0;
		int lower = 0;
		int current = 0;
		int count = 0;
		while (number != 0) {
			current = number % 10;
			number = number / 10;
			pow = (int) Math.pow(10, digit);
			digit++;
			switch (current) {
			case 0:
				count += number * pow;
				break;
			case 1:
				count += number * pow + lower + 1;
				break;
			default:
				count += (number + 1) * pow;
				break;
			}
			lower = current * pow + lower;
		}
		return count;
	}

	public static void main(String[] args) {
		int n = 100;
		int result = 0;
		do {
			result = count1s(n);
		} while((n++) != result);
		System.out.println(--n);
	}

}
