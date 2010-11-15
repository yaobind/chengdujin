package yuan.jin.interviewQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Random;

/**
 * Given lots and lots of points in a 2D space find all the line with most
 * points on it. 类似题 Given a 2D plane, suppose that there are around 6000 points
 * on it. Find a line which passes the most number of points.
 * 
 * @author Yuan
 * 
 */
class Plane {
	ArrayList<Point> itsPoints;

	public Plane() {
		randomPoints();
	}

	private void randomPoints() {
		Random r = new Random();
		itsPoints = new ArrayList<Point>();
		char c = 'A';
		for (int i = 0; i < 400; i++) {
			double x = r.nextDouble() * 1000;
			double y = r.nextDouble() * 1000;
			itsPoints.add(new Point(x, y, c));
			c++;
		}
	}

	public Plane(ArrayList<Point> thePoints) {
		itsPoints = thePoints;
	}

	public LinkedHashSet<Point> getColinearPoints() {
		HashMap<Line, LinkedHashSet<Point>> aMap = new HashMap<Line, LinkedHashSet<Point>>();
		Line result = null;
		int max = 0;
		for (int i = 0; i < itsPoints.size(); i++) {
			Point a = itsPoints.get(i);
			for (int j = i + 1; j < itsPoints.size(); j++) {
				Point b = itsPoints.get(j);
				Line aLine = new Line(a, b);
				LinkedHashSet<Point> points;
				if (aMap.containsKey(aLine)) {
					points = aMap.get(aLine);
					points.add(a);
					points.add(b);
				} else {
					points = new LinkedHashSet<Point>();
					points.add(a);
					points.add(b);
					aMap.put(aLine, points);
				}
				if (points.size() > max) {
					max = points.size();
					result = aLine;
				}
			}
		}

		return aMap.get(result);
	}
}

class Point {
	public double x;
	public double y;
	public char name;

	public Point(double x, double y, char name) {
		this.x = x;
		this.y = y;
		this.name = name;
	}
}

class Line {

	public double yIntercept;
	public double slope;
	public double episilon = 0.0001;
	public boolean isInfinity = false;

	public Line(Point a, Point b) {
		double dx = b.x - a.x;
		double dy = b.y - a.y;
		if (Math.abs(dx) <= episilon) {
			isInfinity = true;
		} else {
			slope = dy / dx;
			yIntercept = b.y - slope * b.x;
		}
	}

	private boolean isEqual(Line theLine) {
		double dslope = Math.abs(theLine.slope - this.slope);
		double dInt = Math.abs(theLine.yIntercept - this.yIntercept);
		if (dslope <= episilon && dInt <= episilon)
			return true;

		return false;
	}

	@Override
	public boolean equals(Object obj) {
		Line aLine = (Line) obj;
		if (aLine.isInfinity == this.isInfinity && isEqual(aLine))
			return true;
		return false;

	}

	@Override
	public int hashCode() {
		int a = (int) (1000 * yIntercept);
		int b = (int) (1000 * slope);
		return a | b;
	}

}

public class LinesOf2DPlane {

	public static void main(String[] args) {
		Plane aPlane = new Plane();
		LinkedHashSet<Point> x = aPlane.getColinearPoints();
		for (Point p : x) {
			System.out.print("{" + p.x + "," + p.y + "}");
			System.out.print(",");
		}
	}

}
