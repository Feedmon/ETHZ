package sort;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class HeapStore<T> {

	private final static int DEFAULT_INIT_SIZE = 15;
	private final static double DEFAULT_INCREMENT = 1.5;
	private T[] memory;
	private Comparator<? super T> comparator;
	private int size;

	public HeapStore() {
		this(DEFAULT_INIT_SIZE);
	}

	@SuppressWarnings("unchecked")
	public HeapStore(int initSize) {
		memory = (T[]) new Object[initSize];
		size = 0;
	}

	public HeapStore(Comparator<T> comparator) {
		this(comparator, DEFAULT_INIT_SIZE);
	}

	public HeapStore(Comparator<T> comparator, int initSize) {
		this(initSize);
		this.comparator = comparator;
	}

	public T extractFirst() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		T ret = memory[0];
		memory[0] = memory[--size];
		bubbleDown(0);
		return ret;
	}

	public void insert(T element) {
		if (size == 0) {
			checkComparingIsPossible(element);
		}
		if (size == memory.length) {
			increaseMemory();
		}
		memory[size++] = element;
		bubbleUp(size - 1);
	}

	private void bubbleDown(int index) {
		int lhsChildIndex = index * 2 + 1;
		int rhsChildIndex = index * 2 + 2;
		boolean smallerThanLhs = lhsChildIndex < size && compare(memory[index], memory[lhsChildIndex]) < 0;
		boolean smallerThanRhs = rhsChildIndex < size && compare(memory[index], memory[rhsChildIndex]) < 0;

		T tmp = memory[index];

		if (smallerThanLhs && smallerThanRhs) {
			if (compare(memory[lhsChildIndex], memory[rhsChildIndex]) > 0) {
				memory[index] = memory[lhsChildIndex];
				memory[lhsChildIndex] = tmp;
				bubbleDown(lhsChildIndex);
			} else {
				memory[index] = memory[rhsChildIndex];
				memory[rhsChildIndex] = tmp;
				bubbleDown(rhsChildIndex);
			}
		} else if (smallerThanRhs) {
			memory[index] = memory[rhsChildIndex];
			memory[rhsChildIndex] = tmp;
			bubbleDown(rhsChildIndex);
		} else if (smallerThanLhs) {
			memory[index] = memory[lhsChildIndex];
			memory[lhsChildIndex] = tmp;
			bubbleDown(lhsChildIndex);
		}
	}

	private void bubbleUp(int index) {
		if (index == 0) {
			return;
		}

		int parent = (index - 1) / 2;
		if (compare(memory[index], memory[parent]) > 0) {
			T tmp = memory[index];
			memory[index] = memory[parent];
			memory[parent] = tmp;
			bubbleUp(parent);
		}
	}

	@SuppressWarnings("unchecked")
	private void increaseMemory() {
		int newSize = (int) Math.ceil(DEFAULT_INCREMENT * memory.length + 1);
		T[] tmp = (T[]) new Object[newSize];
		for (int i = 0; i < size; i++) {
			tmp[i] = memory[i];
		}
		memory = tmp;
	}

	private void checkComparingIsPossible(T element) {
		compare(element, element);
	}

	@SuppressWarnings("unchecked")
	private int compare(T e1, T e2) {
		if (comparator == null) {
			return ((Comparable<? super T>) e1).compareTo(e2);
		}
		return comparator.compare(e1, e2);
	}
}
