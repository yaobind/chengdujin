package yuan.jin.interviewQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Arrange numbers in decreasing order of their frequency
 * 
 * Input : {5, 13, 6, 5, 13, 7, 8, 6, 5}
 * 
 * Output : {5, 5, 5, 13, 13, 6, 6, 7, 8}
 * 
 * The question is to arrange the numbers in the array in decreasing order of
 * their frequency, preserving the order of their occurrence. If there is a tie,
 * like in this example between 13 and 6, then the number occurring first in the
 * input array would come first in the output array.
 * 
 * http://inder-gnu.blogspot.com/2010/03/arrange-numbers-in-decreasing-order-of.
 * html
 * 
 * @author Yuan
 * 
 */
public class RearrangeInDecreasingFrequency {
	static LinkedHashMap<Integer, Integer> maps = new LinkedHashMap<Integer, Integer>();
	
	static int[] process(int[] a) {
		for (int i = 0; i < a.length; i++) {
			if (maps.containsKey(a[i]))
				maps.put(a[i], maps.get(a[i]) + 1);
			else
				maps.put(a[i], 1);
		}
		
		List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(
				maps.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
			@Override
			public int compare(Entry<Integer, Integer> a0,
					Entry<Integer, Integer> a1) {
				return (a1.getValue() - a0.getValue());
			}
		});
		
		int[] b = new int[list.size()];
		for (int i = 0; i < list.size(); i++)
			b[i] = list.get(i).getKey();
		
		return b;
	}

	public static void main(String[] args) {
		int[] input = { 7, 13, 6, 5, 13, 5, 8, 6, 5 };
		System.out.println(Arrays.toString(process(input)));
	}

}
