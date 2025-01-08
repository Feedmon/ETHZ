package dynamic_programming;

public class LongestAscendingSubsequence {

	// n*n
	public static int getLAS(int[] input) {
		int[][] dp = new int[input.length + 1][input.length + 1];

		for (int row = 0; row <= input.length; row++) {
			for (int col = 1; col <= input.length; col++) {
				dp[row][col] = Integer.MAX_VALUE;
			}
		}

		for (int curNumber = 1; curNumber <= input.length; curNumber++) {
			int curValue = input[curNumber - 1];
			for (int sequenceLength = curNumber; sequenceLength > 0; sequenceLength--) {
				if (curValue > dp[curNumber - 1][sequenceLength - 1] && curValue < dp[curNumber - 1][sequenceLength]) {
					dp[curNumber][sequenceLength] = curValue;
				} else {
					dp[curNumber][sequenceLength] = dp[curNumber - 1][sequenceLength];
				}
			}
		}

		for (int i = 1; i <= input.length; i++) {
			if (dp[input.length][i] == Integer.MAX_VALUE) {
				return i - 1;
			}
		}

		return input.length;
	}

	// n log n
	public static int getBinSearchLAS(int[] input) {
		int[] dp = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			dp[i] = Integer.MAX_VALUE;
		}

		int longestAscSubsequence = 0;

		for (int i = 0; i < input.length; i++) {
			int index = getFirstIndexBiggerThanValue(dp, input[i], 0, i);
			longestAscSubsequence = Math.max(index + 1, longestAscSubsequence);
			dp[index] = input[i];
		}

		return longestAscSubsequence;
	}

	private static int getFirstIndexBiggerThanValue(int[] searchInput, int value, int lowerBound, int upperBound) {
		if (lowerBound == upperBound) {
			return lowerBound;
		}

		while (lowerBound < upperBound) {
			int middleIndex = Math.floorDiv(lowerBound + upperBound, 2);

			if (value > searchInput[middleIndex]) {
				lowerBound = middleIndex + 1;
			} else {
				upperBound = middleIndex;
			}
		}
		return lowerBound;
	}
}
