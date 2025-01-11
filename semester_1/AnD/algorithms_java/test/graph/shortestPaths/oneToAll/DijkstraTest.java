package graph.shortestPaths.oneToAll;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import graph.util.Edge;

public class DijkstraTest {

	@Test
	void testLineGraph() {
		ArrayList<ArrayList<Edge>> adjList = new ArrayList<>(4);

		for (int i = 0; i < 4; i++) {
			adjList.add(new ArrayList<>());
		}

		adjList.get(0).add(new Edge(0,1,1));
		adjList.get(1).add(new Edge(1,2,1));
		adjList.get(2).add(new Edge(2,3,1));

		assertEquals(0, Dijkstra.getMinDistance(adjList,0,0));
		assertEquals(1, Dijkstra.getMinDistance(adjList,0,1));
		assertEquals(2, Dijkstra.getMinDistance(adjList,0,2));
		assertEquals(3, Dijkstra.getMinDistance(adjList,0,3));
	}

	@Test
	void testStarGraph() {
		ArrayList<ArrayList<Edge>> adjList = new ArrayList<>(5);

		for (int i = 0; i < 5; i++) {
			adjList.add(new ArrayList<>());
		}

		adjList.get(0).add(new Edge(0,1,10));
		adjList.get(0).add(new Edge(0,2,20));
		adjList.get(0).add(new Edge(0,3,5));

		assertEquals(0, Dijkstra.getMinDistance(adjList,0,0));
		assertEquals(10, Dijkstra.getMinDistance(adjList,0,1));
		assertEquals(20, Dijkstra.getMinDistance(adjList,0,2));
		assertEquals(5, Dijkstra.getMinDistance(adjList,0,3));
		assertEquals(Integer.MAX_VALUE, Dijkstra.getMinDistance(adjList,0,4));
	}

	@Test
	void testMoreComplexGraph() {
		ArrayList<ArrayList<Edge>> adjList = new ArrayList<>(4);

		for (int i = 0; i < 4; i++) {
			adjList.add(new ArrayList<>());
		}

		adjList.get(0).add(new Edge(0,1,1));
		adjList.get(0).add(new Edge(0,2,4));
		adjList.get(1).add(new Edge(1,2,2));
		adjList.get(1).add(new Edge(1,3,5));
		adjList.get(2).add(new Edge(2,3,1));

		assertEquals(0, Dijkstra.getMinDistance(adjList,0,0));
		assertEquals(1, Dijkstra.getMinDistance(adjList,0,1));
		assertEquals(3, Dijkstra.getMinDistance(adjList,0,2));
		assertEquals(4, Dijkstra.getMinDistance(adjList,0,3));
	}

	@Test
	void testUndirectedUsage() {
		ArrayList<ArrayList<Edge>> adjList = new ArrayList<>(3);

		for (int i = 0; i < 3; i++) {
			adjList.add(new ArrayList<>());
		}

		adjList.get(0).add(new Edge(0,1,1));
		adjList.get(1).add(new Edge(1,0,1));
		adjList.get(1).add(new Edge(1,2,2));
		adjList.get(2).add(new Edge(2,1,2));

		assertEquals(0, Dijkstra.getMinDistance(adjList,0,0));
		assertEquals(1, Dijkstra.getMinDistance(adjList,0,1));
		assertEquals(3, Dijkstra.getMinDistance(adjList,0,2));
	}


	@ParameterizedTest
	@MethodSource("provideMinTime")
	public void testMinDistance(int nodeCount, int[] fromNodes, int[] toNodes, int[] edgeWeights, int expectedTime) {
		ArrayList<ArrayList<Edge>> adjList = new ArrayList<>(nodeCount);

		for (int i = 0; i < nodeCount; i++) {
			adjList.add(new ArrayList<>());
		}

		for (int i = 0; i < edgeWeights.length; i++) {
			Edge edge = new Edge(fromNodes[i], toNodes[i], edgeWeights[i]);
			adjList.get(fromNodes[i]).add(edge);
			adjList.get(toNodes[i]).add(edge);
		}

		int minTime = Dijkstra.getMinDistance(adjList, 0, nodeCount - 1);

		assertEquals(expectedTime, minTime);
	}

	private static Stream<Arguments> provideMinTime() {
		return Stream.of(
				Arguments.of(6, new int[] { 0, 1, 2, 3, 2, 4, 1 }, new int[] { 1, 2, 3, 4, 4, 5, 5 },
						new int[] { 2, 3, 3, 0, 5, 1, 11 }, 9),
				Arguments.of(1, new int[] { 0 }, new int[] { 0 }, new int[] { 1 }, 0),
				Arguments.of(2, new int[] { 0 }, new int[] { 1 }, new int[] { 2 }, 2),
				Arguments.of(3, new int[] { 0 }, new int[] { 1 }, new int[] { 5 }, Integer.MAX_VALUE));
	}
}
