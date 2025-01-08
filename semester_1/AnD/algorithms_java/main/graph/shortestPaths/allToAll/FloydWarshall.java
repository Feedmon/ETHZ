package graph.shortestPaths.allToAll;

import graph.util.Edge;

public class FloydWarshall {

	// runtime: O(|V|^3)
	public static int[][] runFloydWarshall(int nodes, Edge[] edges) {
		int[][] shortestPaths = new int[nodes][nodes];

		for (int row = 0; row < nodes; row++) {
			for (int col = 0; col < nodes; col++) {
				if (row == col) {
					continue;
				}
				shortestPaths[row][col] = Integer.MAX_VALUE;
			}
		}

		for (Edge edge : edges) {
			shortestPaths[edge.fromNode][edge.toNode] = Math.min(shortestPaths[edge.fromNode][edge.toNode], edge.distance);
		}

		for(int middleNode = 0; middleNode < nodes; middleNode++) {
			for(int curSourceNode = 0; curSourceNode < nodes; curSourceNode++) {
				for(int curEndNode = 0; curEndNode < nodes; curEndNode++) {
					if(shortestPaths[curSourceNode][middleNode] == Integer.MAX_VALUE || shortestPaths[middleNode][curEndNode] == Integer.MAX_VALUE) {
						continue;
					}
					int curVal = shortestPaths[curSourceNode][curEndNode];
					int valWithMiddleNode = shortestPaths[curSourceNode][middleNode] + shortestPaths[middleNode][curEndNode];
					shortestPaths[curSourceNode][curEndNode] = Math.min(curVal, valWithMiddleNode);
				}
			}
		}

		return shortestPaths;
	}
}
