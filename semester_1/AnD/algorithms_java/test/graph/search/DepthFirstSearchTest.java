package graph.search;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class DepthFirstSearchTest {

	@Test
	public void testDFS() {
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<>(5);

		for (int i = 0; i < 5; i++) {
			adjList.add(new ArrayList<>());
		}

		adjList.get(0).add(1);
		adjList.get(0).add(2);
		adjList.get(1).add(3);
		adjList.get(1).add(0);
		adjList.get(2).add(3);
		adjList.get(3).add(2);
		adjList.get(4).add(1);

		DepthFirstSearch.runDFS(adjList);
	}
}
