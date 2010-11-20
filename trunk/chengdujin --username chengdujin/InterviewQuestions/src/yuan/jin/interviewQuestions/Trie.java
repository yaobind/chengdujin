package yuan.jin.interviewQuestions;

/**
 * http://www.cs.duke.edu/~ola/courses/cps108/fall96/joggle/trie/Trie.java
 * 
 * @author Yuan
 * 
 */
public class Trie {

	private static final int ALPH = 26;
	private Trie[] links;
	private boolean isWord;

	public static void main(String[] args) {
		Trie t = new Trie();
		t.addString("donate");
		t.addString("donated");
		t.addString("donation");
		t.addString("donor");
		System.out.println("Is donation included? " + t.isWord("donation"));
		System.out.println("Is doner included? " + t.isWord("doner"));
	}

	public Trie() {
		links = new Trie[ALPH];
		isWord = false;
	}

	public void addString(String s) {
		Trie t = this;
		int k;
		int limit = s.length();
		for (k = 0; k < limit; k++) {
			int index = s.charAt(k) - 'a';
			if (t.links[index] == null)
				t.links[index] = new Trie();
			t = t.links[index];
		}
		t.isWord = true;
	}

	void print(String s, Trie t) {
		if (t != null) {
			if (t.isWord)
				System.out.println(s);
			int k;
			for (k = 0; k < ALPH; k++)
				if (t.links[k] != null)
					print(s + (char) (k + 'a'), t.links[k]);
		}
	}

	public boolean isWord(String s) {
		Trie t = this;
		int k;
		int limit = s.length();
		for (k = 0; k < limit; k++) {
			int index = s.charAt(k) - 'a';
			if (t.links[index] == null)
				return false;
			t = t.links[index];
		}
		return t.isWord;
	}

	public boolean isWord() {
		return isWord;
	}

	Trie childAt(char ch) {
		return links[ch - 'a'];
	}
}
