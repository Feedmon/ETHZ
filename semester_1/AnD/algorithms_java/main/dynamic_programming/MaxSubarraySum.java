package dynamic_programming;

public class MaxSubarraySum {

	// max subarray sum
	public static int getMSS(int[] input) {
		int[] maxSums = calcRandMaxSums(input);

		return getMaxSum(maxSums);
	}

	// max almost sub sum (same as MSS but one index may be missing but not first or
	// last)
	public static int getMASS(int[] input) {
		int[] maxAlmMaxSums = new int[input.length];
		int[] randMaxSums = calcRandMaxSums(input);

		maxAlmMaxSums[2] = input[0] + input[2];

		for (int i = 3; i < input.length; i++) {
			maxAlmMaxSums[i] = Math.max(maxAlmMaxSums[i - 1] + input[i], randMaxSums[i - 2] + input[i]);
		}

		return getMaxSum(maxAlmMaxSums);
	}

	private static int[] calcRandMaxSums(int[] input) {
		int[] randMaxSums = new int[input.length];

		for (int i = 0; i < input.length; i++) {
			randMaxSums[i] = i == 0 ? input[i] : Math.max(randMaxSums[i - 1] + input[i], input[i]);
		}

		return randMaxSums;
	}

	private static int getMaxSum(int[] maxVals) {
		int maxSubSum = 0;

		for (int randSum : maxVals) {
			maxSubSum = Math.max(maxSubSum, randSum);
		}

		return maxSubSum;
	}
}
