package yuan.jin.interviewQuestions;

/**
 * This code wraps a strings in multiple lines and allows you to specify how
 * many characters on each line. It does not cut in the middle of a word, well
 * it wraps it the real way!
 * 
 * http://www.dreamincode.net/code/snippet1022.htm
 * 
 * @author Yuan
 * 
 */
public class WordWrapping {

	public static String wordwrap(String base, int regex) {
		// Prepare variables
		String rsm = base;
		boolean gotspace = false;
		boolean gotfeed = false;

		// Jump to characters to add line feeds
		int pos = regex;
		while (pos < rsm.length()) {
			// Progressivly go backwards until next space
			int bf = pos - regex; // What is the stop point
			gotspace = false;
			gotfeed = false;

			// Find space just before to avoid cutting words
			for (int ap = pos; ap > bf; ap--) {
				// Is it a space?
				if (String.valueOf(rsm.charAt(ap)).equals(" ") == true
						&& gotspace == false) {
					// Insert line feed and compute position variable
					gotspace = true;
					pos = ap; // Go to position
				}
				// If it is a line feed, go to it
				else if (String.valueOf(rsm.charAt(ap)).equals("\n") == true
						&& gotfeed == false) {
					pos = ap; // Go to position
					gotfeed = true;
				}
			}
			// Got no feed? Append a line feed to the appropriate place
			if (gotfeed == false) {
				if (gotspace == false) {
					rsm = new StringBuffer(rsm).insert(pos, "\n").toString();
				} else {
					rsm = new StringBuffer(rsm).insert(pos + 1, "\n")
							.toString();
				}
			}
			// Increment position by regex and restart loop
			pos += (regex + 1);
		}
		// Return th result
		return (rsm);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
