package yuan.jin.interviewQuestions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * There is a linked list of numbers of length N. N is very large and you don’t
 * know N. You have to write a function that will return k random numbers from
 * the list. Numbers should be completely random.
 * 
 * @author Yuan
 * 
 */
public class RandomKNumbersLinkedList {
	static Set numbers = new HashSet();
	static List min = new ArrayList();

	class Node {
		public int id;
		public int value;
		public int weight;

		public Node(int id, int value, int weight) {
			this.id = id;
			this.value = value;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "[" + this.id + "] = " + this.weight;
		}
	}

	public void init() {
		Random gen = new Random();
		while (numbers.size() < 50) {
			int value = gen.nextInt(100) + 1;
			while (numbers.contains(value))
				value = gen.nextInt(100) + 1;
			numbers.add(value);
			Node n = new Node(numbers.size(), value, gen.nextInt(value) + 1);
			min.add(n);
		}
	}

	public static void main(String[] args) {
		RandomKNumbersLinkedList r = new RandomKNumbersLinkedList();
		r.init();
		System.out.println(min.toString());
		Collections.sort(min, new Comparator<Node>() {
			@Override
			public int compare(Node arg0, Node arg1) {
				return arg1.weight - arg0.weight;
			}
		});
		System.out.println(min.toString());
	}

}
