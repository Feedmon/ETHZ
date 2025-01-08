package graph.shortestPaths.oneToAll;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import graph.util.Edge;

public class BellmanFordTest {

	@ParameterizedTest
	@MethodSource("provideMinTime")
	public void testMinDistance(int nodeCount, int[] fromVerts, int[] toVerts, int[] edgeWeights, int expectedTime) {
		Edge[] edges = new Edge[edgeWeights.length];

		for (int i = 0; i < edgeWeights.length; i++) {
			edges[i] = new Edge(fromVerts[i], toVerts[i], edgeWeights[i]);
		}

		int minTime = BellmanFord.getMinDistance(0, nodeCount - 1, nodeCount, edges);

		assertEquals(expectedTime, minTime);
	}
	
	@Test
	public void testNegCycle() {
		Edge[] edges = new Edge[5];
		
		edges[0] = new Edge(0,1,3);
		edges[1] = new Edge(1,2,1);
		edges[2] = new Edge(2,3,-5);
		edges[3] = new Edge(2,4,0);
		edges[4] = new Edge(3,0,0);

		String message = assertThrows(IllegalArgumentException.class,() -> BellmanFord.getMinDistance(0, 4, 5, edges)).getMessage();
		assertEquals("Negative cycle with nodes: [0, 1] detected", message);
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
