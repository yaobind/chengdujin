package yuan.jin.interviewQuestions;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Find the Closest two integers in a array
 * 
 * http://www.dreamincode.net/code/snippet5589.htm
 * 
 * @author Yuan
 * 
 */
public class ClosestPairArray {

	static void closest(double number[]) {
		LinkedList<Integer> indexes = new LinkedList<Integer>();
		LinkedList<Double> diff = new LinkedList<Double>();
		double num[] = number;
		Arrays.sort(num);
		int len = num.length;
		int i = 2;
		double difference = num[1] - num[0];
		indexes.add(1);
		diff.add(difference);
		for (; i < len; i++)
			if (difference >= num[i] - num[i - 1]) {
				difference = num[i] - num[i - 1];
				indexes.add(i);
				diff.add(difference);
			}
		i = 0;
		for (; i < indexes.size(); i++)
			if (difference == (Double) diff.get(i))
				System.out.println(num[indexes.get(i) - 1] + " "
						+ num[indexes.get(i)]);
	}

	public static void main(String[] args) {
		double number[] = { 56, 44, 8, 59, 120, 9, 121, -6, -10.5, 90, -11 };
		closest(number);
	}
}
