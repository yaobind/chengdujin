package yuan.jin.interviewQuestions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.Date;

/**
 * This Source code converts a Gregorian Calendar date into a International
 * Fixed Calendar date. The Gregorian date can be abbreviated, or the nothing
 * entered for today's date. The program is in 2 classes: IFCConverter and
 * TestIFCConverter.
 * 
 * http://en.wikibooks.org/wiki/Algorithm_Implementation/Date_and_time/
 * Conversion_of_a_Gregorian_date_to_an_International_Fixed_Calendar_date
 * 
 * @author Yuan
 * 
 */
public class GregorianCalendar2International {

	// a gregorian date
	private String gregDate;
	// an International fixed calendar date
	private String IFCdate;
	private static final String[] MONTHS = { "January", "February", "March",
			"April", "May", "June", "Midi", "July", "August", "September",
			"October", "November", "December" };// creates an constant array of
												// months

	// sets gregDate
	public void setGregDate(String gD) {
		if (gD.contains("  ") && !gD.startsWith(" ")) { // if the spaces won't
														// //drive later code
														// wacko
			gregDate = gD;
		}
	}

	// gets IFCdate
	public String getIFCdate() {
		return IFCdate;
	}

	public void GregIFC() {
		// separates the Month part of the date
		String month = gregDate.substring(0, gregDate.indexOf(' '));
		// sets a month # equal to the Number of the //month (0 fo Jan., 1 for
		// Feb., etc.)
		int monthNum = monthNum(month);
		// sets the conversion factors
		int[] cfs = { 0, 3, 3, 6, 8, 11, 13, 0, -12, -9, -7, -4, -2 };
		// separates the day of the month
		String dayStr = gregDate.substring(gregDate.indexOf(' ') + 1);
		// parses the day string into a number
		int day = Integer.parseInt(dayStr);
		// sets IFC day of month
		day += cfs[monthNum];
		if (day > 28 && monthNum != 12) {
			// if IFC date is greater than 28 and it //isn't the 29 day month
			day -= 28;
			// subtract 28 and go to the next month
			month = MONTHS[monthNum + 1];
		}
		if (day < 1) {
			// if IFC date is less than 1
			day += 28;
			// add 28 and go to previous month
			month = MONTHS[monthNum - 1];
		}
		// sets IFCdate equal to final month and day
		IFCdate = month + " " + day;
		if (gregDate.equals("December 31")) {
			// because in the International Fixed Calendar, there's no Dec. //29
			IFCdate = "Extra day";
		}
	}

	public int monthNum(String month) {
		String subMonth = month.substring(0, 3);// so abbreviations will work
		int monthN = 0;
		for (; monthN < MONTHS.length; monthN++) {
			// don't want to have the index be out of bounds
			if (subMonth.equals(MONTHS[monthN].substring(0, 3))) {
				// if month equals current month
				// leave monthN where it is
				break;
			}
		}

		return monthN;
	}

	public static void main(String[] args) {
		// prompts the user to enter a date
		String input = javaInput("enter a date:");
		// makes a reference to IFCConverter
		GregorianCalendar2International ifcc = new GregorianCalendar2International();
		// sets the Greg. date to the date string earlier made
		ifcc.setGregDate(input);
		if (input.equals("")) {
			// Sets a variable equal to the current date
			Date today = new Date();
			// formats today to MONTH Date, YEAR
			String date = DateFormat.getDateInstance(DateFormat.LONG).format(
					today);
			// separates the year
			ifcc.setGregDate(date.substring(0, date.length() - 6));
		}
		// converts the Greg. date to IFC
		ifcc.GregIFC();
		// prints the IFC Date
		System.out.println(ifcc.getIFCdate());
	}

	public static String javaInput(String prompt) {
		String inputLine = null;
		System.out.print(prompt + " ");
		try {
			BufferedReader is = new BufferedReader(new InputStreamReader(
					System.in));
			inputLine = is.readLine();
		} catch (IOException e) {
			System.err.println("IOException: " + e);
		}
		return inputLine;
	}

}
