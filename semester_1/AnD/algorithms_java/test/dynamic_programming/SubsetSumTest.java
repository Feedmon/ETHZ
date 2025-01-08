package dynamic_programming;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SubsetSumTest {

	@Test
	public void testSubsetSum() {
        assertNull(SubsetSum.getSubsetSum(new int[]{5, 3, 10, 7, 3, 1}, 2));
        assertNull(SubsetSum.getSubsetSum(new int[]{5, 3, 10, 7, 3, 1}, 27));
        assertNull(SubsetSum.getSubsetSum(new int[]{5, 3, 10, 7, 3, 1}, 30));
		Assertions.assertArrayEquals(new int[] { 5, 3, 10, 7, 3, 1 }, SubsetSum.getSubsetSum(new int[] { 5, 3, 10, 7, 3, 1 }, 29));
		Assertions.assertArrayEquals(new int[] { 5, 3, 1 }, SubsetSum.getSubsetSum(new int[] { 5, 3, 10, 7, 3, 1 }, 9));
		Assertions.assertArrayEquals(new int[] { 1, 7, 1 }, SubsetSum.getSubsetSum(new int[] { 1, 3, 10, 7, 3, 1 }, 9));
		Assertions.assertArrayEquals(new int[] {}, SubsetSum.getSubsetSum(new int[] { 1, 3, 10, 7, 3, 1 }, 0));
	}

	@Test
	public void testSubsetSumWithDuplicates() {
        assertNull(SubsetSum.getSubSetSumWithDuplicates(new int[]{3, 4, 2, 7}, 35));
		Assertions.assertArrayEquals(new int[] { 3, 2, 7, 2, 7 }, SubsetSum.getSubSetSumWithDuplicates(new int[] { 3, 0, 2, 7 }, 21));
		Assertions.assertArrayEquals(new int[] {}, SubsetSum.getSubsetSum(new int[] { 1, 3, 10, 7, 3, 1 }, 0));
	}
}
