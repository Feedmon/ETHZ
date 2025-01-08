package dynamic_programming;

public class Knapsack {

	public static int getMaxProfitWeight(int[] weights, int[] profits, int weightLimit) {
		int[][] dp = new int[weights.length + 1][weightLimit + 1];

		for (int allowedItems = 1; allowedItems <= weights.length; allowedItems++) {
			int curItemWeight = weights[allowedItems - 1];
			int curItemProfit = profits[allowedItems - 1];

			for (int allowedWeight = 0; allowedWeight <= weightLimit; allowedWeight++) {
				if (curItemWeight <= allowedWeight) {
					int maxProfitWithoutCurItem = dp[allowedItems - 1][allowedWeight];
					int maxProfitWithCurItem = dp[allowedItems - 1][allowedWeight - curItemWeight] + curItemProfit;
					dp[allowedItems][allowedWeight] = Math.max(maxProfitWithCurItem, maxProfitWithoutCurItem);
				} else {
					dp[allowedItems][allowedWeight] = dp[allowedItems - 1][allowedWeight];
				}
			}
		}

		return dp[weights.length][weightLimit];
	}

	public static int getMaxProfitWeightAndVolume(int[] weights, int[] volumes, int[] profits, int weightLimit,
			int volumeLimit) {
		int[][][] dp = new int[weights.length + 1][weightLimit + 1][volumeLimit + 1];

		for (int allowedItems = 1; allowedItems <= weights.length; allowedItems++) {
			int curItemWeight = weights[allowedItems - 1];
			int curItemVolume = volumes[allowedItems - 1];
			int curItemProfit = profits[allowedItems - 1];

			for (int allowedWeight = 0; allowedWeight <= weightLimit; allowedWeight++) {
				for (int allowedVolume = 0; allowedVolume <= volumeLimit; allowedVolume++) {
					if (curItemWeight <= allowedWeight && curItemVolume <= allowedVolume) {
						int maxProfitWithoutCurItem = dp[allowedItems - 1][allowedWeight][allowedVolume];
						int maxProfitWithCurItem = dp[allowedItems - 1][allowedWeight - curItemWeight][allowedVolume - curItemVolume] + curItemProfit;
						dp[allowedItems][allowedWeight][allowedVolume] = Math.max(maxProfitWithCurItem, maxProfitWithoutCurItem);
					} else {
						dp[allowedItems][allowedWeight][allowedVolume] = dp[allowedItems- 1][allowedWeight][allowedVolume];
					}

				}
			}
		}

		return dp[weights.length][weightLimit][volumeLimit];
	}

}
