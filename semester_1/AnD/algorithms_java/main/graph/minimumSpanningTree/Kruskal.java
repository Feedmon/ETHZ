package graph.minimumSpanningTree;

import java.util.ArrayList;
import java.util.Arrays;

import graph.util.Edge;
import graph.util.ImprovedUnionFind;
import graph.util.UnionFind;

public class Kruskal {

	// runtime: O(|E| * log(|E|) + |V| * log(|V|)) if many edges -> prim is more efficient
	public static int minCost(int nodes, Edge[] edges) {
		Arrays.sort(edges);

		UnionFind unionFind = new UnionFind(nodes);

		ArrayList<Edge> costs = new ArrayList<>();

		for (Edge e : edges) {
			if (!unionFind.same(e.fromNode, e.toNode)) {
				unionFind.union(e.fromNode, e.toNode);
				costs.add(e);
			}
		}

		return costs.stream().mapToInt(edge -> edge.distance).sum();
	}

	// notably slower for large graphs as the above one, but needed if ArrayList is not allowed to be used
	public static int minCostPoorMan(int nodes, Edge[] edges) {
		Arrays.sort(edges);

		ImprovedUnionFind unionFind = new ImprovedUnionFind(nodes);

		ArrayList<Edge> costs = new ArrayList<>();
		// int costs = 0;

		for (Edge e : edges) {
			if (unionFind.union(e.fromNode, e.toNode)) {
				costs.add(e);
				// costs += e.distance;
			}
		}

		return costs.stream().mapToInt(edge -> edge.distance).sum();
		// return costs;
	}
}
