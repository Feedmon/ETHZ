package graph.search;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BreadthFirstSearchTest {

	private ArrayList<ArrayList<Integer>> adjList;

	@BeforeEach
	void setup(){
		adjList = new ArrayList<>(5);

		for (int i = 0; i < 5; i++) {
			adjList.add(new ArrayList<>());
		}
	}

	@Test
	void testSimpleChain() {
		adjList.get(0).add(1);
		adjList.get(1).add(2);
		adjList.get(2).add(3);

		int[] dist = BreadthFirstSearch.runBFS(adjList);

		assertEquals(0, dist[0]);
		assertEquals(1, dist[1]);
		assertEquals(2, dist[2]);
		assertEquals(3, dist[3]);
		assertEquals(-1, dist[4]);
	}

	@Test
	void testStarShapedGraph() {
		adjList.get(0).add(1);
		adjList.get(0).add(2);
		adjList.get(0).add(3);
		adjList.get(0).add(4);

		int[] dist = BreadthFirstSearch.runBFS(adjList);

		assertEquals(0, dist[0]);
		assertEquals(1, dist[1]);
		assertEquals(1, dist[2]);
		assertEquals(1, dist[3]);
		assertEquals(1, dist[4]);
	}

	@Test
	void testDisconnectedGraph() {
		adjList.get(0).add(1);
		adjList.get(2).add(3);

		int[] dist = BreadthFirstSearch.runBFS(adjList);

		assertEquals(0, dist[0]);
		assertEquals(1, dist[1]);
		assertEquals(-1, dist[2]);
		assertEquals(-1, dist[3]);
		assertEquals(-1, dist[4]);
	}

	@Test
	void testUndirectedShortestPath() {
		adjList.get(0).add(1);
		adjList.get(1).add(0);
		adjList.get(1).add(2);
		adjList.get(2).add(1);

		int[] dist = BreadthFirstSearch.runBFS(adjList);

		assertEquals(0, dist[0]);
		assertEquals(1, dist[1]);
		assertEquals(2, dist[2]);
		assertEquals(-1, dist[3]);
		assertEquals(-1, dist[4]);
	}
	@Test
	public void testBFS() {
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
		adjList.get(0).add(1);
		adjList.get(1).add(2);
		adjList.get(2).add(3);
		adjList.get(3).add(4);
		adjList.get(4).add(1);

		Assertions.assertArrayEquals(new int[] { 0, 1, 2, 3, 4 }, BreadthFirstSearch.runBFS(adjList));
	}

	@Test
	public void testBFS3() {
		adjList.get(0).add(1);
		adjList.get(1).add(2);
		adjList.get(2).add(3);
		adjList.get(4).add(1);

		Assertions.assertArrayEquals(new int[] { 0, 1, 2, 3, -1 }, BreadthFirstSearch.runBFS(adjList));
	}
}
