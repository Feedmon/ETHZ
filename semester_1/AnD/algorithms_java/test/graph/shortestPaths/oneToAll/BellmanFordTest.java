package graph.shortestPaths.oneToAll;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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

	@Test
	void testSimpleGraph() {
		ArrayList<Edge> edges = new ArrayList<>();
		edges.add(new Edge(0, 1, 5));
		edges.add(new Edge(0, 2, 2));
		edges.add(new Edge(1, 2, 1));
		edges.add(new Edge(1, 3, 3));
		edges.add(new Edge(2, 3, 2));

		int numVertices = 4;
		int source = 0;

		int[] distances = BellmanFord.runBellmanFord(source, numVertices, edges.toArray(Edge[]::new));

		int[] expected = {0, 5, 2, 4};

		assertNotNull(distances, "Distances array should not be null");
		assertArrayEquals(expected, distances, "Distances do not match the expected values");
	}

	@Test
	void testNegativeEdgeNoCycle() {
		ArrayList<Edge> edges = new ArrayList<>();
		edges.add(new Edge(0, 1, 4));
		edges.add(new Edge(0, 2, 5));
		edges.add(new Edge(1, 2, -2));

		int numVertices = 3;
		int source = 0;

		int[] distances = BellmanFord.runBellmanFord(source, numVertices, edges.toArray(Edge[]::new));

		int[] expected = {0, 4, 2};

		assertNotNull(distances);
		assertArrayEquals(expected, distances, "Distances do not match for negative edge test");
	}

	@Test
	void testDisconnectedGraph() {
		ArrayList<Edge> edges = new ArrayList<>();
		edges.add(new Edge(0, 1, 4));

		int numVertices = 3;
		int source = 0;

		int[] distances = BellmanFord.runBellmanFord(source, numVertices, edges.toArray(Edge[]::new));

		assertNotNull(distances);
		assertEquals(0, distances[0]);
		assertEquals(4, distances[1]);
		assertEquals(Integer.MAX_VALUE, distances[2]);
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
