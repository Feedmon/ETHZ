package search;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BinarySearchTest {

	@ParameterizedTest
	@MethodSource("provideBinarySearch")
	public void testBinarySearch(int[] sortedArray, int wanted, int expectedIndex) {
		int index = BinarySearch.binarySearch(sortedArray, wanted);

		assertEquals(expectedIndex, index);
	}
	
	private static Stream<Arguments> provideBinarySearch() {
	    return Stream.of(
	        Arguments.of(new int[]{1, 2, 3, 4, 5}, 3, 2),
	        Arguments.of(new int[]{1, 2, 3, 4, 5}, 1, 0),
	        Arguments.of(new int[]{1, 2, 3, 4, 5}, 5, 4),
	        Arguments.of(new int[]{1, 2, 3, 4, 5}, 9, -1),
	        Arguments.of(new int[]{-11, -2, 3, 4, 5}, 2, -1),
	        Arguments.of(new int[]{-11, -2, 3, 4, 5}, -2, 1),
	        Arguments.of(new int[]{-11, -2, 3, 4, 5}, 3, 2)
	    );
	}
}
