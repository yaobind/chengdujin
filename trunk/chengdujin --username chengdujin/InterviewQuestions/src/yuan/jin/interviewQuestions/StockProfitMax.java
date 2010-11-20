package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * Stock prices are given to you at various time intervals. p1, p2, p3,... you
 * are allowed to buy and sell only once each. So write a program to find the
 * index where you would buy and where you would sell to maximize profit.
 * 
 * http://www.careercup.com/question?id=3229685
 * 
 * @author Yuan
 * 
 */
public class StockProfitMax {
	
	// method1 O(n) time O(n) space
	static int compute(int[] input) {
		int current = input[0], currstart = 0, currend = 0;
		int max = input[0], maxstart = 0, maxend = 0;
		for (int i = 1; i < input.length; i++) {
			if (input[i] > (current + input[i])) {
				current = input[i];
				currstart = i;
				currend = i;
			} else {
				current += input[i];
				currend += 1;
			}
			if (current > max) {
				max = current;
				maxstart = currstart;
				maxend = currend;
				System.out.println(maxstart + "-" + (maxend + 1));
			}
		}
		return max;
	}
	
	// method2 O(n)
	static void getBestTime(int stocks[]) {
		int min = 0;
		int maxDiff = 0;
		int buy = 0, sell = 0;
		for (int i = 0; i < stocks.length; i++) {
			if (stocks[i] < stocks[min])
				min = i;
			int diff = stocks[i] - stocks[min];
			if (diff > maxDiff) {
				buy = min;
				sell = i;
				maxDiff = diff;
				System.out.println(maxDiff + ": " + stocks[buy] + "-" + stocks[sell]);
			}
		}
	}


	public static void main(String[] args) {
		int[] input = { 3, 8, 5, 1, 7, 18, 16, 4 };
		int[] ninput = new int[input.length - 1];
		for (int i = 0; i < input.length - 1; i++)
			ninput[i] = input[i + 1] - input[i];
		System.out.println(Arrays.toString(input));
		System.out.println();
		System.out.println(compute(ninput));
		System.out.println();
		getBestTime(input);
	}

}
