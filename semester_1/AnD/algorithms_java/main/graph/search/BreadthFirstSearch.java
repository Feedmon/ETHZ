package graph.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {
	// runtime: |V| + |E|
	public static int[] runBFS(ArrayList<ArrayList<Integer>> directedGraph) {
		int[] parents = new int[directedGraph.size()];
		int[] dists = new int[directedGraph.size()];
		boolean[] visited = new boolean[directedGraph.size()];

		Arrays.fill(dists, -1);

		Queue<Integer> queue = new LinkedList<>();

		queue.offer(0);
		dists[0] = 0;
		visited[0] = true;

		while (!queue.isEmpty()) {
			Integer node = queue.poll();
			for (int child : directedGraph.get(node)) {
				if (!visited[child]) {
					parents[child] = node;
					visited[child] = true;
					dists[child] = dists[node] + 1;
					queue.offer(child);
				}
			}
		}

		return dists;
	}
}
