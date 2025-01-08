package dynamic_programming;

import java.util.ArrayList;
import java.util.Collections;

public class SubsetSum {

	// standard
	public static int[] getSubsetSum(int[] input, int targetSum) {
		return calcSubsetSum(input, targetSum);
	}

	// if we can use a given value twice
	public static int[] getSubSetSumWithDuplicates(int[] input, int targetSum) {
		int[] duplicateInput = new int[input.length * 2];

		for (int i = 0; i < input.length; i++) {
			duplicateInput[i] = input[i];
			duplicateInput[i + input.length] = input[i];
		}

		return calcSubsetSum(duplicateInput, targetSum);
	}

	private static int[] calcSubsetSum(int[] input, int targetSum) {
		boolean[][] dp = new boolean[input.length + 1][targetSum + 1];

		// sum 0 always reachable
		for (int row = 0; row <= input.length; row++) {
			dp[row][0] = true;
		}

		for (int row = 1; row <= input.length; row++) {
			int currentNum = input[row - 1];
			for (int col = 0; col <= targetSum; col++) {
				if (dp[row - 1][col]) {
					dp[row][col] = true;
					if (col + currentNum <= targetSum) {
						dp[row][col + currentNum] = true;
					}
				}
			}
		}

		if (!dp[input.length][targetSum]) {
			return null;
		}

		return backtraceUsedNums(dp, input, targetSum);
	}

	private static int[] backtraceUsedNums(boolean[][] dp, int[] input, int targetSum) {
		ArrayList<Integer> neededNums = new ArrayList<>();

		for (int row = input.length; row >= 0; row--) {
			if (targetSum == 0) {
				break;
			}

			if (!dp[row][targetSum]) {
				neededNums.add(input[row]);
				targetSum -= input[row];
			}
		}
		Collections.reverse(neededNums);
		return neededNums.stream().mapToInt(i -> i).toArray();
	}
}
