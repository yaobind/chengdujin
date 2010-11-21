package yuan.jin.interviewQuestions;

import java.util.Random;

/**
 * Suppose you're on a game show and you're given the choice of three doors.
 * Behind one door is a car; behind the others, goats. The car and the goats
 * were placed randomly behind the doors before the show. The rules of the game
 * show are as follows: After you have chosen a door, the door remains closed
 * for the time being. The game show host, Monty Hall, who knows what is behind
 * the doors, now has to open one of the two remaining doors, and the door he
 * opens must have a goat behind it. If both remaining doors have goats behind
 * them, he chooses one randomly. After Monty Hall opens a door with a goat, he
 * will ask you to decide whether you want to stay with your first choice or to
 * switch to the last remaining door. Imagine that you chose Door 1 and the host
 * opens Door 3, which has a goat. He then asks you
 * "Do you want to switch to Door Number 2?" Is it to your advantage to change
 * your choice?
 * 
 * http://rosettacode.org/wiki/Monty_Hall_problem
 * 
 * @author Yuan
 * 
 */
public class MontyHallProblem {

	public static void main(String[] args) {
		int switchWins = 0;
		int stayWins = 0;
		Random gen = new Random();
		for (int plays = 0; plays < 32768; plays++) {
			int[] doors = { 0, 0, 0 };// 0 is a goat, 1 is a car
			doors[gen.nextInt(3)] = 1;// put a winner in a random door
			int choice = gen.nextInt(3); // pick a door, any door
			int shown; // the shown door
			do {
				shown = gen.nextInt(3);
				// don't show the winner or the choice
			} while (doors[shown] == 1 || shown == choice);

			stayWins += doors[choice];// if you won by staying, count it

			// the switched (last remaining) door is (3 - choice - shown),
			// because 0+1+2=3
			switchWins += doors[3 - choice - shown];
		}
		System.out.println("Switching wins " + switchWins + " times.");
		System.out.println("Staying wins " + stayWins + " times.");
	}

}
