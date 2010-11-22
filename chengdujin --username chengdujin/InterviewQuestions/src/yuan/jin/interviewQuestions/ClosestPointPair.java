package yuan.jin.interviewQuestions;

import java.util.Arrays;
import java.util.Comparator;

class CPoint {
	double x;
	double y;

	public CPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public CPoint(int t, int l) {
		this.x = t;
		this.y = l;
	}

	double getX() {
		return x;
	}

	double getY() {
		return y;
	}

	@Override
	public String toString() {
		return "[" + this.x + "," + this.y + "]";
	}
}

/**
 * Given a set of points (x,y) , find all pairs of points whose distance is less
 * than a given number, say, K.
 * 
 * Beauty of Programming 2.11
 * http://blog.csdn.net/midgard/archive/2009/05/18/4199043.aspx
 * 
 * @author Yuan
 * 
 */
public class ClosestPointPair {

	void PrintPoints(CPoint[] points) {
		for (int i = 0; i < points.length; i++)
			System.out.print(points[i] + " ");
		System.out.println();
	}

	double distance(CPoint s, CPoint t) {
		double sx = (s.getX() - t.getX()) * (s.getX() - t.getX());
		double sy = (s.getY() - t.getY()) * (s.getY() - t.getY());
		return Math.sqrt(sx + sy);
	}

	double closestPair(CPoint[] points) {
		double result = distance(points[0], points[1]);
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				if (distance(points[i], points[j]) < result) {
					result = distance(points[i], points[j]);
				}
			}
		}
		return result;
	}

	double findClosestPair(CPoint[] points) {
		if (points.length < 2) {
			System.out.println("Need to input more than 2 points!");
			return -1;
		}
		if (points.length < 4)
			return closestPair(points);

		double dL, dR, d, midXVal;
		int mid = (points.length + 1) / 2;

		CPoint[] pL = new CPoint[mid - 1];
		for (int i = 0; i < (mid - 1); i++)
			pL[i] = points[i];
		CPoint[] pR = new CPoint[mid - 1];
		for (int i = 0; i < (mid - 1); i++)
			pR[i] = points[mid + i];

		dL = findClosestPair(pL);
		dR = findClosestPair(pR);
		d = dL < dR ? dL : dR;

		midXVal = points[mid].getX();

		int stripStart = 0;
		int stripEnd = points.length - 1;
		for (int i = 0; i < points.length - 1; i++) {
			if ((points[i].getX() < midXVal - d)
					&& (points[i + 1].getX() >= midXVal - d))
				stripStart = i + 1;
			if ((points[i].getX() <= midXVal + d)
					&& (points[i + 1].getX() > midXVal + d))
				stripEnd = i;
		}

		int start = 0;
		int end = 1;
		for (int i = stripStart; i < stripEnd; i++) {
			for (int j = i + 1; j < stripEnd; j++) {
				if (distance(points[i], points[j]) < d) {
					d = distance(points[i], points[j]);
					start = i;
					end = j;
				}
			}
		}

		if (start != 0 || end != 0)
			System.out.println("The shortest pair is: P" + start + 1 + ", P"
					+ end + 1 + "");
		System.out.println("the distance is: " + d);

		return d;
	}

	public void init() {
		CPoint p[] = { new CPoint(2, 3), new CPoint(5, 3), new CPoint(4, 6),
				new CPoint(5, 7), new CPoint(4, 3) };
		PrintPoints(p);
		Arrays.sort(p, new Comparator<CPoint>() {
			@Override
			public int compare(CPoint arg0, CPoint arg1) {
				return (int) (arg0.x - arg1.x);
			}

		});
		PrintPoints(p);
		findClosestPair(p);
	}

	public static void main(String[] args) {
		ClosestPointPair test = new ClosestPointPair();
		test.init();
	}

}
