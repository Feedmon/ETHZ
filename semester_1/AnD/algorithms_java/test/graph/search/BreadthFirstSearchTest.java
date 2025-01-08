package graph.search;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BreadthFirstSearchTest {
	@Test
	public void testBFS() {
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<>(5);

		for (int i = 0; i < 5; i++) {
			adjList.add(new ArrayList<>());
		}

		adjList.get(0).add(1);
		adjList.get(0).add(4);
		adjList.get(0).add(2);
		adjList.get(1).add(3);
		adjList.get(1).add(0);
		adjList.get(2).add(3);
		adjList.get(3).add(2);
		adjList.get(4).add(1);

		Assertions.assertArrayEquals(new int[] { 0, 1, 1, 2, 1 }, BreadthFirstSearch.runBFS(adjList));
	}

	@Test
	public void testBFS2() {
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<>(5);

		for (int i = 0; i < 5; i++) {
			adjList.add(new ArrayList<>());
		}

		adjList.get(0).add(1);
		adjList.get(1).add(2);
		adjList.get(2).add(3);
		adjList.get(3).add(4);
		adjList.get(4).add(1);

		Assertions.assertArrayEquals(new int[] { 0, 1, 2, 3, 4 }, BreadthFirstSearch.runBFS(adjList));
	}

	@Test
	public void testBFS3() {
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<>(5);

		for (int i = 0; i < 5; i++) {
			adjList.add(new ArrayList<>());
		}

		adjList.get(0).add(1);
		adjList.get(1).add(2);
		adjList.get(2).add(3);
		adjList.get(4).add(1);

		Assertions.assertArrayEquals(new int[] { 0, 1, 2, 3, 0 }, BreadthFirstSearch.runBFS(adjList));
	}
}
