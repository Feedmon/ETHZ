package graph.search;

import graph.util.Edge;
import graph.util.ZHK;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DepthFirstSearch {

	private static int time;

	private static ArrayList<Integer> ret;

	// can be done with stack instead of recursive
	// adjList runtime: |V| + |E|
	// adjMatrix runtime: n*n
	public static ArrayList<Integer> runDFS(ArrayList<ArrayList<Integer>> adjList) {
		int[] preTime = new int[adjList.size()];
		int[] postTime = new int[adjList.size()];
		ret = new ArrayList<>();

		time = 1;

		for (int node = 0; node < adjList.size(); node++) {
			if (preTime[node] == 0) {
				visit(node, adjList, preTime, postTime);
			}
		}

		return ret;
	}

	private static void visit(int node, ArrayList<ArrayList<Integer>> adjList, int[] preTime, int[] postTime) {
		preTime[node] = time++;

		for (int neighBour : adjList.get(node)) {
			if (preTime[neighBour] == 0) {
				visit(neighBour, adjList, preTime, postTime);
			}
		}

		ret.add(0,node);
		postTime[node] = time++;
	}


	public static Edge boruvkaMinEdge(ZHK zhk, ArrayList<ArrayList<Edge>> graph){
		boolean[] memberOfZHK = new boolean[graph.size()];

		for (Integer member : zhk.members) {
			memberOfZHK[member] = true;
		}

		PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.distance));
		for (Integer member : zhk.members) {
			for (Edge edge : graph.get(member)) {
				if (memberOfZHK[edge.fromNode] != memberOfZHK[edge.toNode]) {
					minHeap.add(edge);
				}
			}
		}

		return minHeap.isEmpty() ? null : minHeap.poll();
	}
}
