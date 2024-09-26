// Java Loops and Array Problem Set 2024.09.25

import java.lang.Math;
import java.util.Arrays; //used only for easy printing of arrays as strings (lines 104-105, 108-110)

class LoopArrayPractice {
	public static int middleValue(int[] integerArray) {
		return integerArray[(int) Math.ceil((float) integerArray.length / 2.0) - 1];
	}

	public static int numMatches(String[] stringArray, String stringQuery) {
		int queryAppearanceCount = 0;
		for (int i = 0; i < stringArray.length; i++) {
			if (stringArray[i] == stringQuery) {
				queryAppearanceCount++;
			}
		}
		return queryAppearanceCount;
	}

	public static boolean inOrder(int[] integerArray) {
		return (LoopArrayPractice.inAscendingOrder(integerArray) || LoopArrayPractice.inDescendingOrder(integerArray));
	}

	private static boolean inAscendingOrder(int[] integerArray) {
		for (int i = 1; i < integerArray.length; i++) {
			if (integerArray[i] < integerArray[i - 1]) {
				return false;
			}
		}
		return true;
	}

	private static boolean inDescendingOrder(int[] integerArray) {
		for (int i = 1; i < integerArray.length; i++) {
			if (integerArray[i] > integerArray[i - 1]) {
				return false;
			}
		}
		return true;
	}

	public static boolean[] doubleLetter(String[] stringArray) {
		boolean[] resultArray = new boolean[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			resultArray[i] = LoopArrayPractice.hasDoubleLetter(stringArray[i]);
		}
		return resultArray;
	}

	private static boolean hasDoubleLetter(String stringQuery) {
		for (int i = 1; i < stringQuery.length(); i++) {
			if (stringQuery.charAt(i) == stringQuery.charAt(i - 1)) {
				return true;
			}
		}
		return false;
	}

	public static String[] bookEnds(String[] stringArray) {
		// finding number of valid strings since arrays have to have set lengths
		int validStrings = 0;
		for (int i = 0; i < stringArray.length; i++) {
			if (LoopArrayPractice.hasBookEnds(stringArray[i])) {
				validStrings++;
			}
		}
		String[] returnArray = new String[validStrings];
		int j = 0;
		for (int i = 0; i < stringArray.length; i++) {
			if (LoopArrayPractice.hasBookEnds(stringArray[i])) {
				returnArray[j] = stringArray[i];
				j++;
			}
		}
		return returnArray;
	}

	private static boolean hasBookEnds(String stringQuery) {
		return stringQuery.charAt(0) == stringQuery.charAt(stringQuery.length() - 1);
	}

	public static void main(String[] args) {
		System.out.println(LoopArrayPractice.middleValue(new int[] {4, 9, 1, 25, 6})); // expected 1
		System.out.println(LoopArrayPractice.middleValue(new int[] {4, 9, 25, 6, 3, 3})); // expected 25
		System.out.println(LoopArrayPractice.middleValue(new int[] {2})); // expected 2
		System.out.println(LoopArrayPractice.middleValue(new int[] {10, 11})); // expected 10
		System.out.println("");

		System.out.println(LoopArrayPractice.numMatches(new String[] {"dar", "foo", "baz"}, "bar")); // expected 0
		System.out.println(LoopArrayPractice.numMatches(new String[] {"akjrgalibg", "ebeb", "Ebeb", "bebeb"}, "ebeb")); // expected 1
		System.out.println(LoopArrayPractice.numMatches(new String[] {}, "j")); // expected 0
		System.out.println(LoopArrayPractice.numMatches(new String[] {"da", "daa", "dar", "DDAR", "dar", "dar", "ar", "dar"}, "dar")); // expected 4
		System.out.println(LoopArrayPractice.numMatches(new String[] {"foo", "bar", "bar", "foo", "baz", "qux"}, "foo")); // expected 2
		System.out.println("");

		System.out.println(LoopArrayPractice.inOrder(new int[] {1, 3, 4, 9, 103, 103, 200})); // expected true
		System.out.println(LoopArrayPractice.inOrder(new int[] {8, 9, 8})); // expected false
		System.out.println(LoopArrayPractice.inOrder(new int[] {9, 9, 9, 4, 1})); // expected true
		System.out.println(LoopArrayPractice.inOrder(new int[] {})); // expected true
		System.out.println(LoopArrayPractice.inOrder(new int[] {4})); // expected true
		System.out.println("");

		System.out.println(Arrays.toString(LoopArrayPractice.doubleLetter(new String[] {"butter", "bab", "Bb", "bb", "abcdeff"}))); // expected {true, false, false, true, true}
		System.out.println(Arrays.toString(LoopArrayPractice.doubleLetter(new String[] {}))); // expected {}
		System.out.println("");

		System.out.println(Arrays.toString(LoopArrayPractice.bookEnds(new String[] {"b", "aar", "saseifuhaai", "reader", "gh"}))); // expected {"b", "reader"}
		System.out.println(Arrays.toString(LoopArrayPractice.bookEnds(new String[] {}))); // expected {}
		System.out.println(Arrays.toString(LoopArrayPractice.bookEnds(new String[] {"b"}))); // expected {"b"}
		System.out.println("");
	}
}







