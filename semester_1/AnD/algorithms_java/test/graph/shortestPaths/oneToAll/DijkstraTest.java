package graph.shortestPaths.oneToAll;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import graph.util.Edge;

public class DijkstraTest {

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
