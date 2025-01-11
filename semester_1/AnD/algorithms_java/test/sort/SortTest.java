package sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortTest {

	@Test
	void testAlreadySorted() {
		int[] arr = {1, 2, 3, 4, 5};
		MergeSort.mergeSort(arr);
		assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
	}

	@Test
	void testReverseSorted() {
		int[] arr = {5, 4, 3, 2, 1};
		MergeSort.mergeSort(arr);
		assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
	}

	@Test
	void testWithDuplicates() {
		int[] arr = {2, 2, 2, 1, 1, 5, 5, 5};
		MergeSort.mergeSort(arr);
		assertArrayEquals(new int[]{1, 1, 2, 2, 2, 5, 5, 5}, arr);
	}

	@Test
	void testEmptyArray() {
		int[] arr = {};
		MergeSort.mergeSort(arr);
		assertArrayEquals(new int[]{}, arr);
	}

	@Test
	void testSingleElement() {
		int[] arr = {10};
		MergeSort.mergeSort(arr);
		assertArrayEquals(new int[]{10}, arr);
	}

	@Test
	void testRandomArray() {
		Random rand = new Random();
		int[] arr = new int[10];
		for (int i = 0; i < 10; i++) {
			arr[i] = rand.nextInt(100);
		}

		int[] expected = Arrays.copyOf(arr, arr.length);

		Arrays.sort(expected);

		MergeSort.mergeSort(arr);

		assertArrayEquals(expected, arr);
	}

	@ParameterizedTest
	@MethodSource("provideIntArraysToSort")
	public void testMergeSort(int[] inputArray) {
		int[] sortedArray = Arrays.copyOf(inputArray, inputArray.length);
		Arrays.sort(sortedArray);

		MergeSort.mergeSort(inputArray);

        assertArrayEquals(sortedArray, inputArray);
	}

	@ParameterizedTest
	@MethodSource("provideIntArraysToSort")
	public void testHeapSortMax(int[] input) {
		Integer[] unsortedArray = Arrays.stream(input).boxed().toArray(Integer[]::new);
		Integer[] sortedArray = Arrays.copyOf(unsortedArray, unsortedArray.length);
		Arrays.sort(sortedArray);

		sortHeap(new HeapStore<>(unsortedArray.length), unsortedArray);

        assertArrayEquals(sortedArray, unsortedArray);
	}

	@ParameterizedTest
	@MethodSource("provideIntArraysToSort")
	public void testHeapSortMin(int[] input) {
		Integer[] unsortedArray = Arrays.stream(input).boxed().toArray(Integer[]::new);
		Integer[] sortedArray = Arrays.copyOf(unsortedArray, unsortedArray.length);
		Arrays.sort(sortedArray, Comparator.reverseOrder());

		sortHeap(new HeapStore<Integer>(Comparator.reverseOrder(), unsortedArray.length), unsortedArray);

        assertArrayEquals(sortedArray, unsortedArray);
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

        assertArrayEquals(sortedArray, inputArray);
	}


	private static Stream<int[]> provideIntArraysToSort() {
		return Stream.of(new int[] { 1, 2, 3, 4, 5 }, new int[] { 5, 4, 3, 2, 1 }, new int[] { 3, 1, 4, 1, 5, 9 },
				new int[] { -3, -1, -4, -1, -5, -9 }, new int[] { 0, 0, 0, 0 }, new int[] { 100, -50, 0, 50, -100 },
				new int[] {}, new int[] { 42 }, new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE }, new int[] { 2, 1 },
				new int[] { -1000, 1000, -1000, 1000 }, new int[] { Integer.MAX_VALUE, 0, Integer.MIN_VALUE },
				new int[] { 1, 1, 1, 2, 2, 2, 3, 3, 3 }, new int[] { 3, 3, 3, 2, 2, 2, 3, 1, 3, 1, 1 });
	}
}
