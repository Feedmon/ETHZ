package sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class SortTest {

	@ParameterizedTest
	@MethodSource("provideIntArraysToSort")
	public void testMergeSort(int[] inputArray) {
		int[] sortedArray = Arrays.copyOf(inputArray, inputArray.length);
		Arrays.sort(sortedArray);

		MergeSort.mergeSort(inputArray);

        Assertions.assertArrayEquals(sortedArray, inputArray);
	}

	@ParameterizedTest
	@MethodSource("provideIntArraysToSort")
	public void testHeapSortMax(int[] input) {
		Integer[] unsortedArray = Arrays.stream(input).boxed().toArray(Integer[]::new);
		Integer[] sortedArray = Arrays.copyOf(unsortedArray, unsortedArray.length);
		Arrays.sort(sortedArray);

		sortHeap(new HeapStore<>(unsortedArray.length), unsortedArray);

        Assertions.assertArrayEquals(sortedArray, unsortedArray);
	}

	@ParameterizedTest
	@MethodSource("provideIntArraysToSort")
	public void testHeapSortMin(int[] input) {
		Integer[] unsortedArray = Arrays.stream(input).boxed().toArray(Integer[]::new);
		Integer[] sortedArray = Arrays.copyOf(unsortedArray, unsortedArray.length);
		Arrays.sort(sortedArray, Comparator.reverseOrder());

		sortHeap(new HeapStore<Integer>(Comparator.reverseOrder(), unsortedArray.length), unsortedArray);

        Assertions.assertArrayEquals(sortedArray, unsortedArray);
	}

	private void sortHeap(HeapStore<Integer> heapStore, Integer[] input) {
		for (Integer inp : input) {
			heapStore.insert(inp);
		}

		for (int i = input.length - 1; i >= 0; i--) {
			input[i] = heapStore.extractFirst();
		}
	}

	@ParameterizedTest
	@MethodSource("provideIntArraysToSort")
	public void testInplaceHeapSort(int[] inputArray) {
		int[] sortedArray = Arrays.copyOf(inputArray, inputArray.length);
		Arrays.sort(sortedArray);

		HeapSort.inplaceHeapSort(inputArray);

        Assertions.assertArrayEquals(sortedArray, inputArray);
	}


	private static Stream<int[]> provideIntArraysToSort() {
		return Stream.of(new int[] { 1, 2, 3, 4, 5 }, new int[] { 5, 4, 3, 2, 1 }, new int[] { 3, 1, 4, 1, 5, 9 },
				new int[] { -3, -1, -4, -1, -5, -9 }, new int[] { 0, 0, 0, 0 }, new int[] { 100, -50, 0, 50, -100 },
				new int[] {}, new int[] { 42 }, new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE }, new int[] { 2, 1 },
				new int[] { -1000, 1000, -1000, 1000 }, new int[] { Integer.MAX_VALUE, 0, Integer.MIN_VALUE },
				new int[] { 1, 1, 1, 2, 2, 2, 3, 3, 3 }, new int[] { 3, 3, 3, 2, 2, 2, 3, 1, 3, 1, 1 });
	}
}
