package sort;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class HeapStoreTest {
	@Test
	public void testHeapStoreNoComparator() {
		HeapStore<Dummy> heapStore = new HeapStore<>();
		assertThrows(ClassCastException.class, () -> heapStore.insert(new Dummy(1)));
	}

	@Test
	public void testHeapStoreNoElements() {
		HeapStore<Dummy> heapStore = new HeapStore<>();
		assertThrows(NoSuchElementException.class, heapStore::extractFirst);
	}

	@Test
	public void testHeapStoreAutoSizeWork() {
		HeapStore<Dummy2> heapStore = new HeapStore<>(0);

		for (int i = 0; i < 1000; i++) {
			int insert = i;
			assertDoesNotThrow(() -> heapStore.insert(new Dummy2(insert)));
		}
	}

	@Test
	public void testHeapStoreGivenMinComparator() {
		HeapStore<Dummy> heapStore = new HeapStore<>((dummy1, dummy2) -> dummy2.id - dummy1.id);

		for (int i = 1000; i > 0; i--) {
			int insert = i;
			assertDoesNotThrow(() -> heapStore.insert(new Dummy(insert)));
		}

		assertEquals(1, heapStore.extractFirst().id);
	}

	@Test
	public void testHeapStoreGivenMaxComparator() {
		HeapStore<Dummy> heapStore = new HeapStore<>(Comparator.comparingInt(dummy -> dummy.id));

		for (int i = 0; i < 1000; i++) {
			int insert = i;
			assertDoesNotThrow(() -> heapStore.insert(new Dummy(insert)));
		}

		assertEquals(999, heapStore.extractFirst().id);
	}

	@Test
	public void testHeapStoreImplComparator() {
		HeapStore<Dummy2> heapStore = new HeapStore<>();

		assertDoesNotThrow(() -> heapStore.insert(new Dummy2(5)));
	}

	@Test
	public void testHeapStorePrioritizesGivenComparator() {
		Dummy2 dummy1 = new Dummy2(1, "AlJokes");
		Dummy2 dummy2 = new Dummy2(999, "Feed");

		HeapStore<Dummy2> heapStore1 = new HeapStore<>();
		HeapStore<Dummy2> heapStore2 = new HeapStore<>((d1, d2) -> d2.name.compareTo(d1.name));

		heapStore1.insert(dummy1);
		heapStore1.insert(dummy2);

		heapStore2.insert(dummy1);
		heapStore2.insert(dummy2);

		assertEquals(999, heapStore1.extractFirst().id);
		assertEquals(1, heapStore2.extractFirst().id);
	}

	private static class Dummy {
		public int id;

		public Dummy(int id) {
			this.id = id;
		}
	}

	private static class Dummy2 implements Comparable<Dummy2> {
		public int id;
		public String name;

		public Dummy2(int id) {
			this.id = id;
		}

		public Dummy2(int id, String name) {
			this.id = id;
			this.name = name;
		}

		@Override
		public int compareTo(Dummy2 other) {
			return this.id - other.id;
		}
	}
}
