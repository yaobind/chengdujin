package yuan.jin.interviewQuestions;

import java.util.*; // this is required for Arrays.sort() method

/**
 * A program to check whether the sides of the triangle submitted are valid or
 * not and whether they form a triangle Developer: Prasad Kharkar Date : 10th
 * April 2010
 * 
 * http://www.dreamincode.net/code/snippet5300.htm
 */
class TriangleChecker {
	public static void checkSides(String fs, String ss, String ts) {

		// this code converts the string arguments to double values
		double side1 = Double.parseDouble(fs);
		double side2 = Double.parseDouble(ss);
		double side3 = Double.parseDouble(ts);
		double[] sideArray = new double[3]; // declare the array to store values
											// of sides
		// this code stores the sides of tringle into array
		sideArray[0] = side1;
		sideArray[1] = side2;
		sideArray[2] = side3;
		// sorting is done to sort the array in the ascending order
		// this is required because we are comparing addition of smaller sides
		// with the largest one
		// for simplicity sorting the array and comparing the addition is done
		// rather than
		// checking for each and every combination of values
		Arrays.sort(sideArray);// this sorts the array
		if ((sideArray[0] > 0) && (sideArray[1] > 0) && (sideArray[2] > 0)) {
			if ((sideArray[0] + sideArray[1]) > sideArray[2]) {
				System.out.println("This is a valid triangle");
			} else {
				System.out.println("Triangle is invalid and cannot be formed");
			}
		} else {
			System.out.println("Invalid sides! Enter non zero positive values");
		}
	}

	public static void main(String[] args) {
		checkSides("3", "4", "5");
	}
}
