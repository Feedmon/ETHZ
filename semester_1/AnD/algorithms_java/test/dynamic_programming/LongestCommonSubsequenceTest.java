package dynamic_programming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestCommonSubsequenceTest {
	@Test
	public void testLongestCommonSubsequence() {
		assertEquals(3, LongestCommonSubsequence.getLCS("Ziege", "Tiger"));
		assertEquals(3, LongestCommonSubsequence.getLCS("Tiger", "Ziege"));
		assertEquals(3, LongestCommonSubsequence.getLCS("Tiger", "BIBER"));
		assertEquals(3, LongestCommonSubsequence.getLCS("Ferkel", "Geifer"));
		assertEquals(5, LongestCommonSubsequence.getLCS("Luffy", "Fluffy"));
	}
}
