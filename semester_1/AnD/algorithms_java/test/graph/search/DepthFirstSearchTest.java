package graph.search;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DepthFirstSearchTest {
	private ArrayList<ArrayList<Integer>> adjList;

	@Test
	void testEmptyGraph() {
		initAdjList(5);

		ArrayList<Integer> result = DepthFirstSearch.runDFS(adjList);

		assertEquals(5, result.size());
		assertTrue(isPermutationOf(result, 5));
	}

	@Test
	void testSimpleAcyclicGraph() {
		initAdjList(4);

		adjList.get(0).add(1);
		adjList.get(1).add(2);
		adjList.get(2).add(3);

		ArrayList<Integer> result = DepthFirstSearch.runDFS(adjList);

		assertEquals(4, result.size());
		assertIterableEquals(List.of(0, 1, 2, 3), result);
	}

	@Test
	void testMultipleValidOrders() {
		initAdjList(3);

		adjList.get(0).add(2);
		adjList.get(1).add(2);

		ArrayList<Integer> result = DepthFirstSearch.runDFS(adjList);

		assertEquals(3, result.size());

		int index0 = result.indexOf(0);
		int index1 = result.indexOf(1);
		int index2 = result.indexOf(2);

		assertTrue(index2 > index0);
		assertTrue(index2 > index1);
	}

	@Test
	void testComplexGraph() {
		initAdjList(6);

		adjList.get(0).add(1);
		adjList.get(0).add(2);
		adjList.get(1).add(3);
		adjList.get(2).add(3);
		adjList.get(1).add(4);
		adjList.get(3).add(5);

		ArrayList<Integer> result = DepthFirstSearch.runDFS(adjList);

		assertEquals(6, result.size());

		checkOrderConstraints(result, 0, 1);
		checkOrderConstraints(result, 0, 2);
		checkOrderConstraints(result, 1, 3);
		checkOrderConstraints(result, 2, 3);
		checkOrderConstraints(result, 1, 4);
		checkOrderConstraints(result, 3, 5);
	}

	@Test
	public void testDFS() {
		initAdjList(5);

		adjList.get(0).add(1);
		adjList.get(0).add(2);
		adjList.get(1).add(3);
		adjList.get(1).add(0);
		adjList.get(2).add(3);
		adjList.get(3).add(2);
		adjList.get(4).add(1);

		ArrayList<Integer> result = DepthFirstSearch.runDFS(adjList);

		assertIterableEquals(List.of(4, 0, 1, 3, 2), result);
	}

	private boolean isPermutationOf(ArrayList<Integer> list, int n) {
		if (list.size() != n) {
			return false;
		}
		boolean[] seen = new boolean[n];
		for (Integer val : list) {
			if (val < 0 || val >= n || seen[val]) {
				return false;
			}
			seen[val] = true;
		}
		return true;
	}

	private void checkOrderConstraints(List<Integer> topologicalOrder, int before, int after) {
		assertTrue(
				topologicalOrder.indexOf(before) < topologicalOrder.indexOf(after),
				() -> String.format("Expected %d to come before %d in topological order", before, after)
		);
	}

	private void initAdjList(int size){
		adjList = new ArrayList<>();

		for (int i = 0; i < size; i++) {
			adjList.add(new ArrayList<>());
		}
	}
}
