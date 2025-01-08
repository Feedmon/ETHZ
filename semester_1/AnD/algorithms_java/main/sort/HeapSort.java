package sort;

// max heap
public class HeapSort {

	public static void inplaceHeapSort(int[] numbers) {
		int firstNonLeafNode = numbers.length / 2 - 1;
		// change array to max heap
		for (int curNodeIndex = firstNonLeafNode; curNodeIndex >= 0; curNodeIndex--) {
			heapify(numbers, curNodeIndex, numbers.length - 1);
		}

		// orderArray
		for (int firstNonHeapIndex = numbers.length - 1; firstNonHeapIndex > 0; firstNonHeapIndex--) {

			// max element at end of array
			int tmp = numbers[firstNonHeapIndex];
			numbers[firstNonHeapIndex] = numbers[0];
			numbers[0] = tmp;

			// fix heap condition
			heapify(numbers, 0, firstNonHeapIndex - 1);
		}
    }

	// bubble down
	private static void heapify(int[] numbers, int startIndex, int heapEndIndex) {
		int leftChildIndex = startIndex * 2 + 1;
		int rightChildIndex = startIndex * 2 + 2;
		while (leftChildIndex <= heapEndIndex) {
			int swapIndex = leftChildIndex;

			if (rightChildIndex <= heapEndIndex && numbers[leftChildIndex] < numbers[rightChildIndex]) {
				swapIndex = rightChildIndex;
			}

			if (numbers[startIndex] >= numbers[swapIndex]) {
				return;
			}

			int tmp = numbers[startIndex];
			numbers[startIndex] = numbers[swapIndex];
			numbers[swapIndex] = tmp;

			startIndex = swapIndex;
			leftChildIndex = swapIndex * 2 + 1;
			rightChildIndex = swapIndex * 2 + 2;
		}
    }
}
