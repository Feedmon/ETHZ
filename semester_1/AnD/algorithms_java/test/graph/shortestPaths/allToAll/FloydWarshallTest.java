package graph.shortestPaths.allToAll;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import graph.util.Edge;

public class FloydWarshallTest {
	
	@ParameterizedTest
	@MethodSource("provideMinTime")
	public void testMinDistance(int nodeCount, int[] fromNodes, int[] toNodes, int[] edgeWeights, int expectedTime) {
		Edge[] edges = new Edge[edgeWeights.length];

		for (int i = 0; i < edgeWeights.length; i++) {
			edges[i] = new Edge(fromNodes[i], toNodes[i], edgeWeights[i]);
		}

		int[][] shortestPaths = FloydWarshall.runFloydWarshall(nodeCount, edges);

		assertEquals(expectedTime, shortestPaths[0][nodeCount-1]);
	}
	
	@Test
	public void testNegCycle() {
		Edge[] edges = new Edge[5];
		
		edges[0] = new Edge(0,1,3);
		edges[1] = new Edge(1,2,1);
		edges[2] = new Edge(2,3,-5);
		edges[3] = new Edge(2,4,0);
		edges[4] = new Edge(3,0,0);

		int[][] shortestPaths = FloydWarshall.runFloydWarshall(5, edges);

		Assertions.assertArrayEquals(new int[] {-1,2,3,-2,2}, shortestPaths[0]);
		Assertions.assertArrayEquals(new int[] {-4,-1,0,-5,-1}, shortestPaths[1]);
		Assertions.assertArrayEquals(new int[] {-5,-2,-1,-6,-2}, shortestPaths[2]);
		Assertions.assertArrayEquals(new int[] {-1,2,3,-2,2}, shortestPaths[3]);
		Assertions.assertArrayEquals(new int[] {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,0}, shortestPaths[4]);
	}

	private static Stream<Arguments> provideMinTime() {
		return Stream.of(
				Arguments.of(6, new int[] { 0, 1, 2, 3, 2, 4, 1 }, new int[] { 1, 2, 3, 4, 4, 5, 5 },
						new int[] { 2, 3, 3, 0, 5, 1, 11 }, 9),
				Arguments.of(1, new int[] { 0 }, new int[] { 0 }, new int[] { 1 }, 0),
				Arguments.of(2, new int[] { 0 }, new int[] { 1 }, new int[] { 2 }, 2),
				Arguments.of(3, new int[] { 0 }, new int[] { 1 }, new int[] { 5 }, Integer.MAX_VALUE),
				Arguments.of(6, new int[] { 0, 1, 2, 3, 2, 4, 1 }, new int[] { 1, 2, 3, 4, 4, 5, 5 },
						new int[] { 2, 3, 3, 0, 5, -3, 11 }, 5));
	}
}
