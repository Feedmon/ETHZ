package dynamic_programming;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class EditDistanceTest {

	@Test
	public void testEditDistance() {
		assertEquals(3, EditDistance.getED("Ziege", "Tiger"));
		assertEquals(3, EditDistance.getED("Ziege", "Tiger"));
		assertEquals(2, EditDistance.getED("Biber", "Tiger"));
		assertEquals(4, EditDistance.getED("Ferkel", "Geifer"));
		assertEquals(1, EditDistance.getED("Luffy", "Fluffy"));
		assertEquals(3, EditDistance.getED("lfu", "Luffy"));
		assertEquals(0, EditDistance.getED("", ""));
	}
}
