package dynamic_programming;

public class LongestCommonSubsequence {

	public static int getLCS(String input1, String input2) {
		input1 = input1.toUpperCase();
		input2 = input2.toUpperCase();

		int[][] dp = new int[input1.length() + 1][input2.length() + 1];

		for (int inp1 = 1; inp1 <= input1.length(); inp1++) {
			for (int inp2 = 1; inp2 <= input2.length(); inp2++) {
				int left = dp[inp1 - 1][inp2];
				int above = dp[inp1][inp2 - 1];
				int diagonal = increaseIfSame(dp[inp1 - 1][inp2 - 1], input1.charAt(inp1 - 1), input2.charAt(inp2 - 1));
				dp[inp1][inp2] = max(left, above, diagonal);
			}
		}

		return dp[input1.length()][input2.length()];
	}

	private static int increaseIfSame(int lcs, char inp1, char inp2) {
		return inp1 == inp2 ? lcs + 1 : lcs;
	}

	private static int max(int left, int above, int upperLeft) {
		return Math.max(left, Math.max(above, upperLeft));
	}
}