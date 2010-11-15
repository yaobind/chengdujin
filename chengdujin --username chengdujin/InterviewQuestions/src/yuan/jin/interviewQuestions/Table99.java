package yuan.jin.interviewQuestions;

public class Table99 {
	
	static void print99(int start, int end) {
		for (int i = start; i < end; i++) {
			String padding = "";
			for (int j = start; j < end; j++) {
				System.out.println(padding + i + "*" + j + "=" + i*j );
				padding += "  ";
			}
		}
	}

	public static void main(String[] args) {
		print99(1, 9);
	}

}
