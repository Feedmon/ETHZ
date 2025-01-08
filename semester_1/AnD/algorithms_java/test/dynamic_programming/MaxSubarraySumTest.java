package dynamic_programming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxSubarraySumTest {

	@Test
	public void testMaxSubarraySum() {
		assertEquals(6, MaxSubarraySum.getMSS(new int[] { 3, 2, -2, 1, -2, -3, 4, 1, -3, 4 }));
		assertEquals(0, MaxSubarraySum.getMSS(new int[] { -3, -2, -2, -1, -2, -3, -4, -1, -3, -4 }));
		assertEquals(3, MaxSubarraySum.getMSS(new int[] { -3, -2, 3, -1, -2, 1, 2, -1, -3, 2 }));
		assertEquals(5, MaxSubarraySum.getMSS(new int[] { 3, 2, -2 }));
		assertEquals(2, MaxSubarraySum.getMSS(new int[] { -1, -1, 2 }));
	}

	@Test
	public void testMaxAlmostSubarraySum() {
		assertEquals(9, MaxSubarraySum.getMASS(new int[] { 3, 2, -2, 1, -2, -3, 4, 1, -3, 4 }));
		assertEquals(0, MaxSubarraySum.getMASS(new int[] { -3, -2, -2, -1, -2, -3, -4, -1, -3, -4 }));
		assertEquals(5, MaxSubarraySum.getMASS(new int[] { -3, -2, 3, -1, -2, 1, 2, -1, -3, 2 }));
		assertEquals(1, MaxSubarraySum.getMASS(new int[] { 3, 2, -2 }));
		assertEquals(1, MaxSubarraySum.getMASS(new int[] { -1, -1, 2 }));
	}
}
