package yuan.jin.interviewQuestions;

import java.util.Arrays;

/**
 * Given 3 sorted arrays (in ascending order). Let the arrays be A(of n1
 * length), B(of n2 length), C(of n3 length). Let x be an element of A, y of B,
 * z of C. The distance between x, y and z is defined as the
 * max(|x-y|,|y-z|,|z-x|). Find the tuple with the minimum distance?
 * 
 * http://effprog.blogspot.com/2010/08/given-3-sorted-arrays-in-ascending.html
 * 
 * http://placementsindia.blogspot.com/2007/09/sorting-mergesort.html
 * 
 * @author Yuan
 * 
 */
public class TripletMinDistance {

	static int min3(int a, int b, int c) {
		int temp = Math.min(a, b);
		return Math.min(temp, c);
	}

	static int max3(int a, int b, int c) {
		int temp = Math.max(a, b);
		return Math.max(temp, c);
	}

	static Tuple minDist(int a[], int b[], int c[], int m, int n, int l) {
		int i = 0;
		int j = 0;
		int k = 0;
		int distance = Integer.MAX_VALUE;
		Tuple out = new Tuple(-1, -1, -1);
		while (i < m && j < n && k < l) {
			int x = Math.abs(a[i] - b[j]);
			int y = Math.abs(b[j] - c[k]);
			int z = Math.abs(a[i] - c[k]);
			int d = max3(x, y, z);
			if (d < distance) {
				distance = d;
				out.x = i;
				out.y = j;
				out.z = k;
				out.max = d;
			}
			int min = min3(a[i], b[j], c[k]);
			if (min == a[i])
				i++;
			else if (min == b[j])
				j++;
			else if (min == c[k])
				k++;
		}
		return out;
	}

	public static void main(String[] args) {
		int m = 5, n = 7, l = 10;
		int a[] = new int[m];
		int b[] = new int[n];
		int c[] = new int[l];
		int i;
		for (i = 0; i < m; ++i)
			a[i] = i + b.length;
		for (i = 0; i < n; ++i)
			b[i] = i + c.length;
		for (i = 0; i < l; ++i)
			c[i] = i + a.length;
		
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(b));
		System.out.println(Arrays.toString(c));

		Tuple r = minDist(a, b, c, m, n, l);
		System.out.println("(" + a[r.x] + "," + b[r.y] + "," + c[r.z] + "):" + r.max);
	}
}

class Tuple {
	int x, y, z, max;

	public Tuple(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
