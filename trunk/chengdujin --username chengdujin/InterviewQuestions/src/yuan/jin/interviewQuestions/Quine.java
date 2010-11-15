package yuan.jin.interviewQuestions;

/**
 * http://www.jeggu.com/2009/08/program-prints-itself-using-java.html
 * 
 * @author Yuan
 * 
 */
public class Quine {
	public static void main(String[] args) {
		char c = 34;
		System.out.println(s + c + s + c + ';' + '}');
	}

	static String s = "public class Quine { public static void main(String[] args) "
			+ "{ char c=34; System.out.println(s+c+s+c+';'+'}'); } static String s=";
}
