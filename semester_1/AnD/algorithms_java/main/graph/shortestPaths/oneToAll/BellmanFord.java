package graph.shortestPaths.oneToAll;

import graph.util.Edge;

public class BellmanFord {

	// runtime n*m
	public static int getMinDistance(int startNode, int targetNode, int nodes, Edge[] edges) {
		int[] distances = new int[nodes];

		for (int i = 0; i < nodes; i++) {
			distances[i] = Integer.MAX_VALUE;
		}

		distances[startNode] = 0;

		for (int maxAllowedEdges = 1; maxAllowedEdges <= nodes; maxAllowedEdges++) {
			for (Edge edge : edges) {
				if (distances[edge.fromNode] == Integer.MAX_VALUE) {
					continue;
				}

				distances[edge.toNode] = Math.min(distances[edge.fromNode] + edge.distance, distances[edge.toNode]);
			}
		}

		for (Edge edge : edges) {
			if (distances[edge.fromNode] + edge.distance < distances[edge.toNode]) {
				throw new IllegalArgumentException("Negative cycle with nodes: [%s, %s] detected".formatted(edge.fromNode, edge.toNode));
			}
		}

		return distances[targetNode];
	}
}
