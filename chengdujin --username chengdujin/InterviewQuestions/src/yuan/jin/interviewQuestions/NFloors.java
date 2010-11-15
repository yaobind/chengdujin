package yuan.jin.interviewQuestions;

/**
 * 
 * N级的楼梯，一次可以走1级，或者两级。打印出所有可能的走法。
 * 
 * http://www.mitbbssg.com/bbsann2/life.faq/JobHunting/17/D12842543542i0/M
 * .1284994212
 * _2.g0/%C7%EB%CE%CA%D7%DF%C2%A5%CC%DD%B5%C4%CE%CA%CC%E2%C8%E7%BA%CE%
 * B4%F2%D3%A1%CB%F9%D3%D0%B5%C4%C2%B7%BE%B6
 * 
 * Compare this project with Telephone, Coin and Permutation
 * 
 * @author Yuan
 * 
 */
public class NFloors {

	static void printLadderPath(int[] path, int height, int level) {
		int i = 0, sum = 0;
		for (i = 0, sum = 0; i < level; i++) {
			sum += path[i];
		}
		if (sum == height) {
			for (i = 0; i < level; i++)
				System.out.print(path[i]);
			System.out.println();
			return;
		}
		if (sum <= height - 1) {
			path[level] = 1;
			printLadderPath(path, height, ++level);
			level--;
		}
		if (sum <= height - 2) {
			path[level] = 2;
			printLadderPath(path, height, ++level);
			level--;
		}
	}

	public static void main(String[] args) {
		int n = 5;
		int[] path = new int[n];
		printLadderPath(path, n, 0);
	}

}
