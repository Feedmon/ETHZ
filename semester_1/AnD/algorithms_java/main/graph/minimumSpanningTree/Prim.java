package graph.minimumSpanningTree;

import java.util.ArrayList;
import java.util.PriorityQueue;

import graph.util.Edge;

public class Prim {

	// runtime: same as boruvka: O((|V| + |E|) * log(|V|))
	public static Edge[] getMST(int sourceNode, ArrayList<ArrayList<Edge>> graph) {
		boolean[] nodeInMST = new boolean[graph.size()];
		ArrayList<Edge> MST = new ArrayList<>(graph.size() - 1);

		PriorityQueue<Edge> pQueue = new PriorityQueue<>();
		
		for (Edge edge : graph.get(sourceNode)) {
			pQueue.offer(edge);
		}
		
		while (!pQueue.isEmpty()) {
			Edge edge = pQueue.poll();
			if (nodeInMST[edge.toNode] && nodeInMST[edge.fromNode]) {
				continue;
			}
			nodeInMST[edge.fromNode] = true;
			nodeInMST[edge.toNode] = true;
			MST.add(edge);

			for (Edge e : graph.get(edge.toNode)) {
				pQueue.offer(e);
			}
		}

		return MST.toArray(Edge[]::new);
	}
}
