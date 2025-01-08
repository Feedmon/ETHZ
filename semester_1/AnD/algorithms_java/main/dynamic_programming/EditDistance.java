package dynamic_programming;

public class EditDistance {

	public static int getED(String input1, String input2) {
		input1 = input1.toUpperCase();
		input2 = input2.toUpperCase();

		int[][] dp = new int[input1.length() + 1][input2.length() + 1];

		for (int inp1 = 1; inp1 < input1.length(); inp1++) {
			dp[inp1][0] = inp1;
		}

		for (int inp2 = 1; inp2 <= input2.length(); inp2++) {
			dp[0][inp2] = inp2;
		}

		for (int inp1 = 1; inp1 <= input1.length(); inp1++) {
			for (int inp2 = 1; inp2 <= input2.length(); inp2++) {
				int delete = dp[inp1 - 1][inp2] + 1;
				int add = dp[inp1][inp2 - 1] + 1;
				int useOrReplace = dp[inp1 - 1][inp2 - 1] + replace(input1.charAt(inp1 - 1), input2.charAt(inp2 - 1));
				dp[inp1][inp2] = min(delete, add, useOrReplace);
			}
		}

		return dp[input1.length()][input2.length()];
	}

	private static int replace(char left, char right) {
		return left == right ? 0 : 1;
	}

	private static int min(int delete, int add, int replace) {
		return Math.min(delete, Math.min(add, replace));
	}
}
