package yuan.jin.interviewQuestions;

public class Stacks3ByArray {
	static int stackSize = 300;
	static int[] buffer = new int[stackSize * 3];
	static int[] stackPointer = { 0, 0, 0 };
	
	void push(int stackNum, int value) {
		int start = stackNum * stackSize;
		int index = stackPointer[stackNum];
		
		if (index + 1 < stackSize) {
			buffer[start + index + 1] = value;
			stackPointer[stackNum] ++;
		}
	}
	
	int pop(int stackNum) {
		int index = stackPointer[stackNum];
		int start = stackNum * stackSize;
		int result = -1;
		if (index - 1 >= 0) {
			result = buffer[start + index - 1];
			stackPointer[stackNum] --;
		}
		return result;
	}
	
	public static void main(String[] args) {
		
	}

}
