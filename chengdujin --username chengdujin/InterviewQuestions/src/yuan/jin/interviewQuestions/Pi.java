package yuan.jin.interviewQuestions;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Pi {

	static void calcPi() {
		NumberFormat blah = new DecimalFormat(
				"#.###############################");
		double PI = 4.0 * Math.atan(1.0);
		System.out.println(blah.format(PI));
	}

	public static void main(String[] args) {
		calcPi();
	}

}
