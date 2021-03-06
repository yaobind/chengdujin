package yuan.jin.interviewQuestions;

/**
 * Maximum Value Contiguous Subsequence Problem: Given a sequence of n real
 * numbers A(1) ... A(n), determine a contiguous subsequence A(i) ... A(j) for
 * which the sum of elements in the subsequence is maximized.
 * 
 * @author Yuan
 * 
 */
public class MaxValueContiguousSequence {

	class SequenceResult {
		public int start = -1;
		public int end = -1;
		public int sum = 0;

		public SequenceResult(int index, int value) {
			reset(index, value);
		}

		public void reset(int index, int value) {
			start = end = index;
			sum = value;
		}

		public void extend(int value) {
			end++;
			sum += value;
		}

		public String toString() {
			return "sum=" + sum + ", range=(" + start + "," + end + ")";
		}
	}

	SequenceResult findMaxValueContigousSeq(int[] input) {
		SequenceResult curr = new SequenceResult(0, input[0]);
		SequenceResult max = new SequenceResult(0, input[0]);
		for (int i = 1; i < input.length; i++) {
			int s = input[i] + curr.sum;
			if (input[i] > s)
				curr.reset(i, input[i]);
			else
				curr.extend(input[i]);
			if (curr.sum > max.sum) {
				max.start = curr.start;
				max.end = curr.end;
				max.sum = curr.sum;
			}
		}
		return max;
	}

	// method1, based on class
	// only compute for the length
	static int max_sum(int[] vector) {
		int best = 0, current = 0;
		int i = 0;
		for (i = 0; i < vector.length; ++i) {
			current += vector[i];
			if (current < 0)
				current = 0;
			best = best > current ? best : current;
		}
		return best;
	}

	// method2, without using class ....
	static int sum(int[] a) {
		int max = 0, mi = 0, mj = 0;
		int curr = 0, ci = 0, cj = 0;
		for (int i = 0; i < a.length; i++) {
			int temp = curr + a[i];
			if (a[i] > temp) {
				ci = i;
				cj = i;
				curr = a[i];
			} else {
				curr = temp;
				cj++;
			}
			if (curr > max) {
				mi = ci;
				mj = cj;
				max = curr;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		MaxValueContiguousSequence result = new MaxValueContiguousSequence();
		// int[] seq = { -2, 11, -4, 13, -5, 2 };
		// int[] seq = { 1, -2, 4, 5, -2, 6, 7 };
		// int[] seq = { 2, -8, 3, -2, 4, -10 };
		int[] seq = { 1, 2, -5, 4, 5, -1, 2, -11 };
		System.out.println(result.findMaxValueContigousSeq(seq));
		System.out.println(sum(seq));
	}

}
