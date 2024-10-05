// Java Loops and Array Problem Set 2 2024.10.01

import java.lang.Math;
import java.util.Arrays; //used only for easy printing of arrays as strings (lines 89-93)

class LoopArrayPracticeTwo {
	public static int rollDice(int x) {
		assert(x >= 1);
		int[] integerList = new int[x];
		for (int i = 1; i <= x; i++) {
			integerList[i - 1] = i;
		}
		return integerList[(int) Math.floor(Math.random() * integerList.length)];
	}

	public static int luckyDice(int x) {
		int a1 = LoopArrayPracticeTwo.rollDice(x);
		int a2 = LoopArrayPracticeTwo.rollDice(x);
		if (a1 > a2) {
			return a1;
		}
		return a2;
	}

	public static boolean evenOrOdd(int[] integers) {
		int evenNumbers = 0;
		int oddNumbers = 0;
		for (int i = 0; i < integers.length; i++) {
			if (integers[i] % 2 == 0) {
				evenNumbers++;
			} else {
				oddNumbers++;
			}
		}
		if (evenNumbers == oddNumbers) {
			System.out.println("Integer evens and odds equal!");
		}
		return evenNumbers >= oddNumbers;
	}

	public static String[] nickName(String[] names) {
		for (int i = 0; i < names.length; i++) {
			int startingIndex = (int) Math.floor(Math.random() * names[i].length());
			int endingIndex = (int) Math.floor(Math.random() * (names[i].length() - startingIndex)) + startingIndex + 1;
			names[i] = names[i].substring(startingIndex, endingIndex);
		}
		return names;
	}

	public static boolean isPalindrome(String word) {
		return (word.equals(LoopArrayPracticeTwo.reverseString(word))); // cant use == because thats if its the same object not the same data
	}

	private static String reverseString(String word) {
		String reversedString = "";
		for (int i = word.length() - 1; i >= 0; i--) {
			reversedString += word.charAt(i);
		}
		return reversedString;
	}

	public static void main(String[] args) {
		System.out.println(LoopArrayPracticeTwo.rollDice(6));
		System.out.println(LoopArrayPracticeTwo.rollDice(6));
		System.out.println(LoopArrayPracticeTwo.rollDice(6));
		System.out.println(LoopArrayPracticeTwo.rollDice(6));
		System.out.println(LoopArrayPracticeTwo.rollDice(6));
		System.out.println(LoopArrayPracticeTwo.rollDice(6));
		System.out.println(LoopArrayPracticeTwo.rollDice(6));
		System.out.println(LoopArrayPracticeTwo.rollDice(6));
		System.out.println("");

		System.out.println(LoopArrayPracticeTwo.luckyDice(6));
		System.out.println(LoopArrayPracticeTwo.luckyDice(6));
		System.out.println(LoopArrayPracticeTwo.luckyDice(6));
		System.out.println(LoopArrayPracticeTwo.luckyDice(6));
		System.out.println(LoopArrayPracticeTwo.luckyDice(6));
		System.out.println(LoopArrayPracticeTwo.luckyDice(6));
		System.out.println(LoopArrayPracticeTwo.luckyDice(6));
		System.out.println(LoopArrayPracticeTwo.luckyDice(6));
		System.out.println("");

		System.out.println(LoopArrayPracticeTwo.evenOrOdd(new int[] {1, 2, 3, 4, 5})); // expected false
		System.out.println(LoopArrayPracticeTwo.evenOrOdd(new int[] {6, 6, 6, 6, 6})); // expected true
		System.out.println(LoopArrayPracticeTwo.evenOrOdd(new int[] {1, 2})); // expected true + warning
		System.out.println(LoopArrayPracticeTwo.evenOrOdd(new int[] {})); // expected true + warning
		System.out.println("");

		System.out.println(Arrays.toString(LoopArrayPracticeTwo.nickName(new String[] {"October", "November", "December", "January"})));
		System.out.println(Arrays.toString(LoopArrayPracticeTwo.nickName(new String[] {"October", "November", "December", "January"})));
		System.out.println(Arrays.toString(LoopArrayPracticeTwo.nickName(new String[] {"October", "November", "December", "January"})));
		System.out.println(Arrays.toString(LoopArrayPracticeTwo.nickName(new String[] {"October", "November", "December", "January"})));
		System.out.println(Arrays.toString(LoopArrayPracticeTwo.nickName(new String[] {"October", "November", "December", "January"})));
		System.out.println("");

		System.out.println(LoopArrayPracticeTwo.isPalindrome("brick"));
		System.out.println(LoopArrayPracticeTwo.isPalindrome("racecar"));
		System.out.println(LoopArrayPracticeTwo.isPalindrome("aA"));
		System.out.println("");
	}
}







