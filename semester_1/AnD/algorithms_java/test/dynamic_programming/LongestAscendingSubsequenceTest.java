package dynamic_programming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestAscendingSubsequenceTest {

	@Test
	public void testLongestAscSubsequence() {
		assertEquals(6, LongestAscendingSubsequence.getLAS(new int[] { 2, 13, 17, 9, 11, 4, 78, 28, 15, 25, 99 }));
		assertEquals(0, LongestAscendingSubsequence.getLAS(new int[] {}));
		assertEquals(9, LongestAscendingSubsequence.getLAS(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }));
		assertEquals(1, LongestAscendingSubsequence.getLAS(new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }));
		
		assertEquals(6,	LongestAscendingSubsequence.getBinSearchLAS(new int[] { 2, 13, 17, 9, 11, 4, 78, 28, 15, 25, 99 }));
		assertEquals(0, LongestAscendingSubsequence.getBinSearchLAS(new int[] {}));
		assertEquals(9, LongestAscendingSubsequence.getBinSearchLAS(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }));
		assertEquals(1,	LongestAscendingSubsequence.getBinSearchLAS(new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }));
		
	}
}
