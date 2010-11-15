package yuan.jin.interviewQuestions;

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

	class Point {
		public int x;
		public int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	class Rect {
		public Point ul;
		public Point lr;

		public Rect(Point ul, Point lr) {
			this.ul = ul;
			this.lr = lr;
		}
	}

	boolean overlap(Rect a, Rect b) {
		return (a.ul.x <= b.lr.x && a.ul.y >= b.lr.y && a.lr.x >= b.ul.x && a.lr.y <= b.ul.y);
	}

	void init() {
		Rect r1 = new Rect(new Point(1, 4), new Point(3, 2));
		Rect r2 = new Rect(new Point(2, 3), new Point(4, 1));
		System.out.println(overlap(r2, r1));
	}

	public static void main(String[] args) {
		RectangleOverlap o = new RectangleOverlap();
		o.init();
	}
}
