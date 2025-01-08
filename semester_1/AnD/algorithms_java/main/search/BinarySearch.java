package search;

public class BinarySearch {

	public static int binarySearch(int[] numbers, int wanted) {
		int leftBound = 0;
		int rightBound = numbers.length - 1;

		while (leftBound <= rightBound) {
			int middleIndex = Math.floorDiv(leftBound + rightBound, 2);

			if (numbers[middleIndex] == wanted) {
				return middleIndex;
			}

			if (wanted < numbers[middleIndex]) {
				rightBound = middleIndex - 1;
			} else {
				leftBound = middleIndex + 1;
			}
		}

		// not found
		return -1;
	}

}
