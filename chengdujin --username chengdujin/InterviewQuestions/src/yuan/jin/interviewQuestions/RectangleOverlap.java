package yuan.jin.interviewQuestions;

import java.util.Arrays;

class Rect {
	public CPoint upperLeft;
	public CPoint lowerRight;

	public Rect(CPoint ul, CPoint lr) {
		this.upperLeft = ul;
		this.lowerRight = lr;
	}

	public Rect(double x1, double y1, double x2, double y2) {
		this.upperLeft = new CPoint(x1, y1);
		this.lowerRight = new CPoint(x2, y2);
	}

	public double area() {
		return (this.upperLeft.y - this.lowerRight.y) * (this.lowerRight.x - this.upperLeft.x);
	}

	public boolean overlap(Rect b) {
		return (this.upperLeft.x <= b.lowerRight.x
				&& this.upperLeft.y >= b.lowerRight.y
				&& this.lowerRight.x >= b.upperLeft.x && this.lowerRight.y <= b.upperLeft.y);
	}

	/**
	 * Find an efficient algorithm to calculate area of overlapping rectangles
	 * 
	 * http://stackoverflow.com/questions/244452/what-is-an-efficient-algorithm-
	 * to- find-area-of-overlapping-rectangles
	 * 
	 */
	public Rect intersection(Rect b) {
		if (!this.overlap(b))
			return new Rect(0, 0, 0, 0);
		
		double[] horiz = { this.upperLeft.x, this.lowerRight.x, b.upperLeft.x,
				b.lowerRight.x };
		Arrays.sort(horiz);
		double[] vert = { this.lowerRight.y, this.upperLeft.y, b.lowerRight.y,
				b.upperLeft.y };
		Arrays.sort(vert);

		return new Rect(horiz[1], vert[2], horiz[2], vert[1]);
	}

	@Override
	public String toString() {
		return "" + this.upperLeft + " - " + this.lowerRight;
	}
}

/**
 * You are given two rectangles, each defined by an upper left (UL) corner and a
 * lower right (LR) corner. Both rectangles’ edges will always be parallel to
 * the x or y axis, as shown in Figure 11-2. Write a function that determines
 * whether the two rectangles overlap. The function should return 1 if the
 * rectangles overlap and 0 if they do not.
 * 
 * Programming Interview Exposed. Page. 172
 * 
 * @author Yuan
 * 
 */
public class RectangleOverlap {

	public static void main(String[] args) {
		Rect r1 = new Rect(new CPoint(0, 3), new CPoint(4, 1));
		Rect r2 = new Rect(new CPoint(1, 4), new CPoint(4, 1));
		if (r1.overlap(r2)) {
			Rect inter = r1.intersection(r2);
			System.out.println(inter.toString());
			System.out.println(inter.area());
		}
	}
}
