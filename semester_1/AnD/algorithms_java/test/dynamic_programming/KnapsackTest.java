package dynamic_programming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class KnapsackTest {

	@Test
	public void testKnapsackMaxProfitWeight() {
		int[] weights = { 2, 3, 4 };
		int[] profits = { 3, 4, 5 };
		assertEquals(7, Knapsack.getMaxProfitWeight(weights, profits, 5));
		assertEquals(8, Knapsack.getMaxProfitWeight(weights, profits, 6));
		assertEquals(9, Knapsack.getMaxProfitWeight(weights, profits, 7));
		assertEquals(12, Knapsack.getMaxProfitWeight(weights, profits, 55));
		assertEquals(12, Knapsack.getMaxProfitWeight(new int[] { 0, 0, 0 }, profits, 0));
	}
	
	@Test
	public void testKnapsackMaxProfitWeightAndVolume() {
		int[] weights = { 2, 3, 4 };
		int[] volumes = { 1, 1, 1 };
		int[] profits = { 3, 4, 5 };

		assertEquals(7, Knapsack.getMaxProfitWeightAndVolume(weights, volumes, profits, 5, 3));
		assertEquals(8, Knapsack.getMaxProfitWeightAndVolume(weights, volumes, profits, 6, 3));
		assertEquals(9, Knapsack.getMaxProfitWeightAndVolume(weights, volumes, profits, 7, 3));
		assertEquals(12, Knapsack.getMaxProfitWeightAndVolume(weights, volumes, profits, 55, 55));
		assertEquals(12, Knapsack.getMaxProfitWeightAndVolume(new int[] { 0, 0, 0 }, volumes, profits, 0, 3));

		volumes = new int[] { 1, 2, 3 };

		assertEquals(7, Knapsack.getMaxProfitWeightAndVolume(weights, volumes, profits, 5, 3));
		assertEquals(7, Knapsack.getMaxProfitWeightAndVolume(weights, volumes, profits, 6, 3));
		assertEquals(3, Knapsack.getMaxProfitWeightAndVolume(weights, volumes, profits, 7, 1));
		assertEquals(4, Knapsack.getMaxProfitWeightAndVolume(weights, volumes, profits, 55, 2));
		assertEquals(8, Knapsack.getMaxProfitWeightAndVolume(weights, volumes, profits, 55, 4));
		assertEquals(9, Knapsack.getMaxProfitWeightAndVolume(weights, volumes, profits, 15, 5));
		assertEquals(12, Knapsack.getMaxProfitWeightAndVolume(weights, volumes, profits, 55, 55));
		assertEquals(12, Knapsack.getMaxProfitWeightAndVolume(weights, new int[] { 0, 0, 0 }, profits, 55, 0));
		assertEquals(12, Knapsack.getMaxProfitWeightAndVolume(new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 }, profits, 0, 0));
	}
}
