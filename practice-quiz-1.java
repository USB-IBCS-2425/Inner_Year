// Practice Quiz 1
// 2024 Oct 1


class PracticeQuiz {
	public static int rangeVals(int[] values) {
		int smallestInt = values[0];
		int largestInt = values[0];
		for (int i = 1; i < values.length; i++) {
			if (values[i] > largestInt) {
				largestInt = values[i];
			}
			if (values[i] < smallestInt) {
				smallestInt = values[i];
			}
		}
		return largestInt - smallestInt;
	}

	public static String smallWords(String[] stringArray) {
		String result = "";
		for (int i = 0; i < stringArray.length; i++) {
			if (stringArray[i].length() <= 4) {
				result += stringArray[i];
				result += " ";
			}
		}
		if (result.charAt(result.length() - 1) == ' ') {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	public static String randomSentence(String[] stringArray, int n) {
		String result = "";
		for (int i = 0; i < n; i++) {
			result += stringArray[(int) Math.floor(Math.random() * stringArray.length)];
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(PracticeQuiz.rangeVals(new int[] {3, 6, 7, 1, 10}));
		System.out.println(PracticeQuiz.smallWords(new String[] {"help", "quiz", "today", "or", "tomorrow"}));
		System.out.println(PracticeQuiz.randomSentence(new String[] {"I", "love", "CS", "and", "homework"}, 3));
		System.out.println(PracticeQuiz.randomSentence(new String[] {"I", "love", "CS", "and", "homework"}, 4));
	}
}







