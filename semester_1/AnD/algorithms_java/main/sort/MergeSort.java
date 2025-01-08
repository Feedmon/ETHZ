package sort;

public class MergeSort {

	public static void mergeSort(int[] numbers) {
		mergeSort(numbers, 0, numbers.length - 1);
	}

	private static void mergeSort(int[] numbers, int fromIndex, int toIndex) {
		// base case fromIndex is > if and only if array is empty else it's equal
		if (fromIndex >= toIndex) {
			return;
		}

		int middleIndex = (fromIndex + toIndex) / 2;

		mergeSort(numbers, fromIndex, middleIndex);
		mergeSort(numbers, middleIndex + 1, toIndex);

		int[] sortedArray = merge(numbers, fromIndex, middleIndex, toIndex);

		for (int curIndex = fromIndex; curIndex <= toIndex; curIndex++) {
			numbers[curIndex] = sortedArray[curIndex - fromIndex];
		}

	}

	private static int[] merge(int[] numbers, int leftIndex, int middleIndex, int rightIndex) {
		int[] helperArray = new int[rightIndex - leftIndex + 1];
		int nextUnusedLhsElement = leftIndex;
		int nextUnusedRhsElement = middleIndex + 1;
		int nextHelperArrayIndex = 0;

		while (nextUnusedLhsElement <= middleIndex && nextUnusedRhsElement <= rightIndex) {
			if (numbers[nextUnusedLhsElement] < numbers[nextUnusedRhsElement]) {
				helperArray[nextHelperArrayIndex] = numbers[nextUnusedLhsElement];
				nextUnusedLhsElement++;
				nextHelperArrayIndex++;
			} else {
				helperArray[nextHelperArrayIndex] = numbers[nextUnusedRhsElement];
				nextUnusedRhsElement++;
				nextHelperArrayIndex++;
			}
		}

		// if left has any left
		while (nextUnusedLhsElement <= middleIndex) {
			helperArray[nextHelperArrayIndex] = numbers[nextUnusedLhsElement];
			nextUnusedLhsElement++;
			nextHelperArrayIndex++;
		}

		// if right has any left
		while (nextUnusedRhsElement <= rightIndex) {
			helperArray[nextHelperArrayIndex] = numbers[nextUnusedRhsElement];
			nextUnusedRhsElement++;
			nextHelperArrayIndex++;
		}

		return helperArray;
	}
}
