package yuan.jin.interviewQuestions.sort;

import java.util.Arrays;

public class InsertionSort {

    private static int[] insertionSort(int[] input) {
        int i, j, newValue;
        for (i = 1; i < input.length; i++) {
            newValue = input[i];
            j = i;
            while (j > 0 && input[j - 1] > newValue) {
                  input[j] = input[j - 1];
                  j--;
            }
            input[j] = newValue;
      }
		return input;
    }

    public static void main(String[] args) {
        int[] seq = { 1, 0, 5, 7, 4, 2 };
        
        System.out.println(Arrays.toString(insertionSort(seq)));
    }

}
