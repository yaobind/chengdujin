package yuan.jin.interviewQuestions;

/**
 * Implement subtraction by addition http://fayaa.com/tiku/view/18/
 * 
 * @author Yuan
 * 
 */
public class SubtractionWithoutSubtraction {

	private static int decreaseOne(int a) {
		int c = 0;
		for (int i = 1; i < a; i++)
			c++;
		return c;
	}

	public static void main(String[] args) {
		System.out.println(decreaseOne(5));

	}

}
