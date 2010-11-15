package yuan.jin.interviewQuestions.sort;

import java.util.Arrays;

public class BubbleSort {

    private static int[] bubbleSort(int[] input) {
        for (int i = 1; i < input.length - 1; i ++) {
            for (int j = 0; j < input.length - i; j ++) {
                if (input[j] > input[j+1]) {
                    int temp = input[j];
                    input[j] = input[j+1];
                    input[j+1] = temp;
                }
            }
        }
        
		return input;
    }

    public static void main(String[] args) {
        int[] seq = { 1, 0, 5, 7, 4, 2 };
        
        System.out.println(Arrays.toString(bubbleSort(seq)));
    }

}
