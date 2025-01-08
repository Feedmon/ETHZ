package dynamic_programming;

public class Fall2020ExamPalindrom {

	private static int[][] dp;

	public static int getLongestPalindromeSubsequence(char[] input, int charCount) {
		dp = new int[charCount][charCount];

		// bottom up is faster due recursion stack and other java technicalities
		return bottomUp(input);
		// return topDownWithMemoization(input, 0, charCount - 1);
	}

	private static int topDownWithMemoization(char[] input, int leftInd, int rightInd) {
		if (leftInd == rightInd) {
			return 1;
		}
		if (leftInd > rightInd) {
			return 0;
		}

		if (dp[leftInd][rightInd] != 0) {
			return dp[leftInd][rightInd];
		}

		if (input[leftInd] == input[rightInd]) {
			dp[leftInd][rightInd] = topDownWithMemoization(input, leftInd + 1, rightInd - 1) + 2;
			return dp[leftInd][rightInd];
		}

		dp[leftInd][rightInd] = Math.max(topDownWithMemoization(input, leftInd + 1, rightInd),
				topDownWithMemoization(input, leftInd, rightInd - 1));
		return dp[leftInd][rightInd];
	}

	private static int topDownNoMemoization(char[] input, int leftInd, int rightInd) {
		if (leftInd == rightInd) {
			return 1;
		}
		if (leftInd > rightInd) {
			return 0;
		}

		if (input[leftInd] == input[rightInd]) {
			return topDownNoMemoization(input, leftInd + 1, rightInd - 1) + 2;
		}

		return Math.max(topDownNoMemoization(input, leftInd + 1, rightInd),
				topDownNoMemoization(input, leftInd, rightInd - 1));
	}

	private static int bottomUp(char[] input) {
		int[][] dp = new int[input.length][input.length];

		for (int i = 0; i < input.length; i++) {
			dp[i][i] = 1;
		}

		// aka palindrom length - 1 => colOffset == 1 => palindrome length == 2
		for (int colOffset = 1; colOffset < input.length; colOffset++) {
			for (int row = 0; row < input.length - colOffset; row++) {
				int col = row + colOffset;

				if (input[row] == input[col]) {
					dp[row][col] = dp[row + 1][col - 1] + 2;
				} else {
					dp[row][col] = Math.max(dp[row + 1][col], dp[row][col - 1]);
				}
			}
		}

		return dp[0][input.length - 1];
	}
}
